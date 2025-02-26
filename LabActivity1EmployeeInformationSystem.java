package main;
import java.util.Scanner;
public class LabActivity1EmployeeInformationSystem {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		// employee input
		System.out.print("Enter your first name: ");
		String firstName = scan.nextLine();
		
		System.out.print("Enter your last name: ");
		String lastName = scan.nextLine();
		
		System.out.print("Enter your age: ");
		int age = scan.nextInt();
		
		System.out.print("Enter hours worked: ");
		float hoursWorked = scan.nextFloat();
		
		System.out.print("Enter hourly wage: ");
		float hourlyWage = scan.nextFloat();
		
		// calculate daily salary
		float dailySalary = hoursWorked * hourlyWage;
		
		// Display employee information
		System.out.println("\n");
		
		System.out.println("Employee Information");
		System.out.println("---------------------");
		System.out.println("Full Name   : " + firstName + " " + lastName);
		System.out.println("Age	    : " + age + " years old");
		System.out.printf("Daily Salary: PHP %.2f",  dailySalary);
		
		
		
		
		
		
		
		

	}

}
