package com.cluster.demo.actor;

import akka.actor.AbstractActor;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;
import akka.cluster.ClusterEvent.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class SimpleClusterListener extends AbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    Cluster cluster = Cluster.get(getContext().getSystem());

    @Override
    public void preStart() throws Exception {
        cluster.subscribe(getSelf(), ClusterEvent.initialStateAsEvents(),
                MemberEvent.class, UnreachableMember.class);
    }

    @Override
    public void postStop() throws Exception {
        cluster.unsubscribe(getSelf());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(MemberUp.class, mUp -> {
                    log.info("Member is up :: {}", mUp.member());
                })
                .match(UnreachableMember.class, mUnreachable -> {
                    log.info("Member is Unreachable :: {}", mUnreachable.member());
                })
                .match(MemberRemoved.class, mRemoved -> {
                    log.info("Member is Removed :: {}", mRemoved.member());
                })
                .match(MemberEvent.class, message -> {

                })
                .build();
    }
}
