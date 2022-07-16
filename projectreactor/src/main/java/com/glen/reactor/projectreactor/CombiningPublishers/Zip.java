package com.glen.reactor.projectreactor.CombiningPublishers;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;

import reactor.core.publisher.Flux;

public class Zip {
	public static void main(String args[]) {
		Flux.zip(getBody(),getEngine(),getTires()).subscribe(DefaultSubscriber.subscriber());
	}
	
	private static Flux<String> getBody(){
		return Flux.range(1, 5).map(i->"Body");
	}
	private static Flux<String> getEngine(){
		return Flux.range(1, 2).map(i->"Engine");
	}
	private static Flux<String> getTires(){
		return Flux.range(1, 5).map(i->"Tires");
	}
}
