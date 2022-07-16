package com.glen.reactor.projectreactor.Overflow;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;
import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Buffer {
	public static void main(String args[]) {
		System.setProperty("reactor.bufferSize.small","16");
		Flux.create(fluxSink->{
			for(int i=1;i<500;i++) {
				fluxSink.next(i);
				System.out.println("Pushed "+i);
			}
			fluxSink.complete();
		})
		.onBackpressureBuffer(20)
		.publishOn(Schedulers.boundedElastic())
		.doOnNext(i->{
			Utils.sleepMillis(10);
			System.out.println("Recieved "+i);
		})
		.subscribe(DefaultSubscriber.subscriber());
		
		Utils.sleepSeconds(10);
	}
}
