package com.aos.core.utils;

import java.util.List;

import com.aos.core.constants.BaseConstant;
import com.aos.core.entity.BaseEntity;
import org.springframework.util.CollectionUtils;

public final class CommonUtils {

    public static <T extends BaseEntity> T findFirstById(List<T> collection, Integer id) {
        if (CollectionUtils.isEmpty(collection)) return null;
        for (T element : collection) {
            if (element.getId().equals(id)) return element;
        }
        return null;
    }

    public static <T> T firstNonNull(T... params) {
        for (T param : params)
            if (param != null)
                return param;
        return null;
    }

    public static int showType(boolean success) {
        if (success) {
            return BaseConstant.SHOW_TYPE_SUCCESS;
        } else {
            return BaseConstant.SHOW_TYPE_ERROR_MESSAGE;
        }
    }

}
