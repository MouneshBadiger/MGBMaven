package com.mgb.corejava.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Stream {
	
	public static void main(String[] args) {
		Stream<Employee> empStream=Stream.of(new Employee("mounesh",40000),new Employee("archana",45000),new Employee("bhargav",50000),new Employee("lokesh",55000));
		List<Employee> empList=empStream.collect(Collectors.toList());	
		//printing the stream
		System.out.println("-------Orginal list------");
		empList.forEach(System.out::println);
		System.out.println();
		System.out.println("-------SORTED LIST------");
		empList.stream().sorted((e1,e2)->e1.getName().compareTo(e2.getName())).forEach(System.out::println);
		
		System.out.println("------sum of all salaries------");
		System.out.println(empList.stream().mapToInt(e->e.getSalary()).sum());
		
		/*System.out.println("----checking reduce--------");
		empList.stream().reduce(accumulator)*/
		
		
	}

}

class Employee{
	private String name;
	private int salary;

	public Employee(String name,int sal) {
		super();
		this.name = name;
		this.salary=sal;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	
}