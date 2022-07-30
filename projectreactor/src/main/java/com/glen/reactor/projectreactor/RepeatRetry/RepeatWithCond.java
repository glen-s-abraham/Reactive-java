package com.glen.reactor.projectreactor.RepeatRetry;

import java.util.concurrent.atomic.AtomicInteger;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;

import reactor.core.publisher.Flux;

public class RepeatWithCond {
	
	private static AtomicInteger atomicInteger = new AtomicInteger(1);
	
	public static void main(String args[]) {
		getInteger()
		.repeat(()->atomicInteger.get()<14)
			.subscribe(DefaultSubscriber.subscriber());
	}
	
	private static Flux<Integer> getInteger(){
		return Flux.range(1,3)
				.doOnSubscribe(s->System.out.println("subscribed"))
				.doOnComplete(()->System.out.println("completed"))
				.map(i->atomicInteger.getAndIncrement());
		
			
	}
}
