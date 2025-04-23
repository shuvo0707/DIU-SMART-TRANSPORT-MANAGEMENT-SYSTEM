package com.vercity.transport.controller;

import com.vercity.transport.dao.BookingDAO;
import com.vercity.transport.model.Booking;
import java.util.List;

public class BookingController {
    private BookingDAO bookingDAO;

    public BookingController() {
        bookingDAO = new BookingDAO();
    }

    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBookings();
    }

    public List<Booking> getBookingsByStudent(int studentId) {
        return bookingDAO.getBookingsByStudentId(studentId);
    }

    public boolean cancelBooking(int bookingId) {
        return bookingDAO.cancelBooking(bookingId);
    }
}
