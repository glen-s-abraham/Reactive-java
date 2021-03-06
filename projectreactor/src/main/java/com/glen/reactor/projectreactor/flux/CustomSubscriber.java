package com.glen.reactor.projectreactor.flux;

import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.atomic.AtomicReference;

import org.reactivestreams.Subscriber;

import reactor.core.publisher.Flux;

public class CustomSubscriber {
	public static void main(String args[]) {
		
		AtomicReference<org.reactivestreams.Subscription> atomicRef = new AtomicReference<>();
		
		Flux.range(1,20)
		.log()
		.subscribeWith(new Subscriber<Integer>() {
			
			@Override
			public void onSubscribe(org.reactivestreams.Subscription subscription) {
				System.out.println("Recieved sub:"+subscription);
				atomicRef.set(subscription);
			}
			
			
			@Override
			public void onNext(Integer integer) {
				System.out.println("onNext:"+integer);
			}
			
			@Override
			public void onError(Throwable throwable) {
				System.out.println("onError:"+throwable.getMessage());
			}
			
			@Override
			public void onComplete() {
				System.out.println("onComplete");
			}

		});
		
		atomicRef.get().request(3);
		atomicRef.get().request(3);
		atomicRef.get().cancel();
		atomicRef.get().request(3);
	}

}
