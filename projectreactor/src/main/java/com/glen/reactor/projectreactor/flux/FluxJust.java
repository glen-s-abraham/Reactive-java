package com.glen.reactor.projectreactor.flux;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;

public class FluxJust {
	
	public static void main(String args[]) {
		Flux<Object> flux=Flux.just(1,2,3,4,"a",Utils.faker().name().firstName());
		flux.subscribe(Utils.onNext());
		Flux<Integer> integerFlux = Flux.just(1,2,3,4,5,6);
		Flux<Integer> evenFlux = integerFlux.filter(i->i%2==0);
		
		integerFlux.subscribe(i->System.out.println("Sub 1:"+i));
		evenFlux.subscribe(i->System.out.println("Sub 2:"+i));
	}
}
