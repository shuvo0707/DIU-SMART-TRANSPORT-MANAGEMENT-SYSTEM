package com.vercity.transport.controller;

import com.vercity.transport.dao.AdminDAO;
import com.vercity.transport.dao.StudentDAO;
import com.vercity.transport.model.Admin;
import com.vercity.transport.model.Student;

public class AuthController {
    private StudentDAO studentDAO;
    private AdminDAO adminDAO;

    public AuthController() {
        studentDAO = new StudentDAO();
        adminDAO = new AdminDAO();
    }

    public Student authenticateStudent(String username, String password) {
        Student student = studentDAO.getStudentByUsername(username);
        if (student != null && student.getPassword().equals(password)) {
            return student;
        }
        return null;
    }

    public Admin authenticateAdmin(String username, String password) {
        Admin admin = adminDAO.getAdminByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }

    public boolean registerStudent(Student student) {
        return studentDAO.addStudent(student);
    }
}