/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */


package org.graalvm.compiler.core.test;

import static java.lang.reflect.Modifier.isProtected;
import static java.lang.reflect.Modifier.isPublic;

import java.lang.reflect.Field;

import org.graalvm.compiler.core.common.type.TypeReference;
import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.nodes.java.InstanceOfNode;
import org.graalvm.compiler.nodes.java.MethodCallTargetNode;
import org.graalvm.compiler.phases.VerifyPhase;
import org.graalvm.compiler.phases.tiers.PhaseContext;
import org.graalvm.compiler.serviceprovider.GraalUnsafeAccess;

import jdk.vm.ci.meta.MetaAccessProvider;
import jdk.vm.ci.meta.ResolvedJavaMethod;
import jdk.vm.ci.meta.ResolvedJavaType;
import sun.misc.Unsafe;

/**
 * Checks that the {@link Unsafe} singleton instance is only accessed via well known classes such as
 * {@link GraalUnsafeAccess}.
 */
public class VerifyUnsafeAccess extends VerifyPhase<PhaseContext> {

    @Override
    protected void verify(StructuredGraph graph, PhaseContext context) {
        MetaAccessProvider metaAccess = context.getMetaAccess();
        final ResolvedJavaType unsafeType = metaAccess.lookupJavaType(Unsafe.class);

        ResolvedJavaMethod caller = graph.method();
        String holderQualified = caller.format("%H");
        String holderUnqualified = caller.format("%h");
        String packageName = holderQualified.equals(holderUnqualified) ? "" : holderQualified.substring(0, holderQualified.length() - holderUnqualified.length() - 1);
        if ((holderQualified.equals(GraalUnsafeAccess.class.getName()) ||
                        holderQualified.equals("jdk.vm.ci.hotspot.UnsafeAccess")) &&
                        caller.getName().equals("initUnsafe")) {
            // This is the blessed way access Unsafe in Graal and JVMCI
            return;
        } else if (packageName.startsWith("com.oracle.truffle") || packageName.startsWith("org.graalvm.compiler.truffle.runtime")) {
            // Truffle and GraalTruffleRuntime do not depend on Graal and so cannot use
            // GraalUnsafeAccess
            return;
        }

        if (caller.getSignature().getReturnType(caller.getDeclaringClass()).equals(unsafeType)) {
            if (caller.isPublic()) {
                if (holderQualified.equals(GraalUnsafeAccess.class.getName()) && caller.getName().equals("getUnsafe")) {
                    // pass
                } else {
                    throw new VerificationError("Cannot leak Unsafe from public method %s",
                                    caller.format("%H.%n(%p)"));
                }
            }
        }

        for (InstanceOfNode node : graph.getNodes().filter(InstanceOfNode.class)) {
            TypeReference typeRef = node.type();
            if (typeRef != null) {
                if (unsafeType.isAssignableFrom(typeRef.getType())) {
                    throw new VerificationError("Cast to %s in %s is prohibited as it implies accessing Unsafe.theUnsafe via reflection. Use %s.getUnsafe() instead.",
                                    unsafeType.toJavaName(),
                                    caller.format("%H.%n(%p)"),
                                    GraalUnsafeAccess.class.getName());

                }
            }
        }
        for (MethodCallTargetNode t : graph.getNodes(MethodCallTargetNode.TYPE)) {
            ResolvedJavaMethod callee = t.targetMethod();
            if (callee.getDeclaringClass().equals(unsafeType)) {
                if (callee.getName().equals("getUnsafe")) {
                    throw new VerificationError("Call to %s at callsite %s is prohibited. Use %s.getUnsafe() instead.",
                                    callee.format("%H.%n(%p)"),
                                    caller.format("%H.%n(%p)"),
                                    GraalUnsafeAccess.class.getName());
                }
            }
        }
    }

    @Override
    public void verifyClass(Class<?> c, MetaAccessProvider metaAccess) {
        for (Field field : c.getDeclaredFields()) {
            int modifiers = field.getModifiers();
            if (field.getType() == Unsafe.class && (isPublic(modifiers) || isProtected(modifiers))) {
                throw new VerificationError("Field of type %s must be private or package-private: %s", Unsafe.class.getName(), field);
            }
        }
    }
}
