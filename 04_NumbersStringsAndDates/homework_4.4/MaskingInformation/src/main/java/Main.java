import java.util.Scanner;

public class Main {
    // "Текст = Номер кредитной карты <4008 1234 5678> 8912"
    // код <  60,  код >  62
    public static void main(String[] args) {
        // можем ввести данные с консоли:
        // System.out.println("Введите данные (конфиденциальные данные заключите в скобки < > )");
        // String text = new Scanner(System.in).nextLine();

        // или можем задать текст напрямую:
        String text = "Номер кредитной карты <4008 1234 5678> 8912";
//         String text = "Номер кредитной карты 4008 1234 5678 8912";
//        String text = "Пин код <6160>";
//         String text = "Номер паспорта <45 08 361>513";
//         String text = "Номер паспорта <<45 08 36513";
        System.out.println(searchAndReplaceDiamonds(text, "****"));
    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        int firstDiam = text.indexOf("<");
        int secondDiam = text.indexOf(">");

        if ((firstDiam != -1) && (secondDiam != -1)) {
            String createdText = text.replace((text.substring((firstDiam), (secondDiam + 1))), placeholder);
            text = createdText;
        } else {
            String createdText = text;
        }
        return text;
    }
}