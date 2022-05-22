package com.glen.reactor.projectreactor.flux;

import java.util.List;

import com.glen.reactor.projectreactor.utils.Utils;

public class FluxVsList {
	public static void main(String args[]) {
		//All names retruned after 5 seconds once the list is generated
		List<String>names=NameGenerator.getNamesList(5);
		System.out.println(names);
		
		//Returns values as and when single name is generated
		NameGenerator.getNamesFlux(5).subscribe(Utils.onNext());
	}
}
