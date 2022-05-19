package com.thecat.demo.kafka;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;


import javax.enterprise.context.ApplicationScoped;

import com.thecat.demo.common.Order;

@ApplicationScoped
public class OrderConsumer {

    private final Logger logger = Logger.getLogger(OrderConsumer.class);

    @Incoming("order-in")
    public void receive(Record<Integer, String> record) {
        logger.infof("Got an order from : %s value %d$", record.key(), record.value());
    }
}