package com.glen.reactor.projectreactor.flux;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;
import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;

public class FluxCreateIssueFix {
	public static void main(String args[]) {
		Flux.create(fluxSink->{
			String country;
			do {
				country=Utils.faker().country().name();
				System.out.println("emitting: "+country);
				fluxSink.next(country);
			}while(!country.toLowerCase().equals("canada") && !fluxSink.isCancelled());
			fluxSink.complete();
		})
		.take(3)
		.subscribe(DefaultSubscriber.subscriber());
	}
}
