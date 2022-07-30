package com.glen.reactor.projectreactor.batching;

import java.time.Duration;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;
import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;

public class BufferOverlap {
	public static void main(String[] args) {
		//buffer(itemcount)
		//buffer(timeinterval)
		eventStream().bufferTimeout(5, Duration.ofSeconds(2)).subscribe(DefaultSubscriber.subscriber());
		
		Utils.sleepSeconds(60);

	}
	private static Flux<String> eventStream(){
		return Flux.interval(Duration.ofMillis(300))
				.map(i->"event"+i);
	}
}
