package com.cluster.demo;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.cluster.demo.actor.SimpleClusterListener;

public class SeedMain {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("calculator");
        system.actorOf(Props.create(SimpleClusterListener.class), "simpleClusterListener");
    }
}
