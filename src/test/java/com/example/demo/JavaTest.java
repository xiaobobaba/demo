package com.example.demo;

import java.util.Arrays;

import org.junit.Test;

//@RunWith(SpringRunner.class)
public class JavaTest {
	@Test
	public void And() {
//		if(1 == 2 & isTrue()) {
//			System.out.println("true");
//		}
		
	}
	public boolean isTrue() {
		System.out.println("123");
		return false;
	}
	@Test
	public void Test() {
		//从小到大
		int[] intArray = new int[]{1,56,-5,33};
		Arrays.sort(intArray);
		System.out.println(Arrays.toString(intArray));
		//大写在前
		String[] strArray = new String[]{"Z", "a", "D"};
		Arrays.sort(strArray);
		System.out.println(Arrays.toString(strArray));
	} 

}
