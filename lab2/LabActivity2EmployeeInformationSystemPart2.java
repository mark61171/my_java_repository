package lab2;
import java.util.Scanner;
public class LabActivity2EmployeeInformationSystemPart2 {

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
		double hoursWorked = scan.nextDouble();
		
		System.out.print("Enter hourly wage: ");
		double hourlyWage = scan.nextDouble();
		
		
		// calculate salary
		double dailySalary = hoursWorked * hourlyWage;
		double weeklySalary = dailySalary * 5;
		double monthlySalary = weeklySalary * 4;
		double grossYearlySalary = monthlySalary * 12;
		
		// net yearly salary
		double taxDeduction = grossYearlySalary * 0.32; // taas ng tax :(
		double govermentBenefits = 1500.00;
		double netYearlySalary = grossYearlySalary - taxDeduction - govermentBenefits;
		
		// age for retirement
		int ageForRetirement = Math.abs(65 - age);
		
		// make the string into upper case
		String fullName = (lastName + ", " + firstName).toUpperCase();
		
		// Display employee information
		System.out.println("\n");
		
		System.out.println("Employee Information");
		System.out.println("---------------------");
		System.out.println("Full Name: \t\t" + fullName);
        	System.out.println("Age: \t\t\t " + age + " years old");
        	System.out.println("Years to Retirement: \t" + ageForRetirement + " years");
        	System.out.printf("Daily Salary:\t\t Php %.2f%n", dailySalary);
        	System.out.printf("Weekly Salary:\t\t Php %.2f%n", weeklySalary);
        	System.out.printf("Monthly Salary:\t\t Php %.2f%n", monthlySalary);
        	System.out.printf("Gross Yearly Salary:\t Php %.2f%n", grossYearlySalary);
        	System.out.printf("Net Yearly Salary:\t Php %.2f%n", netYearlySalary);
		
		
		
		
		
		
		
		

	}

}
 