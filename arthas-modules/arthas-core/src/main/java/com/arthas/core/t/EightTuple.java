package com.arthas.core.t;

public class EightTuple<A, B, C, D, E, F, G, H> extends SevenTuple<A, B, C, D, E, F, G> {


    private H eight;

    public EightTuple(A a, B b, C c, D d, E e, F f, G g, H h) {
        super(a, b, c, d, e, f, g);
        eight = h;
    }


    public H getEight() {
        return eight;
    }

    public void setEight(H eight) {
        this.eight = eight;
    }
}
