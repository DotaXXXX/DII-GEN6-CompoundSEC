import javax.swing.*;
import java.awt.event.*;

// คลาส Beverage (Super Class)
abstract class Beverage {
    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double getCost();
}

// คลาสเครื่องดื่มหลัก
class Espresso extends Beverage {
    public Espresso() {
        description = "Espresso";
    }

    public double getCost() {
        return 1.99;
    }
}

class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "House Blend Coffee";
    }

    public double getCost() {
        return 0.89;
    }
}

// คลาสตกแต่ง (Decorator)
abstract class CondimentDecorator extends Beverage {
    public abstract String getDescription();
}

// เครื่องปรุง Mocha
class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    public double getCost() {
        return 0.20 + beverage.getCost();
    }
}

// เครื่องปรุง Whip
class Whip extends CondimentDecorator {
    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }

    public double getCost() {
        return 0.30 + beverage.getCost();
    }
}

// GUI สำหรับเลือกเครื่องดื่มและคำนวณราคา
public class StarbuzzCoffee extends JFrame {
    private JComboBox<String> coffeeOptions;
    private JCheckBox mochaCheckBox, whipCheckBox;
    private JButton calculateButton;
    private JLabel resultLabel;

    public StarbuzzCoffee() {
        setTitle("Starbuzz Coffee");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel coffeeLabel = new JLabel("Select Coffee:");
        coffeeLabel.setBounds(20, 20, 150, 20);
        add(coffeeLabel);

        String[] coffeeTypes = {"Espresso", "House Blend"};
        coffeeOptions = new JComboBox<>(coffeeTypes);
        coffeeOptions.setBounds(150, 20, 150, 20);
        add(coffeeOptions);

        mochaCheckBox = new JCheckBox("Mocha (+$0.20)");
        mochaCheckBox.setBounds(50, 60, 150, 20);
        add(mochaCheckBox);

        whipCheckBox = new JCheckBox("Whip (+$0.30)");
        whipCheckBox.setBounds(50, 90, 150, 20);
        add(whipCheckBox);

        calculateButton = new JButton("Calculate Price");
        calculateButton.setBounds(50, 130, 150, 30);
        add(calculateButton);

        resultLabel = new JLabel("Total Price: $0.00");
        resultLabel.setBounds(50, 170, 300, 20);
        add(resultLabel);

        // Event Handling
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Beverage beverage;
                if (coffeeOptions.getSelectedItem().equals("Espresso")) {
                    beverage = new Espresso();
                } else {
                    beverage = new HouseBlend();
                }

                if (mochaCheckBox.isSelected()) {
                    beverage = new Mocha(beverage);
                }
                if (whipCheckBox.isSelected()) {
                    beverage = new Whip(beverage);
                }

                resultLabel.setText("Total Price: $" + String.format("%.2f", beverage.getCost()));
            }
        });
    }

    public static void main(String[] args) {
        new StarbuzzCoffee().setVisible(true);
    }
}
