package com.arthas.core.utils.matcher;


import com.arthas.core.utils.check.ArthasCheckUtils;

/**
 * 字符串全匹配
 * @author ralf0131 2017-01-06 13:18.
 */
public class EqualsMatcher<T> implements Matcher<T> {

    private final T pattern;

    public EqualsMatcher(T pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean matching(T target) {
        return ArthasCheckUtils.isEquals(target, pattern);
    }
}
