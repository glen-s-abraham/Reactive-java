package com.glen.reactor.projectreactor.helpers;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;

public class EmirateFlights {
	public static Flux<String> getFlights(){
		return Flux.range(1, Utils.faker().random().nextInt(1,10))
				.map(i->"Emirates"+Utils.faker().random().nextInt(100,900))
				.filter(i->Utils.faker().random().nextBoolean());
	}
}
