package com.xzlzx.bean;

public class R<T> {

    public Integer a;

    public String b;

    public T data;

    public R() {
    }

    public R(Integer a, String b) {
        this.a = a;
        this.b = b;
    }

    public static R ok() {
        return new R(1, "2");
    }
}
