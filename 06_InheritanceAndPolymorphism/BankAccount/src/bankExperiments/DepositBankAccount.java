package bankExperiments;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

class DepositBankAccount extends BankAccount {
    protected DepositBankAccount(String ownerName, double balance) throws ParseException {
        super(ownerName, balance);
    }

    // метод для снятия ден средств со счета
    private double getFromBankAccountDeposit(double balance) {
        Calendar calendar = new GregorianCalendar(2020, 9 , 10);
        Date datePut = calendar.getTime();
        Date dateNow = new Date();
        long milliseconds = dateNow.getTime() - datePut.getTime();
        int days = (int) (milliseconds / (24 * 60 * 60 * 1000));
        if (days > 30) {
            System.out.println("Пожалуйста, введите сумму снятия средств со счёта: ");
            double removalMoney = new Scanner(System.in).nextDouble();
            if (removalMoney >= 0 && balance >= removalMoney) {
                balance -= removalMoney;
            } else if (removalMoney > balance) {
                removalMoney = balance;
                balance -= removalMoney;
            } else if (removalMoney < 0) {
                System.out.println("Введена ошибочная сумма снятия");
            }
        } else {
            System.out.println("Вы не можете снять средства со счета,\n 30 дней не прошло");
        }
        return balance;
    }
}



