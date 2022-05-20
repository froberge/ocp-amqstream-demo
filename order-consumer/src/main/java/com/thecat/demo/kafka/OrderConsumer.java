package com.thecat.demo.kafka;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderConsumer {

    private final Logger logger = Logger.getLogger(OrderConsumer.class);

    @Incoming("order-in")
    public void receive(Record<String, Integer> record) {
        logger.infof("Got an order for: %s for %d$", record.key(), record.value());
    }
}