package com.vercity.transport.model;

public class Student {
    private int studentId;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String matricNumber;
    private String department;

    // Constructors, getters, and setters
    public Student() {}

    public Student(String username, String password, String fullName, String email,
                   String matricNumber, String department) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.matricNumber = matricNumber;
        this.department = department;
    }

    // Getters and setters
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMatricNumber() { return matricNumber; }
    public void setMatricNumber(String matricNumber) { this.matricNumber = matricNumber; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}
