package com.glen.reactor.projectreactor.mono;

import reactor.core.publisher.Mono;
import com.glen.reactor.projectreactor.utils.Utils;

public class MonoRunnable {
	
	public static void main(String[] args) {


        Mono.fromRunnable(timeConsumingProcess())
                .subscribe(Utils.onNext(),
                        Utils.onError(),
                        () -> {
                            System.out.println("process is done. Sending emails...");
                        });


    }

    private static Runnable timeConsumingProcess(){
        return () -> {
            Utils.sleepSeconds(300);
            System.out.println("Operation completed");
        };
    }
	
}
