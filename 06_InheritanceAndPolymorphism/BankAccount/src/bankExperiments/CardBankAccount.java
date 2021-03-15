package bankExperiments;
import java.util.Scanner;

 class CardBankAccount extends BankAccount {
    protected CardBankAccount(String ownerName, double balance) {
        super(ownerName, balance);
    }

    private static double getFromBankAccountCard(double balance) {
        System.out.println("Пожалуйста, введите сумму снятия средств со счёта: ");
        double removalMoney = new Scanner(System.in).nextDouble();
        if (removalMoney >= 0 && balance >= removalMoney) {
            balance = balance - (removalMoney * 1.01);
        } else if (removalMoney > balance) {
            removalMoney = balance / 1.01;
            balance = balance - (removalMoney * 1.01);
        } else if (removalMoney < 0) {
            System.out.println("Введена ошибочная сумма снятия");
        }
            return balance;
    }
}
