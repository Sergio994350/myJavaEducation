// ДЗ 3.3
//Что нужно сделать
//Создайте у класса Cat статическую переменную count, которая увеличивается на 1, если кошку создали,
//и убывает на 1, если кошка взорвалась или умерла.
//Создайте статический метод getCount(), который возвращает количество кошек.
//Отправьте в репозиторий Git коммит с выполненным заданием.
//Дополнительное задание*
//Придумайте способ, который поможет проверить, жива ли кошка, на основе значения её веса.
//Используя это, запретите неживой кошке есть, пить, ходить в туалет, выполняя проверку веса внутри
//методов, которые изменяют вес кошки.

public class Loader {

    private static Cat getKitten() {  // создаем метод для создания котенка весом 1100 грамм
        return new Cat (1100);
    }

    public static void main(String[] args) {

       Cat kitten1 = getKitten(); // создаем котенка1
       Cat kitten2 = getKitten(); // создаем котенка2
       Cat kitten3 = getKitten(); // создаем котенка3

        Cat murka3 = new Cat(); // создаем кошку
        System.out.println("Вес нашей кошки: ");
        System.out.println(murka3.getWeight());

        Cat murka4 = new Cat(); // создаем еще одну кошку
        System.out.println("Всего кошек: " + Cat.count);

        System.out.println("Вес нашей кошки: ");
        System.out.println(murka4.getWeight());
        murka4.feed((double) 8000); // перекормим кошку murka4 до состояния "Exploded"
        System.out.println("Вес нашей кошки после кормления: ");
        System.out.println(murka4.getWeight());
        System.out.println(murka4.getStatus());
        murka4.pee(); // вызовем метод pee() для murka4
        murka4.drink((double) 500); // вызовем метод drink() для murka4
        System.out.println("Всего кошек: " + Cat.count);



        System.out.println("Покормим нашу кошку.");
        murka3.feed((double) 150);
        murka3.feed((double) 100);
        System.out.println("Вес нашей кошки после кормления: ");
        System.out.println(murka3.getWeight()); //вес кошки после кормления

        double i = murka3.foodAmount(); // вычисляем вес съеденной еды

        murka3.pee();
        murka3.pee();
        murka3.pee();
        murka3.pee();
        murka3.pee();
        murka3.pee();

        System.out.println("количество съеденного корма: " + i);
        System.out.println("Всего кошек: " + Cat.count);

    }
}