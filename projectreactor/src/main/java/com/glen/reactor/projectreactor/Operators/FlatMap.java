package com.glen.reactor.projectreactor.Operators;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;
import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Flux;

class User{
	private int id;
	private String name;
	public User(int id) {
		this.id = id;
		this.name = Utils.faker().name().firstName();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
	
}

class PurchaseOrder{
	private String item;
	private double price;
	private int userId;
	public PurchaseOrder(int userId) {
		this.userId = userId;
		this.item = Utils.faker().commerce().productName();
		this.price = Double.parseDouble(Utils.faker().commerce().price(10, 100));
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "PurchaseOrder [item=" + item + ", price=" + price + ", userId=" + userId + "]";
	}
	
	
	
}

public class FlatMap {
	private static Map<Integer, List<PurchaseOrder>> db = new HashMap<>();
	static {
		List<PurchaseOrder> list1 = Arrays.asList(
				new PurchaseOrder(1),
				new PurchaseOrder(1),
				new PurchaseOrder(1)
		);
		db.put(1,list1);
		List<PurchaseOrder> list2 = Arrays.asList(
				new PurchaseOrder(2),
				new PurchaseOrder(2)
		);
		db.put(2,list2);
	}
	
	public static Flux<PurchaseOrder> getOrders(int userId){
		return Flux.create(poFluxSink->{
			db.get(userId).forEach(poFluxSink::next);
			poFluxSink.complete();
		});
	}
	
	public static Flux<User> getUsers(){
		return Flux.range(1,2)
				.map(i->new User(i));
	}
	
	public static void main(String args[]) {
		getUsers()
			.flatMap(user->getOrders(user.getId()))
			.filter(p->p.getPrice()>10)
			.subscribe(DefaultSubscriber.subscriber());
	}
}
