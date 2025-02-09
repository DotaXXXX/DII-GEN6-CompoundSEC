//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//// Abstract Class (Abstraction)
//abstract class Shape {
//    abstract double area();
//    abstract double perimeter();
//}
//
//// Subclass: Circle
//class Circle extends Shape {
//    private double radius;
//
//    public Circle(double radius) {
//        this.radius = radius;
//    }
//
//    @Override
//    double area() {
//        return Math.PI * radius * radius;
//    }
//
//    @Override
//    double perimeter() {
//        return 2 * Math.PI * radius;
//    }
//}
//
//// Subclass: Square
//class Square extends Shape {
//    private double side;
//
//    public Square(double side) {
//        this.side = side;
//    }
//
//    @Override
//    double area() {
//        return side * side;
//    }
//
//    @Override
//    double perimeter() {
//        return 4 * side;
//    }
//}
//
//// GUI Class
//public class AbstractionSwing extends JFrame {
//    private JComboBox<String> shapeSelector;
//    private JTextField inputField;
//    private JLabel resultLabel;
//
//    public AbstractionSwing() {
//        setTitle("Abstraction - Shape Calculator");
//        setSize(300, 200);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new GridLayout(4, 2, 5, 5));
//
//        // UI Components
//        JLabel selectLabel = new JLabel("Select Shape:");
//        shapeSelector = new JComboBox<>(new String[]{"Circle", "Square"});
//        JLabel inputLabel = new JLabel("Enter Value:");
//        inputField = new JTextField();
//        JButton calculateButton = new JButton("Calculate");
//        resultLabel = new JLabel("", SwingConstants.CENTER);
//
//        // Button Action
//        calculateButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String selectedShape = (String) shapeSelector.getSelectedItem();
//                double value = Double.parseDouble(inputField.getText());
//                Shape shape;
//
//                if (selectedShape.equals("Circle")) {
//                    shape = new Circles(value);
//                } else {
//                    shape = new Squares(value);
//                }
//
//                resultLabel.setText("Area: " + shape.area() + " | Perimeter: " + shape.perimeter());
//            }
//        });
//
//        // Add components to JFrame
//        add(selectLabel);
//        add(shapeSelector);
//        add(inputLabel);
//        add(inputField);
//        add(new JLabel()); // Empty space
//        add(calculateButton);
//        add(resultLabel);
//
//        setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        new AbstractionSwing();
//    }
//}
//
