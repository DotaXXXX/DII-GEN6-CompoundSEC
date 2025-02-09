import java.awt.*;

// คลาสแม่สำหรับรูปทรง
abstract class Shape {
    protected int size;

    public Shape(int size) {
        this.size = size;
    }

    public abstract void draw(Graphics g);
}

// รูปสี่เหลี่ยม
class Square extends Shape {
    public Square(int size) {
        super(size);
    }

    @Override
    public void draw(Graphics g) {
        g.fillRect(50, 50, size * 10, size * 10);
    }
}

// รูปสามเหลี่ยม
class Triangle extends Shape {
    public Triangle(int size) {
        super(size);
    }

    @Override
    public void draw(Graphics g) {
        int[] xPoints = {50, 50 - size * 5, 50 + size * 5};
        int[] yPoints = {50, 50 + size * 10, 50 + size * 10};
        g.drawPolygon(xPoints, yPoints, 3);
    }
}

// รูปวงกลม (เพิ่มง่ายๆ โดยไม่ต้องแก้ไขโค้ดหลัก)
class Circle extends Shape {
    public Circle(int size) {
        super(size);
    }

    @Override
    public void draw(Graphics g) {
        g.fillOval(50, 50, size * 10, size * 10);
    }
}

// Factory สำหรับสร้างรูปทรง
class ShapeFactory {
    public static Shape createShape(String shapeType, int size) {
        switch (shapeType) {
            case "Square":
                return new Square(size);
            case "Triangle":
                return new Triangle(size);
            case "Circle":
                return new Circle(size);
            default:
                throw new IllegalArgumentException("Invalid shape type");
        }
    }
}

