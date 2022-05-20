package com.thecat.demo.endpoint;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thecat.demo.common.Order;
import com.thecat.demo.kafka.OrderProducer;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    OrderProducer producer;

    @POST
    public Response send(Order order) {
        producer.sendOrderToKafka(order);
        // Return an 202 - Accepted response.
        return Response.accepted().build();
    }

    @GET
    @Path("/health")
    public int health() {
        return Response.Status.OK.getStatusCode();
    }
}