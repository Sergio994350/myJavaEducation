import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

//          По поводу 4.6, прошу доработать.
//          Не используйте явное присваивание дня недели. Используйте алгоритм
////        Calendar calendar = new GregorianCalendar(year, (month - 1), day);
////        Calendar calendar1 = new GregorianCalendar();
////        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy - E", Locale.US);
////        int counter = 0;
////        StringBuilder tempResult = new StringBuilder();
public class Main {

    public static void main(String[] args) {

        // вводим свой день рождения
        int day = 10;
        int month = Calendar.JUNE;
        int year = 1975;
        System.out.println("Мои дни рождения: ");
        collectBirthdays(year, month, day);
    }

    public static String collectBirthdays(int year, int month, int day) {
        Calendar calendar = new GregorianCalendar(year, month, day);
        for (int i = 0; i <= 45; i++) {
            int year1 = year + i;
            calendar.set(Calendar.YEAR, year1);
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE - d.MM.yyyy");
            System.out.print(i + " - " + dateFormat.format(calendar.getTime()) + "\n");
        }
        return "";
    }
}