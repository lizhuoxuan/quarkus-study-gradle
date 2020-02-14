package com.xzlzx.router;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xzlzx.bean.PostBody;
import com.xzlzx.bean.R;
import com.xzlzx.bean.TableUser;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.common.Sort;
import io.vertx.axle.core.Vertx;
import io.vertx.axle.core.http.HttpServerRequest;
import io.vertx.core.MultiMap;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterStyle;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.hibernate.usertype.DynamicParameterizedType;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Path("/hello")
public class quarkus {

    @Inject
    Vertx vertx;

    // swagger ui 说明
    @Operation(summary = "接口的简述", description = "详细说明")
    @Parameters({
            @Parameter(name = "a", description = "a的说明", in = ParameterIn.DEFAULT),
            @Parameter(name = "b", description = "b的说明", in = ParameterIn.PATH),
    })
    @Path("/test/{b}")
    @GET
    @Produces(value = "plain/text")
    public String test(@QueryParam("a") String a,
                       @PathParam("b") String b) throws IOException {
        System.out.println(a);
        System.out.println(b);
        String json = "[{\"a\":null,\"b\":\"abcd\"}]";
        ObjectMapper mapper = new ObjectMapper();
        List<R> o = mapper.readValue(json, new TypeReference<List<R>>() {});
        for (R r : o) {
            System.out.println(r.a);
            System.out.println(r.b);
            r.data = new PostBody();
        }
        String s = mapper.writeValueAsString(o);
        System.out.println(s);
        return "hello";
    }

    @Path("/get")
    @GET
    public String getHello(
            HttpServerRequest httpServerRequest,
            @QueryParam("a") Integer a,
                           @QueryParam("b") String b) {
        MultiMap headers = httpServerRequest.getDelegate().headers();

        return String.format("hello:  %d  ,  %s", a, b);
    }

    // swagger ui 说明
    @Operation(summary = "接口的简述2", description = "详细说明2")
    @RequestBody(description = "post参数说明", required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Path("/post")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postHello(PostBody body) throws JsonProcessingException {
        System.out.println(body);
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(body);
        return s;
    }

    @Inject
    EntityManager em;

    @Path("/response")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response response(@QueryParam("a") Integer a, @QueryParam("b") String b) {
        TableUser tableUser = new TableUser();
//        tableUser.name = b;
//        tableUser.createTime = LocalDateTime.now();
//        tableUser.persist();
//        List<TableUser> users1 = TableUser.listAll();
//        System.out.println(users1);
//        List<TableUser> users2 = TableUser.findAll(Sort.by("name")).list();
//        System.out.println(users2);
        TableUser users = (TableUser) em.createNativeQuery("select * from tableuser order by random() limit 1", TableUser.class).getSingleResult();
        System.out.println(users);
        return Response.ok(tableUser).build();
    }

    @Path("/body")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R body(@QueryParam("a") Integer a, @QueryParam("b") String b) {
        TableUser tableUser = new TableUser();
        tableUser.name = b;
        return R.ok();
    }
}