/*
 * Copyright (c) 2003, 2019, Oracle and/or its affiliates. All rights reserved.
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
 *
 *
 */

package jdk.javadoc.internal.doclets.toolkit.builders;

import java.util.*;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import jdk.javadoc.internal.doclets.toolkit.AnnotationTypeRequiredMemberWriter;
import jdk.javadoc.internal.doclets.toolkit.BaseConfiguration;
import jdk.javadoc.internal.doclets.toolkit.Content;
import jdk.javadoc.internal.doclets.toolkit.DocletException;
import jdk.javadoc.internal.doclets.toolkit.util.VisibleMemberTable;

import static jdk.javadoc.internal.doclets.toolkit.util.VisibleMemberTable.Kind.*;

/**
 * Builds documentation for required annotation type members.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 *
 * @author Jamie Ho
 * @author Bhavesh Patel (Modified)
 */
public class AnnotationTypeRequiredMemberBuilder extends AbstractMemberBuilder {


    /**
     * The writer to output the member documentation.
     */
    protected AnnotationTypeRequiredMemberWriter writer;

    /**
     * The list of members being documented.
     */
    protected List<? extends Element> members;

    /**
     * The index of the current member that is being documented at this point
     * in time.
     */
    protected Element currentMember;

    /**
     * Construct a new AnnotationTypeRequiredMemberBuilder.
     *
     * @param context  the build context.
     * @param typeElement the class whose members are being documented.
     * @param writer the doclet specific writer.
     * @param memberType the kind of member this builder processes.
     */
    protected AnnotationTypeRequiredMemberBuilder(Context context,
            TypeElement typeElement,
            AnnotationTypeRequiredMemberWriter writer,
            VisibleMemberTable.Kind memberType) {
        super(context, typeElement);
        this.writer = writer;
        this.members = getVisibleMembers(memberType);
    }


    /**
     * Construct a new AnnotationTypeMemberBuilder.
     *
     * @param context  the build context.
     * @param typeElement the class whose members are being documented.
     * @param writer the doclet specific writer.
     * @return an instance of this object
     */
    public static AnnotationTypeRequiredMemberBuilder getInstance(
            Context context, TypeElement typeElement,
            AnnotationTypeRequiredMemberWriter writer) {
        return new AnnotationTypeRequiredMemberBuilder(context, typeElement,
                writer, ANNOTATION_TYPE_MEMBER_REQUIRED);
    }

    /**
     * Returns whether or not there are members to document.
     * @return whether or not there are members to document
     */
    @Override
    public boolean hasMembersToDocument() {
        return !members.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void build(Content contentTree) throws DocletException {
        buildAnnotationTypeRequiredMember(contentTree);
    }

    /**
     * Build the annotation type required member documentation.
     *
     * @param memberDetailsTree the content tree to which the documentation will be added
     * @throws DocletException if there is a problem while building the documentation
     */
    protected void buildAnnotationTypeRequiredMember(Content memberDetailsTree)
            throws DocletException {
        buildAnnotationTypeMember(memberDetailsTree);
    }

    /**
     * Build the member documentation.
     *
     * @param memberDetailsTree the content tree to which the documentation will be added
     * @throws DocletException if an error occurs
     */
    protected void buildAnnotationTypeMember(Content memberDetailsTree)
            throws DocletException {
        if (writer == null) {
            return;
        }
        if (hasMembersToDocument()) {
            writer.addAnnotationDetailsMarker(memberDetailsTree);
            Content annotationDetailsTreeHeader = writer.getAnnotationDetailsTreeHeader(typeElement);
            Content detailsTree = writer.getMemberTreeHeader();

            for (Element member : members) {
                currentMember = member;
                Content annotationDocTree = writer.getAnnotationDocTreeHeader(
                        currentMember, detailsTree);

                buildAnnotationTypeMemberChildren(annotationDocTree);

                detailsTree.add(writer.getAnnotationDoc(annotationDocTree));
            }
            memberDetailsTree.add(writer.getAnnotationDetails(annotationDetailsTreeHeader, detailsTree));
        }
    }

    protected void buildAnnotationTypeMemberChildren(Content annotationDocTree) {
        buildSignature(annotationDocTree);
        buildDeprecationInfo(annotationDocTree);
        buildMemberComments(annotationDocTree);
        buildTagInfo(annotationDocTree);
    }

    /**
     * Build the signature.
     *
     * @param annotationDocTree the content tree to which the documentation will be added
     */
    protected void buildSignature(Content annotationDocTree) {
        annotationDocTree.add(writer.getSignature(currentMember));
    }

    /**
     * Build the deprecation information.
     *
     * @param annotationDocTree the content tree to which the documentation will be added
     */
    protected void buildDeprecationInfo(Content annotationDocTree) {
        writer.addDeprecated(currentMember, annotationDocTree);
    }

    /**
     * Build the comments for the member.  Do nothing if
     * {@link BaseConfiguration#nocomment} is set to true.
     *
     * @param annotationDocTree the content tree to which the documentation will be added
     */
    protected void buildMemberComments(Content annotationDocTree) {
        if (!configuration.nocomment) {
            writer.addComments(currentMember, annotationDocTree);
        }
    }

    /**
     * Build the tag information.
     *
     * @param annotationDocTree the content tree to which the documentation will be added
     */
    protected void buildTagInfo(Content annotationDocTree) {
        writer.addTags(currentMember, annotationDocTree);
    }

    /**
     * Return the annotation type required member writer for this builder.
     *
     * @return the annotation type required member constant writer for this
     * builder.
     */
    public AnnotationTypeRequiredMemberWriter getWriter() {
        return writer;
    }
}
