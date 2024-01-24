import java.util.ArrayList;
import java.util.Scanner;

class Expense {
    String date;
    String description;
    double amount;

    public Expense(String date, String description, double amount) {
        this.date = date;
        this.description = description;
        this.amount = amount;
    }
}

public class ExpenseTracker {
    private static ArrayList<Expense> expenses = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your budget: ");

        double budget;
        while (true) {
            try {
                budget = scanner.nextDouble();
                break;
            } catch (Exception e) {
                scanner.nextLine();
            }
        }

        while (true) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses and Analyze");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            while (true) {
                try {
                    choice = scanner.nextInt();
                    break;
                } catch (Exception e) {
                    scanner.nextLine();
                }
            }

            switch (choice) {
                case 1:
                    addExpense(scanner);
                    break;
                case 2:
                    viewExpenses(budget);
                    break;
                case 3:
                    System.out.println("Exiting Expense Tracker. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void addExpense(Scanner scanner) {
        System.out.println("\nEnter Expense Details:");

        System.out.print("Date (D/M/Y): ");
        String date = scanner.next();

        System.out.print("Description: ");
        scanner.nextLine();
        String description = scanner.nextLine();

        System.out.print("Amount: ");
        double amount;
        while (true) {
            try {
                amount = scanner.nextDouble();
                break;
            } catch (Exception e) {
                scanner.nextLine();
            }
        }

        Expense expense = new Expense(date, description, amount);
        expenses.add(expense);

        System.out.println("Expense added successfully!");
    }

    private static void viewExpenses(double budget) {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to display.");
            return;
        }

        System.out.println("\nExpense Summary:");
        System.out.printf("%-15s %-20s %-10s\n", "Date", "Description", "Amount (in rupees)");
        System.out.println("-------------------------------------------------------");

        double totalAmount = 0;

        for (Expense expense : expenses) {
            System.out.printf("%-15s %-20s %-10.2f\n", expense.date, expense.description, expense.amount);
            totalAmount += expense.amount;
        }

        System.out.println("-------------------------------------------------------");
        System.out.printf("%-15s %-20s %-10.2f\n", "Total", "", totalAmount);

        if (budget <= totalAmount) {
            System.out.printf("Your expenses have exceeded your budget by: %.2f/-\n", totalAmount - budget);
        } else {
            System.out.printf("You are under your budget by: %.2f/-\n", budget - totalAmount);
        }
    }
}