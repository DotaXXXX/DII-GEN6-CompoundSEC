import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Shape Drawer"); // ตั้งชื่อหน้าต่างหลัก
        setSize(400, 300); // กำหนดขนาดหน้าต่าง
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // จัดให้อยู่ตรงกลางหน้าจอ

        // สร้าง Panel สำหรับปุ่มกด
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10)); // จัดปุ่มเป็นแนวตั้ง 3 แถว

        // สร้างปุ่มกดสำหรับเลือกรูปทรง
        JButton squareButton = new JButton("Draw Square");
        JButton triangleButton = new JButton("Draw Triangle");
        JButton rightTriangleButton = new JButton("Draw Right Triangle");

        // เพิ่ม Event ให้ปุ่ม เมื่อกดจะเปิดหน้าต่างใหม่
        squareButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DrawFrame("Square").setVisible(true);
            }
        });

        triangleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DrawFrame("Triangle").setVisible(true);
            }
        });

        rightTriangleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DrawFrame("RightTriangle").setVisible(true);
            }
        });

        // เพิ่มปุ่มไปยัง Panel
        panel.add(squareButton);
        panel.add(triangleButton);
        panel.add(rightTriangleButton);

        // เพิ่ม Panel ลงใน JFrame
        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
