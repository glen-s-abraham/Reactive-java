package com.glen.reactor.projectreactor.flux;

import java.util.ArrayList;
import java.util.List;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;

public class NameGenerator {
	
	public static List<String>getNamesList(int count){
		List<String> names = new ArrayList<>();
		for(int i=0;i<count;i++)
			names.add(getName());
		return names;
	}
	
	public static Flux<String>getNamesFlux(int count){
		return Flux.range(0, count)
				.map(i->getName());
	}
	
	private static String getName() {
		Utils.sleepSeconds(1);
		return Utils.faker().name().fullName();
	}
}
