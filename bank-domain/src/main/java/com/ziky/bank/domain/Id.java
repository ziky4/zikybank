package com.ziky.bank.domain;


import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Created by Ziky on 5.12.2014.
 */
public abstract class Id<V> {
    @JsonIgnore
    public abstract V getId();
}
