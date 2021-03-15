package bankExperiments;
import java.text.ParseException;
import java.util.Scanner;
//Изучите методы и переменные проекта задания 6.1
// и установите подходящие модификаторы доступа у методов и переменных.

public class BankAccount {
    private double balance;
    private String ownerName;

    protected BankAccount(String ownerName, double balance) {
        this.ownerName = ownerName;
        this.balance = balance;
    }

    // создадим геттеры и сеттеры
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // метод для снятия ден средств со счета
    private static double getFromBankAccount(double balance) {
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
        return balance;
    }

    // метод для пополнения ден средств на счете
    private static double putOnBankAccount(double balance) {
        System.out.println("Пожалуйста, введите сумму пополнения счёта: ");
        double putOnMoney = new Scanner(System.in).nextDouble();
        if (putOnMoney >= 0) {
            balance = balance + putOnMoney;
        } else if (putOnMoney < 0) {
            System.out.println("Введена ошибочная сумма пополнения");
        }
        return balance;
    }

    // метод для получения остатка ден средств на счете
    private static void printMoneyOnBankAccount(double balance) {
        System.out.println("Остаток на счёте составляет: " + balance);
    }

    public static void main(String[] args) throws ParseException {

        double balance = 1000;
        String name = "Sergey Tarasov";
        BankAccount MyAccount = new BankAccount(name, balance);
        DepositBankAccount MyAccount1 = new DepositBankAccount(name, balance);
        CardBankAccount MyAccount2 = new CardBankAccount(name, balance);
        int command = 1;

        while (command != 0) {
            System.out.println("Пожалуйста, введите код операции со счетом: ");
            System.out.println("0 - выйти из программы\n1 - снять деньги со счёта\n2 - положить деньги на счёт\n3 - узнать остаток");
            command = new Scanner(System.in).nextInt();

            if (command == 1) {
                MyAccount.balance = getFromBankAccount(MyAccount.balance);
                printMoneyOnBankAccount(MyAccount.balance);
            } else if (command == 2) {
                MyAccount.balance = putOnBankAccount(MyAccount.balance);
                printMoneyOnBankAccount(MyAccount.balance);
            } else if (command == 3) {
                printMoneyOnBankAccount(MyAccount.balance);
            } else if (command == 0) {
                break;
            } else {
                System.out.println("Ошибка ввода. Повторите ввод");
            }
        }
    }
}