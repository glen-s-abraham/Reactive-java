package com.glen.reactor.projectreactor.flux;

import java.util.List;
import java.util.stream.Stream;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;

public class FluxStream {

	public static void main(String[] args) {
		List<Integer> list=List.of(1,2,3,4,5);
		Stream<Integer> stream = list.stream();
		//streams can only be used once
		//Flux<Integer> integerFlux=Flux.fromStream(stream);
		Flux<Integer> integerFlux=Flux.fromStream(()->list.stream());
		integerFlux.subscribe(Utils.onNext(),Utils.onError(),Utils.onComplete());
		
		integerFlux.subscribe(Utils.onNext(),Utils.onError(),Utils.onComplete());
		

	}

}
