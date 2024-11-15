package TaskManagement;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private List<Task> tasks;
    private static final String FILE_NAME = "tasks.dat";

    public TaskManager() {
        tasks = loadTasks();
    }

    private List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            tasks = (List<Task>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous tasks found.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    private void saveTasks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTask(String title, String description, String priority, String dueDateStr, String status) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dueDate = sdf.parse(dueDateStr);
            Task newTask = new Task(title, description, priority, dueDate, status);
            tasks.add(newTask);
            saveTasks();
            System.out.println("Task added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding task: " + e.getMessage());
        }
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to display.");
            return;
        }
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void updateTaskStatus(String title, String newStatus) {
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                task.setStatus(newStatus);
                saveTasks();
                System.out.println("Task status updated!");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void deleteTask(String title) {
        tasks.removeIf(task -> task.getTitle().equalsIgnoreCase(title));
        saveTasks();
        System.out.println("Task deleted.");
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        while (true) {
            System.out.println("\nTask Management System");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Update Task Status");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter task priority (High/Medium/Low): ");
                    String priority = scanner.nextLine();
                    System.out.print("Enter due date (yyyy-MM-dd): ");
                    String dueDate = scanner.nextLine();
                    System.out.print("Enter task status (To Do/In Progress/Completed): ");
                    String status = scanner.nextLine();
                    taskManager.addTask(title, description, priority, dueDate, status);
                    break;
                case 2:
                    taskManager.displayTasks();
                    break;
                case 3:
                    System.out.print("Enter task title to update status: ");
                    String taskToUpdate = scanner.nextLine();
                    System.out.print("Enter new status (To Do/In Progress/Completed): ");
                    String newStatus = scanner.nextLine();
                    taskManager.updateTaskStatus(taskToUpdate, newStatus);
                    break;
                case 4:
                    System.out.print("Enter task title to delete: ");
                    String taskToDelete = scanner.nextLine();
                    taskManager.deleteTask(taskToDelete);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }
}
