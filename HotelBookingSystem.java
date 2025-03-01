import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelBookingSystem {
    public static void main(String[] args) {
        new UserSelectionPage();
    }
}

// หน้าหลักเลือก User หรือ Admin
class UserSelectionPage extends JFrame {
    public UserSelectionPage() {
        setTitle("User Selection");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton userButton = new JButton("User");
        JButton adminButton = new JButton("Admin");

        userButton.addActionListener(e -> {
            new LoginPage("User");
            dispose();
        });

        adminButton.addActionListener(e -> {
            new LoginPage("Admin");
            dispose();
        });

        add(userButton);
        add(adminButton);
        setVisible(true);
    }
}

// หน้าล็อกอิน
class LoginPage extends JFrame {
    public LoginPage(String userType) {
        setTitle(userType + " Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();
        JButton loginButton = new JButton("Log in");

        loginButton.addActionListener(e -> {
            if (userType.equals("User")) {
                new UserHomepage();
            } else {
                new AdminDashboard();
            }
            dispose();
        });

        add(userLabel);
        add(userField);
        add(passLabel);
        add(passField);
        add(loginButton);

        setVisible(true);
    }
}

// หน้าหลักของ User
class UserHomepage extends JFrame {
    public UserHomepage() {
        setTitle("User Homepage");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTable table = new JTable(new String[][]{{"101", "Single Bed", "1000", "Available"}},
                new String[]{"Room No", "Type", "Price", "Status"});
        JButton bookButton = new JButton("Book");
        JButton logoutButton = new JButton("Log out");

        logoutButton.addActionListener(e -> {
            new UserSelectionPage();
            dispose();
        });

        JPanel panel = new JPanel();
        panel.add(bookButton);
        panel.add(logoutButton);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        setVisible(true);
    }
}

// แดชบอร์ดของแอดมิน
class AdminDashboard extends JFrame {
    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 4));

        JButton roomsButton = new JButton("Rooms");
        JButton bookingsButton = new JButton("Bookings");
        JButton customersButton = new JButton("Customers");
        JButton logoutButton = new JButton("Log out");

        roomsButton.addActionListener(e -> new AdminRoomsPage());
        bookingsButton.addActionListener(e -> new AdminBookingsPage());
        customersButton.addActionListener(e -> new AdminCustomersPage());
        logoutButton.addActionListener(e -> {
            new UserSelectionPage();
            dispose();
        });

        add(roomsButton);
        add(bookingsButton);
        add(customersButton);
        add(logoutButton);
        setVisible(true);
    }
}

// หน้าจัดการห้องพักของแอดมิน
class AdminRoomsPage extends JFrame {
    public AdminRoomsPage() {
        setTitle("Manage Rooms");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTable table = new JTable(new String[][]{{"101", "Single", "Available"}},
                new String[]{"Room ID", "Type", "Status"});
        JButton addRoomButton = new JButton("Add Room");
        JButton editRoomButton = new JButton("Edit Room");
        JButton deleteRoomButton = new JButton("Delete Room");

        JPanel panel = new JPanel();
        panel.add(addRoomButton);
        panel.add(editRoomButton);
        panel.add(deleteRoomButton);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        setVisible(true);
    }
}

// หน้าจัดการการจองของแอดมิน
class AdminBookingsPage extends JFrame {
    public AdminBookingsPage() {
        setTitle("Manage Bookings");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTable table = new JTable(new String[][]{{"B001", "John Doe", "101", "Checked-In"}},
                new String[]{"Booking ID", "Customer Name", "Room No", "Status"});
        JButton approveButton = new JButton("Approve Booking");
        JButton cancelButton = new JButton("Cancel Booking");

        JPanel panel = new JPanel();
        panel.add(approveButton);
        panel.add(cancelButton);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        setVisible(true);
    }
}

// หน้าจัดการลูกค้าของแอดมิน
class AdminCustomersPage extends JFrame {
    public AdminCustomersPage() {
        setTitle("Manage Customers");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTable table = new JTable(new String[][]{{"C001", "John Doe", "john@example.com"}},
                new String[]{"Customer ID", "Name", "Email"});
        JButton addCustomerButton = new JButton("Add Customer");
        JButton editCustomerButton = new JButton("Edit Customer");
        JButton deleteCustomerButton = new JButton("Delete Customer");

        JPanel panel = new JPanel();
        panel.add(addCustomerButton);
        panel.add(editCustomerButton);
        panel.add(deleteCustomerButton);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        setVisible(true);
    }
}