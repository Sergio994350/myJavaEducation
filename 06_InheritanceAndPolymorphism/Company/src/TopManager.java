public class TopManager extends EmployeeAbs implements Employee {
    //TopManager — зарплата складывается из фиксированной части
// и бонуса в виде 150% от заработной платы,
// если доход компании более 10 млн рублей.
    private String name;
    private double salary;
    private String position;

    public TopManager(String name, double salary, String position) {
        this.name = name;
        this.salary = salary;
        this.position = position;
    }


    public void hire() {
        TopManager employee = new TopManager(name, salary, position);
        employee.setName(Company.nameGenerator());
        employee.setPosition(employee.POSITION_TM);
        double fix = Math.random() * 100000 + 100000; // фикс оклад (случайным образом от 100 до 200 тыс)
        double topManagerBonus = 0;
        if (companyIncome > 10000000) {
            topManagerBonus = fix * 1.5; // бонус топ-менеджера
        }
        employee.setSalary((double) (Math.round(100 * (fix + topManagerBonus))) / 100);
        Company.employeeList.add(new TopManager(employee.name, employee.salary, employee.position));
        Company.employeeCount++;
        System.out.println(employee.name + " " + employee.position + " " + employee.salary);
    }

    public void hireAll(int quantity, String position) {
        if (position.equalsIgnoreCase("TopManager")) {
            for (int i = 0; i < quantity; i++) {
                TopManager employee = new TopManager(name, salary, position);
                employee.setName(Company.nameGenerator());
                employee.setPosition(employee.POSITION_TM);
                double fix = 100000; // фикс оклад
                double topManagerBonus = 0;
                if (companyIncome > 10000000) {
                    topManagerBonus = fix * 1.5; // бонус топ-менеджера
                }
                employee.setSalary(fix + topManagerBonus);
                Company.employeeList.add(new TopManager(employee.name, employee.salary, employee.position));
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