import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
    private static ArrayList<String> tasks = new ArrayList<>();
    private static final String FILE_NAME = "tasks.txt";

    public static void main(String[] args) {
        loadTasksFromFile();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Ứng dụng Quản lý Công việc ===");
            System.out.println("1. Xem danh sách công việc");
            System.out.println("2. Thêm công việc");
            System.out.println("3. Sửa công việc");
            System.out.println("4. Xóa công việc");
            System.out.println("5. Thoát");
            System.out.print("Chọn tùy chọn (1-5): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    displayTasks();
                    break;
                case 2:
                    System.out.print("Nhập công việc mới: ");
                    String task = scanner.nextLine();
                    addTask(task);
                    break;
                case 3:
                    System.out.print("Nhập số thứ tự công việc cần sửa: ");
                    int indexToEdit = scanner.nextInt() - 1;
                    scanner.nextLine();
                    System.out.print("Nhập nội dung công việc mới: ");
                    String newTask = scanner.nextLine();
                    editTask(indexToEdit, newTask);
                    break;
                case 4:
                    System.out.print("Nhập số thứ tự công việc cần xóa: ");
                    int indexToDelete = scanner.nextInt() - 1;
                    deleteTask(indexToDelete);
                    break;
                case 5:
                    saveTasksToFile();
                    System.out.println("Tạm biệt!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private static void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Danh sách công việc trống.");
        } else {
            System.out.println("\nDanh sách công việc:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void addTask(String task) {
        if (task.trim().isEmpty()) {
            System.out.println("Công việc không được để trống.");
        } else {
            tasks.add(task);
            System.out.println("Đã thêm công việc: " + task);
            saveTasksToFile();
        }
    }

    private static void editTask(int index, String newTask) {
        if (index >= 0 && index < tasks.size()) {
            if (newTask.trim().isEmpty()) {
                System.out.println("Công việc không được để trống.");
            } else {
                tasks.set(index, newTask);
                System.out.println("Đã sửa công việc thành: " + newTask);
                saveTasksToFile();
            }
        } else {
            System.out.println("Số thứ tự không hợp lệ.");
        }
    }

    private static void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            String removedTask = tasks.remove(index);
            System.out.println("Đã xóa công việc: " + removedTask);
            saveTasksToFile();
        } else {
            System.out.println("Số thứ tự không hợp lệ.");
        }
    }

    private static void loadTasksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Chưa có file tasks.txt, sẽ tạo mới khi lưu.");
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }

    private static void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu file: " + e.getMessage());
        }
    }
}