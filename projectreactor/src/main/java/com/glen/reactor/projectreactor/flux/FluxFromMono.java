package com.glen.reactor.projectreactor.flux;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxFromMono {
	public static void main(String args[]) {
		Mono<String> mono = Mono.just("a");
		Flux<String> flux= Flux.from(mono);
		flux.subscribe(Utils.onNext());
	}
	private static void doSomething(Flux<String> flux) {
		
	}
}
