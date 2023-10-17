package com.aos.core.utils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionsUtils {
    public static <T,R> boolean duplicate(List<T> list, Function<T,R> duplicate) {
        return list.stream().collect(Collectors.groupingBy(duplicate,Collectors.counting())).values().stream().anyMatch(time -> time > 1);
    }
}
