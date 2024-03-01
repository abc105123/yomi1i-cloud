package top.yomi1i.framework.common.util.collection;

import cn.hutool.core.collection.CollUtil;

import java.util.Set;

/**
 * @author abcran
 * @since 2024/3/2
 */
public class SetUtils {

    public static <T> Set<T> asSet(T... elements) {
        return CollUtil.newHashSet(elements);
    }

}
