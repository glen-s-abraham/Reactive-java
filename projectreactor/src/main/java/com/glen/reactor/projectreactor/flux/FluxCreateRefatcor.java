package com.glen.reactor.projectreactor.flux;

import com.glen.reactor.projectreactor.flux.helper.NameProducer;
import com.glen.reactor.projectreactor.utils.DefaultSubscriber;

import reactor.core.publisher.Flux;

public class FluxCreateRefatcor {
	public static void main(String args[]) {
		NameProducer nameProducer=new NameProducer();
		Flux.create(nameProducer).subscribe(DefaultSubscriber.subscriber());
		//nameProducer.produce();
		Runnable runnable = ()->nameProducer.produce();
		for(int i=0;i<10;i++) {
			new Thread(runnable).start();
		}
	}
}
