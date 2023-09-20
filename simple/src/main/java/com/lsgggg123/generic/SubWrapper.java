package com.lsgggg123.generic;

import java.util.List;
import java.util.Map;

/**
 * javap -v simple/target/classes/com/lsgggg123/generic/SubWrapper
 *
 * output:
 * Signature: #12 // Lcom/lsgggg123/generic/Wrapper<Ljava/util/List<Ljava/util/Map<Ljava/lang/String;Ljava/lang/Object;>;>;>;
 */
public class SubWrapper extends Wrapper<List<Map<String, Object>>> {
}