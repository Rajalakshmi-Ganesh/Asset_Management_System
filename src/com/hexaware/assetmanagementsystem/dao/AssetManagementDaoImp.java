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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAsset(Asset asset) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAsset(int assetId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean allocateAsset(int assetId, int employeeId, String allocationDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deallocateAsset(int assetId, int employeeId, String returnDate) {
		// TODO Auto-generated method stub
		return false;
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
