import javax.swing.*;
import java.awt.event.*;

// คลาสหลักสำหรับเก็บข้อมูลบุคคล
class Person {
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age;
    }
}

// คลาส Student สืบทอดจาก Person
class Student extends Person {
    private String studentID;

    public Student(String name, int age, String studentID) {
        super(name, age);
        this.studentID = studentID;
    }

    public String getStudentID() { return studentID; }
    public void setStudentID(String studentID) { this.studentID = studentID; }

    @Override
    public String toString() {
        return "Student Name: " + name + ", Age: " + age + ", ID: " + studentID;
    }
}

// คลาสสำหรับสร้าง GUI
public class StudentForm extends JFrame {
    private JTextField nameField, ageField, idField;
    private JButton submitButton, clearButton;
    private JLabel resultLabel;

    public StudentForm() {
        setTitle("Student Information");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Label และ TextField สำหรับรับชื่อ
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 100, 20);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(120, 20, 150, 20);
        add(nameField);

        // Label และ TextField สำหรับรับอายุ
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(20, 60, 100, 20);
        add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(120, 60, 150, 20);
        add(ageField);

        // Label และ TextField สำหรับรับรหัสนักศึกษา
        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setBounds(20, 100, 100, 20);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(120, 100, 150, 20);
        add(idField);

        // ปุ่ม Submit
        submitButton = new JButton("Submit");
        submitButton.setBounds(50, 150, 100, 30);
        add(submitButton);

        // ปุ่ม Clear
        clearButton = new JButton("Clear");
        clearButton.setBounds(180, 150, 100, 30);
        add(clearButton);

        // Label สำหรับแสดงผล
        resultLabel = new JLabel("");
        resultLabel.setBounds(20, 200, 300, 20);
        add(resultLabel);

        // Event Handling สำหรับปุ่ม Submit
        submitButton.addActionListener(new ActionListener() {
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
                resultLabel.setText(student.toString());
            }
        });

        // Event Handling สำหรับปุ่ม Clear
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                ageField.setText("");
                idField.setText("");
                resultLabel.setText("");
            }
        });
    }

    public static void main(String[] args) {
        new StudentForm().setVisible(true);
    }
}