package com.glen.reactor.projectreactor.Operators;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;

import reactor.core.publisher.Flux;

public class RateLimit {
	public static void main(String args[]) {
		Flux.range(1, 1000)
		.log()
		.limitRate(100,99)//if 99% of data consumed sent request for next dataset
		.subscribe(DefaultSubscriber.subscriber());
	}
}
