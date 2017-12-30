package com.add.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.pattern.Patterns;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import static scala.compat.java8.FutureConverters.toJava;

/**
 * Created by pravin on 3/29/17.
 */
public class AdderActor extends UntypedActor {


    @Override
    public void onReceive(Object message) throws Throwable {

        System.out.println("Sender :: " + sender());
        System.out.println("Self :: " + self());

        if(message instanceof Integer[]) {
            Integer[] ints = (Integer[]) message;
            Integer result = 0;
            for (Integer anInt : ints) {
                result += anInt;
            }
            System.out.println("Addition is :: " +result);
            ActorRef actorRef = getContext().actorOf(Props.create(AverageActor.class));
            scala.concurrent.Future<Object> future = Patterns.ask(actorRef, message, 1000);
            CompletionStage cs = toJava(future);
            CompletableFuture<String> cf = (CompletableFuture) cs;
            cf.thenApply(new Function() {
                @Override
                public Object apply(Object o) {
                    System.out.println("inside then apply :: " + o);
                    return o;
                }
            });
            System.out.println("After getting response in Adder ::   " + cf.get());
//            System.exit(0);
        } else if(message instanceof String) {
            System.out.println(message);
        } else {
            System.out.println(message);




        }

    }
}
