# Java Lab & Midterm Projects

A collection of Java console and GUI programs created for lab assignments and a midterm exam.

## 🧑‍🏫 Author
*Mark Anthony P. Ogena* – *BSCS-1A*

---

## 📚 Contents

1. [Lab 1 – Employee Information System](#lab-1--employee-information-system)  
2. [Lab 2 – Employee Information System (Part 2)](#lab-2--employee-information-system-part-2)  
3. [Lab 3 – Conditional Statements & Role Selection](#lab-3--conditional-statements--role-selection)  
4. [Lab 4 – Employee Info GUI (AWT)](#lab-4--employee-info-gui-awt)  
5. [Lab 5 – Quiz App (AWT GUI)](#lab-5--quiz-app-awt-gui)  
6. [Lab 6 – To‑Do List App (Swing GUI)](#lab-6--to‑do-list-app-swing-gui)  
7. [Midterm – IT Ticket Processing System](#midterm--it-ticket-processing-system)

---

### Lab 1 – Employee Information System

**File:** `lab1/LabActivity1EmployeeInformationSystem.java`  
A console app that:
- Collects first/last name, age, hours worked, hourly wage
- Calculates and displays daily salary in PHP

---

### Lab 2 – Employee Information System (Part 2)

**File:** `lab2/LabActivity2EmployeeInformationSystemPart2.java`  
Enhances Lab 1 with:
- Daily, weekly, monthly, gross yearly, and net yearly salary
- Net salary after 32% tax + benefits deduction
- Computes years to retirement (age 65)
- Formats full name in uppercase

---

### Lab 3 – Conditional Statements & Role Selection

**File:** `lab2/LabActivity3ConditionalStatement.java`  
Adds to Lab 2 with:
- Age (18–64) and hours (1–24) validation
- Role selection via code: Manager, Supervisor, Staff, Intern
- Conditional tax for yearly income > PHP 250,000

---

### Lab 4 – Employee Info GUI (AWT)

**File:** `lab4/LabActivity4EmpInfoSystemGUI.java`  
A graphical AWT version that:
- Uses Labels, TextFields, Buttons, TextArea
- Input validation: non-empty, letter-only names, age integer, numeric hours/rates
- Calculates and displays daily wage

---

### Lab 5 – Quiz App (AWT GUI)

**File:** `lab5/LabActivity5QuizAppAWT.java`  
Features:
- Multiple-choice quiz with 3 questions and 4 options
- Uses `CheckboxGroup` for single choice
- Validates selection per question
- Shows final score and disables further interaction

---

### Lab 6 – To‑Do List App (Swing GUI)

**File:** `lab6/LabActivity6SwingToDoList.java`  
A full to‑do list manager that:
- Displays tasks in a `JTable`
- Add/Edit/Delete tasks via separate form window
- Each task has name, description, status
- Form field validation and deletion confirmation
- Prevents opening duplicate forms

---

### Midterm – IT Ticket Processing System

**File:** `midtermExam/MyMidtermLabExam.java`  
Console-based IT ticket system that allows:
- Adding up to 5 tickets (description + urgency)
- Input validation for description and urgency
- Updating ticket status (“In Progress” or “Resolved”)
- Viewing all tickets with status and urgency
- Generating a report on pending vs. resolved tickets

---

## 🛠️ How to Compile & Run

```bash
# Example: compile Lab 1
javac lab1/LabActivity1EmployeeInformationSystem.java
java lab1.LabActivity1EmployeeInformationSystem

# For GUI apps, include package paths:
javac lab4/LabActivity4EmpInfoSystemGUI.java
java lab4.LabActivity4EmpInfoSystemGUI
# Similarly for lab5, lab6, and midtermExam packages
