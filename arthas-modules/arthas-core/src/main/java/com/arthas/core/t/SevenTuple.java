package com.arthas.core.t;

public class SevenTuple<A, B, C, D, E, F,G> extends SixTuple<A, B, C, D, E, F> {

    private G seven;

    public SevenTuple(A a, B b, C c, D d, E e, F f,G g) {
        super(a, b, c, d, e, f);
        seven = g;
    }

    public G getSeven() {
        return seven;
    }

    public void setSeven(G seven) {
        this.seven = seven;
    }
}
