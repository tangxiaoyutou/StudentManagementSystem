package com.tang.servlet;

import com.google.gson.Gson;
import model.Student;
import model.util.WebUtil;
import sql.dao.StudentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "StudentServlet", urlPatterns = "/student")
public class StudentServlet extends BaseServlet {

    public void addInformation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Student student = WebUtil.copyParamToBean(request.getParameterMap(), new Student());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (!student.getName().isEmpty() && !student.getSex().isEmpty()) {
            student.setSn((new StudentDao()).getSN(student));
            (new StudentDao()).addInformation(student);
            resultMap.put("addResult", "添加成功, 学号为" + student.getSn());
        } else if (student.getName().isEmpty()) {
            resultMap.put("addResult", "请输入姓名");
        } else {
            resultMap.put("addResult", "请选择性别");
        }
        Gson gson = new Gson();
        String gsonString = gson.toJson(resultMap);
        response.getWriter().write(gsonString);
    }

    public void searchStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = WebUtil.copyParamToBean(request.getParameterMap(), new Student());
        List<Student> studentList = new ArrayList<Student>();
        if (student.getSn().isEmpty() && student.getName().isEmpty()) {
            studentList = (new StudentDao().getAllStudent());
        } else if (!student.getSn().isEmpty()) {
            student = (new StudentDao()).getStudentFromSN(student.getSn());
            if (student != null) {
                studentList.add(student);
            }
        } else {
            studentList = (new StudentDao().getAllStudentFromName(student.getName()));
        }
        Gson gson = new Gson();
        String gsonString = gson.toJson(studentList);
        response.getWriter().write(gsonString);
    }

    public void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = WebUtil.copyParamToBean(request.getParameterMap(), new Student());
        (new StudentDao()).deleteStudent(student.getSn());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("deleteResult", "删除成功");
        Gson gson = new Gson();
        String gsonString = gson.toJson(resultMap);
        response.getWriter().write(gsonString);
    }

    public void updateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> requestParameterMap = request.getParameterMap();
        Iterator<Map.Entry<String, String[]>> entries = requestParameterMap.entrySet().iterator();
        int i = 0;
        String snValue = null, updateName = null, updateValue = null;
        while (entries.hasNext()) {
            Map.Entry<String, String[]> entry = entries.next();
            String key = entry.getKey();
            String value = entry.getValue()[0];
            if (i == 1) {
                snValue = value;
            } else if (i == 2) {
                updateName = key;
                updateValue = value;
            }
            i++;
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (updateName.equals("math") || updateName.equals("java") || updateName.equals("sports") || updateName.equals("english")) {
            if (Integer.parseInt(updateValue) < 0 && Integer.parseInt(updateValue) > 100) {
                resultMap.put("updateResult", "分数应大于等于0，且小于100");
                i = 0;
            }
        }
        if (i > 0) {
            (new StudentDao()).updateStudent(snValue, updateName, updateValue);
            resultMap.put("updateResult", "更新成功");
        }
        Gson gson = new Gson();
        String gsonString = gson.toJson(resultMap);
        response.getWriter().write(gsonString);
    }
    public void searchClassScores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = WebUtil.copyParamToBean(request.getParameterMap(), new Student());
        List<Student> studentList = (new StudentDao()).getClassStudent(student.getGrade(),student.getStuClass());
        studentList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student arg0, Student arg1) {
                return arg1.getTotalScores() - arg0.getTotalScores();
            }
        });
        Gson gson = new Gson();
        String gsonString = gson.toJson(studentList);
        response.getWriter().write(gsonString);
    }
}
