package com.hexaware.assetmanagementsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author Rajalakshmi Ganesh
 * @author Shrinidhii Muthukumaran
 * Descp: Implementation of AssetManagementDao
 * Date:12-04-2025
 */

import com.hexaware.assetmanagementsystem.entity.Asset;

public class AssetManagementDaoImp implements IAssetManagementDao{

	@Override
    public boolean addAsset(Asset asset) {
        try (Connection conn = DBUtil.getDBConnection()) {
            String query = "INSERT INTO assets (name, type, serial_number, purchase_date, location, status, owner_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, asset.getName());
            ps.setString(2, asset.getType());
            ps.setString(3, asset.getSerialNumber());
            ps.setDate(4, asset.getPurchaseDate());
            ps.setString(5, asset.getLocation());
            ps.setString(6, asset.getStatus());
            ps.setInt(7, asset.getOwnerId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateAsset(Asset asset) {
        try (Connection conn = DBUtil.getDBConnection()) {
            String query = "UPDATE assets SET name = ?, type = ?, serial_number = ?, purchase_date = ?, location = ?, status = ?, owner_id = ? WHERE asset_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, asset.getName());
            ps.setString(2, asset.getType());
            ps.setString(3, asset.getSerialNumber());
            ps.setDate(4, asset.getPurchaseDate());
            ps.setString(5, asset.getLocation());
            ps.setString(6, asset.getStatus());
            ps.setInt(7, asset.getOwnerId());
            ps.setInt(8, asset.getAssetId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAsset(int assetId) {
        try (Connection conn = DBUtil.getDBConnection()) {
            String query = "DELETE FROM assets WHERE asset_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, assetId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean allocateAsset(int assetId, int employeeId, String allocationDate) {
        try (Connection conn = DBUtil.getDBConnection()) {
            String query = "INSERT INTO asset_allocations (asset_id, employee_id, allocation_date) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, assetId);
            ps.setInt(2, employeeId);
            ps.setString(3, allocationDate);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deallocateAsset(int assetId, int employeeId, String returnDate) {
        try (Connection conn = DBUtil.getDBConnection()) {
            String query = "UPDATE asset_allocations SET return_date = ? WHERE asset_id = ? AND employee_id = ? AND return_date IS NULL";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, returnDate);
            ps.setInt(2, assetId);
            ps.setInt(3, employeeId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

	/**
	 * @author Rajalakshmi Ganesh
	 * descp: maintenance record for an asset.
	 * @param assetId
	 * @param maintenanceDate
	 * @param description
	 * @param cost
	 * @return boolean
	 */
	@Override
	public boolean performMaintenance(int assetId, String maintenanceDate, String description, double cost) {
		// TODO Auto-generated method stub
		
		try (Connection conn = DBUtil.getDBConnection()){
			
			String query = "INSERT INTO maintenance_records (asset_id, maintenance_date, description, cost) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, assetId);
            ps.setString(2, maintenanceDate);
            ps.setString(3, description);
            ps.setDouble(4, cost);
            
            int count = ps.executeUpdate();
            
            if(count>0)
            	return true;
            return false;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * @author Rajalakshmi Ganesh
	 * descp: Reserves an asset for an employee within a date range.
	 * @param assetId
	 * @param employeeId
	 * @param reservationDate
	 * @param startDate
	 * @param endDate
	 * @return boolean
	 */
	@Override
	public boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate) {
		// TODO Auto-generated method stub
		
		try(Connection conn = DBUtil.getDBConnection()) {
			
			String query = "INSERT INTO reservations (asset_id, employee_id, reservation_date, start_date, end_date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, assetId);
            ps.setInt(2, employeeId);
            ps.setString(3, reservationDate);
            ps.setString(4, startDate);
            ps.setString(5, endDate);

            int count = ps.executeUpdate();
            
            if(count>0)
            	return true;
            return false;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}

	
	/**
	 * @author Rajalakshmi Ganesh
	 * descp: Cancel a reservation based on reservation ID.
	 * @param reservation ID
	 * @return boolean
	 */
	@Override
	public boolean withdrawReservation(int reservationId) {
		// TODO Auto-generated method stub
		
		try(Connection conn = DBUtil.getDBConnection()) {
			
			String query = "DELETE FROM reservations WHERE reservation_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, reservationId);

            int count = ps.executeUpdate();
            
            if(count>0)
            	return true;
            return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	
	

}
