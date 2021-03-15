// Пропишите в проекте ConsoleCustomerList все возможные варианты защиты
// от некорректной работы и её преждевременного завершения.
//Программа должна выбрасывать исключения (Exception) при неверном формате
//команды (количество элементов в команде),
//номера телефона, email.

public class CsExceptions extends Exception {
    public static void checkExceptions(String data)
    {
        String[] components = data.split("\\s+");
        if (components.length !=4) {
            throw new IllegalArgumentException("Неверный формат. Повторите ввод в правильном формате: \n"
                    + "add Василий Петров vasily.petrov@gmail.com +79215637722");
        }
        if (!components[2].contains("@")) {
            throw new IllegalArgumentException("Неверный формат ввода e-mail. Повторите ввод в правильном формате: \n"
                    + "add Василий Петров vasily.petrov@gmail.com +79215637722");
        }
        if (components[3].charAt(1) != '7' || components[3].length() !=12) {
            throw new IllegalArgumentException("Неверный формат номера телефона. Повторите ввод в правильном формате: \n"
                    + "add Василий Петров vasily.petrov@gmail.com +79215637722");
        }
    }

}
