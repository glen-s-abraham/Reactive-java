package com.glen.reactor.projectreactor.Threading;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ParallelProcessing {

	public static void main(String args[]) {
		
		
		Flux.range(1,10)
			.parallel()
			.runOn(Schedulers.parallel())
			.doOnNext(i -> printThreadName("next" + i))
			.subscribe(v -> printThreadName("sub " + v));
		
	
		Utils.sleepSeconds(5);
	}

	private static void printThreadName(String msg) {
		System.out.println(msg + "\t\t" + Thread.currentThread().getName());
	}

}
