package com.arthas.core.t;


/***
 * @param <A>
 * @param <B>
 * @param <C>
 */
public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
    private C third;

    public ThreeTuple(A a, B b, C c) {
        super(a, b);
        third = c;
    }


    public C getThird() {
        return third;
    }

    public void setThird(C third) {
        this.third = third;
    }
}
