import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee [name=" + name + ", id=" + id + ", salary=INR " + String.format("%.2f", calculateSalary()) + " /-]";
    }
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

class PayrollSystem {
    private List<Employee> employeeList;

    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(int id) {
        Employee employeeToRemove = null;
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }
        }
        if (employeeToRemove != null) {
            employeeList.remove(employeeToRemove);
        }
    }

    public void displayEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees in the system.");
        } else {
            System.out.println("Current Employees:");
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        PayrollSystem payrollSystem = new PayrollSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Full-Time Employee");
            System.out.println("2. Add Part-Time Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Display All Employees");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 5) {
                System.out.println("Exiting...");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String fullTimeName = scanner.nextLine();
                    System.out.print("Enter ID: ");
                    int fullTimeId = scanner.nextInt();
                    System.out.print("Enter monthly salary: ");
                    double monthlySalary = scanner.nextDouble();
                    payrollSystem.addEmployee(new FullTimeEmployee(fullTimeName, fullTimeId, monthlySalary));
                    System.out.println("Full-Time Employee added successfully!");
                    break;

                case 2:
                    System.out.print("Enter name: ");
                    String partTimeName = scanner.nextLine();
                    System.out.print("Enter ID: ");
                    int partTimeId = scanner.nextInt();
                    System.out.print("Enter hours worked: ");
                    int hoursWorked = scanner.nextInt();
                    System.out.print("Enter hourly rate: ");
                    double hourlyRate = scanner.nextDouble();
                    payrollSystem.addEmployee(new PartTimeEmployee(partTimeName, partTimeId, hoursWorked, hourlyRate));
                    System.out.println("Part-Time Employee added successfully!");
                    break;

                case 3:
                    System.out.print("Enter employee ID to remove: ");
                    int idToRemove = scanner.nextInt();
                    payrollSystem.removeEmployee(idToRemove);
                    System.out.println("Employee removed successfully!");
                    break;

                case 4:
                    payrollSystem.displayEmployees();
                    break;

                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }

        scanner.close();
    }
}