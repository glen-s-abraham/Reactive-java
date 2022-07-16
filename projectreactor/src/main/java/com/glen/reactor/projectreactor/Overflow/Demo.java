package com.glen.reactor.projectreactor.Overflow;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;
import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Demo {
	public static void main(String args[]) {
		Flux.create(fluxSink->{
			for(int i=1;i<501;i++) {
				fluxSink.next(i);
				System.out.println("Pushed "+i);
			}
		})
		.publishOn(Schedulers.boundedElastic())
		.doOnNext(i->{
			Utils.sleepMillis(10);
			System.out.println("Recieved "+i);
		})
		.subscribe(DefaultSubscriber.subscriber());
		
		Utils.sleepSeconds(60);
	}
}

