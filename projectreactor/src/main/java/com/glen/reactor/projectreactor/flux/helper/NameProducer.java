package com.glen.reactor.projectreactor.flux.helper;

import java.util.function.Consumer;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.FluxSink;

public class NameProducer implements Consumer<FluxSink<String>>{

	private FluxSink<String> fluxSink;
	
	@Override
	public void accept(FluxSink<String> t) {
		this.fluxSink=t;
	}
	
	public void produce() {
		String name = Utils.faker().country().name();
		String thread = Thread.currentThread().getName();
		this.fluxSink.next(thread+": "+name);
	}

}
