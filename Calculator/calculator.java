import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class calculator extends JFrame implements ActionListener {
    // Core Calculator State
    private JTextField display;
    private JLabel operationLabel;
    private String operator = "";
    private double num1 = 0;
    private boolean startNewNumber = true; // True when ready to start typing a new number

    // History Feature Components
    private JTextArea historyDisplay;
    private JScrollPane historyScrollPane;
    private ArrayList<String> historyList;
    private JButton clearHistoryButton; // New button for clearing history

    public calculator() {
        // JFrame Setup
        setTitle("Calculator");
        setSize(550, 500); // Wider to accommodate history
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window
        setLayout(new BorderLayout());

        // History Panel Initialization
        historyList = new ArrayList<>();
        historyDisplay = new JTextArea();
        historyDisplay.setEditable(false);
        historyDisplay.setFont(new Font("Monospaced", Font.PLAIN, 14));
        historyDisplay.setLineWrap(true);
        historyDisplay.setWrapStyleWord(true);
        historyScrollPane = new JScrollPane(historyDisplay);
        historyScrollPane.setPreferredSize(new Dimension(150, 0)); // Initial width for history

        // Clear History Button Setup
        clearHistoryButton = new JButton("Clear History");
        clearHistoryButton.addActionListener(this);
        // Set a smaller font for the button
        clearHistoryButton.setFont(new Font("Arial", Font.PLAIN, 10));

        // Panel to hold history display and clear history button
        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.add(historyScrollPane, BorderLayout.CENTER);
        JPanel clearButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // To center the button
        clearButtonPanel.add(clearHistoryButton);
        historyPanel.add(clearButtonPanel, BorderLayout.SOUTH);


        // Display & Operation Label Setup
        operationLabel = new JLabel(" ");
        operationLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        operationLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        display = new JTextField("0"); // Starts with "0"
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(operationLabel);
        topPanel.add(display);

        // Button Grid Setup
        JPanel buttonGridPanel = new JPanel(new GridLayout(5, 4, 5, 5));

        // Define button layout, with '.' replacing '±'
        String[] buttonOrder = {
                "7", "8", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "*",
                "0", "%", "^", "/",
                "√", ".", "DEL", "C"
        };

        for (String label : buttonOrder) {
            JButton btn = new JButton(label);
            btn.addActionListener(this);
            buttonGridPanel.add(btn);
        }

        // Equals Button Setup
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton eqBtn = new JButton("=");
        eqBtn.setFont(new Font("Arial", Font.BOLD, 20));
        eqBtn.addActionListener(this);
        bottomPanel.add(eqBtn, BorderLayout.CENTER);

        // Assemble Main Calculator Panel
        JPanel calculatorPanel = new JPanel(new BorderLayout());
        calculatorPanel.add(topPanel, BorderLayout.NORTH);
        calculatorPanel.add(buttonGridPanel, BorderLayout.CENTER);
        calculatorPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Integrate History and Calculator with JSplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, historyPanel, calculatorPanel); // Use historyPanel here
        splitPane.setResizeWeight(0.0); // History panel retains its preferred size
        splitPane.setDividerLocation(150); // Initial divider position
        splitPane.setOneTouchExpandable(true); // Allows collapsing/expanding history

        add(splitPane, BorderLayout.CENTER);
        setVisible(true);
    }

    // Action Listener: Handles all Button Clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String currentDisplayText = display.getText();
        DecimalFormat df = new DecimalFormat("0.########"); // For consistent number formatting

        // Block input if "Error" is displayed, unless it's 'C', a digit, or decimal, or Clear History
        if (currentDisplayText.equals("Error") && !cmd.equals("C") && !cmd.matches("\\d") && !cmd.equals(".") && !cmd.equals("Clear History")) {
            return;
        }

        // Handle Clear History Button
        if (cmd.equals("Clear History")) {
            historyList.clear();
            updateHistoryDisplay();
            return; // Don't process other calculator logic
        }

        // Number Button Logic (0-9)
        if (cmd.matches("\\d")) {
            if (currentDisplayText.equals("Error") || startNewNumber) {
                // Handle new number input, including replacing "0" or "-0"
                if ((currentDisplayText.equals("0") || currentDisplayText.equals("-0")) && !cmd.equals("0")) {
                    display.setText(currentDisplayText.equals("-0") ? "-" + cmd : cmd);
                } else if (cmd.equals("0") && (currentDisplayText.equals("0") || currentDisplayText.equals("-0"))) {
                    // Do nothing if already "0" or "-0" and '0' is pressed
                } else {
                    display.setText(cmd); // Start new number
                }
                startNewNumber = false;
            } else {
                display.setText(currentDisplayText + cmd); // Append digit
            }
        }
        // Decimal Point Logic (".")
        else if (cmd.equals(".")) {
            if (currentDisplayText.equals("Error") || startNewNumber) {
                display.setText("0."); // Start with "0." for new numbers
                startNewNumber = false;
            } else if (!currentDisplayText.contains(".")) {
                display.setText(currentDisplayText + "."); // Add decimal if not already present
            }
        }
        // Clear Button Logic ("C")
        else if (cmd.equals("C")) {
            display.setText("0");
            operationLabel.setText("");
            operator = "";
            num1 = 0;
            startNewNumber = true;
            // Note: 'C' no longer clears history as "Clear History" button handles it
        }
        // Delete Button Logic ("DEL")
        else if (cmd.equals("DEL")) {
            if (currentDisplayText.equals("Error")) {
                display.setText("0");
                startNewNumber = true;
                return;
            }
            if (currentDisplayText.length() > 1 && !currentDisplayText.equals("-")) {
                display.setText(currentDisplayText.substring(0, currentDisplayText.length() - 1));
            } else {
                display.setText("0"); // Reset to "0" if last digit or just "-"
                startNewNumber = true;
            }
        }
        // Square Root Logic ("√")
        else if (cmd.equals("√")) {
            if (!currentDisplayText.isEmpty() && !currentDisplayText.equals("-") && !currentDisplayText.equals(".")) {
                double val = Double.parseDouble(currentDisplayText);
                if (val < 0) {
                    display.setText("Error");
                    operationLabel.setText("√(" + currentDisplayText + ")");
                    num1 = Double.NaN; // Indicate invalid state
                } else {
                    double result = Math.sqrt(val);
                    display.setText(df.format(result));
                    operationLabel.setText("√(" + currentDisplayText + ")");

                    // Add to history
                    historyList.add("√(" + df.format(val) + ") = " + df.format(result));
                    updateHistoryDisplay();

                    num1 = result; // Result becomes base for chaining
                }
                operator = ""; // Clear operator as it's an immediate calculation
                startNewNumber = true;
            }
        }
        // Equals Button Logic ("=")
        else if (cmd.equals("=")) {
            if (operator.isEmpty() || startNewNumber) {
                return; // Nothing to calculate
            }
            double num2 = Double.parseDouble(currentDisplayText);
            double result = performCalculation(num1, num2, operator);

            // Format and update displays
            String formattedNum1 = df.format(num1);
            String formattedNum2 = df.format(num2);
            String formattedResult = df.format(result);
            operationLabel.setText(formattedNum1 + " " + operator + " " + formattedNum2 + " =");
            display.setText(formattedResult);

            // Add to history
            historyList.add(formattedNum1 + " " + operator + " " + formattedNum2 + " = " + formattedResult);
            updateHistoryDisplay();

            num1 = result; // Result becomes num1 for potential next operations
            operator = "";
            startNewNumber = true;
        }
        // Operator Button Logic (+, -, *, /, %, ^)
        else {
            // Special handling for '-' to allow negative initial input (e.g., "-5")
            if (cmd.equals("-")) {
                if (startNewNumber && currentDisplayText.equals("0")) {
                    display.setText("-");
                    startNewNumber = false;
                    return;
                } else if (startNewNumber && currentDisplayText.equals("-")) {
                    return; // Prevent "--"
                }
            }

            // Handle chained operations (e.g., "5 + 3 -")
            if (!operator.isEmpty() && !startNewNumber) {
                double num2 = Double.parseDouble(currentDisplayText);
                double oldNum1 = num1;
                String oldOperator = operator;

                num1 = performCalculation(num1, num2, operator);

                // Add intermediate calculation to history
                historyList.add(df.format(oldNum1) + " " + oldOperator + " " + df.format(num2) + " = " + df.format(num1));
                updateHistoryDisplay();

                display.setText(df.format(num1)); // Show intermediate result
            } else {
                num1 = Double.parseDouble(currentDisplayText); // Set num1 for a new operation
            }

            operator = cmd; // Set the new operator
            operationLabel.setText(df.format(num1) + " " + operator);
            startNewNumber = true;
        }
    }

    // Helper Method: Performs the Calculation
    private double performCalculation(double n1, double n2, String op) {
        switch (op) {
            case "+": return n1 + n2;
            case "-": return n1 - n2;
            case "*": return n1 * n2;
            case "/":
                if (n2 == 0) {
                    display.setText("Error"); // Indicate error on display
                    return Double.NaN; // Return NaN for error state
                }
                return n1 / n2;
            case "%": return n1 % n2;
            case "^": return Math.pow(n1, n2);
            default: return 0;
        }
    }

    // Helper Method: Updates History Display
    private void updateHistoryDisplay() {
        historyDisplay.setText(""); // Clear current text
        for (String entry : historyList) {
            historyDisplay.append(entry + "\n"); // Append each entry
        }
        historyDisplay.setCaretPosition(historyDisplay.getDocument().getLength()); // Auto-scroll
    }

    // Main Method: Starts the Calculator
    public static void main(String[] args) {
        SwingUtilities.invokeLater(calculator::new); // Ensures GUI is created on the Event Dispatch Thread
    }
}