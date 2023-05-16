#!/bin/bash

oc delete deployment order-consumer -n mykafka
oc delete deployment order-producer -n mykafka
oc delete service order-consumer -n mykafka
oc delete service order-producer -n mykafka
oc delete route order-producer -n mykafka

oc delete kafkatopics my-topic -n mykafka

oc delete sa order-consumer -n mykafka
oc delete sa order-producer -n mykafka