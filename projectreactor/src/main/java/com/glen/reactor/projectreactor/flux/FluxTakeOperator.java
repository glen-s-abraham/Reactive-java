package com.glen.reactor.projectreactor.flux;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;

import reactor.core.publisher.Flux;

public class FluxTakeOperator {
	public static void main(String args[]) {
		Flux.range(0,10)
		.log()
		.take(3)//takes 3 elements and cancel
		.log()
		.subscribe(DefaultSubscriber.subscriber());
	}
}
