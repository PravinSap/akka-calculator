package com.add.actor;

import akka.actor.UntypedActor;

/**
 * Created by pravin on 3/29/17.
 */
public class AverageActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Throwable {

        if(message instanceof Integer[]) {
            Integer[] ints = (Integer[]) message;
            Integer result = 0;
            for (Integer anInt : ints) {
                result += anInt;
            }
            String resultStr = "Average is :: " + (result/ints.length);
            System.out.println(resultStr);
            Thread.sleep(1000);
            sender().tell(resultStr, self());
        }

    }
}
