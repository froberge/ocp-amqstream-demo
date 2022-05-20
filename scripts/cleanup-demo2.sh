#!/bin/bash

oc delete deployment music-chart-app -n mykafka
oc delete deployment player-app -n mykafka

oc delete service music-chart-app -n mykafka
oc delete service player-app -n mykafka

oc delete kafkatopics played-songs -n mykafka
oc delete kafkatopics songs -n mykafka

oc delete sa music-chart-app -n mykafka
oc delete sa player-app -n mykafka