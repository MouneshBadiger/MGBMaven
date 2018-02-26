package com.mgb.corejava.test;

import java.util.Scanner;

public class PrimeNumberTest {

	public static void main(String[] args) {
		while(true) {
			System.out.println("Enter the prime number to be checked");
			Scanner sc= new Scanner(System.in);
			int num=sc.nextInt();
			boolean isPrime=true;
			if(num==0 || num==1) {
				isPrime=false;
			}
			else {
				for(int i=2;i<(num/2);i++) {
					if(num%i==0) {
						isPrime=false;	
					}
				}
			}
			if(isPrime) {
				System.out.println("Its a prime number");
			}else {
				System.out.println("Its not a prime number");
			}
			System.out.println();
		}
		

	}

}
