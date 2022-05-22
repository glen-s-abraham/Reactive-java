package com.glen.reactor.projectreactor.flux;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;

public class FluxToMono {
	public static void main(String args[]) {
		Flux.range(0,10)
		.filter(i->i>3)
		.next()
		.subscribe(Utils.onNext(),Utils.onError(),Utils.onComplete());
		
	}
}
