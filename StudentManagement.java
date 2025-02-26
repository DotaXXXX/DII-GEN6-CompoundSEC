import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

// คลาส Student สำหรับเก็บข้อมูลนักศึกษา
class Student {
    private String name;
    private int age;
    private String studentID;

    public Student(String name, int age, String studentID) {
        this.name = name;
        this.age = age;
        this.studentID = studentID;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getStudentID() { return studentID; }
}

// คลาส GUI สำหรับจัดการนักศึกษา
public class StudentManagement extends JFrame {
    private JTextField nameField, ageField, idField;
    private JButton addButton, deleteButton, clearButton;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private ArrayList<Student> students;

    public StudentManagement() {
        setTitle("Student Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        students = new ArrayList<>();

        // Label และ TextField สำหรับรับค่าชื่อ
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 100, 20);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(120, 20, 150, 20);
        add(nameField);

        // Label และ TextField สำหรับรับอายุ
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(20, 50, 100, 20);
        add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(120, 50, 150, 20);
        add(ageField);

        // Label และ TextField สำหรับรับรหัสนักศึกษา
        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setBounds(20, 80, 100, 20);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(120, 80, 150, 20);
        add(idField);

        // ปุ่มเพิ่มข้อมูล
        addButton = new JButton("Add");
        addButton.setBounds(300, 20, 100, 30);
        add(addButton);

        // ปุ่มลบข้อมูลที่เลือก
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(300, 55, 100, 30);
        add(deleteButton);

        // ปุ่มล้างฟอร์ม
        clearButton = new JButton("Clear");
        clearButton.setBounds(300, 90, 100, 30);
        add(clearButton);

        // ตารางแสดงรายชื่อนักศึกษา
        String[] columnNames = {"Name", "Age", "Student ID"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBounds(20, 150, 450, 200);
        add(scrollPane);

        // Event Handling สำหรับปุ่ม Add
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String studentID = idField.getText();
                int age;

                try {
                    age = Integer.parseInt(ageField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid age.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Student student = new Student(name, age, studentID);
                students.add(student);

                tableModel.addRow(new Object[]{student.getName(), student.getAge(), student.getStudentID()});

                // ล้างข้อมูลหลังจากเพิ่ม
                nameField.setText("");
                ageField.setText("");
                idField.setText("");
            }
        });

        // Event Handling สำหรับปุ่ม Delete
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow >= 0) {
                    students.remove(selectedRow);
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Event Handling สำหรับปุ่ม Clear
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                ageField.setText("");
                idField.setText("");
            }
        });
    }

    public static void main(String[] args) {
        new StudentManagement().setVisible(true);
    }
}
