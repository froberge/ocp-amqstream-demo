package com.thecat.demo.kafka;

import io.smallrye.reactive.messaging.kafka.Record;
import com.thecat.demo.common.Order;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class OrderProducer {

    @Inject @Channel("order-out")
    Emitter<Record<String, Integer>> emitter;

    public void sendMovieToKafka(Order order) {
        emitter.send(Record.of(order.name, order.value));
    }
}