package com.add;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.client.ClusterClientReceptionist;
import com.add.actor.AdderActor;
import com.core.cluster.CommonClusterController;
import com.typesafe.config.ConfigFactory;



/**
 * Created by pravin on 3/29/17.
 */
public class AdderMain {

    public static void main(String[] args) {

        final ActorSystem system = ActorSystem.create("application");
        final ActorRef clusterController = system.actorOf(Props.create(CommonClusterController.class), "clusterController");
        ClusterClientReceptionist.get(system).registerService(clusterController);

        final ActorRef adder = system.actorOf(Props.create(AdderActor.class), "adder");
        ClusterClientReceptionist.get(system).registerService(adder);

    }

}
