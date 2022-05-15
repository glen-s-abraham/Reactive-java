package com.glen.reactor.projectreactor.mono;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Mono;

public class MonoSupplierRefactor {
	
	private static Mono<String> getName() {
		System.out.println("Generating name");
		Utils.sleepSeconds(3);
		return Mono.fromSupplier(()->Utils.faker().name().fullName())
				.map(String::toUpperCase);
	}
	
	public static void main(String args[]) {
		//Building the pipeline
		getName();
		getName();
		//Execute the pipeline
		getName().subscribe(name->System.out.println(name));
	}
}
