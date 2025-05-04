package lab4;
import java.awt.*;
import java.awt.event.*;

public class LabActivity4EmpInfoSystemGUI {
    public static void main(String[] args) {
        Frame frame = new Frame("Laboratory Activity 4");
        frame.setSize(450, 500);
        frame.setLayout(new BorderLayout());

        // Panel for inputs 
        Panel inputPanel = new Panel(new GridLayout(5, 2, 10, 10));

        Label lblFirstName = new Label("First Name:");
        TextField txtFirstName = new TextField();

        Label lblLastName = new Label("Last Name:");
        TextField txtLastName = new TextField();

        Label lblAge = new Label("Age:");
        TextField txtAge = new TextField();

        Label lblHours = new Label("Hours Worked:");
        TextField txtHours = new TextField();

        Label lblRate = new Label("Hourly Rate:");
        TextField txtRate = new TextField();

        inputPanel.add(lblFirstName);
        inputPanel.add(txtFirstName);
        inputPanel.add(lblLastName);
        inputPanel.add(txtLastName);
        inputPanel.add(lblAge);
        inputPanel.add(txtAge);
        inputPanel.add(lblHours);
        inputPanel.add(txtHours);
        inputPanel.add(lblRate);
        inputPanel.add(txtRate);

        // Panel for button
        Panel buttonPanel = new Panel(new FlowLayout());
        Button btnSubmit = new Button("Submit");
        buttonPanel.add(btnSubmit);

        // Output Area
        TextArea outputArea = new TextArea(5, 50);
        outputArea.setEditable(false);

        // Add all panels to frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(outputArea, BorderLayout.SOUTH);

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = txtFirstName.getText().trim();
                String lastName = txtLastName.getText().trim();
                String ageStr = txtAge.getText().trim();
                String hoursStr = txtHours.getText().trim();
                String rateStr = txtRate.getText().trim();

                if (firstName.isEmpty() || lastName.isEmpty() || ageStr.isEmpty() ||
                        hoursStr.isEmpty() || rateStr.isEmpty()) {
                    outputArea.setText("Error: All fields must be filled.");
                    return;
                }

                // Name should contain only letters 
                if (!firstName.matches("[a-zA-Z\\s]+") || !lastName.matches("[a-zA-Z\\s]+")) {
                    outputArea.setText("Error: Names must only contain letters.");
                    return;
                }

                try {
                    int age = Integer.parseInt(ageStr);
                    double hours = Double.parseDouble(hoursStr);
                    double rate = Double.parseDouble(rateStr);

                    String fullName = firstName + " " + lastName;
                    double dailyWage = hours * rate;

                    outputArea.setText("Employee Name: " + fullName +
                            "\nAge: " + age +
                            "\nDaily Wage: " + String.format("%.2f", dailyWage));
                } catch (NumberFormatException ex) {
                    if (!ageStr.matches("\\d+")) {
                        outputArea.setText("Error: Age must be a valid integer.");
                    } else {
                        outputArea.setText("Error: Hours worked and hourly rate must be valid numbers.");
                    }
                }
            }
        });

        // Proper window close
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                frame.dispose();
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }
}
