package controller;

import DAO.ClassDAO;
import model.ClassOfStudent;
import model.Student;
import service.StudentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

@WebServlet(name = "StudentServlet", urlPatterns = "/students")
public class StudentServlet extends HttpServlet {
    private StudentService studentService;
    private ClassDAO classDAO;

    @Override
    public void init() throws ServletException {
        studentService = new StudentService();
        classDAO = new ClassDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "":
                    showAllStudent(req, resp);
                    break;
                case "createStudent":
                    showFormCreateStudent(req, resp);
                    break;
                case "editStudent":
                    showFormEditStudent(req, resp);
                    break;
                case "deleteStudent":
                    deleteStudent(req, resp);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        studentService.delete(req, resp);
        showAllStudent(req, resp);
    }

    private void showFormEditStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = studentService.selectOne(req, resp);
        req.setAttribute("student", student);
        List<ClassOfStudent> classOfStudents = classDAO.selectAll();
        req.setAttribute("classOfStudents", classOfStudents);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/formEditStudent.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showFormCreateStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ClassOfStudent> classOfStudents = classDAO.selectAll();
        req.setAttribute("classOfStudents", classOfStudents);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/formCreateStudent.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showAllStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> studentList = studentService.selectAll(req, resp);
        req.setAttribute("studentList", studentList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/show_all_student.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "createStudent":
                    createStudent(req, resp);
                    break;
                case "editStudent":
                    editStudent(req, resp);
                    break;
                case "search":
                    search(req, resp);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("nameSearch");
        List<Student> studentList1 = studentService.selectAll(req, resp);
        List<Student> studentList = new ArrayList<>();
        for (Student student: studentList1) {
            if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                studentList.add(student);
            }
        }
        req.setAttribute("studentList", studentList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("view/showStudentSearch.jsp");
        requestDispatcher.forward(req, resp);
    }
    private void editStudent(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        studentService.update(req, resp);
        showAllStudent(req, resp);
    }

    private void createStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        studentService.insert(req, resp);
        showAllStudent(req, resp);
    }
}
