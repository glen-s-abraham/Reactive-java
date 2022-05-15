package com.glen.reactor.projectreactor.mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Mono;

public class MonoSupplier {
	
	private static String getName() {
		System.out.println("Generating name.....");
		return Utils.faker().name().fullName();
	}
	
	public static void main(String args[]) {
		
		Supplier<String> nameSupplier = ()->getName();
		Callable<String> nameCallable = ()->getName();
		Mono.fromCallable(nameCallable).subscribe(n->System.out.println(n));
		
		//use just when you have data already
		Mono<String>name= Mono.fromSupplier(()->getName());
		name.subscribe(n->System.out.println(n));
		
		
	}
	
}
