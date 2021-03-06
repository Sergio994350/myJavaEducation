import java.util.Scanner;

//Выполните задание в классе Main 04_NumbersStringsAndDates/homework_4.5/PhoneCleaner
//Реализуйте удаление лишних символов при вводе номера телефона в консоли и проверку соответствия
//номера формату мобильных номеров России. Если введённую строку нельзя привести к формату
//мобильного номера — выводите сообщение о неверном вводе. Телефон может быть введён
//не только в формате 79091234567, но и с лишними символами.
//Пример ввода номеров и результата вывода программы
//Ввод пользователя
//Вывод программы в консоль	Примечание
//+7 909 123-45-67	79091234567	Символов 11 в номере, код страны 7 — номер верный.
//+7 (909) 1234567	79091234567	Символов 11 в номере, код страны 7 — номер верный.
//8-905-1234567	79051234567	Символов 11 в номере, первый символ 8 (код выхода на мобильный номер) заменяем на код страны 7 — номер верный.
//9-453-1234567	Неверный формат номера	Символов 11 в номере, первый символ после очистки 9, это не 7 и не 8 — формат номера неверный.
//8-905-123	Неверный формат номера	Символов 7 в номере — номер неверный.
//905-1234567	79051234567	Количество символов 10 после очистки — значит, приводим к формату номера с 7.
//8-905-12345672342	Неверный формат номера	Символов в номере больше чем 11 — номер неверный.
// коды: 57 - 9, 48 - 0, 55 - 7, 56 - 8

public class Main {
    public static void main(String[] args) {

        System.out.println("Пожалуйста, введите телефонный номер в формате: 79051234567");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
//    String input = "+7 909 123-45-67";
//    String input = "+7 (909) 1234567";
//    String input = "8-905-1234567";
//    String input = "9-453-1234567"; // "Номер = 11 цифр, код страны не 7 или 8"
//    String input = "905-1234567"; // "905-1234567 -> 79051234567"
//    String input = "8-905-123"; // "Номер < 10 цифр"
//    String input = "8-905-12345672342"; // "Номер > 11 цифр"

        System.out.println("Вы ввели номер: " + input);
        String cleanNumber = input.replaceAll("[^0-9]", "");
        boolean format = false;
        //проверка длины и правильности очищенного номера
        if ((cleanNumber.length() == 11 && (int) cleanNumber.charAt(0) == 55)
                || (cleanNumber.length() == 11 && (int) cleanNumber.charAt(0) == 56)
                || (cleanNumber.length() < 11 && (int) cleanNumber.charAt(0) == 57)) {
            if (cleanNumber.length() == 11 && (int) cleanNumber.charAt(0) == 56) {
                cleanNumber = cleanNumber.replaceFirst("8", "7");
            }
            if (cleanNumber.length() < 11 && (int) cleanNumber.charAt(0) == 57) {
                cleanNumber = ("7" + cleanNumber);
            }
            format = true;
        } else {
            format = false;
        }
        if (format) {
            System.out.println("Номер телефона: " + cleanNumber);
        } else {
            System.out.println("Неверный формат номера\n");
        }
    }
}
