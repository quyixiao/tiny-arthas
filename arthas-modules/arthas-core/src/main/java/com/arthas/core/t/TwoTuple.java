package com.arthas.core.t;

public class TwoTuple<A, B> extends OneTuple<A> {
    private B second;

    public TwoTuple(A a, B b) {
        super(a);
        second = b;
    }

    public B getSecond() {
        return second;
    }

    public void setSecond(B second) {
        this.second = second;
    }

}

