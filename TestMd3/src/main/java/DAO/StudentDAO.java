package DAO;

import connection.MyConnection;
import model.ClassOfStudent;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements I_DAO<Student> {
    private static final long serialVersionUID = 1L;
    private static final String SELECT_ALL_STUDENT = "select * from STUDENT;";
    private static final String SELECT_STUDENT_BY_ID = "select * from STUDENT where id =?;";
    private static final String UPDATE_STUDENT_SQL = "update STUDENT set name = ?, email = ?, dateOfBirth = ?, address = ?, phoneNumber = ?, id_class = ? where id = ?;";
    private static final String DELETE_STUDENT_SQL = "delete from student where id = ?;";
    private static final String INSERT_STUDENT_SQL = "insert into STUDENT(name, email, dateOfBirth, address, phoneNumber, id_class) values (?, ?, ?, ?, ?, ?);";

    private final Connection connection = MyConnection.getInstance();
    private ClassDAO classDAO;

    public StudentDAO() {
        this.classDAO = new ClassDAO();
    }

    @Override
    public List<Student> selectAll() {
        List<Student> studentList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_STUDENT)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String date = resultSet.getString("dateOfBirth");
                String email = resultSet.getString("email");
                LocalDate dateOfBirth = LocalDate.parse(date);
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                int id_class = resultSet.getInt("id_class");
                ClassOfStudent classOfStudent = classDAO.selectOne(id_class);
                Student student = new Student(id, name, dateOfBirth, email, address, phoneNumber, classOfStudent);
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    @Override
    public Student selectOne(int id) {
        Student student = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_STUDENT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String date = resultSet.getString("dateOfBirth");
                String email = resultSet.getString("email");
                LocalDate dateOfBirth = LocalDate.parse(date);
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                int id_class = resultSet.getInt("id_class");
                ClassOfStudent classOfStudent = classDAO.selectOne(id_class);
                student = new Student(id, name, dateOfBirth, email, address, phoneNumber, classOfStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public void insert(Student student) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setObject(3, student.getDateOfBirth());
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setString(5, student.getPhoneNumber());
            preparedStatement.setInt(6, student.getClassOfStudent().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(Student student) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT_SQL)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setObject(3, student.getDateOfBirth());
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setString(5, student.getPhoneNumber());
            preparedStatement.setInt(6, student.getClassOfStudent().getId());
            preparedStatement.setInt(7, student.getId());
            preparedStatement.executeUpdate();
        }
    }
}
