package com.cmh.readinglist;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public long getLong(){
		Integer a = 0;
		int b = 23;
		return a +b;
	}

	public static void main(String[] args) {
		System.out.println(Collections.emptyList());

		List<String> list = new ArrayList<>();
		list.add(null);
		list.add("fdkafa");
		list.add(null);
		list.remove(null);
		System.out.println(list.toString());
	}

}
