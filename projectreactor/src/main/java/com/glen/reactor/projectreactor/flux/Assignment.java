package com.glen.reactor.projectreactor.flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.atomic.AtomicInteger;

import org.reactivestreams.Subscriber;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;

public class Assignment {
	
	public static void main(String args[]) throws InterruptedException {
		
		CountDownLatch latch = new CountDownLatch(1);
		getCurrentPrice().subscribeWith(new Subscriber<Integer>() {
			
			private org.reactivestreams.Subscription subscription;
			

			@Override
			public void onSubscribe(org.reactivestreams.Subscription s) {
				this.subscription = s;
				s.request(Long.MAX_VALUE);
				
			}

			@Override
			public void onNext(Integer price) {
				System.out.println(LocalDateTime.now()+"Price:"+price);
			}

			@Override
			public void onError(Throwable t) {
				latch.countDown();
			}

			@Override
			public void onComplete() {
				latch.countDown();
				
			}
		});
		
		latch.await();
	}
	
	public static Flux<Integer> getCurrentPrice(){
		AtomicInteger atomicInteger = new AtomicInteger(100);
		return Flux.interval(Duration.ofSeconds(1))
				.map(i->atomicInteger.getAndAccumulate(Utils.faker().random().nextInt(-5,5)
				,(a,b)->a+b)
		);
	}
}
