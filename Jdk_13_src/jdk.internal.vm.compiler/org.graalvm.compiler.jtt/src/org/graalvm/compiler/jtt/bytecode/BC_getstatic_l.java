/*
 * Copyright (c) 2007, 2018, Oracle and/or its affiliates. All rights reserved.
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
/*
 * Copyright (c) 2007, 2018, Oracle and/or its affiliates. All rights reserved.
 */


package org.graalvm.compiler.jtt.bytecode;

import org.junit.Test;

import org.graalvm.compiler.jtt.JTTTest;

/*
 */
public class BC_getstatic_l extends JTTTest {

    private static long field = 11;

    public static long test() {
        return field;
    }

    @Test
    public void run0() throws Throwable {
        runTest("test");
    }

}
