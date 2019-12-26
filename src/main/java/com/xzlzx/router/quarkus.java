package com.xzlzx.router;

import com.xzlzx.bean.PostBody;
import com.xzlzx.bean.R;
import com.xzlzx.bean.User;
import io.vertx.axle.core.Vertx;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class quarkus {

    @Inject
    Vertx vertx;

    @Path("/test")
    @GET
    public String test() {
        System.out.println(vertx);
        return "hello";
    }

    @Path("/get")
    @GET
    public String getHello(@QueryParam("a") Integer a, @QueryParam("b") String b) {
        return String.format("hello:  %d  ,  %s", a, b);
    }

    @Path("/post")
    @POST
    public String postHello(PostBody body) {
        return String.format("hello:  %d  ,  %s", body.a, body.b);
    }

    @Path("/response")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response response(@QueryParam("a") Integer a, @QueryParam("b") String b) {
        User user = new User();
        user.id = a;
        user.name = b;
        return Response.ok(user).build();
    }

    @Path("/body")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R body(@QueryParam("a") Integer a, @QueryParam("b") String b) {
        User user = new User();
        user.id = a;
        user.name = b;
        return R.ok();
    }
}