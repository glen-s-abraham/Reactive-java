package com.glen.reactor.projectreactor.Threading;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SubscribeOnDemo {

	public static void main(String args[]) {
		Flux<Object> flux=Flux.create(fluxSink->{
			printThreadName("create");
			fluxSink.next(1);
		}).doOnNext(i->printThreadName("next"+i));
		flux
				.doFirst(()->printThreadName("first2"))
				.subscribeOn(Schedulers.boundedElastic())
				.doFirst(()->printThreadName("first1"))
				.subscribe(v->printThreadName("sub "+v));
		Utils.sleepSeconds(5);
	}
	private static void printThreadName(String msg) {
		System.out.println(msg+"\t\t"+Thread.currentThread().getName());
	}

}
