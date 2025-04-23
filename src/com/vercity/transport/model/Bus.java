package com.vercity.transport.model;

import java.sql.Time;

public class Bus {
    private int busId;
    private String busNumber;
    private int capacity;
    private String route;
    private Time departureTime;
    private Time arrivalTime;
    private String availableDays;

    // Constructors, getters, and setters
    public Bus() {}

    public Bus(String busNumber, int capacity, String route,
               Time departureTime, Time arrivalTime, String availableDays) {
        this.busNumber = busNumber;
        this.capacity = capacity;
        this.route = route;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableDays = availableDays;
    }

    // Getters and setters
    public int getBusId() { return busId; }
    public void setBusId(int busId) { this.busId = busId; }
    public String getBusNumber() { return busNumber; }
    public void setBusNumber(String busNumber) { this.busNumber = busNumber; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public String getRoute() { return route; }
    public void setRoute(String route) { this.route = route; }
    public Time getDepartureTime() { return departureTime; }
    public void setDepartureTime(Time departureTime) { this.departureTime = departureTime; }
    public Time getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(Time arrivalTime) { this.arrivalTime = arrivalTime; }
    public String getAvailableDays() { return availableDays; }
    public void setAvailableDays(String availableDays) { this.availableDays = availableDays; }
}