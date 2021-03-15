import java.util.ArrayList;
import java.util.Scanner;

// // Сергей, добрый день. Алгоритм решения верный.
//// Прошу изменить структуру за счет использования switch вместо if/else
//// и подумайте над оптимизацией за счет использования разбиения
//// на методы, хотя бы один можно сделать однозначно.
// список команд из консоли:
// LIST — выводит дела с их порядковыми номерами;
// ADD — добавляет дело в конец списка или дело на определённое место,
// сдвигая остальные дела вперёд, если указать номер;
// EDIT — заменяет дело с указанным номером;
// DELETE — удаляет.

public class Main {
    public static void main(String[] args) {
        String order = "";
        String listCom = "LIST"; // задаем список команд для управления списком
        String addCom = "ADD";
        String editCom = "EDIT";
        String deleteCom = "DELETE";
        int position = 0;
        int temp = 0;
        String cleanCommand = "";
        String addContain = "";

        ArrayList<String> list1 = new ArrayList<>(); // создаем список

        list1.add("Первое дело"); // заполняем список
        list1.add("Второе дело");
        list1.add("Третье дело");
        list1.add("Четвертое дело");

        System.out.println("Список команд: LIST , ADD текст или номер , EDIT номер , DELETE номер, 0 - выход");
        System.out.println("(можно печатать в любом регистре)");

        while (!order.equals("0")) {  // проверка на 0 - выход из программы
            System.out.println("Пожалуйста, введите Вашу команду: ");
            order = new Scanner(System.in).nextLine();

            cleanCommand = commandFromOrder(order);

            switch (cleanCommand) {
                case "list":
                    for (int i = 0; i < list1.size(); i++) {
                        System.out.println("Номер:  " + i + " - " + list1.get(i));
                    }
                    break;

                case "add":
                    if ((int) order.charAt(3) == 32 && (int) order.charAt(5) == 32) {
                        position = Integer.parseInt(Character.toString(order.charAt(4)));
                        temp = 6;
                    } else {
                        position = list1.size();
                        temp = 4;
                    }
                    addContain = order.substring(order.indexOf(cleanCommand) + temp);
                    list1.add(position, addContain);
                    System.out.println("Добавлено: ");
                    System.out.println("Номер: " + position + " - " + list1.get(position));
                    break;

                case "edit":
                    if ((int) order.charAt(4) == 32 && (int) order.charAt(6) == 32) {
                        position = Integer.parseInt(Character.toString(order.charAt(5)));
                        temp = 7;
                    } else {
                        continue;
                    }
                    addContain = order.substring(order.indexOf(cleanCommand) + temp);
                    list1.set(position, addContain);
                    System.out.println("Заменено содержимое: ");
                    System.out.println("Номер: " + position + " - " + list1.get(position));
                    break;

                case "delete":
                    if ((int) order.charAt(6) == 32) {
                        position = Integer.parseInt(Character.toString(order.charAt(7)));
                        temp = 7;
                    } else {
                        continue;
                    }
                    System.out.println("Удалено: ");
                    System.out.println("Номер: " + position + " - " + list1.get(position));
                    list1.remove(position);
                    break;
            }
        }
        System.out.println("Программа закончила работу");
    }
    // создадим метод для очистки строки до команды
    public static String commandFromOrder(String text) {
        String command = "";
        String listCom = "LIST"; // задаем список команд для управления списком
        String addCom = "ADD";
        String editCom = "EDIT";
        String deleteCom = "DELETE";
        // "очищаем" строку до "чистой" команды
        if (text.toLowerCase().contains(addCom.toLowerCase())) {
            command = "add";
        } else if (text.toLowerCase().contains(listCom.toLowerCase())) {
            command = "list";
        } else if (text.toLowerCase().contains(editCom.toLowerCase())) {
            command = "edit";
        } else if (text.toLowerCase().contains(deleteCom.toLowerCase())) {
            command = "delete";
        }
        return command;
    }
}