package com.example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.client.ClusterClientReceptionist;
import com.example.seed.cluster.SeedClusterController;

public class SeedMain {

    public static void main(String[] args) {
        System.out.println("----------- STARTING CLUSTER -----------");

        final ActorSystem system = ActorSystem.create("application");
        ActorRef clusterController = system.actorOf(Props.create(SeedClusterController.class), "SeedClusterController");
        ClusterClientReceptionist.get(system).registerService(clusterController);
    }

}
