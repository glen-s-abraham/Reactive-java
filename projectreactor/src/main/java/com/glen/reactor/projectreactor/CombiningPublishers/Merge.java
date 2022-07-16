package com.glen.reactor.projectreactor.CombiningPublishers;

import com.glen.reactor.projectreactor.helpers.AmericanAirlinesFlights;
import com.glen.reactor.projectreactor.helpers.EmirateFlights;
import com.glen.reactor.projectreactor.helpers.QuatarFlights;
import com.glen.reactor.projectreactor.utils.DefaultSubscriber;

import reactor.core.publisher.Flux;

public class Merge {

	public static void main(String[] args) {
		
		Flux<String>agentFlux = Flux.merge(
				QuatarFlights.getFlights(),
				EmirateFlights.getFlights(),
				AmericanAirlinesFlights.getFlights()
		);
		agentFlux.subscribe(DefaultSubscriber.subscriber());
	}

}
