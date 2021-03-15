public class Operator extends EmployeeAbs implements Employee {

    private String name;
    private double salary;
    private String position;

    public Operator(String name, double salary, String position) {
        this.name = name;
        this.salary = salary;
        this.position = position;
    }


    public void hire() {
        Operator employee = new Operator(name, salary, position);
        employee.setName(Company.nameGenerator());
        employee.setPosition(employee.POSITION_OP);
        double fix = 35000; // фикс оклад
        employee.setSalary(fix);
        Company.employeeList.add(new Operator(employee.name, employee.salary, employee.position));
        Company.employeeCount++;
        System.out.println(employee.name + " " + employee.position + " " + employee.salary);
    }

    public void hireAll(int quantity, String position) {
        if (position.equalsIgnoreCase("Operator")) {
            for (int i = 0; i < quantity; i++) {
                Operator employee = new Operator(name, salary, position);
                employee.setName(Company.nameGenerator());
                employee.setPosition(employee.POSITION_OP);
                double fix = 35000; // фикс оклад
                employee.setSalary(fix);
                Company.employeeList.add(new Operator(employee.name, employee.salary, employee.position));
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



