import java.util.Scanner;
import java.util.TreeSet;

public class TreeSetEmails {
    public static void main(String[] args) {


        // read source data
        TreeSet<String> emails = new TreeSet<>();

        // заполним список разными e-mail'ами
        emails.add("12345@mail.ru");
        emails.add("67890@mail.ru");
        emails.add("12345@yandex.ru");
        emails.add("67890@yandex.ru");
        emails.add("12345@gmail.com");
        emails.add("67890@gmail.com");

        String order = "";
        String listCom = "LIST"; // задаем список команд для управления списком
        String addCom = "ADD";
        int position = 0;
        int temp = 0;
        String sub1 = "@", sub2 = ".ru", sub3 = ".com", sub4 = ".by"; // добавляем доменные зоны


        System.out.println("Список команд: LIST (вывести список), ADD (email) , 0 - выход");
        System.out.println("(можно печатать в любом регистре)");

        while (!order.equals("0")) {  // проверка на 0 - выход из программы
            System.out.println("Пожалуйста, введите Вашу команду: ");
            order = new Scanner(System.in).nextLine();

            // проверка на LIST
            if (order.toLowerCase().contains(listCom.toLowerCase())) {
                System.out.println(emails);
            }

            // проверка на ADD и правильность e-mail - @ и доменные зоны //  вариант:  if (s.indexOf(sub) != -1)
            if (order.toLowerCase().contains(addCom.toLowerCase())) {
                String cleanCommand = "add";
                if (((int) order.charAt(3) == 32 && order.contains(sub1) && order.indexOf(sub1) >=5)
                        && (order.contains(sub2) || (order.contains(sub3))
                        || (order.contains(sub3)) || (order.contains(sub4))))  {
                    position = Integer.parseInt(Character.toString(order.charAt(4)));
                    temp = 4;
                } else {
                    System.out.println("Неверный формат ввода");
                    continue;
                }
                String addContain = order.substring(order.indexOf(cleanCommand) + temp);
                System.out.println("Добавлено: ");
                System.out.println("email: " + addContain);
                emails.add(addContain);
            }
        }
    }
}
