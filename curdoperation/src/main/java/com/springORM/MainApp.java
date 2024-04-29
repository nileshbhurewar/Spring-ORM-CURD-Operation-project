package com.springORM;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.DepartmentDAO;
import com.dao.StudentDAO;
import com.entities.Department;
import com.entities.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("com/springORM/config.xml");

       final StudentDAO studentDAO = (StudentDAO) context.getBean("studentDAO");
       final DepartmentDAO departmentDAO = (DepartmentDAO) context.getBean("departmentDAO");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean running = true;
            while (running) {
                System.out.println("Choose an operation:");
                System.out.println("1. Add Student");
                System.out.println("2. View Student");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Add Department");
                System.out.println("6. View Department");
                System.out.println("7. Update Department");
                System.out.println("8. Delete Department");
                System.out.println("9. Exit");

                int choice = Integer.parseInt(reader.readLine());

                switch (choice) {
                    case 1:
                        addStudent(reader, studentDAO , departmentDAO);
                        break;
                    case 2:
                        viewStudent(reader, studentDAO);
                        break;
                    case 3:
                        updateStudent(reader, studentDAO);
                        break;
                    case 4:
                        deleteStudent(reader, studentDAO);
                        break;
                    case 5:
                        addDepartment(reader, departmentDAO);
                        break;
                    case 6:
                        viewDepartment(reader, departmentDAO);
                        break;
                    case 7:
                        updateDepartment(reader, departmentDAO);
                        break;
                    case 8:
                        deleteDepartment(reader, departmentDAO);
                        break;
                    case 9:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addStudent(BufferedReader reader, StudentDAO studentDAO, DepartmentDAO departmentDAO) throws IOException {
        System.out.println("Enter student name:");
        String name = reader.readLine();
        System.out.println("Enter student email:");
        String email = reader.readLine();
        
        // Fetch all departments and display their names and IDs
        List<Department> departments = departmentDAO.getAllDepartments();
        System.out.println("Available Departments:");
        for (Department department : departments) {
            System.out.println("ID: " + department.getId() + ", Name: " + department.getName());
        }
        System.out.println("Enter departmentId:");
        Long departmentId = Long.parseLong(reader.readLine());
        
        // Fetch department by id to associate with the student
        Department department = departmentDAO.getDepartmentById(departmentId);
        if (department == null) {
            System.out.println("Department with ID " + departmentId + " does not exist!");
            return; // Abort student creation
        }
        
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setDepartment(department); // Associate student with department
        studentDAO.addStudent(student);
        System.out.println("Student added successfully!");
    }

    private static void viewStudent(BufferedReader reader, StudentDAO studentDAO) throws IOException {
        System.out.println("Enter student id:");
        Long id = Long.parseLong(reader.readLine());
        Student student = studentDAO.getStudentById(id);
        if (student != null) {
            System.out.println("Student details:");
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Email: " + student.getEmail());
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void updateStudent(BufferedReader reader, StudentDAO studentDAO) throws IOException {
        System.out.println("Enter student id:");
        Long id = Long.parseLong(reader.readLine());
        Student student = studentDAO.getStudentById(id);
        if (student != null) {
            System.out.println("Enter new name:");
            String name = reader.readLine();
            System.out.println("Enter new email:");
            String email = reader.readLine();
            student.setName(name);
            student.setEmail(email);
            studentDAO.updateStudent(student);
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void deleteStudent(BufferedReader reader, StudentDAO studentDAO) throws IOException {
        System.out.println("Enter student id:");
        Long id = Long.parseLong(reader.readLine());
        studentDAO.deleteStudent(id);
        System.out.println("Student deleted successfully!");
    }

    private static void addDepartment(BufferedReader reader, DepartmentDAO departmentDAO) throws IOException {
        System.out.println("Enter department name:");
        String name = reader.readLine();
        System.out.println("Enter department location:");
        String location = reader.readLine();
        Department department = new Department();
        department.setName(name);
        department.setLocation(location);
        departmentDAO.addDepartment(department);
        System.out.println("Department added successfully!");
    }

    private static void viewDepartment(BufferedReader reader, DepartmentDAO departmentDAO) throws IOException {
        System.out.println("Enter department id:");
        Long id = Long.parseLong(reader.readLine());
        Department department = departmentDAO.getDepartmentById(id);
        if (department != null) {
            System.out.println("Department details:");
            System.out.println("ID: " + department.getId());
            System.out.println("Name: " + department.getName());
            System.out.println("Location: " + department.getLocation());
        } else {
            System.out.println("Department not found!");
        }
    }

    private static void updateDepartment(BufferedReader reader, DepartmentDAO departmentDAO) throws IOException {
        System.out.println("Enter department id:");
        Long id = Long.parseLong(reader.readLine());
        Department department = departmentDAO.getDepartmentById(id);
        if (department != null) {
            System.out.println("Enter new name:");
            String name = reader.readLine();
            System.out.println("Enter new location:");
            String location = reader.readLine();
            department.setName(name);
            department.setLocation(location);
            departmentDAO.updateDepartment(department);
            System.out.println("Department updated successfully!");
        } else {
            System.out.println("Department not found!");
        }
    }

    private static void deleteDepartment(BufferedReader reader, DepartmentDAO departmentDAO) throws IOException {
        System.out.println("Enter department id:");
        Long id = Long.parseLong(reader.readLine());
        departmentDAO.deleteDepartment(id);
        System.out.println("Department deleted successfully!");
        
    }
}

