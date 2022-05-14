package com.glen.reactor.projectreactor.mono;

import java.util.stream.Stream;

public class StreamsDemo {

	public static void main(String args[]) {
		Stream<Integer> stream = Stream.of(1).map(i->{
			try {
				Thread.sleep(1000);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return i*2;
		});
		
		stream.forEach(System.out::println);
		
	}
}
