package com.glen.reactor.projectreactor.batching;

import java.time.Duration;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;

public class GroupBy {
	public static void main(String args[]) {
		Flux.range(1, 30)
			.delayElements(Duration.ofSeconds(1))
			.groupBy(i->i%2) //key 0,1
			.subscribe(gf->process(gf,gf.key()));
		
		Utils.sleepSeconds(60);
	}
	
	private static void process(Flux<Integer> flux,int key) {
		flux.subscribe(i->System.out.println("Key:"+key+ "Item:"+i));
	}
}
