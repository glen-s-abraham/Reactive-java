package com.glen.reactor.projectreactor.CombiningPublishers;

import com.glen.reactor.projectreactor.helpers.NameGenerator;
import com.glen.reactor.projectreactor.utils.DefaultSubscriber;

public class StartWith {

	public static void main(String[] args) {
		NameGenerator generator = new NameGenerator();
		generator.generateNames()
		.take(2)
		.subscribe(DefaultSubscriber.subscriber("Mike"));
		
		generator.generateNames()
		.take(2)
		.subscribe(DefaultSubscriber.subscriber("Tom"));
		
		generator.generateNames()
		.take(3)
		.subscribe(DefaultSubscriber.subscriber("Hank"));
		
		generator.generateNames()
		.filter(name->name.startsWith("A"))
		.take(1)
		.subscribe(DefaultSubscriber.subscriber("Jane"));

	}

}
