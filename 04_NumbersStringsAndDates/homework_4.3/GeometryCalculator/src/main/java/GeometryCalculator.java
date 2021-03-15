//Реализуйте в классе GeometryCalculator методы:
//— расчёта площади круга,
//— расчёта площади треугольника,
// S = SQR(p*(p-a)*(p-b)*(p-c))
// где p = (a+b+c)/2  -это полупериметр треугольника
//— расчёта объёма сферы,
//— проверки возможности построения треугольника по трём длинам сторон.

public class GeometryCalculator {
    // считаем площадь круга от радиуса
    public static double getCircleSquare(double radius) {
        double circleSquare = Math.PI * Math.pow(Math.abs(radius), 2);
        return circleSquare;
    }

    // считаем объем сферы от радиуса
    // V = ⁴/₃πr³
    public static double getSphereVolume(double radius) {
        double sphereVolume = 4 * (Math.PI * Math.pow(Math.abs(radius), 3)) / 3;
        return sphereVolume;
    }

    // считаем возможность построения треугольника. сумма 2х сторон должна быть больше третьей стороны.
    public static boolean isTrianglePossible(double a, double b, double c) {
        boolean trianglePossible = ((a + b) > c && (a + c) > b && (b + c) > a);
        return trianglePossible;
    }

    // перед расчетом площади рекомендуется проверить возможен ли такой треугольник
    // методом isTrianglePossible, если невозможен вернуть -1.0
    public static double getTriangleSquare(double a, double b, double c) {
        if (GeometryCalculator.isTrianglePossible(a, b, c)) {
            double p = (a + b + c) / 2;
            double triangleSquare = Math.sqrt((p * (p - a) * (p - b) * (p - c)));
            return triangleSquare;
        } else {
            return -1;
        }
    }
}
