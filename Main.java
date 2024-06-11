import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    private int taskId;
    private String description;
    private LocalDate dueDate;
    private boolean completed;

    public Task(int taskId, String description, LocalDate dueDate) {
        this.taskId = taskId;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = false;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    public void markAsCompleted() {
        this.completed = true;
    }
}

class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markTaskAsCompleted(int taskId) {
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                task.markAsCompleted();
                return;
            }
        }
        System.out.println("Task ID not found.");
    }

    public void deleteTask(int taskId) {
        tasks.removeIf(task -> task.getTaskId() == taskId);
    }

    public void viewAllTasks() {
        for (Task task : tasks) {
            System.out.println(task.getDescription());
        }
    }

    public void generateReportForToday() {
        LocalDate today = LocalDate.now();
        for (Task task : tasks) {
            if (task.getDueDate().equals(today)) {
                System.out.println(task.getDescription());
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("~Welcome to the TaskManagerApp~");
            System.out.println("Below you'd find the Menu. Please choose one option:");
            System.out.println("1. Add a new task.");
            System.out.println("2. Mark a task as completed.");
            System.out.println("3. Delete a task.");
            System.out.println("4. View all tasks.");
            System.out.println("5. Create a report for today's tasks.");
            System.out.println("6. Exit.");
            System.out.println("");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter the task ID: ");
                    int taskId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter a description of the task: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter the due date (yyyy-MM-dd): ");
                    String dueDateStr = scanner.nextLine();
                    LocalDate dueDate = LocalDate.parse(dueDateStr);
                    Task newTask = new Task(taskId, description, dueDate);
                    taskManager.addTask(newTask);
                    System.out.println("Task added successfully.");
                    break;
                case "2":
                    System.out.print("Enter the task ID to mark it as completed: ");
                    int taskIdToComplete = Integer.parseInt(scanner.nextLine());
                    taskManager.markTaskAsCompleted(taskIdToComplete);
                    System.out.println("Task marked as done.");
                    break;
                case "3":
                    System.out.print("Enter the task ID to delete it: ");
                    int taskIdToDelete = Integer.parseInt(scanner.nextLine());
                    taskManager.deleteTask(taskIdToDelete);
                    System.out.println("Task deleted.");
                    break;
                case "4":
                    taskManager.viewAllTasks();
                    break;
                case "5":
                    taskManager.generateReportForToday();
                    break;
                case "6":
                    System.out.println("Exiting the program successfully...");
                    return;
                default:
                    System.out.println("Please enter a number that is on the menu.");
            }
        }
    }
}
