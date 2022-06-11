package com.glen.reactor.projectreactor.Operators;

import java.time.Duration;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;

import reactor.core.publisher.Flux;

public class DefaultIfEmpty {
	public static void main(String args[]) {
		getOrderNumbers()
			.filter(i->i>10)
			.defaultIfEmpty(-100)
			.subscribe(DefaultSubscriber.subscriber());
	}
	
	public static Flux<Integer> getOrderNumbers(){
		return Flux.range(1, 12);
	}
}
