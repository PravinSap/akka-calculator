package controllers;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.cluster.routing.ClusterRouterGroup;
import akka.cluster.routing.ClusterRouterGroupSettings;
import akka.routing.RandomGroup;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Collections;

/**
 * Created by pravin on 3/29/17.
 */
public class CalculatorController extends Controller {

    ActorRef adder;

    public CalculatorController(ActorSystem system) {
        String actorRoute = "adder";
        String actorRole = "";
        if (!actorRoute.startsWith("/user/")) {
            actorRoute = "/user/" + actorRoute;
        }
        Iterable<String> actorRouteesPaths = Collections.singletonList(actorRoute);
        adder = system.actorOf(
                new ClusterRouterGroup(new RandomGroup(actorRouteesPaths),
                        new ClusterRouterGroupSettings(10000,actorRouteesPaths,
                                false, actorRole)).props());
    }

    public Result addition() {

        return ok("2+2=" + (2+2));
    }


}
