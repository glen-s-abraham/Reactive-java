package com.glen.reactor.projectreactor.Operators;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;

import reactor.core.publisher.Flux;

public class DoHooks {
	public static void main(String args[]) {
		Flux.create(fluxSink->{
			for(int i=0;i<5;i++) {
				fluxSink.next(i);
			}
			fluxSink.complete();
		})
		.doOnComplete(()->System.out.println("doOnComplete"))
		.doFirst(()->System.out.println("doFirst"))
		.doOnNext(o->System.out.println("doNext: "+o))
		.doOnSubscribe(s->System.out.println("doOnSubscribe: "+s))
		.doOnRequest(l->System.out.println("doOnRequest: "+l))
		.doOnError(err->System.out.println("doOnError: "+err))
		.doOnTerminate(()->System.out.println("doOnTerminates"))
		.doOnCancel(()->System.out.println("doOnCancel"))
		.doFinally(signal->System.out.println("doFinally:"+signal))
		.doOnDiscard(Object.class, o->System.out.println("doOnDiscard: "+o))
		.subscribe(DefaultSubscriber.subscriber());
	}
}
