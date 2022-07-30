package com.glen.reactor.projectreactor.RepeatRetry;

import java.time.Duration;

import javax.management.RuntimeErrorException;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;
import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

public class RetryWhenAdvanced {
	
	public static void main(String args[]) {
		orderService(Utils.faker().business().creditCardNumber())
		.doOnError(err->System.out.println(err.getMessage()))
		.retryWhen(Retry.from(
				flux->flux.doOnNext(rs->{
					System.out.println(rs.totalRetries());
					System.out.println(rs.failure());
				})
				.handle((rs,synchronousSink)->{
					if(rs.failure().getMessage().equals("500"))
						synchronousSink.next(1);
					else
						synchronousSink.error(rs.failure());
				})
				.delayElements(Duration.ofSeconds(1))
		))
		.subscribe(DefaultSubscriber.subscriber());
		Utils.sleepSeconds(60);
	}
	
	private static Mono<String> orderService(String ccNumber){
		return Mono.fromSupplier(()->{
			processPayement(ccNumber);
			return Utils.faker().idNumber().valid();
		});
	}

	private static void  processPayement(String ccNumber){
		int random = Utils.faker().random().nextInt(1,10);
		if(random<8)
				throw new RuntimeException("500");
		else if(random<10)
				throw new RuntimeException("404");
	}
}
