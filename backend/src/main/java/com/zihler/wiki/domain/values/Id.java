package com.zihler.wiki.domain.values;

public class Id {
    private long id;

    private Id(long id) {
        this.id = id;
    }

    public static Id of(long id) {
        return new Id(id);
    }

    public long longValue() {
        return id;
    }
}
