package com.glen.reactor.projectreactor.flux;

import java.time.Duration;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;

public class FluxInterval {
	public static void main(String args[]) {
		Flux.interval(Duration.ofSeconds(1)).subscribe(Utils.onNext());
		Utils.sleepSeconds(500);
	}
	
	
}
