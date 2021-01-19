package com.arthas.core.t;

import java.io.Serializable;

public class OneTuple<A> implements Serializable {
    private A first;

    public OneTuple(A a) {
        first = a;
    }

    public A getFirst() {
        return first;
    }

    public void setFirst(A first) {
        this.first = first;
    }


}

