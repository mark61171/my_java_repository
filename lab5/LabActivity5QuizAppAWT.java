import java.awt.*;
import java.awt.event.*;

public class LabActivity5QuizAppAWT extends Frame implements ActionListener {

    // Questions
    String[] questions = {
        "What does CPU stand for?",
        "Which language is platform-independent?",
        "What does RAM stand for?"
    };

    // Choices for each question
    String[][] options = {
        {"Central Process Unit", "Central Processing Unit", "Computer Personal Unit", "Central Processor Unit"},
        {"Python", "C++", "Java", "Assembly"},
        {"Random Access Memory", "Read Access Memory", "Run Access Memory", "Random Actual Memory"}
    };

    // Correct answers (index)
    int[] correctAnswers = {1, 2, 0};

    Label questionLabel;          
    CheckboxGroup choicesGroup;   
    Checkbox[] choiceButtons = new Checkbox[4]; 
    Button nextButton;            
    Panel choicesPanel;           

    int currentQuestion = 0;      
    int score = 0;                

    public LabActivity5QuizAppAWT() {
        setTitle("Quiz App");
        setSize(500, 300);
        setLayout(null);  // using manual layout

        // Question text
        questionLabel = new Label();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setBounds(50, 30, 400, 30);
        add(questionLabel);

        // Panel for choices
        choicesPanel = new Panel();
        choicesPanel.setLayout(new GridLayout(2, 2, 5, 5));
        choicesPanel.setBounds(50, 70, 400, 130);
        add(choicesPanel);

        choicesGroup = new CheckboxGroup();  // only pick one

        // Radio buttons
        for (int i = 0; i < 4; i++) {
            choiceButtons[i] = new Checkbox("", choicesGroup, false);
            choiceButtons[i].setBackground(Color.WHITE);
            choicesPanel.add(choiceButtons[i]);
        }

        // Button to go next
        nextButton = new Button("Next");
        nextButton.setBounds(200, 220, 100, 30);
        nextButton.setFont(new Font("Arial", Font.PLAIN, 14));
        nextButton.setBackground(Color.CYAN);
        nextButton.addActionListener(this);
        add(nextButton);

        // Show first question
        showQuestion(currentQuestion);

        // Close window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    // Show question and choices
    void showQuestion(int index) {
        questionLabel.setText("Q" + (index + 1) + ". " + questions[index]);
        for (int i = 0; i < 4; i++) {
            choiceButtons[i].setLabel(options[index][i]);
            choiceButtons[i].setEnabled(true);
        }
        choicesGroup.setSelectedCheckbox(null);  // reset choice
        nextButton.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Checkbox selected = choicesGroup.getSelectedCheckbox();

        // If nothing is selected, show a popup
        if (selected == null) {
            Dialog dialog = new Dialog(this, "Error", true);
            Label msg = new Label("Please select an answer before proceeding.", Label.CENTER);
            Button ok = new Button("OK");

            ok.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose(); // Closes the popup
                }
            });

            dialog.setLayout(new BorderLayout());
            dialog.add(msg, BorderLayout.CENTER);
            dialog.add(ok, BorderLayout.SOUTH);
            dialog.setSize(300, 150);
            dialog.setLocationRelativeTo(this);

            // Handles "X" button on the dialog
            dialog.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    dialog.dispose();
                }
            });

            dialog.setVisible(true);
            return;
        }

        // Check selected answer
        int selectedIndex = -1;
        for (int i = 0; i < 4; i++) {
            if (choiceButtons[i] == selected) {
                selectedIndex = i;
                break;
            }
        }

        // If answer is correct
        if (selectedIndex == correctAnswers[currentQuestion]) {
            score++;
        }

        currentQuestion++;

        // If quiz is done
        if (currentQuestion == questions.length) {
            questionLabel.setText("Quiz Completed! You got " + score + " out of " + questions.length + " correct.");
            for (int i = 0; i < 4; i++) {
                choiceButtons[i].setEnabled(false);
            }
            nextButton.setEnabled(false);
        } else {
            showQuestion(currentQuestion);
        }
    }

    public static void main(String[] args) {
        new LabActivity5QuizAppAWT();
    }
}
