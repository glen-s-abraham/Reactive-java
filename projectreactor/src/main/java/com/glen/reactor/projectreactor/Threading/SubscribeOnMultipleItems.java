package com.glen.reactor.projectreactor.Threading;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SubscribeOnMultipleItems {
	public static void main(String args[]) {
		Flux<Object> flux = Flux.create(fluxSink -> {
			printThreadName("create");
			for(int i=0;i<20;i++) {
				fluxSink.next(i);
				Utils.sleepSeconds(1);
			}
				
		}).doOnNext(i -> printThreadName("next" + i));
		
		Runnable runnable=()->flux
			.subscribeOn(Schedulers.boundedElastic())
			.subscribe(v -> printThreadName("sub " + v));
		for(int i=0;i<5;i++) {
			new Thread(runnable).start();
		}
		
		Utils.sleepSeconds(5);
	}

	private static void printThreadName(String msg) {
		System.out.println(msg + "\t\t" + Thread.currentThread().getName());
	}

}
