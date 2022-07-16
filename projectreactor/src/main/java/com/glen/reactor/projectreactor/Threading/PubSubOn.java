package com.glen.reactor.projectreactor.Threading;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class PubSubOn {
	public static void main(String args[]) {
		Flux<Object> flux = Flux.create(fluxSink -> {
			printThreadName("create");
			for(int i=0;i<20;i++) {
				fluxSink.next(i);
			}
			fluxSink.complete();				
		}).doOnNext(i -> printThreadName("next" + i));
		
		flux
			.publishOn(Schedulers.parallel())
			.doOnNext(i -> printThreadName("next" + i))
			.subscribeOn(Schedulers.boundedElastic())
			.subscribe(v -> printThreadName("sub " + v));
		
	
		Utils.sleepSeconds(5);
	}

	private static void printThreadName(String msg) {
		System.out.println(msg + "\t\t" + Thread.currentThread().getName());
	}
}
