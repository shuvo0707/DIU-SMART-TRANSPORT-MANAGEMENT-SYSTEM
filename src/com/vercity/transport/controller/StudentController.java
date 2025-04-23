package com.vercity.transport.controller;

import com.vercity.transport.dao.BookingDAO;
import com.vercity.transport.dao.BusDAO;
import com.vercity.transport.dao.StudentDAO;
import com.vercity.transport.model.Booking;
import com.vercity.transport.model.Bus;
import com.vercity.transport.model.Student;
import java.sql.Date;
import java.util.List;

public class StudentController {
    private StudentDAO studentDAO;
    private BusDAO busDAO;
    private BookingDAO bookingDAO;

    public StudentController() {
        studentDAO = new StudentDAO();
        busDAO = new BusDAO();
        bookingDAO = new BookingDAO();
    }

    public boolean updateStudentProfile(Student student) {
        return studentDAO.updateStudent(student);
    }

    public List<Bus> getAllBuses() {
        return busDAO.getAllBuses();
    }

    public boolean bookBus(int studentId, int busId, Date travelDate) {
        Booking booking = new Booking();
        booking.setStudentId(studentId);
        booking.setBusId(busId);
        booking.setBookingDate(new Date(System.currentTimeMillis()));
        booking.setTravelDate(travelDate);
        booking.setStatus("confirmed");

        return bookingDAO.addBooking(booking);
    }

    public List<Booking> getStudentBookings(int studentId) {
        return bookingDAO.getBookingsByStudentId(studentId);
    }

    public boolean cancelBooking(int bookingId) {
        return bookingDAO.cancelBooking(bookingId);
    }
}
