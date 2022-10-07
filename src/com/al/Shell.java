package com.al;

import java.util.Scanner;

public class Shell {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		Runner runner = new Runner();
		
		while(true) {
			System.out.print(">>> ");
			String input = sc.nextLine();
			if(input.length() <1 || input.isBlank()) continue;
			runner.run("<stdin>",input);
			System.out.println(runner.getAbstractSyntaxTree());
			
		}
	}

}
