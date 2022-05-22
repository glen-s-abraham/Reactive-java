package com.glen.reactor.projectreactor.flux;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;

public class FluxRange {
	public static void main(String args[]) {
		Flux.range(3, 10).log().map(i->Utils.faker().name().firstName()).subscribe(Utils.onNext());
	}
}
