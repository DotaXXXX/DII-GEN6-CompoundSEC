import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// หน้าต่างหลัก
class ShapeDrawer extends JFrame {
    private Shape shape;
    private DrawPanel drawPanel;
    private JComboBox<String> shapeSelector;
    private JTextField sizeInput;

    public ShapeDrawer() {
        setTitle("Shape Drawer");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel controlPanel = new JPanel();
        shapeSelector = new JComboBox<>(new String[]{"Square", "Triangle", "Circle"});
        sizeInput = new JTextField("5", 3);
        JButton drawButton = new JButton("Draw");

        controlPanel.add(new JLabel("Select Shape:"));
        controlPanel.add(shapeSelector);
        controlPanel.add(new JLabel("Size:"));
        controlPanel.add(sizeInput);
        controlPanel.add(drawButton);

        add(controlPanel, BorderLayout.NORTH);

        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);

        drawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedShape = (String) shapeSelector.getSelectedItem();
                int size = Integer.parseInt(sizeInput.getText());
                shape = ShapeFactory.createShape(selectedShape, size);
                drawPanel.repaint();
            }
        });
    }

    // Panel สำหรับวาดรูป
    private class DrawPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (shape != null) {
                shape.draw(g);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShapeDrawer().setVisible(true));
    }
}
