package com.glen.reactor.projectreactor.mono;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Mono;

public class MonoSubscribe {
	public static void main(String args[]) {
		//publisher
		Mono<String> mono=Mono.just("ball");
		//Triggers the publisher-> mono.subscribe();
		mono.subscribe(
				Utils.onNext(),
				Utils.onError(),
				Utils.onComplete()
		);
		
		
	}
}
