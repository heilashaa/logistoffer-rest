package com.heilash.logistoffer.util;

import java.util.UUID;

public class IdUtils {

    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
