package com.zzq.common.util;

import java.util.List;

public class ListUtil {
    private ListUtil() {

    }

    public static <T> boolean isEmpty(List<T> list) {
        if (list == null || list.size() == 0) {
            return true;
        }else {
            return false;
        }
    }
}
