package com.glen.reactor.projectreactor.helpers;

import java.util.ArrayList;
import java.util.List;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;

public class NameGenerator {
	
	private List<String> list = new ArrayList<>();
	
	public Flux<String> generateNames(){
		return Flux.generate(stringSynchronousSink->{
			System.out.println("generated Fresh");
			Utils.sleepSeconds(1);
			String name = Utils.faker().name().firstName();
			list.add(name);
			stringSynchronousSink.next(name);
		})
		.cast(String.class)
		.startWith(getFromCache());
	}
	
	private Flux<String> getFromCache(){
		return Flux.fromIterable(list);
	}
}
