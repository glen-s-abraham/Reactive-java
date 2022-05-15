package com.glen.reactor.projectreactor.utils;

import java.util.function.Consumer;

import com.github.javafaker.Faker;

public class Utils {
	
	private static final Faker FAKER = Faker.instance();
	
	public static Consumer<Object> onNext(){
		return o->System.out.println("Recieved "+ o);
	}
	
	public static Consumer<Throwable> onError(){
		return err->err.getMessage();
	}
	
	public static Runnable onComplete(){
		return ()->System.out.println("Completed");
	}
	
	public static Faker faker() {
		return FAKER;
	}
	
	public static void sleepSeconds(int seconds) {
		try {
			Thread.sleep(seconds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
