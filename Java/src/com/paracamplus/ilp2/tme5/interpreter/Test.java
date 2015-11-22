package com.paracamplus.ilp2.tme5.interpreter;

public class Test {

	public static Dog dog;
	public static int hash = 0;

	public static void main(String[] args) {

		dog = new Dog("Rover");
		System.out.println("start : " + dog);
		System.out.println("before foo : " + dog);
		// foo(dog);
		//System.out.println("");
		dog = new Test.Dog("Fifi");
		System.out.println("after foo : " + dog + ", " + dog.getName());
	}

	public static void foo(Dog someDog) {
		someDog.setName("Max"); 		// AAA
		someDog = new Test.Dog("Fifi"); // BBB
		someDog.setName("Rowlf"); 		// CCC
		System.out.println("inside foo : " + dog);
	}

	static class Dog {
		private String name;

		public Dog(String name) {
			this.name = name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
		
		
	}
	
	
}
