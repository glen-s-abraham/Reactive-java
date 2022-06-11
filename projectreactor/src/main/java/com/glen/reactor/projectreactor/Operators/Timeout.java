package com.glen.reactor.projectreactor.Operators;

import java.time.Duration;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;

import reactor.core.publisher.Flux;

public class Timeout {
	
	public static void main(String args[]) {
		getOrderNumbers()
			.timeout(Duration.ofSeconds(2),fallback())
			.subscribe(DefaultSubscriber.subscriber());
	}
	
	public static Flux<Integer> getOrderNumbers(){
		return Flux.range(1, 10)
				.delayElements(Duration.ofSeconds(5));
	}
	
	private static Flux<Integer> fallback(){
		return Flux.range(100, 10).delayElements(Duration.ofMillis(200));
	}

}
