package com.glen.reactor.projectreactor.flux;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;
import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;

public class FluxGenerateUntil {
	public static void main(String args[]) {
		Flux.generate(
			()->1,
			(counter,synchronousSink)->{
				String country= Utils.faker().country().name();
				System.out.println("Emitting :"+country);
				synchronousSink.next(country);
				if(country.toLowerCase().equals("canada")||counter>=10)
					synchronousSink.complete();
				return counter+1;
			}
		).subscribe(DefaultSubscriber.subscriber());
	}
}
