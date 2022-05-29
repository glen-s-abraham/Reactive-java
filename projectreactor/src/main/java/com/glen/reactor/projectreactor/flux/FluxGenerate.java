package com.glen.reactor.projectreactor.flux;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;
import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;

public class FluxGenerate {
	public static void main(String args[]) {
		Flux.generate(synchronoesSink->{
			//no async calls-can emit only one data at a time
			synchronoesSink.next(Utils.faker().country().name());
			//synchronoesSink.next(Utils.faker().country().name());
			synchronoesSink.complete();
		})
		.take(2)
		.subscribe(DefaultSubscriber.subscriber());
	}
}
