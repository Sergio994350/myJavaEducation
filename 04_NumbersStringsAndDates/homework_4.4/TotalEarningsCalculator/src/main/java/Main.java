public class Main {

    public static void main(String[] args) {
//Напишите код, который считает сумму заработка всех друзей.
//Используйте методы indexOf(), lastIndexOf(), substring() и trim().
//Использование регулярных выражений в данном задании не допускается.
// Прошу лишь оптимизировать задание №2 с суммой зарплат.
// Не используйте цикл и не делайте арифметических действий.
// Необходимо отталкиваться от пробелов.
// Используйте lastIndexOf и indexOf

        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        System.out.println(text);
        System.out.println(digitsFromText(text));
    }

    public static int digitsFromText(String str) {
        int vasya, petya, masha;
        vasya = Integer.parseInt(str.substring((str.indexOf("Вася") + 15), (str.indexOf(" рублей,"))));
        petya = Integer.parseInt(str.substring((str.indexOf("Петя") + 7), (str.indexOf(" рубля"))));
        masha = Integer.parseInt(str.substring((str.indexOf("Маша") + 7), (str.lastIndexOf(" рублей"))));
        int sum = vasya + petya + masha;
        return sum;
    }
}
