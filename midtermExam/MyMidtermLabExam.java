package midtermExam;

import java.util.Scanner;

public class MyMidtermLabExam {
	
    static final int MAX_TICKETS = 5; // Constant
    static String[] descriptions = new String[MAX_TICKETS];
    static String[] urgencies = new String[MAX_TICKETS];
    static String[] statuses = new String[MAX_TICKETS];
    static int ticketCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        // Main menu
        while (true) {
            System.out.println("\n--- IT Ticket Processing System ---");
            System.out.println("1. Add Ticket");
            System.out.println("2. Update Ticket Status");
            System.out.println("3. Show All Tickets");
            System.out.println("4. Generate Report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
                continue;
            }
            
            // choices for the input
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addTicket(scanner);
                    break;
                case 2:
                    updateTicketStatus(scanner);
                    break;
                case 3:
                    showTickets();
                    break;
                case 4:
                    generateReport();
                    break;
                case 5:
                    System.out.println("Exiting program. Thank you!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    // applies max ticket count
    public static void addTicket(Scanner scanner) {
        if (ticketCount >= MAX_TICKETS) {
            System.out.println("Cannot add more tickets. Maximum limit reached.");
            return;
        }
        // checks input for description
        String description;
        while (true) {
            System.out.print("Enter issue description: ");
            description = scanner.nextLine().trim();
            
            if (description.isEmpty()) {
                System.out.println("Error. Please try again");
            } else if (description.matches(".*\\d.*")) {
                System.out.println("Error. Please try again");
            } else {
                break;
            }
        }
        // checks input for urgency
        String urgency;
        while (true) {
            System.out.print("Enter urgency (Low, Medium, High): ");
            urgency = scanner.nextLine().trim();
            
            // Convert to sentence case
            if (!urgency.isEmpty()) {
                urgency = urgency.substring(0, 1).toUpperCase() + urgency.substring(1).toLowerCase();
            }

            if (urgency.equals("Low") || urgency.equals("Medium") || urgency.equals("High")) {
               break;
            } else if (urgency.isEmpty()) {
            	System.out.println("Error. Please try again");
            } else {
                System.out.println("Invalid Input. Please try again");
            }
        }

        descriptions[ticketCount] = description;
        urgencies[ticketCount] = urgency;
        statuses[ticketCount] = "Pending"; // Default status
        ticketCount++;

        System.out.println("Ticket added successfully!");
    }

    public static void updateTicketStatus(Scanner scanner) {
        if (ticketCount == 0) {
            System.out.println("No tickets to update.");
            return;
        }

        showTickets();

        int ticketNum = -1;
        while (true) {
            System.out.print("Enter the ticket number to update: ");
            
            if (scanner.hasNextInt()) {
                ticketNum = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        // Check if the ticket number is in range
        if (ticketNum < 1 || ticketNum > ticketCount) {
            System.out.println("Invalid ticket number.");
            return;
        }

        int index = ticketNum - 1;

        // Check if ticket is already resolved
        if (statuses[index].equalsIgnoreCase("Resolved")) {
            System.out.println("Cannot update a resolved ticket.");
            return;
        }

        // Loop for valid status input
        while (true) {
            System.out.print("Enter new status (In Progress / Resolved): ");
            String newStatus = scanner.nextLine().trim();

            // Convert input to sentence case (capitalizing first letter of each word)
            if (!newStatus.isEmpty()) {
                String[] words = newStatus.split(" "); // Split into words if there's more than one word
                StringBuilder correctedStatus = new StringBuilder();

                for (String word : words) {
                    correctedStatus.append(word.substring(0, 1).toUpperCase())  // Capitalize first letter
                                    .append(word.substring(1).toLowerCase())   // Make the rest lowercase
                                    .append(" ");
                }

                newStatus = correctedStatus.toString().trim();  // Remove extra space at the end
            }

            // Check if the new status is valid
            if (newStatus.equalsIgnoreCase("In Progress") || newStatus.equalsIgnoreCase("Resolved")) {
                statuses[index] = newStatus;
                System.out.println("Ticket status updated successfully!");
                break; 
            } else {
                System.out.println("Invalid status. Please enter 'In Progress' or 'Resolved'.");
            }
        }
    }

    // shows ticket description
    public static void showTickets() {
        if (ticketCount == 0) {
            System.out.println("No tickets available.");
            return;
        }

        System.out.println("\n--- All Tickets ---");
        for (int i = 0; i < ticketCount; i++) {
            System.out.println((i + 1) + ". [" + urgencies[i] + "] "+ descriptions[i] + " - Status: " + statuses[i]);

        }
    }
    // checks for ticket status and count for resolved and unresolved tickets
    public static void generateReport() {
        int pendingOrInProgress = 0;
        int resolved = 0;

        for (int i = 0; i < ticketCount; i++) {
            if (statuses[i].equalsIgnoreCase("Resolved")) {
                resolved++;
            } else {
                pendingOrInProgress++;
            }
        }

        System.out.println("\n--- Ticket Report ---");
        System.out.println("Total Tickets: " + ticketCount);
        System.out.println("Pending/In Progress Tickets: " + pendingOrInProgress);
        System.out.println("Resolved Tickets: " + resolved);
    }
}
