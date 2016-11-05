package ranian.bookkeeping.system.standalonetest;

public class TestObjectA {

	public TestObjectA() {
		System.out.println("Object A instantiated: 0 arg constructor");
	}
	
	public TestObjectA(String strParam) {
		System.out.println(
				String.format("Object A instantiated: 1 string arg %s", strParam));
	}
	
	public TestObjectA(String strParam, Integer integerParm) {
		System.out.println(
				String.format("Object A instantiated: 1 string arg %s, 1 integer arg %s", strParam, integerParm));
	}
}
