package lab3;
import java.util.Scanner;
public class LabActivity3ConditionalStatement {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		// employee input
		System.out.print("Enter your first name: ");
		String firstName = scanner.nextLine();
		
		System.out.print("Enter your last name: ");
		String lastName = scanner.nextLine();
		
		System.out.print("Enter your age: ");
		int age = scanner.nextInt();
		
		// Age restrictions
		if (age < 18) {
            System.out.println("Minors are not allowed");
            System.exit(0);
        } else if (age >= 65) {
            System.out.println("Senior Citizens are not allowed");
            System.exit(0);
        }

		System.out.print("Enter hours worked: ");
		double hoursWorked = scanner.nextDouble();

		// Check working hours
		if (hoursWorked > 24) {
            System.out.println("Number of hours worked cannot exceed 24 hours");
            System.exit(0);
        } else if (hoursWorked <= 0) {
            System.out.println("Wrong input on daily work hours");
            System.exit(0);
        }

		System.out.print("Enter hourly wage: ");
		double hourlyWage = scanner.nextDouble();

		System.out.print("Enter role code (1-Manager, 2-Supervisor, 3-Staff, 4-Intern): ");
		int roleCode = scanner.nextInt();
		
		// role code
		String role;
        switch (roleCode) {
            case 1:
                role = "Manager";
                break;
            case 2:
                role = "Supervisor";
                break;
            case 3:
                role = "Staff";
                break;
            case 4:
                role = "Intern";
                break;
            default:
                role = "Undefined";
        }
		
		// calculate salary
		double dailySalary = hoursWorked * hourlyWage;
		double weeklySalary = dailySalary * 5;
		double monthlySalary = weeklySalary * 4;
		double grossYearlySalary = monthlySalary * 12;
		
		// Tax & deductions
		double tax = (grossYearlySalary > 250000) ? grossYearlySalary * 0.32 : 0;
		double netYearlySalary = grossYearlySalary - tax - 1500;
		int yearsToRetirement = 65 - age;
  
		
		// make the string into upper case
		String fullName = (lastName + ", " + firstName).toUpperCase();
		
		// Display employee information
		System.out.println("\nEmployee Information");
		System.out.println("----------------------");
		System.out.printf("Full Name:\t\t%s, %s%n", lastName.toUpperCase(), firstName.toUpperCase());
		System.out.printf("Age:\t\t\t%d years old%n", age);
		System.out.printf("Position:\t\t%s%n", role);
		System.out.printf("Years to Retirement:\t%d years%n", yearsToRetirement);
		System.out.printf("Daily Salary:\t\tPhp %.2f%n", dailySalary);
		System.out.printf("Weekly Salary:\t\tPhp %.2f%n", weeklySalary);
		System.out.printf("Monthly Salary:\t\tPhp %.2f%n", monthlySalary);
		System.out.printf("Gross Yearly Salary:\tPhp %.2f%n", grossYearlySalary);
		System.out.printf("Net Yearly Salary:\tPhp %.2f%n", netYearlySalary);

    }
		
		
}		
		
		
		
		
		




 
