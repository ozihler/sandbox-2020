package com.zihler.wiki.domain.values;

public class Id {
    private Long id;

    private Id(Long id) {
        this.id = id;
    }

    public static Id of(Long id) {
        return new Id(id);
    }

    public static Id empty() {
        return new Id(null);
    }

    public long longValue() {
        return id;
    }
}
