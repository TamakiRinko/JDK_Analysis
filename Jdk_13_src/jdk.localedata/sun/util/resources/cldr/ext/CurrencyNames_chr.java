/*
 * Copyright (c) 2012, 2019, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * COPYRIGHT AND PERMISSION NOTICE
 *
 * Copyright (C) 1991-2016 Unicode, Inc. All rights reserved.
 * Distributed under the Terms of Use in 
 * http://www.unicode.org/copyright.html.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of the Unicode data files and any associated documentation
 * (the "Data Files") or Unicode software and any associated documentation
 * (the "Software") to deal in the Data Files or Software
 * without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, and/or sell copies of
 * the Data Files or Software, and to permit persons to whom the Data Files
 * or Software are furnished to do so, provided that
 * (a) this copyright and permission notice appear with all copies 
 * of the Data Files or Software,
 * (b) this copyright and permission notice appear in associated 
 * documentation, and
 * (c) there is clear notice in each modified Data File or in the Software
 * as well as in the documentation associated with the Data File(s) or
 * Software that the data or software has been modified.
 *
 * THE DATA FILES AND SOFTWARE ARE PROVIDED "AS IS", WITHOUT WARRANTY OF
 * ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT OF THIRD PARTY RIGHTS.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR HOLDERS INCLUDED IN THIS
 * NOTICE BE LIABLE FOR ANY CLAIM, OR ANY SPECIAL INDIRECT OR CONSEQUENTIAL
 * DAMAGES, OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE,
 * DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER
 * TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 * PERFORMANCE OF THE DATA FILES OR SOFTWARE.
 *
 * Except as contained in this notice, the name of a copyright holder
 * shall not be used in advertising or otherwise to promote the sale,
 * use or other dealings in these Data Files or Software without prior
 * written authorization of the copyright holder.
 */

package sun.util.resources.cldr.ext;

import sun.util.resources.OpenListResourceBundle;

public class CurrencyNames_chr extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final Object[][] data = new Object[][] {
            { "USD", "$" },
            { "aed", "\u13cc\u13ca \u13a2\u13f3\u13be\u13b5\u13cd\u13d4\u13c5 \u13a1\u13b3\u13c8 \u13a2\u13b9\u13b5\u13d8\u13cf \u13a0\u13d5\u13b3" },
            { "afn", "\u13a0\u13eb\u13a8\u13c2\u13cd\u13d6\u13c2 \u13a0\u13d5\u13b3" },
            { "all", "\u13a0\u13b5\u13c7\u13c2\u13ef \u13a0\u13d5\u13b3" },
            { "amd", "\u13a0\u13b5\u13bb\u13c2\u13a0 \u13a0\u13d5\u13b3" },
            { "ang", "\u13be\u13cd\u13a9\u13c1\u13db\u13b3\u13c2 \u13a0\u13c2\u13d8\u13b5\u13cf \u13a0\u13d5\u13b3" },
            { "aoa", "\u13a0\u13c2\u13aa\u13b3 \u13a0\u13d5\u13b3" },
            { "ars", "\u13a0\u13e5\u13c2\u13d8\u13c2\u13a0 \u13a0\u13d5\u13b3" },
            { "aud", "\u13a1\u13b3\u13d7\u13dc \u13a0\u13d5\u13b3" },
            { "awg", "\u13a0\u13b7\u13c6 \u13a0\u13d5\u13b3" },
            { "azn", "\u13a0\u13cf\u13b5\u13c6\u13cc\u13c2 \u13a0\u13d5\u13b3" },
            { "bam", "\u13c9\u13cf\u13c2\u13a0-\u13b2\u13e4\u13aa\u13eb \u13a6\u13c1\u13df\u13f4\u13cd\u13d4\u13c5 \u13a0\u13d5\u13b3" },
            { "bbd", "\u13c6\u13c7\u13d9\u13cd \u13a0\u13d5\u13b3" },
            { "bdt", "\u13c6\u13c2\u13a6\u13b5\u13d5\u13cd \u13a0\u13d5\u13b3" },
            { "bgn", "\u13ca\u13b5\u13a8\u13b5\u13a0 \u13a0\u13d5\u13b3" },
            { "bhd", "\u13c6\u13ad\u13b4\u13a2\u13c2 \u13a0\u13d5\u13b3" },
            { "bif", "\u13cb\u13b7\u13c2\u13d7 \u13a0\u13d5\u13b3" },
            { "bmd", "\u13c6\u13ca\u13d3 \u13a0\u13d5\u13b3" },
            { "bnd", "\u13ca\u13be\u13a2 \u13a0\u13d5\u13b3" },
            { "bob", "\u13c9\u13b5\u13eb\u13a0 \u13a0\u13d5\u13b3" },
            { "brl", "\u13c6\u13cf\u13b5\u13a2 \u13a0\u13d5\u13b3" },
            { "bsd", "\u13c6\u13ad\u13b9 \u13a0\u13d5\u13b3" },
            { "btn", "\u13ca\u13d4\u13c2 \u13a0\u13d5\u13b3" },
            { "bwp", "\u13c6\u13e3\u13e9\u13be \u13a0\u13d5\u13b3" },
            { "byn", "\u13c7\u13b3\u13b7\u13cd \u13a0\u13d5\u13b3" },
            { "byr", "\u13c7\u13b3\u13b7\u13cd \u13a0\u13d5\u13b3 (2000\u20132016)" },
            { "bzd", "\u13c7\u13b5\u13cd \u13a0\u13d5\u13b3" },
            { "cad", "\u13a8\u13be\u13d3 \u13a0\u13d5\u13b3" },
            { "cdf", "\u13a7\u13c2\u13aa \u13a0\u13d5\u13b3" },
            { "chf", "\u13cd\u13eb\u13cf \u13a0\u13d5\u13b3" },
            { "clp", "\u13e5\u13b5 \u13a0\u13d5\u13b3" },
            { "cnh", "\u13e3\u13c2\u13cf \u13a0\u13d5\u13b3 (\u13d3\u13b9\u13f3\u13df\u13d7)" },
            { "cny", "\u13d3\u13b6\u13c2\u13a8 \u13a0\u13d5\u13b3" },
            { "cop", "\u13aa\u13b8\u13bb\u13c8\u13a2\u13a0 \u13a0\u13d5\u13b3" },
            { "crc", "\u13aa\u13cd\u13d3\u13b5\u13a7 \u13a0\u13d5\u13b3" },
            { "cuc", "\u13ab\u13c6 \u13a6\u13c1\u13df\u13f4\u13cd\u13d4\u13c5 \u13a0\u13d5\u13b3" },
            { "cup", "\u13ab\u13c6 \u13a0\u13d5\u13b3" },
            { "cve", "\u13a2\u13ac\u13be\u13d5\u13be \u13a2\u13e4\u13f3\u13cd\u13d7 \u13a0\u13d5\u13b3" },
            { "czk", "\u13e4\u13a9 \u13a0\u13d5\u13b3" },
            { "djf", "\u13e5\u13ca\u13d7 \u13a0\u13d5\u13b3" },
            { "dkk", "\u13d5\u13c2\u13cd \u13a0\u13d5\u13b3" },
            { "dop", "\u13d9\u13bb\u13c2\u13a7\u13c2 \u13a0\u13d5\u13b3" },
            { "dzd", "\u13a0\u13b5\u13e5\u13b5\u13ef \u13a0\u13d5\u13b3" },
            { "egp", "\u13a2\u13e5\u13c8\u13a2 \u13a0\u13d5\u13b3" },
            { "ern", "\u13a1\u13b5\u13df\u13ef \u13a0\u13d5\u13b3" },
            { "etb", "\u13a2\u13d7\u13a3\u13c8\u13a0 \u13a0\u13d5\u13b3" },
            { "eur", "\u13f3\u13b3\u13db \u13a0\u13d5\u13b3" },
            { "fjd", "\u13eb\u13a9 \u13a0\u13d5\u13b3" },
            { "fkp", "\u13e9\u13a9\u13a4 \u13da\u13a6\u13da\u13db\u13a2 \u13a0\u13d5\u13b3" },
            { "gbp", "\u13a9\u13b5\u13cf\u13f2 \u13a0\u13d5\u13b3" },
            { "gel", "\u13e3\u13a0\u13e5\u13a2 \u13a0\u13d5\u13b3" },
            { "ghs", "\u13a6\u13a0\u13be \u13a0\u13d5\u13b3" },
            { "gip", "\u13e5\u13c6\u13b5\u13d3 \u13a0\u13d5\u13b3" },
            { "gmd", "\u13a6\u13b9\u13c8\u13a2\u13a0 \u13a0\u13d5\u13b3" },
            { "gnf", "\u13a9\u13a2\u13c2 \u13a0\u13d5\u13b3" },
            { "gtq", "\u13c6\u13d6\u13b9\u13b3 \u13a0\u13d5\u13b3" },
            { "gyd", "\u13a6\u13ef\u13be \u13a0\u13d5\u13b3" },
            { "hkd", "\u13b0\u13c2\u13a9 \u13aa\u13c2\u13a9 \u13a0\u13d5\u13b3" },
            { "hnl", "\u13ad\u13c2\u13da\u13b3\u13cd \u13a0\u13d5\u13b3" },
            { "hrk", "\u13a7\u13b6\u13a1\u13cf\u13a0 \u13a0\u13d5\u13b3" },
            { "htg", "\u13ae\u13d8 \u13a0\u13d5\u13b3" },
            { "huf", "\u13b2\u13c2\u13a6\u13b5 \u13a0\u13d5\u13b3" },
            { "idr", "\u13a2\u13c2\u13d9\u13c2\u13cd\u13ef \u13a0\u13d5\u13b3" },
            { "ils", "\u13a2\u13cf\u13b5\u13f1 \u13a2\u13e4 \u13a0\u13d5\u13b3" },
            { "inr", "\u13a2\u13c2\u13d7\u13a2\u13a0 \u13a0\u13d5\u13b3" },
            { "iqd", "\u13a2\u13b3\u13a9 \u13a0\u13d5\u13b3" },
            { "irr", "\u13a2\u13b4\u13c2 \u13a0\u13d5\u13b3" },
            { "isk", "\u13e7\u13c1\u13cd\u13d3\u13b8\u13af \u13a0\u13d5\u13b3" },
            { "jmd", "\u13e3\u13ba\u13a2\u13a7 \u13a0\u13d5\u13b3" },
            { "jod", "\u13e6\u13d3\u13c2 \u13a0\u13d5\u13b3" },
            { "jpy", "\u13e3\u13e9\u13c2\u13cf \u13a0\u13d5\u13b3" },
            { "kes", "\u13a8\u13c2\u13ef \u13a0\u13d5\u13b3" },
            { "kgs", "\u13a9\u13b5\u13e3\u13a2\u13cd \u13a0\u13d5\u13b3" },
            { "khr", "\u13a7\u13b9\u13c9\u13d7\u13a0\u13c2 \u13a0\u13d5\u13b3" },
            { "kmf", "\u13aa\u13bc\u13b3\u13cd \u13a0\u13d5\u13b3" },
            { "kpw", "\u13e7\u13f4\u13e2 \u13aa\u13b5\u13a0 \u13a0\u13d5\u13b3" },
            { "krw", "\u13e7\u13a6\u13be\u13ee \u13aa\u13b5\u13a0 \u13a0\u13d5\u13b3" },
            { "kwd", "\u13ab\u13ea\u13a2\u13d8 \u13a0\u13d5\u13b3" },
            { "kyd", "\u13a8\u13a2\u13b9\u13c2 \u13da\u13a6\u13da\u13db\u13a2 \u13a0\u13d5\u13b3" },
            { "kzt", "\u13a7\u13ce\u13a7\u13cd\u13d5\u13c2 \u13a0\u13d5\u13b3" },
            { "lak", "\u13b3\u13a3 \u13a0\u13d5\u13b3" },
            { "lbp", "\u13b4\u13c6\u13be\u13c2 \u13a0\u13d5\u13b3" },
            { "lkr", "\u13cd\u13b5 \u13b3\u13c2\u13a7 \u13a0\u13d5\u13b3" },
            { "lrd", "\u13b3\u13c8\u13b5\u13ef \u13a0\u13d5\u13b3" },
            { "lyd", "\u13b5\u13c8\u13ef \u13a0\u13d5\u13b3" },
            { "mad", "\u13bc\u13b6\u13aa \u13a0\u13d5\u13b3" },
            { "mdl", "\u13b9\u13b5\u13d9\u13eb\u13a0 \u13a0\u13d5\u13b3" },
            { "mga", "\u13b9\u13b3\u13a6\u13cf \u13a0\u13d5\u13b3" },
            { "mkd", "\u13b9\u13ce\u13d9\u13c2\u13a0 \u13a0\u13d5\u13b3" },
            { "mmk", "\u13b9\u13ef\u13b9\u13b5 \u13a0\u13d5\u13b3" },
            { "mnt", "\u13b9\u13c2\u13aa\u13b5\u13a0 \u13a0\u13d5\u13b3" },
            { "mop", "\u13b9\u13a7\u13a3 \u13a0\u13d5\u13b3" },
            { "mro", "\u13b9\u13c8\u13d4\u13c2\u13a0 \u13a0\u13d5\u13b3 (1973\u20132017)" },
            { "mru", "\u13b9\u13c8\u13d4\u13c2\u13a0 \u13a0\u13d5\u13b3" },
            { "mur", "\u13b9\u13d8\u13a2\u13ef \u13a0\u13d5\u13b3" },
            { "mvr", "\u13b9\u13b5\u13d7\u13eb\u13cd \u13a0\u13d5\u13b3" },
            { "mwk", "\u13b9\u13b3\u13eb \u13a0\u13d5\u13b3" },
            { "mxn", "\u13cd\u13c6\u13c2 \u13a0\u13d5\u13b3" },
            { "myr", "\u13b9\u13b4\u13cf\u13a2\u13a0 \u13a0\u13d5\u13b3" },
            { "mzn", "\u13bc\u13ce\u13bb\u13c7\u13a9 \u13a0\u13d5\u13b3" },
            { "nad", "\u13be\u13bb\u13c8\u13a2\u13ef \u13a0\u13d5\u13b3" },
            { "ngn", "\u13c2\u13e5\u13b5\u13ef \u13a0\u13d5\u13b3" },
            { "nio", "\u13c2\u13a7\u13b3\u13c6 \u13a0\u13d5\u13b3" },
            { "nok", "\u13c3\u13ea \u13a0\u13d5\u13b3" },
            { "npr", "\u13c1\u13c6\u13b5 \u13a0\u13d5\u13b3" },
            { "nzd", "\u13a2\u13e4 \u13cf\u13a2\u13b4\u13c2\u13d7 \u13a0\u13d5\u13b3" },
            { "omr", "\u13a3\u13ba\u13c2 \u13a0\u13d5\u13b3" },
            { "pab", "\u13c6\u13be\u13b9 \u13a0\u13d5\u13b3" },
            { "pen", "\u13c7\u13b7 \u13a0\u13d5\u13b3" },
            { "pgk", "\u13c6\u13c7 \u13a2\u13e4 \u13a9\u13a2\u13c2 \u13a0\u13d5\u13b3" },
            { "php", "\u13a0\u13c2\u13c8\u13b5\u13a9\u13c3 \u13a0\u13d5\u13b3" },
            { "pkr", "\u13c6\u13a9\u13cd\u13d6\u13c2 \u13a0\u13d5\u13b3" },
            { "pln", "\u13c9\u13b3\u13c2 \u13a0\u13d5\u13b3" },
            { "pyg", "\u13c6\u13b3\u13c7\u13a2\u13ef \u13a0\u13d5\u13b3" },
            { "qar", "\u13a7\u13d4\u13b5 \u13a0\u13d5\u13b3" },
            { "ron", "\u13b6\u13b9\u13c2\u13ef \u13a0\u13d5\u13b3" },
            { "rsd", "\u13d2\u13c8\u13ef \u13a0\u13d5\u13b3" },
            { "rub", "\u13f2\u13c2\u13a2 \u13a0\u13d5\u13b3" },
            { "rwf", "\u13b6\u13e9\u13c2\u13d3 \u13a0\u13d5\u13b3" },
            { "sar", "\u13cc\u13a4\u13d7 \u13a0\u13d5\u13b3" },
            { "sbd", "\u13d0\u13b6\u13b9\u13c2 \u13da\u13a6\u13da\u13db\u13a2 \u13a0\u13d5\u13b3" },
            { "scr", "\u13cf\u13a1\u13e5\u13b5\u13cd \u13a0\u13d5\u13b3" },
            { "sdg", "\u13d1\u13d5\u13c2 \u13a0\u13d5\u13b3" },
            { "sek", "\u13cd\u13eb\u13d5\u13c2 \u13a0\u13d5\u13b3" },
            { "sgd", "\u13cf\u13c2\u13a6\u13c9\u13b5 \u13a0\u13d5\u13b3" },
            { "shp", "\u13a4\u13d3\u13c5\u13d8 \u13ae\u13b5\u13be \u13a0\u13d5\u13b3" },
            { "sll", "\u13cf\u13a1\u13b3\u13b4\u13a3\u13c2 \u13a0\u13d5\u13b3" },
            { "sos", "\u13d0\u13b9\u13b5 \u13a0\u13d5\u13b3" },
            { "srd", "\u13d2\u13b5\u13be\u13bb \u13a0\u13d5\u13b3" },
            { "ssp", "\u13e7\u13a6\u13be\u13ee \u13d1\u13d5\u13c2 \u13a0\u13d5\u13b3" },
            { "std", "\u13cc\u13a3\u13d9\u13ba \u13a0\u13b4 \u13c8\u13c2\u13cf\u13c7 \u13a0\u13d5\u13b3 (1977\u20132017)" },
            { "stn", "\u13cc\u13a3\u13d9\u13ba & \u13c8\u13c2\u13cf\u13c7 \u13a0\u13d5\u13b3" },
            { "syp", "\u13cf\u13b5\u13a0 \u13a0\u13d5\u13b3" },
            { "szl", "\u13cd\u13e9\u13cf \u13a0\u13d5\u13b3" },
            { "thb", "\u13d4\u13ef\u13b4\u13c2 \u13a0\u13d5\u13b3" },
            { "tjs", "\u13d4\u13e5\u13a9\u13cd\u13d5\u13c2 \u13a0\u13d5\u13b3" },
            { "tmt", "\u13db\u13b5\u13a9\u13ba\u13c2\u13cd\u13d4\u13c2 \u13a0\u13d5\u13b3" },
            { "tnd", "\u13da\u13c2\u13cf\u13cd\u13a0 \u13a0\u13d5\u13b3" },
            { "top", "\u13d4\u13c2\u13aa \u13a0\u13d5\u13b3" },
            { "try", "\u13ac\u13c3 \u13a0\u13d5\u13b3" },
            { "ttd", "\u13df\u13c2\u13d5\u13d7 & \u13d9\u13c6\u13aa \u13a0\u13d5\u13b3" },
            { "twd", "\u13a2\u13e4 \u13d4\u13a2\u13e9\u13c2 \u13a0\u13d5\u13b3" },
            { "tzs", "\u13d6\u13c2\u13cf\u13c2\u13ef \u13a0\u13d5\u13b3" },
            { "uah", "\u13f3\u13a7\u13b4\u13c2 \u13a0\u13d5\u13b3" },
            { "ugx", "\u13f3\u13a6\u13c2\u13d3 \u13a0\u13d5\u13b3" },
            { "usd", "US \u13a0\u13d5\u13b3" },
            { "uyu", "\u13f3\u13b7\u13c7 \u13a0\u13d5\u13b3" },
            { "uzs", "\u13a4\u13cd\u13c7\u13a9\u13cd\u13d6\u13c2 \u13a0\u13d5\u13b3" },
            { "vef", "\u13ea\u13c1\u13d1\u13ea \u13a0\u13d5\u13b3 (2008\u20132018)" },
            { "ves", "\u13ea\u13c1\u13d1\u13ea \u13a0\u13d5\u13b3" },
            { "vnd", "\u13eb\u13a1\u13d8\u13be\u13bb\u13cd \u13a0\u13d5\u13b3" },
            { "vuv", "\u13e9\u13c2\u13a4\u13e9\u13da \u13a0\u13d5\u13b3" },
            { "wst", "\u13cc\u13bc\u13a0 \u13a0\u13d5\u13b3" },
            { "xaf", "\u13a0\u13f0\u13df \u13ac\u13bf\u13a8\u13cd\u13db CFA \u13a0\u13d5\u13b3" },
            { "xcd", "\u13d7\u13a7\u13b8\u13ac \u13a8\u13c6\u13d9\u13ef \u13a0\u13d5\u13b3" },
            { "xof", "\u13ed\u13d5\u13b5\u13ac \u13ac\u13bf\u13a8\u13cd\u13db CFA \u13a0\u13d5\u13b3" },
            { "xpf", "CFP \u13a0\u13d5\u13b3" },
            { "xxx", "\u13c4\u13ec\u13b5\u13cd\u13db\u13be \u13a0\u13d5\u13b3" },
            { "yer", "\u13f0\u13ba\u13c2 \u13a0\u13d5\u13b3" },
            { "zar", "\u13e7\u13a6\u13be\u13ee \u13ac\u13bf\u13a8\u13cd\u13db \u13a0\u13d5\u13b3" },
            { "zmw", "\u13cf\u13bb\u13c6\u13c7 \u13a0\u13d5\u13b3" },
        };
        return data;
    }
}
