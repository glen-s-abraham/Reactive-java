package com.glen.reactor.projectreactor.CombiningPublishers;

import java.time.Duration;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;
import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;

public class CombineLatest {
	public static void main(String args[]) {
		
		Flux.combineLatest(getString(), getNumber(),(s,i)->s+i).subscribe(DefaultSubscriber.subscriber());
		Utils.sleepSeconds(10);	
		
		
	}
	private static Flux<String> getString(){
		return Flux.just("A","B","C","D").delayElements(Duration.ofSeconds(1));
	}
	private static Flux<Integer> getNumber(){
		return Flux.just(1,2,3,4,5).delayElements(Duration.ofSeconds(2));
	}
	
}
