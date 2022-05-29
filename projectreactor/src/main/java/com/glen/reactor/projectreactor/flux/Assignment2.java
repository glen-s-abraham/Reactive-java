package com.glen.reactor.projectreactor.flux;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

class FileReaderService{
	
	private Callable<BufferedReader> openReader(Path path){
		return ()->Files.newBufferedReader(path);
	}
	
	private BiFunction<BufferedReader, SynchronousSink<String>, BufferedReader> read(){
		return (br,sink)->{
			try {
				String line=br.readLine();
				if(Objects.isNull(line)) {
					sink.complete();
				}
				sink.next(line);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return br;
		};
	}
	
	private Consumer<BufferedReader> closeReader(){
		return br->{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
	}
	public Flux<String> read(Path path){
		return Flux.generate(openReader(path),read(),closeReader());
	}
}

public class Assignment2 {
	public static void main(String args[]) {
		Path path = Paths.get("src/main/resources/assignment/sec03/file1.txt");
		FileReaderService readerService = new FileReaderService();
		readerService.read(path).subscribe(DefaultSubscriber.subscriber());
	}
}
