// баланс, который можно пополнять, снимать, и посмотреть какой сейчас баланс
// Реализуйте методы, при которых:
//У физических лиц пополнение и снятие происходит без комиссии.
//У юридических лиц — снятие с комиссией 1%.
//У ИП — пополнение с комиссией 1%, если сумма меньше 1000 рублей.
//И с комиссией 0,5%, если сумма больше либо равна 1000 рублей.

import java.util.Scanner;

abstract class Client {

    protected double balance;
    protected String name;
    protected double comissionPut;
    protected double comissionGet;

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected double putOnAccount(double moneyPut) {
        if (moneyPut >= 0) {
            balance = balance + moneyPut - (moneyPut * comissionPut);
            System.out.println("Баланс пополнен на " + moneyPut);
        } else {
            System.out.println("Ошибка. Сумма пополнения некорректна");
        }
        return balance;
    }

    protected double getFromAccount(double moneyGet) {
        if (moneyGet >= 0 && balance >= moneyGet * (1 + comissionGet)) {
            balance = balance - moneyGet - (moneyGet * comissionGet);
            System.out.println("Со счета снято " + moneyGet);
        } else if (balance < moneyGet * (1 + comissionGet)) {
            moneyGet = balance / (1 + comissionGet);
            balance = balance - moneyGet - (moneyGet * comissionGet);
            System.out.println("Со счета снято " + moneyGet);
        } else if (moneyGet < 0) {
            System.out.println("Ошибка. Сумма снятия некорректна");
        }
        return balance;
    }

    public static void main(String[] args) {

        // создадим расчетный счета клиентов:
        // физ лицо
        Client Ivan = new PrivatePerson();
        Ivan.setBalance(1000);
        Ivan.setName("Ivan");

        // юр лицо
        Client Puma = new CorporateBody();
        Puma.setBalance(1000000);
        Puma.setName("Puma");

        // ИП
        Client Sveta = new IndividualBusinessman();
        Sveta.setBalance(5000);
        Sveta.setName("Sveta");

        // выполним ряд действий с расчетными счетами
        System.out.println("Остаток на счете клиента " + Ivan.getName() + " составляет: " + Ivan.getBalance());
        Ivan.putOnAccount(50);
        Ivan.getFromAccount(100);
        System.out.println("Остаток на счете клиента " + Ivan.getName() + " составляет: " + Ivan.getBalance());
        System.out.println();

        System.out.println("Остаток на счете клиента " + Puma.getName() + " составляет: " + Puma.getBalance());
        Puma.putOnAccount(5000);
        Puma.getFromAccount(1000);
        System.out.println("Остаток на счете клиента " + Puma.getName() + " составляет: " + Puma.getBalance());
        System.out.println();

        System.out.println("Остаток на счете клиента " + Sveta.getName() + " составляет: " + Sveta.getBalance());
        Sveta.putOnAccount( 120);
        Sveta.getFromAccount(250);
        System.out.println("Остаток на счете клиента " + Sveta.getName() + " составляет: " + Sveta.getBalance());
    }
}