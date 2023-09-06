package DAO;

import connection.MyConnection;
import model.ClassOfStudent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO implements I_DAO<ClassOfStudent>{
    private static final long serialVersionUID = 1L;
    private static final String SELECT_ALL_CLASS = "select * from CLASS;";
    private static final String SELECT_CLASS_BY_ID = "select * from CLASS where id =?;";
    private final Connection connection = MyConnection.getInstance();

    @Override
    public List<ClassOfStudent> selectAll() {
        List<ClassOfStudent> classOfStudents = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CLASS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                classOfStudents.add(new ClassOfStudent(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classOfStudents;
    }

    @Override
    public ClassOfStudent selectOne(int id) {
        ClassOfStudent classOfStudent = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CLASS_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                classOfStudent = new ClassOfStudent(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classOfStudent;
    }

    @Override
    public void insert(ClassOfStudent aClass) {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public void update(ClassOfStudent aClass) throws SQLException {

    }
}
