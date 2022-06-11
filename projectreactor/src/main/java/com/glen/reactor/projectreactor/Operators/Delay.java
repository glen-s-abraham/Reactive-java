package com.glen.reactor.projectreactor.Operators;

import java.time.Duration;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;

import reactor.core.publisher.Flux;

public class Delay {

	public static void main(String[] args) {
		Flux.range(1, 100)
		.log()
		.delayElements(Duration.ofSeconds(1))
		.subscribe(DefaultSubscriber.subscriber());

	}

}
