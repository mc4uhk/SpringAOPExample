package com.jcg.examples.bo;

import org.springframework.stereotype.Component;

@Component
public class BusinessTargetObject {
	private String name ;
	
	public String performTransaction(String arg) {
		System.out.println("Performing Txn for: " + arg);
		return "Transaction " + arg + " Successful";
	}

	public void merryGoAround() {
		System.out.println("Running merryGoAround for Business");
	}

	public void sayHello() {
		System.out.println("Hello says Hello: " + name);
	}

	public void throwException() {
		System.out.println("Business class about throw an NPE..");
		throw new NullPointerException("THrowing custom Exception");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
