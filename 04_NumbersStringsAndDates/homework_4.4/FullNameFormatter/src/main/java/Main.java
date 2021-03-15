import java.util.Scanner;

//Что нужно сделать
//Выполните задание в классе Main проекта 04_NumbersStringsAndDates/homework_4.4/FullNameFormatter
//Напишите программу, которая на входе через консоль принимает фамилию, имя и отчество одной строкой
//(например, «Иванов Сергей Петрович») и выводит фамилию, имя и отчество отдельно в формате:
//Фамилия: Иванов
//Имя: Сергей
//Отчество: Петрович
//Валидная строка от пользователя, которую мы можем интерпретировать как Ф. И. О., должна содержать
//три слова, состоящих из символов кириллицы, разделённых пробелом, может содержать дефис.
//Если строка не соответствует формату, то вывести в консоль: Введенная строка не является ФИО
//Использование регулярных выражений в данном задании не допускается.
//Строго соблюдайте формат вывода результата.
public class Main {
    public static void main(String[] args) {

        String firstName = "";  // инициализируем строковые переменные - имя
        String middleName = ""; // инициализируем строковые переменные - отчество
        String familyName = "";  // инициализируем строковые переменные - фамилия
        boolean format = true;
        char temp = ' ';

        System.out.println("Пожалуйста, введите ФИО в формате: Иванов Иван Иванович");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        // String input = "0123 4567 895";
        // String input = "Иванов-Петров Иван Иванович";

        //кириллица все:       1040 - 1103
        //кириллица заглавные: 1040 - 1071
        // пробел 32, дефис 45
        // цифры 0123456789  =  от 48 до 57

        // проверка на разрешенные символы
        for (int i = 0; i < input.length(); i++) {
            temp = input.charAt(i);

            if (((int) temp >= 1040 && (int) temp <= 1103) && !Character.isDigit(temp)
                    || ((int) input.charAt(i) == 45 && ((int) input.charAt(i + 1) >= 1040 && (int) input.charAt(i + 1) <= 1071))
                    || ((int) input.charAt(i) == 32 && ((int) input.charAt(i + 1) >= 1040 && (int) input.charAt(i + 1) <= 1071))) {
//                System.out.println((int) input.charAt(i));
                format = true;
            } else {
                format = false;
//                System.out.println(format);
                break;
            }
        }
        int firstSpace = input.indexOf(" ");  // ищем 1й пробел
        if (firstSpace < 0) {
            format = false;
        } else if (format) {
            format = true;
            familyName = input.substring(0, firstSpace);
        }
        int secondSpace = input.indexOf(" ", firstSpace + 1); // ищем 2й пробел
        int thirdSpace = input.indexOf(" ", secondSpace + 1); // ищем 3й пробел
        if (secondSpace < 0 || thirdSpace > 0) {
            format = false;
        } else if (format) {
            firstName = input.substring(firstSpace + 1, secondSpace);
            middleName = input.substring(secondSpace + 1);
        }
        if (format) {
            System.out.println("Фамилия: " + familyName + "\nИмя: " + firstName + "\nОтчество: " + middleName);
        } else {
            System.out.print("Введенная строка не является ФИО, \nВозможен неверный формат ввода");
        }
    }
}