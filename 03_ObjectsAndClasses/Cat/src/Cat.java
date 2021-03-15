//Урок 5. Создание объектов и конструктор
//Цель задания
//Научиться создавать объекты, используя перегруженные конструкторы.
//Что нужно сделать
//Сделайте в классе Cat ещё один конструктор, чтобы можно было создать кошку с весом,
//переданным в конструктор.
//Создайте в главном классе Loader метод генерации котёнка private static Cat getKitten().
//Он должен возвращать кошку с весом 1100.00 грамм с помощью конструктора, в который передаём вес кошки.
//Создайте три объекта класса Cat в методе main(), используя метод getKitten().
//Отправьте в репозиторий Git коммит с выполненным заданием.
//Критерии оценки
//«Зачёт» — создан перегруженный конструктор и написан метод создания кошки в Loader.
//«Незачёт» — задание не выполнено.

//Прошу лишь исключить дублирование кода в конструкторе с параметром и без параметра.
// Попробуйте вызывать один конструктор в другом.

public class Cat {
    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;

    public static int count = 0;
    private boolean isAlive; // переменная проверки, жива ли кошка

    public static final int EYES_COUNT = 2; // константа - количество глаз у кошки
    public static final double MIN_WEIGHT = 1000;  // константа - минимальный вес кошки
    public static final double MAX_WEIGHT = 9000;  // константа - максимальный вес кошки

    public static double boardMinWeight = 1500; // переменная для генерации веса кошки
    public static double boardAverageWeight = 3000; // переменная для генерации веса кошки

    public Cat() {
        weight = boardMinWeight + boardAverageWeight * Math.random();
        originWeight = weight;
        minWeight = MIN_WEIGHT;
        maxWeight = MAX_WEIGHT;
        count++;
        isAlive = true;
    }

    public Cat(double weight) {  // ещё один конструктор, чтобы можно было создать кошку с весом, переданным в конструктор.
        originWeight = weight;
        if (weight <= MIN_WEIGHT || weight >= MAX_WEIGHT) {
            isAlive = false;
        } else {
            new Cat();
        }
    }

    public void meow() {
        if (isAlive) {
            weight = weight - 1;
            System.out.println("Meow");
        } else {
            System.out.println("Cat is dead, baby, cat is dead.");
        }
    }

    public void feed(Double amount) {
        if (isAlive) {
            weight = weight + amount;
        } else {
            System.out.println("Cat is dead, baby, cat is dead.");
        }
    }

    public Double foodAmount() {
        double i = weight - originWeight;
        return i;
    }

    public void pee() {
        if (isAlive) {
            weight = weight - 50;
            System.out.println("Piss");
        } else {
            System.out.println("Cat is dead, baby, cat is dead.");
        }
    }

    public void drink(Double amount) {
        if (isAlive) {
            weight = weight + amount;
        } else {
            System.out.println("Cat is dead, baby, cat is dead.");
        }
    }

    public Double getWeight() {
        return weight;
    }

    public static int getCount() { // создаем метод, возвращающий количество кошек
        return count;
    }

    public String getStatus() {
        if (weight < minWeight) {
            count--;
            isAlive = false;
            return "Dead";
        } else if (weight > maxWeight) {
            count--;
            isAlive = false;
            return "Exploded";
        } else if (weight > originWeight) {
            return "Sleeping";
        } else {
            return "Playing";
        }
    }
}