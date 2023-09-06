package service;

import DAO.ClassDAO;
import DAO.StudentDAO;
import model.ClassOfStudent;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class StudentService implements IService<Student>{
    private StudentDAO studentDAO;
    private ClassDAO classDAO;

    public StudentService() {
        this.studentDAO = new StudentDAO();
        classDAO = new ClassDAO();
    }

    @Override
    public void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email =  req.getParameter("email");
        LocalDate dateOfBirth = LocalDate.parse(req.getParameter("dateOfBirth"));
        String address = req.getParameter("address");
        String phoneNumber = req.getParameter("phoneNumber");
        int id_class = Integer.parseInt(req.getParameter("id_class"));
        ClassOfStudent classOfStudent = classDAO.selectOne(id_class);
        Student student = new Student(name,dateOfBirth, email, address, phoneNumber, classOfStudent);
        studentDAO.insert(student);
    }

    @Override
    public void update(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        int id = Integer.parseInt(req.getParameter("id_student"));
        String name = req.getParameter("name");
        String email =  req.getParameter("email");
        LocalDate dateOfBirth = LocalDate.parse(req.getParameter("dateOfBirth"));
        String address = req.getParameter("address");
        String phoneNumber = req.getParameter("phoneNumber");
        int id_class = Integer.parseInt(req.getParameter("id_class"));
        ClassOfStudent classOfStudent = classDAO.selectOne(id_class);
        Student student = new Student(id, name,dateOfBirth, email, address, phoneNumber, classOfStudent);
        studentDAO.update(student);
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        int id_student = Integer.parseInt(req.getParameter("id_student"));
        studentDAO.delete(id_student);
    }

    @Override
    public List<Student> selectAll(HttpServletRequest req, HttpServletResponse resp) {
        List<Student> studentList = studentDAO.selectAll();
        return studentList;
    }

    @Override
    public Student selectOne(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id_student"));
        Student student = studentDAO.selectOne(id);
        return student;
    }
}
