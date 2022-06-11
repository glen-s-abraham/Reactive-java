package com.glen.reactor.projectreactor.Operators;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;
import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class onError {
	public static void main(String args[]) {
		Flux.range(1, 10)
		.log()
		.map(i->10/(5-i))
		//.onErrorReturn(-1)
		.onErrorResume(e->fallback())
		.subscribe(DefaultSubscriber.subscriber());
	}
	
	private static Mono<Integer> fallback(){
		return Mono.fromSupplier(()->Utils.faker().random().nextInt(100, 200));
	}
}
