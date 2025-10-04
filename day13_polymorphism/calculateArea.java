package day13_polymorphism;

class Shape {
    public double calculateArea() {
        return 0.0;
    }
}

class Circle extends Shape {
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    double height, width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public double calculateArea() {
        return height * width;
    }
}

public class calculateArea {
    public static void main(String[] args) {

        Shape[] shapes = new Shape[4];
        shapes[0] = new Rectangle(10, 10);
        shapes[1] = new Circle(10);
        shapes[2] = new Rectangle(3, 10);
        shapes[3] = new Circle(Math.PI);

        for (Shape shape : shapes) {
            System.out.println(shape.calculateArea());
        }

    }
}
