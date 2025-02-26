import javax.swing.*;

class AppConfig {
    private static AppConfig instance;
    private String setting;

    private AppConfig() {}

    public static AppConfig getInstance() {
        if (instance == null) instance = new AppConfig();
        return instance;
    }

    public void setSetting(String setting) { this.setting = setting; }
    public String getSetting() { return setting; }
}

// GUI
public class SingletonGUI extends JFrame {
    private JTextField settingField;
    private JButton setButton, getButton;
    private JLabel resultLabel;

    public SingletonGUI() {
        setTitle("Singleton Pattern");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        settingField = new JTextField();
        settingField.setBounds(50, 30, 150, 20);
        add(settingField);

        setButton = new JButton("Set");
        setButton.setBounds(50, 60, 80, 25);
        add(setButton);

        getButton = new JButton("Get");
        getButton.setBounds(140, 60, 80, 25);
        add(getButton);

        resultLabel = new JLabel("");
        resultLabel.setBounds(50, 100, 200, 20);
        add(resultLabel);

        setButton.addActionListener(e -> AppConfig.getInstance().setSetting(settingField.getText()));
        getButton.addActionListener(e -> resultLabel.setText(AppConfig.getInstance().getSetting()));
    }

    public static void main(String[] args) {
        new SingletonGUI().setVisible(true);
    }
}
