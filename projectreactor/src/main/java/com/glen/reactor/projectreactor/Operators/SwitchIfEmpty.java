package com.glen.reactor.projectreactor.Operators;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;

import reactor.core.publisher.Flux;

public class SwitchIfEmpty {
	public static void main(String args[]) {
		getOrderNumbers()
			.filter(i->i>10)
			.switchIfEmpty(fallback())
			.subscribe(DefaultSubscriber.subscriber());
			
	}
	//cache lookup
	public static Flux<Integer> getOrderNumbers(){
		return Flux.range(1, 12);
	}
	//direct db
	public static Flux<Integer> fallback(){
		return Flux.range(20,5);
	}
}
