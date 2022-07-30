package com.glen.reactor.projectreactor.RepeatRetry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;

import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

public class RetryWhen {
private static AtomicInteger atomicInteger = new AtomicInteger(1);
	
	public static void main(String args[]) {
		getInteger()
		.retryWhen(Retry.fixedDelay(2,Duration.ofSeconds(2)))
			.subscribe(DefaultSubscriber.subscriber());
	}
	
	private static Flux<Integer> getInteger(){
		return Flux.range(1,3)
				.doOnSubscribe(s->System.out.println("subscribed"))
				.doOnComplete(()->System.out.println("completed"))
				.map(i->i/0)
				.doOnError((err)->System.out.println("Error"));
		
			
	}
}
