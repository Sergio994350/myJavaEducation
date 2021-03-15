public abstract class EmployeeAbs implements Comparable<EmployeeAbs>, Employee {

    protected final String POSITION_MN = "Manager";
    protected final String POSITION_TM = "TopManager";
    protected final String POSITION_OP = "Operator";
    String name = "";
    double salary = 0;
    double incomeForCompany = 0;
    String position = "";
    public static double companyIncome = 0;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMonthSalary() {
        return salary;
    }

    public double setSalary(double salary) {
        this.salary = salary;
        return salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}