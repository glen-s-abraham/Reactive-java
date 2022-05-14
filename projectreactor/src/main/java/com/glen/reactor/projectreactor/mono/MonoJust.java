package com.glen.reactor.projectreactor.mono;

import reactor.core.publisher.Mono;

public class MonoJust {
	public static void main(String args[]) {
		//publisher
		Mono<Integer> mono=Mono.just(1);
		
		System.out.println(mono);
		
		mono.subscribe(i->System.out.println(i));
		
	}
}
