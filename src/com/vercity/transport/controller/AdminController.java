package com.vercity.transport.controller;

import com.vercity.transport.dao.BookingDAO;
import com.vercity.transport.dao.BusDAO;
import com.vercity.transport.dao.StudentDAO;
import com.vercity.transport.model.Booking;
import com.vercity.transport.model.Bus;
import com.vercity.transport.model.Student;
import java.util.List;

public class AdminController {
    private StudentDAO studentDAO;
    private BusDAO busDAO;
    private BookingDAO bookingDAO;

    public AdminController() {
        studentDAO = new StudentDAO();
        busDAO = new BusDAO();
        bookingDAO = new BookingDAO();
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    public boolean addStudent(Student student) {
        return studentDAO.addStudent(student);
    }

    public boolean updateStudent(Student student) {
        return studentDAO.updateStudent(student);
    }

    public boolean deleteStudent(int studentId) {
        return studentDAO.deleteStudent(studentId);
    }

    public List<Bus> getAllBuses() {
        return busDAO.getAllBuses();
    }

    public boolean addBus(Bus bus) {
        return busDAO.addBus(bus);
    }

    public boolean updateBus(Bus bus) {
        return busDAO.updateBus(bus);
    }

    public boolean deleteBus(int busId) {
        return busDAO.deleteBus(busId);
    }

    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBookings();
    }
}
