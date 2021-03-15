import java.util.Date;

//Тип счёта,Номер счета,Валюта,Дата операции,Референс проводки,Описание операции,Приход,Расход
public class Transaction {

    private String accountType;
    private String accountNumber;
    private String currency;
    private Date dateOfTransaction;
    private String codeOfTransaction;
    private String descriptionOfTransaction;
    private Double income;
    private Double expense;

    public Transaction(String accountType, String accountNumber, String currency,
                       Date dateOfTransaction, String codeOfTransaction,
                       String descriptionOfTransaction,
                       Double income, Double expense) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.dateOfTransaction = dateOfTransaction;
        this.codeOfTransaction = codeOfTransaction;
        this.descriptionOfTransaction = descriptionOfTransaction;
        this.income = income;
        this.expense = expense;
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public String getCodeOfTransaction() {
        return codeOfTransaction;
    }

    public void setCodeOfTransaction(String codeOfTransaction) {
        this.codeOfTransaction = codeOfTransaction;
    }

    public String getDescriptionOfTransaction() {
        return descriptionOfTransaction;
    }

    public void setDescriptionOfTransaction(String descriptionOfTransaction) {
        this.descriptionOfTransaction = descriptionOfTransaction;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}