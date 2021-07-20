package sql.dao;


import model.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao extends Database {

    private void addToStudent(Student student, ResultSet executeQuery) throws SQLException {
        student.setSn(executeQuery.getString("sn"));
        student.setName(executeQuery.getString("name").trim());
        student.setSex(executeQuery.getString("sex").trim());
        student.setGrade(executeQuery.getString("grade").trim());
        student.setStuClass(executeQuery.getString("stuClass").trim());
        student.setBirthday(executeQuery.getString("birthday").trim());
        student.setMath(executeQuery.getInt("math"));
        student.setJava(executeQuery.getInt("java"));
        student.setEnglish(executeQuery.getInt("english"));
        student.setSports(executeQuery.getInt("sports"));
        student.setTotalScores(student.getEnglish() + student.getJava() + student.getMath() + student.getSports());
    }

    public void addInformation(Student stu) {
        String sql = "insert into Information(sn, name, sex, birthday, grade, stuClass) values(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, stu.getSn());
            pStatement.setString(2, stu.getName());
            pStatement.setString(3, stu.getSex());
            pStatement.setString(4, stu.getBirthday());
            pStatement.setString(5, stu.getGrade());
            pStatement.setString(6, stu.getStuClass());
            pStatement.execute();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSN(Student stu) {
        String sql = "select MaxNum from count where Grade = ? and StuClass = ?";
        String sn = null;
        try {
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, stu.getGrade());
            pStatement.setString(2, stu.getStuClass());
            ResultSet executeQuery = pStatement.executeQuery();
            int i = 0;
            if (executeQuery.next()) {
                i = executeQuery.getInt("MaxNum");
            }
            if(i == 0) {
                addCount(stu);
            }else {
                updateCount(stu, i + 1);
            }
            i++;
            if(i < 10) {
                sn = stu.getGrade() + stu.getStuClass() + "0" + i;
            } else {
                sn = stu.getGrade() + stu.getStuClass() + i;
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sn;
    }
    public void addCount(Student stu) {
        String sql = "insert into count(Grade, StuClass, MaxNum) values(?, ?, ?)";
        try {
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, stu.getGrade());
            pStatement.setString(2, stu.getStuClass());
            pStatement.setString(3, String.valueOf(1));
            pStatement.execute();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateCount(Student stu, int maxCount) {
        String sql = "update count set MaxNum = ? where Grade = ? and StuClass = ?";
        try {
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, String.valueOf(maxCount));
            pStatement.setString(2, stu.getGrade());
            pStatement.setString(3, stu.getStuClass());
            pStatement.execute();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Student getStudentFromSN(String sn) {
        Student student = null;
        try {
            String sql = "select * from Information where sn = ?";
            PreparedStatement prStatement = connection.prepareStatement(sql);
            prStatement.setString(1, sn);
            ResultSet executeQuery = prStatement.executeQuery();
            if (executeQuery.next()) {
                student = new Student();
                addToStudent(student, executeQuery);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public List<Student> getAllStudent() {
        List<Student> studentList = new ArrayList<>();
        try {
            String sql = "select * from Information";
            PreparedStatement prStatement = connection.prepareStatement(sql);
            ResultSet executeQuery = prStatement.executeQuery();
            while (executeQuery.next()) {
                Student student = new Student();
                addToStudent(student, executeQuery);
                studentList.add(student);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }
    public List<Student> getAllStudentFromName(String name) {
        List<Student> studentList = new ArrayList<>();
        try {
            String sql = "select * from Information where name like ?";
            PreparedStatement prStatement = connection.prepareStatement(sql);
            prStatement.setString(1, "%" + name + "%");
            ResultSet executeQuery = prStatement.executeQuery();
            while (executeQuery.next()) {
                Student student = new Student();
                addToStudent(student, executeQuery);
                studentList.add(student);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }
    public void deleteStudent(String sn) {
        try {
            String sql = "delete from Information where sn = ?";
            PreparedStatement prStatement = connection.prepareStatement(sql);
            prStatement.setString(1, sn);
            prStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateStudent(String sn, String updateName, String updateValue) {
        String sql = "update Information set "+ updateName + " = ? where sn = ?";
        try {
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, updateValue);
            pStatement.setString(2, sn);
            pStatement.execute();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Student> getClassStudent(String grade, String stuClass) {
        List<Student> studentList = new ArrayList<>();
        try {
            String sql = "select * from Information where grade = ? and stuClass = ?";
            PreparedStatement prStatement = connection.prepareStatement(sql);
            prStatement.setString(1, grade);
            prStatement.setString(2, stuClass);
            ResultSet executeQuery = prStatement.executeQuery();
            while (executeQuery.next()) {
                Student student = new Student();
                addToStudent(student, executeQuery);
                studentList.add(student);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }
}
