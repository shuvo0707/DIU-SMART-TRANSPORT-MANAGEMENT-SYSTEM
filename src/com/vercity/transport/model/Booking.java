package com.vercity.transport.model;

import java.sql.Date;

public class Booking {
    private int bookingId;
    private int studentId;
    private int busId;
    private Date bookingDate;
    private Date travelDate;
    private String status;

    // Constructors, getters, and setters
    public Booking() {}

    public Booking(int studentId, int busId, Date bookingDate, Date travelDate, String status) {
        this.studentId = studentId;
        this.busId = busId;
        this.bookingDate = bookingDate;
        this.travelDate = travelDate;
        this.status = status;
    }

    // Getters and setters
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public int getBusId() { return busId; }
    public void setBusId(int busId) { this.busId = busId; }
    public Date getBookingDate() { return bookingDate; }
    public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate; }
    public Date getTravelDate() { return travelDate; }
    public void setTravelDate(Date travelDate) { this.travelDate = travelDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}