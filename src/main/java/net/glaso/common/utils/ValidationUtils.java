package net.glaso.common.utils;

import org.springframework.util.ObjectUtils;

public class ValidationUtils {

    public static boolean isEmpty(Object obj ) {
        return ObjectUtils.isEmpty(obj);
    }
}
