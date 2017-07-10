package com.pdy.designpattern.state;

public class MainTest {

	public static void main(String[] args) {
		GumBallMachine machine = new GumBallMachine(2);
		machine.insertQuarter();
		System.out.println(machine);
		machine.ejectQuarter();
		System.out.println(machine);
		machine.insertQuarter();
		machine.turnCrank();
		machine.dispense();
		System.out.println(machine);
		machine.turnCrank();
		
		machine.insertQuarter();
		machine.turnCrank();
		System.out.println(machine);
		machine.dispense();
		System.out.println(machine);
		
		machine.insertQuarter();
		machine.turnCrank();
		machine.dispense();
		System.out.println(machine);
	}

}
