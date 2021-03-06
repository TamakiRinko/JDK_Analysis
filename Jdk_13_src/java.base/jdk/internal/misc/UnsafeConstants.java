/*
 * Copyright (c) 2019, Red Hat Inc. All rights reserved.
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

package jdk.internal.misc;

/**
 * A class used to expose details of the underlying hardware that
 * configure the operation of class Unsafe.  This class is
 * package-private as the only intended client is class Unsafe.
 * All fields in this class must be static final constants.
 *
 * @since 13
 *
 * @implNote
 *
 * The JVM injects hardware-specific values into all the static fields
 * of this class during JVM initialization. The static initialization
 * block is executed when the class is initialized then JVM injection
 * updates the fields with the correct constants. The static block
 * is required to prevent the fields from being considered constant
 * variables, so the field values will be not be compiled directly into
 * any class that uses them.
 */

final class UnsafeConstants {

    /**
     * This constructor is private because the class is not meant to
     * be instantiated.
     */
    private UnsafeConstants() {}

    /**
     * The size in bytes of a native pointer, as stored via {@link
     * #putAddress}.  This value will be either 4 or 8.  Note that the
     * sizes of other primitive types (as stored in native memory
     * blocks) is determined fully by their information content.
     *
     * @implNote
     * The actual value for this field is injected by the JVM.
     */

    static final int ADDRESS_SIZE0;

    /**
     * The size in bytes of a native memory page (whatever that is).
     * This value will always be a power of two.
     *
     * @implNote
     * The actual value for this field is injected by the JVM.
     */

    static final int PAGE_SIZE;

    /**
     * Flag whose value is true if and only if the native endianness
     * of this platform is big.
     *
     * @implNote
     * The actual value for this field is injected by the JVM.
     */

    static final boolean BIG_ENDIAN;

    /**
     * Flag whose value is true if and only if the platform can
     * perform unaligned accesses
     *
     * @implNote
     * The actual value for this field is injected by the JVM.
     */

    static final boolean UNALIGNED_ACCESS;

    static {
        ADDRESS_SIZE0 = 0;
        PAGE_SIZE = 0;
        BIG_ENDIAN = false;
        UNALIGNED_ACCESS = false;
    }
}
