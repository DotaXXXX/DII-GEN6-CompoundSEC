import javax.swing.*;
import java.awt.event.*;

// Interface พฤติกรรมการบิน
interface FlyBehavior {
    void fly();
}

class FlyWithWings implements FlyBehavior {
    public void fly() { JOptionPane.showMessageDialog(null, "I'm flying!!"); }
}

class FlyNoWay implements FlyBehavior {
    public void fly() { JOptionPane.showMessageDialog(null, "I can't fly!"); }
}

// Interface พฤติกรรมการร้อง
interface QuackBehavior {
    void quack();
}

class Quack implements QuackBehavior {
    public void quack() { JOptionPane.showMessageDialog(null, "Quack!!"); }
}

class MuteQuack implements QuackBehavior {
    public void quack() { JOptionPane.showMessageDialog(null, "<< Silence >>"); }
}

// คลาส Duck
class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public void setFlyBehavior(FlyBehavior fb) { flyBehavior = fb; }
    public void setQuackBehavior(QuackBehavior qb) { quackBehavior = qb; }
    public void performFly() { flyBehavior.fly(); }
    public void performQuack() { quackBehavior.quack(); }
}

// GUI สำหรับเลือกพฤติกรรมเป็ด
public class SimUDuckGUI extends JFrame {
    private JComboBox<String> flyOptions, quackOptions;
    private JButton setBehaviorButton, flyButton, quackButton;
    private Duck duck;

    public SimUDuckGUI() {
        setTitle("SimUDuck Strategy Pattern");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        duck = new Duck();

        String[] flyBehaviors = {"FlyWithWings", "FlyNoWay"};
        String[] quackBehaviors = {"Quack", "MuteQuack"};

        flyOptions = new JComboBox<>(flyBehaviors);
        flyOptions.setBounds(50, 30, 150, 25);
        add(flyOptions);

        quackOptions = new JComboBox<>(quackBehaviors);
        quackOptions.setBounds(50, 70, 150, 25);
        add(quackOptions);

        setBehaviorButton = new JButton("Set Behavior");
        setBehaviorButton.setBounds(220, 30, 130, 25);
        add(setBehaviorButton);

        flyButton = new JButton("Fly");
        flyButton.setBounds(50, 110, 100, 30);
        add(flyButton);

        quackButton = new JButton("Quack");
        quackButton.setBounds(170, 110, 100, 30);
        add(quackButton);

        setBehaviorButton.addActionListener(e -> {
            String flySelection = (String) flyOptions.getSelectedItem();
            String quackSelection = (String) quackOptions.getSelectedItem();

            if (flySelection.equals("FlyWithWings")) duck.setFlyBehavior(new FlyWithWings());
            else duck.setFlyBehavior(new FlyNoWay());

            if (quackSelection.equals("Quack")) duck.setQuackBehavior(new Quack());
            else duck.setQuackBehavior(new MuteQuack());

            JOptionPane.showMessageDialog(null, "Behavior Set Successfully!");
        });

        flyButton.addActionListener(e -> duck.performFly());
        quackButton.addActionListener(e -> duck.performQuack());
    }

    public static void main(String[] args) {
        new SimUDuckGUI().setVisible(true);
    }
}
