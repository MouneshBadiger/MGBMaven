package com.mhi.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DrawingApp {

	public static void main(String[] args) {
		ApplicationContext contex=new ClassPathXmlApplicationContext("spring.xml");
		//Triangle triangle=new Triangle();
		Triangle triangle=(Triangle) contex.getBean("triangle");
		triangle.draw();
	}
}
