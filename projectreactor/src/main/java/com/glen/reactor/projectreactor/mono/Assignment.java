package com.glen.reactor.projectreactor.mono;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;

public class Assignment {
	
	private static final Path PATH = Paths.get("src/main/resources/assignment/sec01");
	
	private static String readFile(String fileName) {
		try {
			return Files.readString(PATH.resolve(fileName));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static void writeFile(String fileName,String data) {
		try {
			Files.writeString(PATH.resolve(fileName), data);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static void deleteFile(String fileName) {
		try {
			Files.delete(PATH.resolve(fileName));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Mono<String>read(String fileName){
		return Mono.fromSupplier(()->readFile(fileName));
	}
	
	public static Mono<Void>write(String fileName,String data){
		return Mono.fromRunnable(()->writeFile(fileName,data));
	}
	
	public static Mono<Void>delete(String fileName){
		return Mono.fromRunnable(()->deleteFile(fileName));
	}
	
	public static void main(String args[]) {
		read("file02.txt").subscribe(Utils.onNext(),Utils.onError(),Utils.onComplete());
		write("file03.txt","this is file03").subscribe(Utils.onNext(),Utils.onError(),Utils.onComplete());
		delete("file02.txt").subscribe(Utils.onNext(),Utils.onError(),Utils.onComplete());
	}


}
