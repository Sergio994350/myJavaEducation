public class IndividualBusinessman extends Client {

    //У ИП — пополнение с комиссией 1%, если сумма меньше 1000 рублей.
    //И с комиссией 0,5%, если сумма больше либо равна 1000 рублей.
    protected double putOnAccount(double moneyPut) {
        if (moneyPut < 1000) {
            comissionPut = 0.01;
        } else if (moneyPut >= 1000) {
            comissionPut = 0.005;
        }
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