import javax.swing.*;
import java.awt.*;

public class DrawFrame extends JFrame {
    private String shapeType;

    public DrawFrame(String shapeType) {
        this.shapeType = shapeType;
        setTitle("Drawing: " + shapeType);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // สร้าง Panel สำหรับการวาด
        add(new DrawPanel());
    }

    // สร้างคลาส DrawPanel สำหรับการวาดรูป
    private class DrawPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);

            switch (shapeType) {
                case "Square":
                    g.fillRect(100, 100, 100, 100);
                    break;
                case "Triangle":
                    g.drawPolygon(new int[]{150, 100, 200}, new int[]{50, 150, 150}, 3);
                    break;
                case "RightTriangle":
                    g.drawPolygon(new int[]{100, 100, 200}, new int[]{100, 200, 200}, 3);
                    break;
            }
        }
    }
}
