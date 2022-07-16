package com.glen.reactor.projectreactor.Threading;


import reactor.core.publisher.Flux;

public class ThreadDemo {
	public static void main(String args[]) {
		Flux<Object> flux=Flux.create(fluxSink->{
			printThreadName("create");
			fluxSink.next(1);
		}).doOnNext(i->printThreadName("next"+i));
		Runnable runnable=()->flux.subscribe(v->printThreadName("sub "+v));
		for(int i =0;i<2;i++) {
			new Thread(runnable).start();
		}
	}
	private static void printThreadName(String msg) {
		System.out.println(msg+"\t\t"+Thread.currentThread().getName());
	}

}
