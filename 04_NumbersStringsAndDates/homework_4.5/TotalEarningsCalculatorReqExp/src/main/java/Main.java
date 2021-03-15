import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Выполните задание в классе Main в методе calculateSalarySum
//проекта 04_NumbersStringsAndDates/homework_4.5/TotalEarningsCalculatorReqExp.
//Реализуйте получение суммы заработка каждого человека из
//текста регулярным выражением, чтобы в конце программы рассчитывалась и распечатывалась общая сумма заработка людей.
//В метод возможна передача любого сочетания имён и суммы зарплат в строке.
public class Main {
    public static void main(String[] args) {
//        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
//        String text = "Коля заработал 50000 рублей, Федя - 45800 рубля, а Саша - 23000 рублей";
          String text = "Толя заработал 35000 рублей, а Лера - 45000 рублей";
//        String text = "Никто ничего не заработал";
        int salarySum = calculateSalarySum(text);
        System.out.println(text);
        System.out.println("Суммарный заработок составил: ");
        System.out.println(salarySum);
    }

    public static int calculateSalarySum(String text) {
        int sum = 0;
        Pattern pattern = Pattern.compile("\\d+"); // находим цифры в тексте
        Matcher matcher = pattern.matcher(text);   // сравниваем
        int start = 0;
        while (matcher.find(start)) {
            String value = text.substring(matcher.start(), matcher.end()); // присваиваем строковой переменной кусок текста с цифрами
            int result = Integer.parseInt(value);  // переводим в формат int
            start = matcher.end();  // сдвигаем точку старта
            sum = sum + result;  // суммируем общий заработок
        }
        return sum;
    }
}