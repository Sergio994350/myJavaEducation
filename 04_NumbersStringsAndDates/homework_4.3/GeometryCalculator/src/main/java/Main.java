import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите радиус круга: ");
        double r = new Scanner(System.in).nextDouble(); // запрос с консоли радиуса круга
        System.out.println("Площадь круга равна: ");
        System.out.println(GeometryCalculator.getCircleSquare(r)); // печатаем площадь круга

        System.out.println("Введите радиус сферы: ");
        double r2 = new Scanner(System.in).nextDouble(); // запрос с консоли радиуса сферы
        System.out.println("Объем сферы равен: ");
        System.out.println(GeometryCalculator.getSphereVolume(r2)); // печатаем объем сферы

        System.out.println("Введите 3 стороны треугольника: ");
        double a1 = new Scanner(System.in).nextDouble(); // запрос с консоли стороны треугольника a1
        double b1 = new Scanner(System.in).nextDouble(); // запрос с консоли стороны треугольника b1
        double c1 = new Scanner(System.in).nextDouble(); // запрос с консоли стороны треугольника c1

        if (GeometryCalculator.isTrianglePossible(a1, b1, c1)) {
            System.out.println("Площадь треугольника равна: ");
            System.out.println(GeometryCalculator.getTriangleSquare(a1, b1, c1));
        } else {
            System.out.println("Построение треугольника невозможно");
        }
    }
}
