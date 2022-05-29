package com.glen.reactor.projectreactor.flux;

import com.glen.reactor.projectreactor.flux.helper.NameProducer;
import com.glen.reactor.projectreactor.utils.DefaultSubscriber;
import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;

public class FluxPush {
	public static void main(String args[]) {
		NameProducer nameProducer = new NameProducer();
		Flux.push(nameProducer).subscribe(DefaultSubscriber.subscriber());
		Runnable runnable = nameProducer::produce;
		
		for(int i=0;i<10;i++) {
			new Thread(runnable).start();
		}
		
		Utils.sleepSeconds(2);
	}
}
