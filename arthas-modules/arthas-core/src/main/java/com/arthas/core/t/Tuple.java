package com.arthas.core.t;


import java.io.Serializable;

public class Tuple implements Serializable {



    private EleventTuple data;


    public Tuple() {
        this.data = new EleventTuple(null, null, null, null, null, null, null, null, null, null, null);
    }


    public Tuple(Object a) {
        this.data = new EleventTuple(a, null, null, null, null, null, null, null, null, null, null);
    }


    public Tuple(Object a, Object b) {
        this.data = new EleventTuple(a, b, null, null, null, null, null, null, null, null, null);
    }

    public Tuple(Object a, Object b, Object c) {
        this.data = new EleventTuple(a, b, c, null, null, null, null, null, null, null, null);
    }


    public Tuple(Object a, Object b, Object c, Object d) {
        this.data = new EleventTuple(a, b, c, d, null, null, null, null, null, null, null);
    }

    public Tuple(Object a, Object b, Object c, Object d, Object e) {
        this.data = new EleventTuple(a, b, c, d, e, null, null, null, null, null, null);
    }

    public Tuple(Object a, Object b, Object c, Object d, Object e, Object f) {
        this.data = new EleventTuple(a, b, c, d, e, f, null, null, null, null, null);
    }

    public Tuple(Object a, Object b, Object c, Object d, Object e, Object f, Object g) {
        this.data = new EleventTuple(a, b, c, d, e, f, g, null, null, null, null);
    }


    public Tuple(Object a, Object b, Object c, Object d, Object e, Object f, Object g, Object h) {
        this.data = new EleventTuple(a, b, c, d, e, f, g, h, null, null, null);
    }

    public Tuple(Object a, Object b, Object c, Object d, Object e, Object f, Object g, Object h, Object i) {
        this.data = new EleventTuple(a, b, c, d, e, f, g, h, i, null, null);
    }


    public Tuple(Object a, Object b, Object c, Object d, Object e, Object f, Object g, Object h, Object i, Object j) {
        this.data = new EleventTuple(a, b, c, d, e, f, g, h, i, j, null);
    }

    public Tuple(Object a, Object b, Object c, Object d, Object e, Object f, Object g, Object h, Object i, Object j, Object k) {
        this.data = new EleventTuple(a, b, c, d, e, f, g, h, i, j, k);
    }

    public EleventTuple getData() {
        return data;
    }

    public void setData(EleventTuple data) {
        this.data = data;
    }




}
