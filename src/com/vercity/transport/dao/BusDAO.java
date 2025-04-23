package com.vercity.transport.dao;

import com.vercity.transport.model.Bus;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusDAO {
    public boolean addBus(Bus bus) {
        String sql = "INSERT INTO buses (bus_number, capacity, route, departure_time, arrival_time, available_days) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bus.getBusNumber());
            stmt.setInt(2, bus.getCapacity());
            stmt.setString(3, bus.getRoute());
            stmt.setTime(4, bus.getDepartureTime());
            stmt.setTime(5, bus.getArrivalTime());
            stmt.setString(6, bus.getAvailableDays());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Bus> getAllBuses() {
        String sql = "SELECT * FROM buses";
        List<Bus> buses = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Bus bus = new Bus();
                bus.setBusId(rs.getInt("bus_id"));
                bus.setBusNumber(rs.getString("bus_number"));
                bus.setCapacity(rs.getInt("capacity"));
                bus.setRoute(rs.getString("route"));
                bus.setDepartureTime(rs.getTime("departure_time"));
                bus.setArrivalTime(rs.getTime("arrival_time"));
                bus.setAvailableDays(rs.getString("available_days"));

                buses.add(bus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buses;
    }

    public Bus getBusById(int busId) {
        String sql = "SELECT * FROM buses WHERE bus_id = ?";
        Bus bus = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, busId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                bus = new Bus();
                bus.setBusId(rs.getInt("bus_id"));
                bus.setBusNumber(rs.getString("bus_number"));
                bus.setCapacity(rs.getInt("capacity"));
                bus.setRoute(rs.getString("route"));
                bus.setDepartureTime(rs.getTime("departure_time"));
                bus.setArrivalTime(rs.getTime("arrival_time"));
                bus.setAvailableDays(rs.getString("available_days"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bus;
    }

    public boolean updateBus(Bus bus) {
        String sql = "UPDATE buses SET bus_number = ?, capacity = ?, route = ?, departure_time = ?, arrival_time = ?, available_days = ? WHERE bus_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bus.getBusNumber());
            stmt.setInt(2, bus.getCapacity());
            stmt.setString(3, bus.getRoute());
            stmt.setTime(4, bus.getDepartureTime());
            stmt.setTime(5, bus.getArrivalTime());
            stmt.setString(6, bus.getAvailableDays());
            stmt.setInt(7, bus.getBusId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBus(int busId) {
        String sql = "DELETE FROM buses WHERE bus_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, busId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}