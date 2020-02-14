package com.xzlzx.bean;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class PostBody {

    // swagger ui 说明
    @Schema(description = "a的说明")
    public Integer a;

    @Schema(description = "b的说明")
    public String b;

    public PostBody() {
    }

    public PostBody(Integer a, String b) {
        this.a = a;
        this.b = b;
    }
}
