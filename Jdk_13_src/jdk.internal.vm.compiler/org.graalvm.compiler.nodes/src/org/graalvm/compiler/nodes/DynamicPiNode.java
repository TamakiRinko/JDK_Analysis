/*
 * Copyright (c) 2016, 2018, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.nodes;

import org.graalvm.compiler.core.common.type.Stamp;
import org.graalvm.compiler.core.common.type.StampFactory;
import org.graalvm.compiler.core.common.type.TypeReference;
import org.graalvm.compiler.graph.Node;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.graph.spi.CanonicalizerTool;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.extended.GuardingNode;

import jdk.vm.ci.meta.Assumptions;
import jdk.vm.ci.meta.ConstantReflectionProvider;
import jdk.vm.ci.meta.ResolvedJavaType;

/**
 * A {@link PiNode} where the type is not yet known. If the type becomes known at a later point in
 * the compilation, this can canonicalize to a regular {@link PiNode}.
 */
@NodeInfo
public final class DynamicPiNode extends PiNode {

    public static final NodeClass<DynamicPiNode> TYPE = NodeClass.create(DynamicPiNode.class);
    @Input ValueNode typeMirror;
    private final boolean exact;

    protected DynamicPiNode(ValueNode object, GuardingNode guard, ValueNode typeMirror, boolean exact) {
        super(TYPE, object, StampFactory.object(), guard);
        this.typeMirror = typeMirror;
        this.exact = exact;
    }

    public static ValueNode create(Assumptions assumptions, ConstantReflectionProvider constantReflection, ValueNode object, GuardingNode guard, ValueNode typeMirror, boolean exact) {
        ValueNode synonym = findSynonym(assumptions, constantReflection, object, guard, typeMirror, exact);
        if (synonym != null) {
            return synonym;
        }
        return new DynamicPiNode(object, guard, typeMirror, exact);
    }

    public static ValueNode create(Assumptions assumptions, ConstantReflectionProvider constantReflection, ValueNode object, GuardingNode guard, ValueNode typeMirror) {
        return create(assumptions, constantReflection, object, guard, typeMirror, false);
    }

    public boolean isExact() {
        return exact;
    }

    private static ValueNode findSynonym(Assumptions assumptions, ConstantReflectionProvider constantReflection, ValueNode object, GuardingNode guard, ValueNode typeMirror, boolean exact) {
        if (typeMirror.isConstant()) {
            ResolvedJavaType t = constantReflection.asJavaType(typeMirror.asConstant());
            if (t != null) {
                Stamp staticPiStamp;
                if (t.isPrimitive()) {
                    staticPiStamp = StampFactory.alwaysNull();
                } else {
                    TypeReference type = exact ? TypeReference.createExactTrusted(t) : TypeReference.createTrusted(assumptions, t);
                    staticPiStamp = StampFactory.object(type);
                }

                return PiNode.create(object, staticPiStamp, (ValueNode) guard);
            }
        }

        return null;
    }

    @Override
    public Node canonical(CanonicalizerTool tool) {
        ValueNode synonym = findSynonym(tool.getAssumptions(), tool.getConstantReflection(), object, guard, typeMirror, exact);
        if (synonym != null) {
            return synonym;
        }
        return this;
    }
}
