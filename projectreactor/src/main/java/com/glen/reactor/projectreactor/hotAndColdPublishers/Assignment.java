package com.glen.reactor.projectreactor.hotAndColdPublishers;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import com.glen.reactor.projectreactor.utils.DefaultSubscriber;
import com.glen.reactor.projectreactor.utils.Utils;

import lombok.Builder.Default;
import reactor.core.publisher.Flux;

class PurchaseOrder{
	private String item;
	private double price;
	private String category;
	private int quantity;
	public PurchaseOrder() {
		
		this.item = Utils.faker().commerce().productName();
		this.price = Double.parseDouble(Utils.faker().commerce().price(10, 100));
		this.category = Utils.faker().commerce().department();
		this.quantity = Utils.faker().random().nextInt(1,10);
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "PurchaseOrder [item=" + item + ", price=" + price + ", category=" + category + ", quantity=" + quantity
				+ "]";
	}
	
}

class OrderService{
	
	private Flux<PurchaseOrder> flux;
	
	public Flux<PurchaseOrder> orderStream(){
		if(Objects.isNull(flux))
			flux = getOrderStream();
		return flux;
	}
	
	private Flux<PurchaseOrder> getOrderStream(){
		return Flux.interval(Duration.ofMillis(100))
				.map(i->new PurchaseOrder())
				.publish()
				.refCount(2);
	}
}

class RevenueService{
	private Map<String, Double> db = new HashMap<>();
	public RevenueService() {
		db.put("Kids", 0.0);
		db.put("Automotive", 0.0);
	}
	
	public Consumer<PurchaseOrder> subscribeOrderStream(){
		return p-> db.computeIfPresent(p.getCategory(), (k,v)->v+p.getPrice());
	}
	
	public Flux<String> revenueStream(){
		return Flux.interval(Duration.ofSeconds(2)).map(i->db.toString());
				
	}
}

class InventoryService{
	private Map<String, Integer> db = new HashMap<>();
	public InventoryService() {
		db.put("Kids", 100);
		db.put("Automotive", 100);
	}
	
	public Consumer<PurchaseOrder> subscribeOrderStream(){
		return p-> db.computeIfPresent(p.getCategory(), (k,v)->v-p.getQuantity());
	}
	
	public Flux<String> inventoryStream(){
		return Flux.interval(Duration.ofSeconds(2)).map(i->db.toString());			
	}
}

public class Assignment {
	public static void main(String args[]) {
		OrderService orderService = new OrderService();
		RevenueService revenueService = new RevenueService();
		InventoryService inventoryService = new InventoryService();
		
		orderService.orderStream().subscribe(revenueService.subscribeOrderStream());
		orderService.orderStream().subscribe(inventoryService.subscribeOrderStream());
	
		revenueService.revenueStream().subscribe(DefaultSubscriber.subscriber("revenue"));
		inventoryService.inventoryStream().subscribe(DefaultSubscriber.subscriber("inventory"));
		
		Utils.sleepSeconds(60);
	}
}
