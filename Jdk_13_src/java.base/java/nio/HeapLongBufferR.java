/*
 * Copyright (c) 2000, 2019, Oracle and/or its affiliates. All rights reserved.
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

// -- This file was mechanically generated: Do not edit! -- //

package java.nio;

import java.util.Objects;

/**



 * A read-only HeapLongBuffer.  This class extends the corresponding
 * read/write class, overriding the mutation methods to throw a {@link
 * ReadOnlyBufferException} and overriding the view-buffer methods to return an
 * instance of this class rather than of the superclass.

 */

class HeapLongBufferR
    extends HeapLongBuffer
{
    // Cached array base offset
    private static final long ARRAY_BASE_OFFSET = UNSAFE.arrayBaseOffset(long[].class);

    // Cached array base offset
    private static final long ARRAY_INDEX_SCALE = UNSAFE.arrayIndexScale(long[].class);

    // For speed these fields are actually declared in X-Buffer;
    // these declarations are here as documentation
    /*




    */

    HeapLongBufferR(int cap, int lim) {            // package-private








        super(cap, lim);
        this.isReadOnly = true;

    }

    HeapLongBufferR(long[] buf, int off, int len) { // package-private








        super(buf, off, len);
        this.isReadOnly = true;

    }

    protected HeapLongBufferR(long[] buf,
                                   int mark, int pos, int lim, int cap,
                                   int off)
    {








        super(buf, mark, pos, lim, cap, off);
        this.isReadOnly = true;

    }

    public LongBuffer slice() {
        int rem = this.remaining();
        return new HeapLongBufferR(hb,
                                        -1,
                                        0,
                                        rem,
                                        rem,
                                        this.position() + offset);
    }

    @Override
    public LongBuffer slice(int index, int length) {
        Objects.checkFromIndexSize(index, length, limit());
        return new HeapLongBufferR(hb,
                                        -1,
                                        0,
                                        length,
                                        length,
                                        index + offset);
    }

    public LongBuffer duplicate() {
        return new HeapLongBufferR(hb,
                                        this.markValue(),
                                        this.position(),
                                        this.limit(),
                                        this.capacity(),
                                        offset);
    }

    public LongBuffer asReadOnlyBuffer() {








        return duplicate();

    }


















































    public boolean isReadOnly() {
        return true;
    }

    public LongBuffer put(long x) {




        throw new ReadOnlyBufferException();

    }

    public LongBuffer put(int i, long x) {




        throw new ReadOnlyBufferException();

    }

    public LongBuffer put(long[] src, int offset, int length) {









        throw new ReadOnlyBufferException();

    }

    public LongBuffer put(LongBuffer src) {


























        throw new ReadOnlyBufferException();

    }

    public LongBuffer put(int index, long[] src, int offset, int length) {






        throw new ReadOnlyBufferException();

    }




















    public LongBuffer compact() {









        throw new ReadOnlyBufferException();

    }

















































































































































































































































































































































































    public ByteOrder order() {
        return ByteOrder.nativeOrder();
    }







}
