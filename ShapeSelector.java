import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapeSelector extends JFrame {
    private DrawPanel drawPanel;
    private JComboBox<String> shapeSelector;
    private JTextField sizeInput;

    public ShapeSelector() {
        setTitle("Shape Selector");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // สร้าง Panel สำหรับ UI ด้านบน
        JPanel controlPanel = new JPanel();
        shapeSelector = new JComboBox<>(new String[]{"Square", "Triangle", "Circle"});
        sizeInput = new JTextField("5", 3);
        JButton drawButton = new JButton("Draw");

        controlPanel.add(new JLabel("Shape:"));
        controlPanel.add(shapeSelector);
        controlPanel.add(new JLabel("Size:"));
        controlPanel.add(sizeInput);
        controlPanel.add(drawButton);

        add(controlPanel, BorderLayout.NORTH);

        // Panel สำหรับวาดรูป
        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);

        // กดปุ่มแล้วอัปเดตการวาด
        drawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawPanel.setShape((String) shapeSelector.getSelectedItem(), Integer.parseInt(sizeInput.getText()));
            }
        });
    }

    // Panel สำหรับวาดรูป
    private class DrawPanel extends JPanel {
        private String shape = "Square";
        private int size = 5;

        public void setShape(String shape, int size) {
            this.shape = shape;
            this.size = size;
            repaint(); // วาดใหม่
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);

            switch (shape) {
                case "Square":
                    g.fillRect(100, 100, size * 10, size * 10);
                    break;
                case "Triangle":
                    int[] xPoints = {150, 100, 200};
                    int[] yPoints = {100, 100 + size * 10, 100 + size * 10};
                    g.drawPolygon(xPoints, yPoints, 3);
                    break;
                case "Circle":
                    g.fillOval(100, 100, size * 10, size * 10);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShapeSelector().setVisible(true));
    }
}
