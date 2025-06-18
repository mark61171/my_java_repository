# Java Laboratory Activities Compilation

This repository contains all laboratory activities developed for the subject **DCIT 23 – Object-Oriented Programming**. Each folder represents a separate lab exercise, showcasing various core concepts in Java including control structures, user input, GUI design, and more.

## Table of Contents

| Folder                      | Activity Description                                                            |
| --------------------------- | ------------------------------------------------------------------------------- |
| [lab1](lab1/)               | Java Basics – Employee salary system with simple input/output.                  |
| [lab2](lab2/)               | Advanced Employee Salary – Adds gross/net yearly computation and formatting.    |
| [lab3](lab3/)               | Conditional Logic – Salary and job system with validation and deductions.       |
| [lab4](lab4/)               | GUI AWT – Form-based employee info system with wage calculation.                |
| [lab5](lab5/)               | Quiz Application – AWT-based quiz with scoring and user validation.             |
| [lab6](lab6/)               | Swing To-Do List – Interactive task manager with add/edit/delete functionality. |
| [midtermExam](midtermExam/) | IT Ticketing System – Console-based status tracker for support tickets.         |
| [calculator](Calculator/)   | Scientific Calculator – Swing calculator with history and advanced operations.  |

---

## Activity Details

### lab1 – Employee Information System

This console-based program demonstrates basic Java syntax, user input handling with Scanner, and simple arithmetic operations. The user is prompted to enter employee details such as name, age, hours worked, and hourly wage. The program then calculates and displays the employee's daily salary along with the collected information.

This activity focuses on:

* String and numeric input handling
* Basic variable declaration and arithmetic computation
* Console output formatting using System.out.printf

### lab2 – Employee Information System (Part 2)

This enhanced version of the employee information system introduces more advanced calculations and formatting. In addition to basic input and daily salary computation, the program calculates weekly, monthly, gross yearly, and net yearly salaries. It also estimates the number of years until retirement and displays the employee’s full name in uppercase format.

This activity covers:

* Use of double for more precise salary calculations
* Basic financial operations (tax deduction and benefits)
* String manipulation with .toUpperCase()
* Use of Math.abs() to calculate retirement age
* Formatted and organized console output

### lab3 – Conditional Employee Information System

This lab introduces condition-based decision-making to the employee information system. It applies constraints such as age and work hours, and incorporates employee roles based on user input. The program performs salary calculations, determines retirement eligibility, and computes deductions based on annual income.

This activity demonstrates:

* Use of if-else statements for input validation (age restrictions, hour limits)
* switch statement for assigning job roles using a numeric code
* Ternary operator (? :) for conditional tax deductions
* String manipulation and formatted console output
* Practical use of System.exit(0) to handle invalid inputs early

💡 This activity focuses on logic control flow and real-world validation scenarios in payroll systems.

### lab4 – Employee Information System with GUI

This activity transitions the employee information system into a graphical user interface using AWT (Abstract Window Toolkit). The program collects user input via text fields, calculates the daily wage, and displays results in a read-only text area.

This activity demonstrates:

* Basic GUI layout management using Frame, Panel, Label, TextField, Button, and TextArea
* Event-driven programming with ActionListener
* Data validation for names, numeric input, and empty fields
* Error handling using try-catch blocks and conditional checks
* Clean output formatting and user-friendly feedback

💡 This activity introduces students to building interactive desktop applications in Java using AWT.

### lab5 – Quiz Application using AWT

This activity presents a simple multiple-choice quiz application using Java AWT components. It allows users to answer three computer-related questions, one at a time, and calculates the final score.

This activity demonstrates:

* GUI creation using Frame, Label, Checkbox, Panel, and Button
* Grouping radio-style choices using CheckboxGroup
* Event handling through ActionListener
* Dynamic question display and scoring system
* Dialog popups for user input validation
* Clean layout management and use of bounds for manual positioning

💡 This task enhances both logic structuring and GUI design skills by combining question-handling logic with user interaction in a desktop-based app.

### lab6 – To-Do List Manager using Swing

This lab focuses on building a task management system using Java Swing components. Users can add, edit, and delete tasks, each containing a name, description, and status.

Key features:

* GUI developed using JFrame, JTable, JButton, JTextArea, and JComboBox
* Non-editable task list using DefaultTableModel
* Pop-up dialogs for form validation and confirmation
* Clean layout separation: main viewer window and task form window
* Efficient task editing and prevention of multiple open forms

🗂️ This lab helps reinforce practical GUI techniques and task management logic, ideal for developing CRUD-based desktop applications.

### midtermExam – IT Ticketing System

A text-based console program that simulates an IT ticketing/helpdesk system. Users can create and update tickets, and view statuses. It reinforces object-oriented principles in a real-world simulation.

This activity demonstrates:

* Use of arrays to store ticket details (description, urgency, status)
* Input validation for strings and urgency levels
* Ticket status management (Pending, In Progress, Resolved)
* Conditional logic to restrict status updates
* Console-based report generation on ticket progress
* Emphasis on program flow control and user feedback

### calculator – Scientific Calculator with History

A modern-looking calculator made with Java Swing. It supports basic operations (addition, subtraction, multiplication, division) as well as advanced ones (square root, exponentiation, modulo). It also features a scrollable history panel, a “Clear History” button, and real-time expression labels above the display.

This activity includes:

* GUI design using JFrame, JPanel, JButton, JTextField, and JLabel
* Arithmetic logic implementation using event listeners
* Dynamic display updates and formatted results
* Input expression tracking and label display
* Scrollable history and clear functionality

---

## Technologies Used

* **Java SE** (Standard Edition)
* **AWT** and **Swing** for GUI development
* Basic file structure and Java conventions for maintainability

## How to Run the Projects

1. Open a terminal or command prompt.
2. Navigate to the folder of the activity you'd like to run.
3. Compile and run using the following commands:

```bash
javac FileName.java
java FileName
```
