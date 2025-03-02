import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

// ─────────────────────────────────────────────────────────────────────────────
// (A) เพิ่มคลาส LogSystem ให้รองรับการบันทึกลงไฟล์ "logs.txt"
class LogSystem {
    private static StringBuilder logs = new StringBuilder();
    private static final String LOG_FILE = "logs.txt"; // ไฟล์ที่เราจะเก็บ Log

    // static block: จะเรียกทุกครั้งที่คลาสนี้ถูกโหลดครั้งแรก
    static {
        loadLogFromFile();
    }

    // เมื่อมีเหตุการณ์เกิดขึ้น -> เรียก add(...)
    public static void add(String message) {
        // ติด timestamp
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String line = "[" + time + "] " + message;

        logs.append(line).append("\n");
        // บันทึกลงไฟล์ทุกครั้ง
        saveLogToFile();
    }

    // คืนค่าข้อความ Log ทั้งหมด
    public static String getAllLogs() {
        return logs.toString();
    }

    // โหลด log จากไฟล์ logs.txt (ถ้ามี)
    private static void loadLogFromFile() {
        File f = new File(LOG_FILE);
        if (!f.exists()) {
            return; // ยังไม่เคยมีไฟล์ log
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                logs.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // บันทึก log ทั้งหมดลงไฟล์ logs.txt
    private static void saveLogToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(LOG_FILE))) {
            pw.print(logs.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
public class HotelBookingSystem {
    public static void main(String[] args) {
        CSVUtils.loadRoomsFromCSV(SharedData.roomsModel, "rooms.csv");
        SwingUtilities.invokeLater(UserSelectionPage::new);
    }
}

// ─────────────────────────────────────────────────────────────────────────────
class SharedData {
    public static DefaultTableModel roomsModel = new DefaultTableModel(
            new Object[]{"Room ID", "Type", "Price", "Status"}, 0
    );
    public static DefaultTableModel bookingsModel = new DefaultTableModel(
            new Object[]{"Booking ID", "Customer", "Room", "Check-in", "Check-out", "Status"}, 0
    );
    public static DefaultTableModel customersModel = new DefaultTableModel(
            new Object[]{"Customer ID", "Name", "Phone", "Email", "Room"}, 0
    );
}

// ─────────────────────────────────────────────────────────────────────────────
class CSVUtils {
    public static void loadRoomsFromCSV(DefaultTableModel model, String fileName) {
        model.setRowCount(0);
        File f = new File(fileName);
        if (!f.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    model.addRow(new Object[]{parts[0], parts[1], parts[2], parts[3]});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveRoomsToCSV(DefaultTableModel model, String fileName) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            int rowCount = model.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                String roomId = (String) model.getValueAt(i, 0);
                String type   = (String) model.getValueAt(i, 1);
                String price  = (String) model.getValueAt(i, 2);
                String status = (String) model.getValueAt(i, 3);
                pw.println(roomId + "," + type + "," + price + "," + status);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
class UserSelectionPage extends JFrame {
    public UserSelectionPage() {
        setTitle("The kk");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("The kk", SwingConstants.CENTER);
        titleLabel.setBounds(100, 20, 200, 30);
        add(titleLabel);

        JButton userButton = new JButton("User");
        userButton.setBounds(140, 70, 120, 40);

        JButton adminButton = new JButton("Admin");
        adminButton.setBounds(140, 120, 120, 40);

        JButton cleanerButton = new JButton("Cleaner");
        cleanerButton.setBounds(140, 170, 120, 40);
        cleanerButton.addActionListener(e -> {
            new CleanerOptionPage();
            dispose();
        });
        add(cleanerButton);

        userButton.addActionListener(e -> {
            new UserOptionPage();
            dispose();
        });

        adminButton.addActionListener(e -> {
            new AdminOptionPage();
            dispose();
        });

        add(userButton);
        add(adminButton);
        setVisible(true);
    }
}

// ─────────────────────────────────────────────────────────────────────────────
class CleanerOptionPage extends JFrame {
    public CleanerOptionPage() {
        setTitle("The kk - Cleaner");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("The kk (Cleaner)", SwingConstants.CENTER);
        titleLabel.setBounds(100, 20, 200, 30);
        add(titleLabel);

        JButton loginButton = new JButton("Log in");
        loginButton.setBounds(140, 70, 120, 40);

        JButton backButton = new JButton("← Back");
        backButton.setBounds(10, 10, 80, 30);
        backButton.addActionListener(e -> {
            new UserSelectionPage();
            dispose();
        });
        add(backButton);

        loginButton.addActionListener(e -> {
            new CleanerLoginPage();
            dispose();
        });
        add(loginButton);

        setVisible(true);
    }
}

// ─────────────────────────────────────────────────────────────────────────────
class CleanerLoginPage extends JFrame {
    public CleanerLoginPage() {
        setTitle("The kk - Cleaner Log in");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Log in (Cleaner)", SwingConstants.CENTER);
        titleLabel.setBounds(100, 20, 200, 30);
        add(titleLabel);

        JLabel userLabel = new JLabel("Username : ");
        userLabel.setBounds(50, 80, 100, 25);
        JTextField userField = new JTextField();
        userField.setBounds(160, 80, 150, 25);

        JLabel passLabel = new JLabel("Password : ");
        passLabel.setBounds(50, 120, 100, 25);
        JPasswordField passField = new JPasswordField();
        passField.setBounds(160, 120, 150, 25);

        JButton loginButton = new JButton("Log in");
        loginButton.setBounds(150, 170, 100, 30);
        add(loginButton);

        JButton backButton = new JButton("← Back");
        backButton.setBounds(10, 10, 80, 30);
        backButton.addActionListener(e -> {
            new CleanerOptionPage();
            dispose();
        });
        add(backButton);

        add(userLabel);
        add(userField);
        add(passLabel);
        add(passField);

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            if (username.equals("cleaner") && password.equals("c123")) {
                // บันทึก log
                LogSystem.add("Cleaner logged in: " + username);
                new CleanerPanel();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Username หรือ Password ไม่ถูกต้อง!",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}

// ─────────────────────────────────────────────────────────────────────────────
class CleanerPanel extends JFrame {
    public CleanerPanel() {
        setTitle("Cleaner Panel");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        DefaultTableModel model = SharedData.roomsModel;
        JTable table = new JTable(model);

        JButton markCleanedBtn = new JButton("Mark as Cleaned");
        JButton logoutButton = new JButton("Logout");

        markCleanedBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this,
                        "กรุณาเลือกห้องก่อน",
                        "No Room Selected",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            String status = (String) model.getValueAt(row, 3);
            // ดึง roomId มาแสดงใน log
            String roomId = (String) model.getValueAt(row, 0);

            if ("Dirty".equalsIgnoreCase(status) || "จองแล้ว".equalsIgnoreCase(status)) {
                int choice = JOptionPane.showConfirmDialog(
                        this,
                        "ห้องนี้สถานะ '" + status + "' ต้องการทำความสะอาดหรือไม่?",
                        "Confirm Cleaning",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (choice == JOptionPane.YES_OPTION) {
                    model.setValueAt("Available", row, 3);
                    JOptionPane.showMessageDialog(this,
                            "ทำความสะอาดเสร็จเรียบร้อย! สถานะห้องเป็น Available",
                            "Cleaned",
                            JOptionPane.INFORMATION_MESSAGE);
                    CSVUtils.saveRoomsToCSV(SharedData.roomsModel, "rooms.csv");

                    // บันทึก log
                    LogSystem.add("Cleaner cleaned room ID=" + roomId);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "สถานะห้องไม่ต้องทำความสะอาด",
                        "Not Dirty",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        logoutButton.addActionListener(e -> {
            // log
            LogSystem.add("Cleaner logged out.");
            new UserSelectionPage();
            dispose();
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(markCleanedBtn);
        bottomPanel.add(logoutButton);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}

// ─────────────────────────────────────────────────────────────────────────────
class UserOptionPage extends JFrame {
    public UserOptionPage() {
        setTitle("The kk - User");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("The kk (User)", SwingConstants.CENTER);
        titleLabel.setBounds(100, 20, 200, 30);
        add(titleLabel);

        JButton signUpButton = new JButton("Sign up");
        signUpButton.setBounds(140, 70, 120, 40);

        JButton loginButton = new JButton("Log in");
        loginButton.setBounds(140, 120, 120, 40);

        JButton backButton = new JButton("← Back");
        backButton.setBounds(10, 10, 80, 30);
        backButton.addActionListener(e -> {
            new UserSelectionPage();
            dispose();
        });

        signUpButton.addActionListener(e -> {
            new UserSignUpPage();
            dispose();
        });
        loginButton.addActionListener(e -> {
            new UserLoginPage();
            dispose();
        });

        add(signUpButton);
        add(loginButton);
        add(backButton);
        setVisible(true);
    }
}

// ─────────────────────────────────────────────────────────────────────────────
class UserSignUpPage extends JFrame {
    public UserSignUpPage() {
        setTitle("The kk - User Sign up");
        setSize(400, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Sign up", SwingConstants.CENTER);
        titleLabel.setBounds(100, 20, 200, 30);
        add(titleLabel);

        JLabel userLabel = new JLabel("Username : ");
        userLabel.setBounds(50, 80, 100, 25);
        JTextField userField = new JTextField();
        userField.setBounds(160, 80, 150, 25);

        JLabel passLabel1 = new JLabel("Create password : ");
        passLabel1.setBounds(50, 120, 110, 25);
        JPasswordField passField1 = new JPasswordField();
        passField1.setBounds(160, 120, 150, 25);

        JLabel passLabel2 = new JLabel("Confirm password : ");
        passLabel2.setBounds(50, 160, 110, 25);
        JPasswordField passField2 = new JPasswordField();
        passField2.setBounds(160, 160, 150, 25);

        JButton accessButton = new JButton("Access");
        accessButton.setBounds(150, 210, 100, 30);

        JButton backButton = new JButton("← Back");
        backButton.setBounds(10, 10, 80, 30);

        add(userLabel);
        add(userField);
        add(passLabel1);
        add(passField1);
        add(passLabel2);
        add(passField2);
        add(accessButton);
        add(backButton);

        accessButton.addActionListener(e -> {
            String username = userField.getText();
            String pass1 = new String(passField1.getPassword());
            String pass2 = new String(passField2.getPassword());

            if (!pass1.equals(pass2)) {
                JOptionPane.showMessageDialog(this,
                        "รหัสผ่านไม่ตรงกัน!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Sign up สำเร็จ (ยังไม่บันทึกจริง)",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                // log
                LogSystem.add("User signed up: " + username);
                new UserLoginPage();
                dispose();
            }
        });

        backButton.addActionListener(e -> {
            new UserOptionPage();
            dispose();
        });

        setVisible(true);
    }
}

// ─────────────────────────────────────────────────────────────────────────────
class UserLoginPage extends JFrame {
    public UserLoginPage() {
        setTitle("The kk - User Log in");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Log in", SwingConstants.CENTER);
        titleLabel.setBounds(100, 20, 200, 30);
        add(titleLabel);

        JLabel userLabel = new JLabel("Username : ");
        userLabel.setBounds(50, 80, 100, 25);
        JTextField userField = new JTextField();
        userField.setBounds(160, 80, 150, 25);

        JLabel passLabel = new JLabel("Password : ");
        passLabel.setBounds(50, 120, 100, 25);
        JPasswordField passField = new JPasswordField();
        passField.setBounds(160, 120, 150, 25);

        JButton loginButton = new JButton("Log in");
        loginButton.setBounds(150, 170, 100, 30);

        JButton backButton = new JButton("← Back");
        backButton.setBounds(10, 10, 80, 30);

        add(userLabel);
        add(userField);
        add(passLabel);
        add(passField);
        add(loginButton);
        add(backButton);

        loginButton.addActionListener(e -> {
            // log
            LogSystem.add("User logged in: " + userField.getText());
            new UserHomepage();
            dispose();
        });

        backButton.addActionListener(e -> {
            new UserOptionPage();
            dispose();
        });

        setVisible(true);
    }
}

// ─────────────────────────────────────────────────────────────────────────────
class UserHomepage extends JFrame {
    public UserHomepage() {
        setTitle("User Homepage");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        DefaultTableModel roomModel = SharedData.roomsModel;
        JTable table = new JTable(roomModel);

        JButton reserveButton = new JButton("reserve");
        JButton backButton = new JButton("Back");
        JButton logoutButton = new JButton("Log out");

        reserveButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(
                        this,
                        "กรุณาเลือกห้องที่ต้องการจองก่อน",
                        "No Room Selected",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            String status = (String) roomModel.getValueAt(selectedRow, 3);
            if ("Available".equalsIgnoreCase(status) || "ว่าง".equals(status)) {
                String roomId = (String) roomModel.getValueAt(selectedRow, 0);

                String name = JOptionPane.showInputDialog(this, "ชื่อ (Name):");
                if (name == null || name.trim().isEmpty()) return;
                String phone = JOptionPane.showInputDialog(this, "เบอร์โทร (Phone):");
                if (phone == null || phone.trim().isEmpty()) return;
                String email = JOptionPane.showInputDialog(this, "อีเมล (Email):");
                if (email == null || email.trim().isEmpty()) return;

                String checkIn = showDatePickerDialog("Check-in Date");
                if (checkIn == null) return;
                String checkOut = showDatePickerDialog("Check-out Date");
                if (checkOut == null) return;

                DefaultTableModel bookingsModel = SharedData.bookingsModel;
                int bookingCount = bookingsModel.getRowCount() + 1;
                String bookingId = "B" + String.format("%03d", bookingCount);

                DefaultTableModel customersModel = SharedData.customersModel;
                int customerCount = customersModel.getRowCount() + 1;
                String customerId = "C" + String.format("%03d", customerCount);

                bookingsModel.addRow(new Object[]{
                        bookingId, name, roomId, checkIn, checkOut, "New"
                });
                customersModel.addRow(new Object[]{
                        customerId, name, phone, email, roomId
                });
                roomModel.setValueAt("จองแล้ว", selectedRow, 3);

                CSVUtils.saveRoomsToCSV(SharedData.roomsModel, "rooms.csv");

                // log
                LogSystem.add("User " + name + " reserved RoomID=" + roomId
                        + " (CheckIn=" + checkIn + ", CheckOut=" + checkOut + ")");

                JOptionPane.showMessageDialog(
                        this,
                        "จองห้องสำเร็จ!\nBooking ID: " + bookingId,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Unavailable",
                        "Cannot Reserve",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });

        backButton.addActionListener(e -> {
            new UserOptionPage();
            dispose();
        });

        logoutButton.addActionListener(e -> {
            // log
            LogSystem.add("User logged out.");
            new UserSelectionPage();
            dispose();
        });

        JPanel panel = new JPanel();
        panel.add(reserveButton);
        panel.add(backButton);
        panel.add(logoutButton);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String showDatePickerDialog(String title) {
        SpinnerDateModel dateModel = new SpinnerDateModel();
        dateModel.setValue(new Date());
        JSpinner dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(editor);

        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.add(new JLabel("เลือกวันที่: "), BorderLayout.WEST);
        panel.add(dateSpinner, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                title,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            Date selectedDate = (Date) dateSpinner.getValue();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(selectedDate);
        } else {
            return null;
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
class AdminOptionPage extends JFrame {
    public AdminOptionPage() {
        setTitle("The kk - Admin");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("The kk (Admin)", SwingConstants.CENTER);
        titleLabel.setBounds(100, 20, 200, 30);
        add(titleLabel);

        JButton loginButton = new JButton("Log in");
        loginButton.setBounds(140, 70, 120, 40);

        JButton backButton = new JButton("← Back");
        backButton.setBounds(10, 10, 80, 30);

        backButton.addActionListener(e -> {
            new UserSelectionPage();
            dispose();
        });
        loginButton.addActionListener(e -> {
            new AdminLoginPage();
            dispose();
        });

        add(loginButton);
        add(backButton);
        setVisible(true);
    }
}

// ─────────────────────────────────────────────────────────────────────────────
class AdminSignUpPage extends JFrame {
    public AdminSignUpPage() {
        setTitle("The kk - Admin Sign up");
        setSize(400, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Sign up (Admin)", SwingConstants.CENTER);
        titleLabel.setBounds(100, 20, 200, 30);
        add(titleLabel);

        JLabel userLabel = new JLabel("Username : ");
        userLabel.setBounds(50, 80, 100, 25);
        JTextField userField = new JTextField();
        userField.setBounds(160, 80, 150, 25);

        JLabel passLabel1 = new JLabel("Create password : ");
        passLabel1.setBounds(50, 120, 110, 25);
        JPasswordField passField1 = new JPasswordField();
        passField1.setBounds(160, 120, 150, 25);

        JLabel passLabel2 = new JLabel("Confirm password : ");
        passLabel2.setBounds(50, 160, 110, 25);
        JPasswordField passField2 = new JPasswordField();
        passField2.setBounds(160, 160, 150, 25);

        JButton accessButton = new JButton("Access");
        accessButton.setBounds(150, 210, 100, 30);

        JButton backButton = new JButton("← Back");
        backButton.setBounds(10, 10, 80, 30);
        backButton.addActionListener(e -> {
            new AdminOptionPage();
            dispose();
        });
        add(backButton);

        add(userLabel);
        add(userField);
        add(passLabel1);
        add(passField1);
        add(passLabel2);
        add(passField2);
        add(accessButton);

        accessButton.addActionListener(e -> {
            String username = userField.getText();
            String pass1 = new String(passField1.getPassword());
            String pass2 = new String(passField2.getPassword());

            if (!pass1.equals(pass2)) {
                JOptionPane.showMessageDialog(this,
                        "รหัสผ่านไม่ตรงกัน!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Admin Sign up สำเร็จ (ยังไม่บันทึกจริง)",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                new AdminLoginPage();
                dispose();
            }
        });

        setVisible(true);
    }
}

// ─────────────────────────────────────────────────────────────────────────────
class AdminLoginPage extends JFrame {
    public AdminLoginPage() {
        setTitle("The kk - Admin Log in");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Log in (Admin)", SwingConstants.CENTER);
        titleLabel.setBounds(100, 20, 200, 30);
        add(titleLabel);

        JLabel userLabel = new JLabel("Username : ");
        userLabel.setBounds(50, 80, 100, 25);
        JTextField userField = new JTextField();
        userField.setBounds(160, 80, 150, 25);

        JLabel passLabel = new JLabel("Password : ");
        passLabel.setBounds(50, 120, 100, 25);
        JPasswordField passField = new JPasswordField();
        passField.setBounds(160, 120, 150, 25);

        JButton loginButton = new JButton("Log in");
        loginButton.setBounds(150, 170, 100, 30);

        JButton backButton = new JButton("← Back");
        backButton.setBounds(10, 10, 80, 30);
        backButton.addActionListener(e -> {
            new AdminOptionPage();
            dispose();
        });
        add(backButton);

        add(userLabel);
        add(userField);
        add(passLabel);
        add(passField);
        add(loginButton);

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            if(username.equals("admin") && password.equals("admin88")) {
                // log
                LogSystem.add("Admin logged in: " + username);

                new AdminPanelWithTabs();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Username หรือ Password ไม่ถูกต้อง!",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}

// ─────────────────────────────────────────────────────────────────────────────
class AdminPanelWithTabs extends JFrame {
    private JTextArea logArea;

    public AdminPanelWithTabs() {
        setTitle("The kk Admin panel");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel dashboardPanel = createDashboardPanel();
        tabbedPane.addTab("Dashboard", dashboardPanel);

        JPanel roomsPanel = createRoomsPanel();
        tabbedPane.addTab("Rooms", roomsPanel);

        JPanel bookingsPanel = createBookingsPanel();
        tabbedPane.addTab("Bookings", bookingsPanel);

        JPanel customersPanel = createCustomersPanel();
        tabbedPane.addTab("Customers", customersPanel);

        add(tabbedPane, BorderLayout.CENTER);

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton logoutButton = new JButton("Log out");
        logoutButton.addActionListener(e -> {
            // log
            LogSystem.add("Admin logged out.");
            new UserSelectionPage();
            dispose();
        });
        logoutPanel.add(logoutButton);
        add(logoutPanel, BorderLayout.SOUTH);

        setVisible(true);

        // แสดง log ทั้งหมดตอนเปิดหน้า
        logArea.setText(LogSystem.getAllLogs());

        // หรือจะ append ว่า "Admin logged in" ตรงนี้ก็ได้
        appendLog("Admin logged in.\n");
    }

    private JPanel createDashboardPanel() {
        JPanel dashboardPanel = new JPanel(new BorderLayout());
        logArea = new JTextArea();
        logArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(logArea);
        JLabel dashLabel = new JLabel("Welcome to Admin Dashboard", SwingConstants.CENTER);

        dashboardPanel.add(dashLabel, BorderLayout.NORTH);
        dashboardPanel.add(scrollPane, BorderLayout.CENTER);
        return dashboardPanel;
    }

    private void appendLog(String message) {
        if (logArea != null) {
            logArea.append(message);
        }
        // เก็บใน LogSystem ด้วย เพื่อคง log ตลอด
        LogSystem.add(message);
    }

    private JPanel createRoomsPanel() {
        JPanel roomsPanel = new JPanel(new BorderLayout());
        DefaultTableModel model = SharedData.roomsModel;
        JTable table = new JTable(model);
        roomsPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton addBtn = new JButton("Add Room");
        JButton editBtn = new JButton("Edit Room");
        JButton delBtn = new JButton("Delete Room");

        // แก้ไข Edit Room
        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String oldRoomID = (String) model.getValueAt(row, 0);
                String oldType   = (String) model.getValueAt(row, 1);
                String oldPrice  = (String) model.getValueAt(row, 2);
                String oldStatus = (String) model.getValueAt(row, 3);

                String newType = JOptionPane.showInputDialog(
                        this,
                        "Enter new Room Type:",
                        oldType
                );
                if (newType != null && !newType.trim().isEmpty()) {
                    model.setValueAt(newType, row, 1);
                }

                String newPrice = JOptionPane.showInputDialog(
                        this,
                        "Enter new Room Price:",
                        oldPrice
                );
                if (newPrice != null && !newPrice.trim().isEmpty()) {
                    model.setValueAt(newPrice, row, 2);
                }

                String newStatus = JOptionPane.showInputDialog(
                        this,
                        "Enter new Room Status (Available/Dirty/Unavailable/etc):",
                        oldStatus
                );
                if (newStatus != null && !newStatus.trim().isEmpty()) {
                    model.setValueAt(newStatus, row, 3);
                }

                CSVUtils.saveRoomsToCSV(SharedData.roomsModel, "rooms.csv");

                // log
                appendLog("Edited Room (ID=" + oldRoomID + ")");
            } else {
                JOptionPane.showMessageDialog(this,
                        "กรุณาเลือกแถวก่อน",
                        "No row selected",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        addBtn.addActionListener(e -> {
            String roomId = JOptionPane.showInputDialog(this, "Enter Room ID:");
            if (roomId == null || roomId.trim().isEmpty()) return;

            String type = JOptionPane.showInputDialog(this, "Enter Room Type:");
            if (type == null || type.trim().isEmpty()) return;

            String price = JOptionPane.showInputDialog(this, "Enter Price:");
            if (price == null || price.trim().isEmpty()) return;

            String status = JOptionPane.showInputDialog(this, "Enter Status (Available/ว่าง/Dirty/etc):");
            if (status == null || status.trim().isEmpty()) return;

            model.addRow(new Object[]{roomId, type, price, status});
            CSVUtils.saveRoomsToCSV(SharedData.roomsModel, "rooms.csv");

            // log
            appendLog("Added Room: " + roomId);
        });

        delBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String deletedRoomId = (String) model.getValueAt(row, 0);
                model.removeRow(row);
                CSVUtils.saveRoomsToCSV(SharedData.roomsModel, "rooms.csv");

                // log
                appendLog("Deleted Room: " + deletedRoomId);
            } else {
                JOptionPane.showMessageDialog(this,
                        "กรุณาเลือกแถวก่อน",
                        "No row selected",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        btnPanel.add(addBtn);
        btnPanel.add(editBtn);
        btnPanel.add(delBtn);
        roomsPanel.add(btnPanel, BorderLayout.SOUTH);
        return roomsPanel;
    }

    private JPanel createBookingsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        DefaultTableModel model = SharedData.bookingsModel;
        JTable table = new JTable(model);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton addBtn = new JButton("Add Booking");
        JButton editBtn = new JButton("Edit Booking");
        JButton delBtn = new JButton("Delete Booking");

        JButton confirmBtn = new JButton("Confirm Booking");
        JButton cancelBtn = new JButton("Cancel Booking");

        addBtn.addActionListener(e -> {
            model.addRow(new Object[]{"B???", "NewCustomer", "Room?", "CheckIn?", "CheckOut?", "New"});
            appendLog("Added new Booking.");
        });

        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                model.setValueAt("EditedCustomer", row, 1);
                appendLog("Edited Booking at row " + row);
            } else {
                JOptionPane.showMessageDialog(this, "กรุณาเลือกแถวก่อน",
                        "No row selected", JOptionPane.WARNING_MESSAGE);
            }
        });

        delBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String bookingId = (String) model.getValueAt(row, 0);
                model.removeRow(row);
                appendLog("Deleted Booking: ID=" + bookingId);
            } else {
                JOptionPane.showMessageDialog(this, "กรุณาเลือกแถวก่อน",
                        "No row selected", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Confirm Booking (แสดง Booking ID ใน log)
        confirmBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                model.setValueAt("Confirmed", row, 5);
                String bookingId = (String) model.getValueAt(row, 0);
                appendLog("Booking confirmed: ID=" + bookingId);
            } else {
                JOptionPane.showMessageDialog(this,
                        "กรุณาเลือกแถวก่อน", "No row selected",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        // Cancel Booking (แสดง Booking ID ใน log)
        cancelBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                model.setValueAt("Cancelled", row, 5);
                String bookingId = (String) model.getValueAt(row, 0);
                appendLog("Booking cancelled: ID=" + bookingId);
            } else {
                JOptionPane.showMessageDialog(this,
                        "กรุณาเลือกแถวก่อน", "No row selected",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        btnPanel.add(addBtn);
        btnPanel.add(editBtn);
        btnPanel.add(delBtn);
        btnPanel.add(confirmBtn);
        btnPanel.add(cancelBtn);

        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createCustomersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        DefaultTableModel model = SharedData.customersModel;
        JTable table = new JTable(model);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton addBtn = new JButton("Add Customer");
        JButton editBtn = new JButton("Edit Customer");
        JButton delBtn = new JButton("Delete Customer");

        addBtn.addActionListener(e -> {
            model.addRow(new Object[]{"C???", "NewName", "Phone?", "Email?", "Room?"});
            appendLog("Added new Customer.");
        });
        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                model.setValueAt("EditedName", row, 1);
                appendLog("Edited Customer at row " + row);
            } else {
                JOptionPane.showMessageDialog(this,
                        "กรุณาเลือกแถวก่อน", "No row selected",
                        JOptionPane.WARNING_MESSAGE);
            }
        });
        delBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String custId = (String) model.getValueAt(row, 0);
                model.removeRow(row);
                appendLog("Deleted Customer: ID=" + custId);
            } else {
                JOptionPane.showMessageDialog(this,
                        "กรุณาเลือกแถวก่อน", "No row selected",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        btnPanel.add(addBtn);
        btnPanel.add(editBtn);
        btnPanel.add(delBtn);
        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }
}
