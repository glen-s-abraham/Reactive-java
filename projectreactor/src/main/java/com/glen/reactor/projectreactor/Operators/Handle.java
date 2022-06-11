package com.glen.reactor.projectreactor.Operators;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;

import reactor.core.publisher.Flux;

public class Handle {
	public static void main(String args[]) {
		Flux.range(1, 20).handle((integer,synchronousSink)->{
			if(integer%2==0)
				synchronousSink.next(integer);
		})
		.subscribe(DefaultSubscriber.subscriber());
	}
}
