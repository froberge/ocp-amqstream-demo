package com.thecat.demo.kafka;

import io.smallrye.reactive.messaging.kafka.Record;
import com.thecat.demo.common.Order;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class OrderProducer {

    @Inject 
    @Channel("order-out")
    Emitter<Record<String, Integer>> emitter;

    private final Logger logger = Logger.getLogger(OrderProducer.class);

    public void sendOrderToKafka(Order order) {
        int value = order.value;
        emitter.send(Record.of(order.name, value));
        logger.infof("Publish and Order from : %s value %d$", order.name, value);
    }
}