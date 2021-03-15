package airports;
import java.util.*;
//Распечатайте с помощью библиотеки airport.jar время вылета
//и модели самолётов, вылетающих в ближайшие два часа.

public class Airport_Main {
    public static void main(String[] args) {

        Date date1 = new Date();
        Date date2 = new Date();
        date1.getTime();
        date2.setTime(date1.getTime() + 1000 * 60 * 60 * 2);
        int planesAmount = 200 + (int) ((Math.random() - 0.5D) * 50.0D);
        int count = 0;

        System.out.println("Код рейса:\t" + "Модель самолета:\t" + "Дата и время вылета");
        for (int i = 0; i < planesAmount; i++) {
            Flight flight1 = Airport.getInstance().generateFlight(Flight.Type.DEPARTURE);
            Flight flight2 = Airport.getInstance().generateFlight(Flight.Type.ARRIVAL);
            if (flight1.getDate().after(date1) && flight1.getDate().before(date2)) {
                System.out.println(flight1.getCode() + "\t\t" + flight1.getAircraft() + "\t\t" + flight1.getDate());
                count++;
            }
        }
        System.out.println("Всего рейсов: " + planesAmount);
        System.out.println("Рейсов, улетающих в ближайшие 2 часа: " + count);
    }
}