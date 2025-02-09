import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// คลาสที่ซ่อนข้อมูล (Data Hiding)
class Mobile {
    private String username = "CAMT";
    private String password = "DII";
    private String privateCode = "1234 Hello DII";

    // ฟังก์ชันตรวจสอบ username และ password
    public String getPrivateCode(String inputUser, String inputPass) {
        if (inputUser.equals(this.username) && inputPass.equals(this.password)) {
            return "Your Private Code: " + privateCode;
        }
        return "Wrong username or password!";
    }
}

// คลาส GUI
public class DataHidingSwing extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private JLabel resultLabel;

    public DataHidingSwing() {
        setTitle("Data Hiding - Login System");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 5, 5));

        // สร้าง UI Components
        JLabel userLabel = new JLabel("Username:");
        userField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        passField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        resultLabel = new JLabel("", SwingConstants.CENTER);

        // เพิ่ม ActionListener ให้ปุ่ม
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mobile myMobile = new Mobile();
                String username = userField.getText();
                String password = new String(passField.getPassword());
                resultLabel.setText(myMobile.getPrivateCode(username, password));
            }
        });

        // เพิ่ม Components ลงใน JFrame
        add(userLabel);
        add(userField);
        add(passLabel);
        add(passField);
        add(new JLabel()); // ช่องว่าง
        add(loginButton);
        add(resultLabel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new DataHidingSwing();
    }
}
