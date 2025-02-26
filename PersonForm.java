//import javax.swing.*;
//import java.awt.event.*;
//
//// คลาสหลักสำหรับเก็บข้อมูลบุคคล
//class Person {
//    private String name;
//    private int age;
//
//    public Person(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
//
//    public String getName() { return name; }
//    public int getAge() { return age; }
//
//    public void setName(String name) { this.name = name; }
//    public void setAge(int age) { this.age = age; }
//
//    @Override
//    public String toString() {
//        return "Name: " + name + ", Age: " + age;
//    }
//}
//
//// คลาสสำหรับสร้าง GUI
//public class PersonForm extends JFrame {
//    private JTextField nameField, ageField;
//    private JButton submitButton, clearButton;
//    private JLabel resultLabel;
//
//    public PersonForm() {
//        setTitle("Person Information");
//        setSize(350, 250);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(null);
//
//        // Label และ TextField สำหรับรับชื่อ
//        JLabel nameLabel = new JLabel("Name:");
//        nameLabel.setBounds(20, 20, 100, 20);
//        add(nameLabel);
//
//        nameField = new JTextField();
//        nameField.setBounds(120, 20, 150, 20);
//        add(nameField);
//
//        // Label และ TextField สำหรับรับอายุ
//        JLabel ageLabel = new JLabel("Age:");
//        ageLabel.setBounds(20, 60, 100, 20);
//        add(ageLabel);
//
//        ageField = new JTextField();
//        ageField.setBounds(120, 60, 150, 20);
//        add(ageField);
//
//        // ปุ่ม Submit
//        submitButton = new JButton("Submit");
//        submitButton.setBounds(50, 100, 100, 30);
//        add(submitButton);
//
//        // ปุ่ม Clear
//        clearButton = new JButton("Clear");
//        clearButton.setBounds(180, 100, 100, 30);
//        add(clearButton);
//
//        // Label สำหรับแสดงผล
//        resultLabel = new JLabel("");
//        resultLabel.setBounds(20, 150, 300, 20);
//        add(resultLabel);
//
//        // Event Handling สำหรับปุ่ม Submit
//        submitButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String name = nameField.getText();
//                int age;
//                try {
//                    age = Integer.parseInt(ageField.getText());
//                } catch (NumberFormatException ex) {
//                    JOptionPane.showMessageDialog(null, "Please enter a valid age.", "Error", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//
//                Person person = new Person(name, age);
//                resultLabel.setText(person.toString());
//            }
//        });
//
//        // Event Handling สำหรับปุ่ม Clear
//        clearButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                nameField.setText("");
//                ageField.setText("");
//                resultLabel.setText("");
//            }
//        });
//    }
//
//    public static void main(String[] args) {
//        new PersonForm().setVisible(true);
//    }
//}
