public class Main {

    public static void main(String[] args) {
        Container container = new Container();
        container.count += 7843;

        int sum = sumDigits(12345);

        System.out.println(sum);
    }

  /* Реализуйте метод sumDigits который возвращает сумму цифр числа, пример:
  передано 12345, метод должен вернуть 15
  если передано null, то должен вернуть -1

  Запустите тесты TestSumDigits для проверки корректности работы метода

  не меняйте название метода, его возвращаемое значение и модификаторы доступа (public).
  В противном случае тестовый метод не сможет проверить ваш код
   */

    //// Сергей, добрый день! Решение верное. Прошу оптимизировать алгоритм,
    // используя Character.getNumericValue и строковый метод charAt
    // СТ - сделано
    public static int sumDigits(Integer number) {
        int sum = 0;
        int counter = 0;
        if (number == null) {
            sum = -1;
        } else {
            String numberString = number.toString(); //  преобразовали Integer в String
            while (counter < numberString.length()) {
                int b = Character.getNumericValue(numberString.charAt(counter));
                sum = sum + b;
                counter++;
            }
        }
        return sum;
    }
}

