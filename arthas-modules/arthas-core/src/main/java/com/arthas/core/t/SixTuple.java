package com.arthas.core.t;

import com.alibaba.fastjson.JSON;

/******
 *
 *
 *
 * @param <A>
 * @param <B>
 * @param <C>
 * @param <D>
 * @param <E>
 * @param <F>
 */
public class SixTuple<A, B, C, D, E, F> extends FiveTuple<A, B, C, D, E> {
    public  F sixth;

    public SixTuple(A a, B b, C c, D d, E e, F f) {
        super(a, b, c, d, e);
        sixth = f;
    }


    public F getSixth() {
        return sixth;
    }

    public void setSixth(F sixth) {
        this.sixth = sixth;
    }

    static SixTuple<String, String, String, Float, Double, Byte> a() {
        return new SixTuple<String, String, String, Float, Double, Byte>(
                "11111", "", "hi", (float) 4.7,
                1.1, (byte) 1);
    }



    public static void main(String[] args) {
        SixTuple result =  a();

        System.out.println(result.getFirst());

        System.out.println(JSON.toJSONString(result));

    }
}
