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
import jdk.internal.misc.Unsafe;

class ByteBufferAsFloatBufferRL                  // package-private
    extends ByteBufferAsFloatBufferL
{







    ByteBufferAsFloatBufferRL(ByteBuffer bb) {   // package-private












        super(bb);

    }

    ByteBufferAsFloatBufferRL(ByteBuffer bb,
                                     int mark, int pos, int lim, int cap,
                                     long addr)
    {






        super(bb, mark, pos, lim, cap, addr);

    }

    @Override
    Object base() {
        return bb.hb;
    }

    public FloatBuffer slice() {
        int pos = this.position();
        int lim = this.limit();
        assert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);
        long addr = byteOffset(pos);
        return new ByteBufferAsFloatBufferRL(bb, -1, 0, rem, rem, addr);
    }

    @Override
    public FloatBuffer slice(int index, int length) {
        Objects.checkFromIndexSize(index, length, limit());
        return new ByteBufferAsFloatBufferRL(bb,
                                                    -1,
                                                    0,
                                                    length,
                                                    length,
                                                    byteOffset(index));
    }

    public FloatBuffer duplicate() {
        return new ByteBufferAsFloatBufferRL(bb,
                                                    this.markValue(),
                                                    this.position(),
                                                    this.limit(),
                                                    this.capacity(),
                                                    address);
    }

    public FloatBuffer asReadOnlyBuffer() {








        return duplicate();

    }


































    public FloatBuffer put(float x) {






        throw new ReadOnlyBufferException();

    }

    public FloatBuffer put(int i, float x) {






        throw new ReadOnlyBufferException();

    }

    public FloatBuffer compact() {

















        throw new ReadOnlyBufferException();

    }

    public boolean isDirect() {
        return bb.isDirect();
    }

    public boolean isReadOnly() {
        return true;
    }









































    public ByteOrder order() {




        return ByteOrder.LITTLE_ENDIAN;

    }






}
