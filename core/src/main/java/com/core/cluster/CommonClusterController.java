package com.core.cluster;

import akka.actor.AbstractActor;
import akka.actor.ActorSystem;
import akka.cluster.Cluster;

import static akka.cluster.ClusterEvent.*;

public class CommonClusterController extends AbstractActor {

    final ActorSystem system = this.getContext().system();
    final Cluster cluster = Cluster.get(system);

    @Override
    public void preStart() {
        this.cluster.subscribe(this.self(), initialStateAsEvents(), MemberEvent.class, UnreachableMember.class);
    }

    @Override
    public void postStop() {
        this.cluster.unsubscribe(this.self());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(MemberEvent.class, message -> {
                    System.out.println("=====> Matched :: MemberEvent");
                })
                .match(MemberUp.class, message -> {
                    System.out.println("=====> Matched :: MemberUp");
                })
                .match(UnreachableMember.class, message -> {
                    System.out.println("=====> Matched :: UnreachableMember");
                })
                .match(MemberRemoved.class, message -> {
                    System.out.println("=====> Matched :: MemberRemoved");
                })
                .build();
    }
}
