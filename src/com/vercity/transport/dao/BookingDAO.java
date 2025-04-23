package com.vercity.transport.dao;

import com.vercity.transport.model.Booking;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    public boolean addBooking(Booking booking) {
        String sql = "INSERT INTO bookings (student_id, bus_id, booking_date, travel_date, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, booking.getStudentId());
            stmt.setInt(2, booking.getBusId());
            stmt.setDate(3, booking.getBookingDate());
            stmt.setDate(4, booking.getTravelDate());
            stmt.setString(5, booking.getStatus());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Booking> getBookingsByStudentId(int studentId) {
        String sql = "SELECT * FROM bookings WHERE student_id = ?";
        List<Booking> bookings = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setStudentId(rs.getInt("student_id"));
                booking.setBusId(rs.getInt("bus_id"));
                booking.setBookingDate(rs.getDate("booking_date"));
                booking.setTravelDate(rs.getDate("travel_date"));
                booking.setStatus(rs.getString("status"));

                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public boolean cancelBooking(int bookingId) {
        String sql = "UPDATE bookings SET status = 'cancelled' WHERE booking_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookingId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Booking> getAllBookings() {
        String sql = "SELECT * FROM bookings";
        List<Booking> bookings = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setStudentId(rs.getInt("student_id"));
                booking.setBusId(rs.getInt("bus_id"));
                booking.setBookingDate(rs.getDate("booking_date"));
                booking.setTravelDate(rs.getDate("travel_date"));
                booking.setStatus(rs.getString("status"));

                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
