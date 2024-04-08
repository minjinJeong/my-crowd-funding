package com.flab.funding.domain.utils;

import java.util.UUID;

public class MakeDomainKeyUtils {

    public static String newKey(String name) {
        return name + "-" + makeUUIDKey();
    }

    public static String makeUUIDKey() {
        return UUID.randomUUID().toString();
    }
}
