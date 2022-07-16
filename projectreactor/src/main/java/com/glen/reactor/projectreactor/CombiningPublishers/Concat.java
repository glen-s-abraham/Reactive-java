package com.glen.reactor.projectreactor.CombiningPublishers;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;

import reactor.core.publisher.Flux;

public class Concat {
	public static void main(String args[]) {
		Flux<String> flux1 = Flux.just("a","b");
		Flux<String> flux2 = Flux.just("c","d","e");
		
		Flux<String> flux = Flux.concat(flux1,flux2);
		
		flux.subscribe(DefaultSubscriber.subscriber());
	}

}
