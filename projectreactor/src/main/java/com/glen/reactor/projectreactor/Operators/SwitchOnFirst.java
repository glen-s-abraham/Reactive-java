package com.glen.reactor.projectreactor.Operators;

import java.util.function.Function;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;
import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;

class _Person{
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public _Person() {
		this.name = Utils.faker().name().firstName();
		this.age = Utils.faker().random().nextInt(1,30);
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
}


public class SwitchOnFirst{
	
	public static void main(String[] args) {
		getPerson()
		.switchOnFirst((signal,personFlux)->{
			return signal.isOnNext() && signal.get().getAge()>10?personFlux:applyFilterMap().apply(personFlux);
		})
		//.transform(applyFilterMap())
		.subscribe(DefaultSubscriber.subscriber());
		
	}
	
	public static Flux<Person> getPerson(){
		return Flux.range(1, 10)
				.map(i->new Person());
	}
	
	public static Function<Flux<Person>, Flux<Person>> applyFilterMap(){
		return flux->flux
				.filter(p->p.getAge()>10)
				.doOnNext(p->p.setName(p.getName().toUpperCase()));
				
	}
}
