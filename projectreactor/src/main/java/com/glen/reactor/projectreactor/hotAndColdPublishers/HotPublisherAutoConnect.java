package com.glen.reactor.projectreactor.hotAndColdPublishers;

import java.time.Duration;
import java.util.stream.Stream;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;
import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;

public class HotPublisherAutoConnect {
	public static void main(String[] args) {
		//share = publish.refcount(1)
		Flux<String>movies=Flux.fromStream(()->getMovie())
				.delayElements(Duration.ofSeconds(1))
				.publish()
				.autoConnect(1);
		movies.subscribe(DefaultSubscriber.subscriber("sam"));
		Utils.sleepSeconds(10);
		movies.subscribe(DefaultSubscriber.subscriber("mike"));
		Utils.sleepSeconds(60);
	}
	private static Stream<String> getMovie(){
		System.out.println("Got the movie streaming request");
		return Stream.of(
				"Scene1",
				"Scene2",
				"Scene3",
				"Scene4",
				"Scene5"
		);
	}
}
