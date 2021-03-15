public class PrivatePerson extends Client {

    //У физических лиц пополнение и снятие происходит без комиссии.
    protected double putOnAccount(double moneyPut) {
        comissionPut = 0;
        if (moneyPut >= 0) {
            balance = balance + moneyPut - (moneyPut * comissionPut);
            System.out.println("Баланс пополнен на " + moneyPut);
        } else {
            System.out.println("Ошибка. Сумма пополнения некорректна");
        }
        return balance;
    }

    protected double getFromAccount(double moneyGet) {
        comissionGet = 0;
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
}