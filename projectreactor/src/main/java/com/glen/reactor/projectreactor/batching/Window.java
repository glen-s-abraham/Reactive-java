package com.glen.reactor.projectreactor.batching;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;
import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Window {
	
	private static AtomicInteger count = new AtomicInteger();
	
	public static void main(String[] args) {
		eventStream()
		.window(5)
		.flatMap(flux->saveEvents(flux))
		.subscribe(DefaultSubscriber.subscriber());
		
		Utils.sleepSeconds(60);

	}
	private static Flux<String> eventStream(){
		return Flux.interval(Duration.ofMillis(300))
				.map(i->"event"+i);
	}
	private static Mono<Integer> saveEvents(Flux<String> flux){
		return flux
				.doOnNext(e->System.out.println("saving"+e))
				.doOnCancel(()->System.out.println("saved this batch"))
				.then(Mono.just(count.getAndIncrement()));
	}
}
