public class Manager extends EmployeeAbs implements Employee {
    //Manager — зарплата складывается из фиксированной части
    // и бонуса в виде 5% от заработанных для компании денег.
    // Количество заработанных денег для компании генерируйте
    // случайным образом от 115 000 до 140 000 рублей.
    private String name;
    private double salary;
    private String position;

    public Manager(String name, double salary, String position) {
        this.name = name;
        this.salary = salary;
        this.position = position;
    }

    public void hire() {
        Manager employee = new Manager(name, salary, position);
        employee.setName(Company.nameGenerator());
        employee.setPosition(employee.POSITION_MN);
        double fix = 40000; // фикс оклад
        double manCompIncome = Math.random() * 25000 + 115000; // заработанные для компании деньги
        companyIncome += manCompIncome;
        double managerPercent = manCompIncome * 0.05; // процент менеджера
        employee.setSalary((double) (Math.round(100 * (fix + managerPercent))) / 100);
        Company.employeeList.add(new Manager(employee.name, employee.salary, employee.position));
        Company.employeeCount++;
        System.out.println(employee.name + " " + employee.position + " " + employee.salary);
    }

    public void hireAll(int quantity, String position) {
        if (position.equalsIgnoreCase("Manager")) {
            for (int i = 0; i < quantity; i++) {
                EmployeeAbs employee = new Manager(name, salary, position);
                employee.setName(Company.nameGenerator());
                employee.setPosition(employee.POSITION_MN);
                double fix = 30000; // фикс оклад
                double manCompIncome = Math.random() * 25000 + 115000; // заработанные для компании деньги
                companyIncome += manCompIncome;
                double managerPercent = manCompIncome * 0.05; // процент менеджера
                employee.setSalary(fix + managerPercent);
                Company.employeeList.add(new Manager(employee.name, employee.salary, employee.position));
                Company.employeeCount++;
                System.out.println(employee.name + " " + employee.position + " " + employee.salary);
            }
        }
    }

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

    @Override
    public int compareTo(EmployeeAbs employeeAbs) {
        if (getMonthSalary() > employeeAbs.getMonthSalary()) {
            return 1;
        }
        if (getMonthSalary() < employeeAbs.getMonthSalary()) {
            return -1;
        } else {
            return 0;
        }
    }
}