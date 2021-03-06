package com.glen.reactor.projectreactor.mono;

import java.util.HashMap;
import java.util.Map;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Mono;

public class MonoEmpty {
	
	private static Mono<String> userRepository(Integer userId){
		Map<Integer,String> users = new HashMap<>();
		users.put(1, Utils.faker().name().firstName());
		users.put(2, Utils.faker().name().firstName());
		users.put(3, Utils.faker().name().firstName());
		users.put(4, Utils.faker().name().firstName());
		
		if(users.containsKey(userId)) {
			return Mono.just(users.get(userId));
		}
		return Mono.empty();
		
	}
	
	public static void main(String args[]) {
		userRepository(1).subscribe(
				Utils.onNext(),
				Utils.onError(),
				Utils.onComplete()
		);
		
		userRepository(69).subscribe(
				Utils.onNext(),
				Utils.onError(),
				Utils.onComplete()
		);
	}

}
