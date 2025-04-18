package com.hexaware.assetmanagementsystem.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.hexaware.assetmanagementsystem.entity.Asset;
import com.hexaware.assetmanagementsystem.exception.AssetNotFoundException;
import com.hexaware.assetmanagementsystem.exception.AssetNotMaintainException;
import com.hexaware.assetmanagementsystem.exception.EmployeeNotFoundException;
import com.hexaware.assetmanagementsystem.exception.InvalidStatusException;
import com.hexaware.assetmanagementsystem.exception.ReservationNotFoundException;

public class AssetManagementDaoImp implements IAssetManagementDao {

    @Override
    public boolean addAsset(Asset asset) throws EmployeeNotFoundException, InvalidStatusException {
        try (Connection conn = DBUtil.getDBConnection()) {

            String checkEmployeeQuery = "SELECT COUNT(*) FROM employees WHERE employee_id = ?";
            try (PreparedStatement empStmt = conn.prepareStatement(checkEmployeeQuery)) {
                empStmt.setInt(1, asset.getOwnerId());
                ResultSet empRs = empStmt.executeQuery();

                if (empRs.next() && empRs.getInt(1) == 0) {
                    throw new EmployeeNotFoundException("Employee with ID " + asset.getOwnerId() + " does not exist.");
                }
            }

            String status = asset.getStatus().toLowerCase();
            if (!(status.equals("in use") || status.equals("decommissioned") || status.equals("under maintenance"))) {
                throw new InvalidStatusException("Status must be one of: in use, decommissioned, under maintenance.");
            }

            String query = "INSERT INTO assets (name, type, serial_number, purchase_date, location, status, owner_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, asset.getName());
                ps.setString(2, asset.getType());
                ps.setString(3, asset.getSerialNumber());
                ps.setDate(4, asset.getPurchaseDate());
                ps.setString(5, asset.getLocation());
                ps.setString(6, asset.getStatus());
                ps.setInt(7, asset.getOwnerId());

                int count = ps.executeUpdate();
                return count > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateAsset(Asset asset) throws AssetNotFoundException, EmployeeNotFoundException {
        try (Connection conn = DBUtil.getDBConnection()) {

            String assetCheckQuery = "SELECT COUNT(*) FROM assets WHERE asset_id = ?";
            try (PreparedStatement assetStmt = conn.prepareStatement(assetCheckQuery)) {
                assetStmt.setInt(1, asset.getAssetId());
                ResultSet assetRs = assetStmt.executeQuery();

                if (assetRs.next() && assetRs.getInt(1) == 0) {
                    throw new AssetNotFoundException("Asset with ID " + asset.getAssetId() + " not found.");
                }
            }

            String empCheckQuery = "SELECT COUNT(*) FROM employees WHERE employee_id = ?";
            try (PreparedStatement empStmt = conn.prepareStatement(empCheckQuery)) {
                empStmt.setInt(1, asset.getOwnerId());
                ResultSet empRs = empStmt.executeQuery();

                if (empRs.next() && empRs.getInt(1) == 0) {
                    throw new EmployeeNotFoundException("Employee with ID " + asset.getOwnerId() + " not found.");
                }
            }

            String query = "UPDATE assets SET name = ?, type = ?, serial_number = ?, purchase_date = ?, location = ?, status = ?, owner_id = ? WHERE asset_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, asset.getName());
                ps.setString(2, asset.getType());
                ps.setString(3, asset.getSerialNumber());
                ps.setDate(4, asset.getPurchaseDate());
                ps.setString(5, asset.getLocation());
                ps.setString(6, asset.getStatus());
                ps.setInt(7, asset.getOwnerId());
                ps.setInt(8, asset.getAssetId());

                int count = ps.executeUpdate();
                return count > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAsset(int assetId) throws AssetNotFoundException {
        try (Connection conn = DBUtil.getDBConnection()) {

            String checkQuery = "SELECT COUNT(*) FROM assets WHERE asset_id = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, assetId);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next() && rs.getInt(1) == 0) {
                    throw new AssetNotFoundException("Asset with ID " + assetId + " not found.");
                }
            }

            String query = "DELETE FROM assets WHERE asset_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, assetId);
                int count = ps.executeUpdate();
                return count > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean allocateAsset(int assetId, int employeeId, String allocationDate)
            throws AssetNotFoundException, AssetNotMaintainException, EmployeeNotFoundException {

        try (Connection conn = DBUtil.getDBConnection()) {

            String checkAsset = "SELECT COUNT(*) FROM assets WHERE asset_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(checkAsset)) {
                ps.setInt(1, assetId);
                ResultSet rs = ps.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) {
                    throw new AssetNotFoundException("Asset with ID " + assetId + " not found.");
                }
            }

            String checkMaintenance = "SELECT MAX(maintenance_date) FROM maintenance_records WHERE asset_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(checkMaintenance)) {
                ps.setInt(1, assetId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Date lastMaintained = rs.getDate(1);
                    if (lastMaintained == null || lastMaintained.toLocalDate().isBefore(LocalDate.now().minusYears(2))) {
                        throw new AssetNotMaintainException("Asset with ID " + assetId + " has not been maintained in the last 2 years.");
                    }
                } else {
                    throw new AssetNotMaintainException("No maintenance record found for asset ID " + assetId + ".");
                }
            }

            String checkEmployee = "SELECT COUNT(*) FROM employees WHERE employee_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(checkEmployee)) {
                ps.setInt(1, employeeId);
                ResultSet rs = ps.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) {
                    throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found.");
                }
            }

            String checkIfAllocated = "SELECT COUNT(*) FROM asset_allocations WHERE asset_id = ? AND return_date IS NULL";
            try (PreparedStatement ps = conn.prepareStatement(checkIfAllocated)) {
                ps.setInt(1, assetId);
                ResultSet rs = ps.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("Asset with ID " + assetId + " is already allocated and not yet returned.");
                    return false;
                }
            }

            String query = "INSERT INTO asset_allocations (asset_id, employee_id, allocation_date) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, assetId);
                ps.setInt(2, employeeId);
                ps.setDate(3, Date.valueOf(allocationDate));
                int count = ps.executeUpdate();
                return count > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deallocateAsset(int assetId, int employeeId, String returnDate) throws AssetNotFoundException {
        try (Connection conn = DBUtil.getDBConnection()) {

            String checkQuery = "SELECT COUNT(*) FROM asset_allocations WHERE asset_id = ? AND employee_id = ? AND return_date IS NULL";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, assetId);
                checkStmt.setInt(2, employeeId);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) {
                    throw new AssetNotFoundException("No active allocation found for asset ID " + assetId + " and employee ID " + employeeId);
                }
            }

            String query = "UPDATE asset_allocations SET return_date = ? WHERE asset_id = ? AND employee_id = ? AND return_date IS NULL";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setDate(1, Date.valueOf(returnDate));
                ps.setInt(2, assetId);
                ps.setInt(3, employeeId);
                int count = ps.executeUpdate();
                return count > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean performMaintenance(int assetId, String maintenanceDate, String description, double cost)
            throws AssetNotFoundException {
        try (Connection conn = DBUtil.getDBConnection()) {

            String checkQuery = "SELECT COUNT(*) FROM assets WHERE asset_id = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, assetId);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) {
                    throw new AssetNotFoundException("Asset with ID " + assetId + " does not exist.");
                }
            }

            String query = "INSERT INTO maintenance_records (asset_id, maintenance_date, description, cost) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, assetId);
                ps.setDate(2, Date.valueOf(maintenanceDate));
                ps.setString(3, description);
                ps.setDouble(4, cost);
                int count = ps.executeUpdate();
                return count > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate)
            throws AssetNotFoundException, AssetNotMaintainException, EmployeeNotFoundException {
        try (Connection conn = DBUtil.getDBConnection()) {

            String checkAsset = "SELECT COUNT(*) FROM assets WHERE asset_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(checkAsset)) {
                ps.setInt(1, assetId);
                ResultSet rs = ps.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) {
                    throw new AssetNotFoundException("Asset with ID " + assetId + " not found.");
                }
            }

            String checkMaintenance = "SELECT MAX(maintenance_date) FROM maintenance_records WHERE asset_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(checkMaintenance)) {
                ps.setInt(1, assetId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Date lastMaintained = rs.getDate(1);
                    if (lastMaintained == null || lastMaintained.toLocalDate().isBefore(LocalDate.now().minusYears(2))) {
                        throw new AssetNotMaintainException("Asset with ID " + assetId + " has not been maintained in the last 2 years.");
                    }
                } else {
                    throw new AssetNotMaintainException("No maintenance record found for asset ID " + assetId + ".");
                }
            }

            String checkEmployee = "SELECT COUNT(*) FROM employees WHERE employee_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(checkEmployee)) {
                ps.setInt(1, employeeId);
                ResultSet rs = ps.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) {
                    throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found.");
                }
            }

            String query = "INSERT INTO reservations (asset_id, employee_id, reservation_date, start_date, end_date) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, assetId);
                ps.setInt(2, employeeId);
                ps.setDate(3, Date.valueOf(reservationDate));
                ps.setDate(4, Date.valueOf(startDate));
                ps.setDate(5, Date.valueOf(endDate));
                int count = ps.executeUpdate();
                return count > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean withdrawReservation(int reservationId) throws ReservationNotFoundException {
        try (Connection conn = DBUtil.getDBConnection()) {

            String checkQuery = "SELECT COUNT(*) FROM reservations WHERE reservation_id = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, reservationId);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) {
                    throw new ReservationNotFoundException("Reservation with ID " + reservationId + " not found.");
                }
            }

            String query = "DELETE FROM reservations WHERE reservation_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, reservationId);
                int count = ps.executeUpdate();
                return count > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
