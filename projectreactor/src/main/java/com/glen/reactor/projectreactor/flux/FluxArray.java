package com.glen.reactor.projectreactor.flux;

import java.util.Arrays;
import java.util.List;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;

public class FluxArray {
	public static void main(String args[]) {
		List<String> strings=Arrays.asList("a","b","c");
		Flux.fromIterable(strings).subscribe(Utils.onNext());
		
		Integer[] arr = {1,2,3,4,5,6};
		Flux.fromArray(arr).subscribe(Utils.onNext());
	}
}
