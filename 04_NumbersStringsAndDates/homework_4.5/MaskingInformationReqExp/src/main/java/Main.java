import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        // "Текст = Номер кредитной карты <4008 1234 5678> 8912"
        // код <  60,  код >  62
        // можем ввести данные с консоли:
        // System.out.println("Введите данные (конфиденциальные данные заключите в скобки < > )");
        // String text = new Scanner(System.in).nextLine();

        // или можем задать текст напрямую:
//        String text = "Номер кредитной карты <4008 1234 5678> 8912";
        String text = "Номер кредитной карты <4008> 1234 <5678> 8912";
//        String text = "Номер кредитной карты 4008 1234 5678 8912";
//        String text = "Пин код <6160>";
//        String text = "Номер паспорта <45 08 361>513";
//        String text = "Номер паспорта <<45 08 36513";

        // int start(): возвращает индекс текущего совпадения
        //int end(): возвращает индекс следующего совпадения после текущего
        //String replaceAll(String str): заменяет все найденные совпадения
        // подстрокой str и возвращает измененную строку с учетом замен
        //  \\s[a-zA-Z]{5}\\s — описывает слово из 5 латинских символов, окруженное пробелами.

//        String text1 = text.split(">")[0]; // регулярные выражения
//        String text2 = text1.split("<")[1];
//        String text3 = text.replaceAll(text2,"****");
//        String text5 = text3.split(">")[0]; // регулярные выражения
//        String text6 = text5.split("<")[1];
//        String text7 = text6.replaceAll(text2,"****");
//        String text4 = text7.replaceAll("<|>", "");
//
//        System.out.println(text1);
//        System.out.println(text2);
//        System.out.println(text3);
//        System.out.println(text4);
//        System.out.println(text5);
//        System.out.println(text6);
//        System.out.println(text7);



//
//        text2.setCharAt(4, 'x');
//
//        System.out.println(myName);


           System.out.println(searchAndReplaceDiamonds(text, "****"));
        }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        char text2[] = text.toCharArray();
        for (int i = 0; i < text2.length; i++) {
            if (text2[i] == '<') {}

        }

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

















//            // TODO: реализовать метод, если в строке нет <> - вернуть строку без изменений
//            int firstDiam = text.indexOf("<");
//            int secondDiam = text.indexOf(">");
//            if ((firstDiam != -1) && (secondDiam != -1)) {
//                String createdText = text.replace((text.substring((firstDiam), (secondDiam + 1))), placeholder);
//                text = createdText;
//            } else {
//                String createdText = text;
//            }
//            return text;
//        }
//
//    }


