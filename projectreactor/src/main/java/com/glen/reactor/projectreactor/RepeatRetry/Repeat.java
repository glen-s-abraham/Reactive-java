package com.glen.reactor.projectreactor.RepeatRetry;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;

import reactor.core.publisher.Flux;

public class Repeat {
	public static void main(String args[]) {
		getInteger()
		.repeat(2)
			.subscribe(DefaultSubscriber.subscriber());
	}
	
	private static Flux<Integer> getInteger(){
		return Flux.range(1,3)
				.doOnSubscribe(s->System.out.println("subscribed"))
				.doOnComplete(()->System.out.println("completed"));
		
			
	}
 }
