import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Interface
interface Geometry {
    double area();
    double perimeter();
}

// Implementing Circle class
class Circles implements Geometry {
    private double radius;

    public Circles(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }
}

// Implementing Square class
class Squares implements Geometry {
    private double side;

    public Squares(double side) {
        this.side = side;
    }

    @Override
    public double area() {
        return side * side;
    }

    @Override
    public double perimeter() {
        return 4 * side;
    }
}

// GUI Class
public class InterfaceSwing extends JFrame {
    private JComboBox<String> shapeSelector;
    private JTextField inputField;
    private JLabel resultLabel;

    public InterfaceSwing() {
        setTitle("Interfaces - Shape Calculator");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 5, 5));

        // UI Components
        JLabel selectLabel = new JLabel("Select Shape:");
        shapeSelector = new JComboBox<>(new String[]{"Circle", "Square"});
        JLabel inputLabel = new JLabel("Enter Value:");
        inputField = new JTextField();
        JButton calculateButton = new JButton("Calculate");
        resultLabel = new JLabel("", SwingConstants.CENTER);

        // Button Action
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedShape = (String) shapeSelector.getSelectedItem();
                double value = Double.parseDouble(inputField.getText());
                Geometry shape;

                if (selectedShape.equals("Circle")) {
                    shape = new Circles(value);
                } else {
                    shape = new Squares(value);
                }

                resultLabel.setText("Area: " + shape.area() + " | Perimeter: " + shape.perimeter());
            }
        });

        // Add components to JFrame
        add(selectLabel);
        add(shapeSelector);
        add(inputLabel);
        add(inputField);
        add(new JLabel()); // Empty space
        add(calculateButton);
        add(resultLabel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new InterfaceSwing();
    }
}
