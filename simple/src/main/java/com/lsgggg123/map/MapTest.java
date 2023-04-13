/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */

package com.lsgggg123.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static final Map<String, Object> M = Collections.unmodifiableMap(new HashMap<String, Object>() {
        private static final long serialVersionUID = 1L;
        {
            put("Id", 1L);
            System.out.println(this.getClass());
        }
    });
}