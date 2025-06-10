import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

// Main class that runs the application
public class LabActivity6SwingToDoList {
    public static void main(String[] args) {
        new ToDoListViewer(); // Create and show the to-do list window
    }
}

// Main GUI for viewing, adding, editing, and deleting tasks
class ToDoListViewer extends JFrame {
    JTable taskTable;
    DefaultTableModel tableModel;
    JButton addTaskButton, editTaskButton, deleteTaskButton;
    ToDoForm currentForm;

    public ToDoListViewer() {
        setTitle("To-Do List Viewer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Column names for the table
        String[] columns = {"Task Name", "Task Description", "Status"};
        
        // Set up table model (cells not editable directly)
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Set up the task table
        taskTable = new JTable(tableModel);
        taskTable.getTableHeader().setReorderingAllowed(false);
        taskTable.getTableHeader().setResizingAllowed(false);

        // Customize table selection behavior
        taskTable.setRowSelectionAllowed(true);
        taskTable.setColumnSelectionAllowed(false);
        taskTable.setCellSelectionEnabled(false);
        taskTable.setSelectionBackground(Color.CYAN);
        taskTable.setSelectionForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(taskTable);

        // Create buttons and add their actions
        addTaskButton = new JButton("Add Task");
        addTaskButton.addActionListener(e -> openForm(false));

        editTaskButton = new JButton("Edit Task");
        editTaskButton.addActionListener(e -> openForm(true));

        deleteTaskButton = new JButton("Delete Task");
        deleteTaskButton.addActionListener(e -> deleteTask());

        // Add buttons to the top panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(addTaskButton);
        topPanel.add(editTaskButton);
        topPanel.add(deleteTaskButton);

        // Add components to the main frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true); // Show the window
    }

    // Open form to add or edit a task
    public void openForm(boolean editMode) {
        if (editMode) {
            int selectedRow = taskTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a task to edit.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Prevent multiple forms from opening
            if (currentForm != null && currentForm.isDisplayable()) {
                currentForm.toFront();
                return;
            }

            // Get task data to edit
            String name = (String) tableModel.getValueAt(selectedRow, 0);
            String desc = (String) tableModel.getValueAt(selectedRow, 1);
            String status = (String) tableModel.getValueAt(selectedRow, 2);
            currentForm = new ToDoForm(this, selectedRow, name, desc, status);
        } else {
            // Add new task
            if (currentForm != null && currentForm.isDisplayable()) {
                currentForm.toFront();
            } else {
                currentForm = new ToDoForm(this);
            }
        }
    }

    // Delete the selected task
    private void deleteTask() {
        int selectedRow = taskTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a task to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete the selected task?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.removeRow(selectedRow);
        }
    }

    // Add a new task to the table
    public void addTask(String name, String desc, String status) {
        tableModel.addRow(new Object[]{name, desc, status});
    }

    // Update an existing task in the table
    public void updateTask(int row, String name, String desc, String status) {
        tableModel.setValueAt(name, row, 0);
        tableModel.setValueAt(desc, row, 1);
        tableModel.setValueAt(status, row, 2);
    }
}

// GUI Form for adding or editing tasks
class ToDoForm extends JFrame implements ActionListener {
    JTextField taskNameField;
    JTextArea taskDescArea;
    JScrollPane taskDescScrollPane;
    JComboBox<String> statusDropdown;
    JButton saveButton;
    ToDoListViewer viewerRef;
    boolean isEditMode;
    int editRowIndex;

    // Constructor for adding new task
    public ToDoForm(ToDoListViewer viewer) {
        this(viewer, -1, "", "", "Not Started");
    }

    // Constructor for editing existing task
    public ToDoForm(ToDoListViewer viewer, int rowIndex, String name, String desc, String status) {
        this.viewerRef = viewer;
        this.isEditMode = rowIndex != -1;
        this.editRowIndex = rowIndex;

        setTitle(isEditMode ? "Edit Task" : "Add New Task");
        setSize(400, 250);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent closing without cleanup

        Font commonFont = new Font("Arial", Font.PLAIN, 12);
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // General layout settings
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Task Name Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(1, 1, 1, 1);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0;
        JLabel nameLabel = new JLabel("Task Name:");
        nameLabel.setFont(commonFont);
        formPanel.add(nameLabel, gbc);

        // Task Name Field
        gbc.gridy = 1;
        gbc.insets = new Insets(1, 0, 5, 0);
        gbc.weightx = 1.0;
        taskNameField = new JTextField(name);
        taskNameField.setFont(commonFont);
        taskNameField.setPreferredSize(new Dimension(0, 28));
        formPanel.add(taskNameField, gbc);

        // Task Description Label
        gbc.gridy = 2;
        gbc.insets = new Insets(1, 1, 1, 1);
        gbc.weightx = 0;
        JLabel descLabel = new JLabel("Task Description:");
        descLabel.setFont(commonFont);
        formPanel.add(descLabel, gbc);

        // Task Description Area
        gbc.gridy = 3;
        gbc.insets = new Insets(1, 0, 5, 0);
        gbc.weighty = 0;
        taskDescArea = new JTextArea(desc);
        taskDescArea.setFont(commonFont);
        taskDescArea.setLineWrap(true);
        taskDescArea.setWrapStyleWord(true);
        taskDescArea.setRows(1);

        taskDescScrollPane = new JScrollPane(taskDescArea);
        taskDescScrollPane.setPreferredSize(new Dimension(0, 28));
        taskDescScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        formPanel.add(taskDescScrollPane, gbc);

        // Status Label
        gbc.gridy = 4;
        gbc.insets = new Insets(1, 1, 1, 1);
        gbc.weightx = 0;
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(commonFont);
        formPanel.add(statusLabel, gbc);

        // Status Dropdown
        gbc.gridy = 5;
        gbc.insets = new Insets(1, 0, 5, 0);
        gbc.weightx = 1.0;
        String[] statuses = {"Not Started", "Ongoing", "Completed"};
        statusDropdown = new JComboBox<>(statuses);
        statusDropdown.setSelectedItem(status);
        statusDropdown.setFont(commonFont);
        formPanel.add(statusDropdown, gbc);

        // Save Button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        saveButton = new JButton("Save Task");
        saveButton.setFont(commonFont);
        saveButton.addActionListener(this);
        buttonPanel.add(saveButton);

        // Add everything to the form
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Reset currentForm when form is closed
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                viewerRef.currentForm = null;
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                viewerRef.currentForm = null;
            }
        });

        setVisible(true); // Show the form
    }

    // Save button action
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = taskNameField.getText().trim();
        String desc = taskDescArea.getText().trim();
        String status = (String) statusDropdown.getSelectedItem();

        // Check if fields are filled
        if (!name.isEmpty() && !desc.isEmpty()) {
            if (isEditMode) {
                // Update existing task
                viewerRef.updateTask(editRowIndex, name, desc, status);
                dispose();
            } else {
                // Add new task
                viewerRef.addTask(name, desc, status);
                int option = JOptionPane.showConfirmDialog(this, "Task saved. Add another task?", "Continue", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    taskNameField.setText("");
                    taskDescArea.setText("");
                    statusDropdown.setSelectedIndex(0);
                } else {
                    dispose();
                }
            }
        } else {
            // Show error if any field is empty
            JOptionPane.showMessageDialog(this, "Please fill out all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
