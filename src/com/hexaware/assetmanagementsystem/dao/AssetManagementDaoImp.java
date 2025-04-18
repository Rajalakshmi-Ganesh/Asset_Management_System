package com.hexaware.assetmanagementsystem.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * @author Rajalakshmi Ganesh
 * @author Shrinidhii Muthukumaran
 * Descp: Implementation of AssetManagementDao
 * Date:12-04-2025
 */

import com.hexaware.assetmanagementsystem.entity.Asset;
import com.hexaware.assetmanagementsystem.exception.AssetNotFoundException;
import com.hexaware.assetmanagementsystem.exception.AssetNotMaintainException;

/**
 *@Author: Rajalakshmi Ganesh
 *@Author: Shrinidhii Muthukumaran 
 * Date: 12-04-2025
*/
public class AssetManagementDaoImp implements IAssetManagementDao{

	/**
	 * @Author: Shrinidhii Muthukumaran
	 * @param asset
	 * @return boolean
	 */
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


            int count = ps.executeUpdate();
            
            if(count>0)
            	return true;
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

	/**
	   * @Author: Shrinidhii Muthukumaran
	   * @param asset
	   * @return boolean
	   */
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


            int count = ps.executeUpdate();
            
            if(count>0)
            	return true;
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    /**
	  * @Author:Shrinidhii Muthukumaran
	  * @param assetId
	  * @return boolean
	  */
    @Override
    public boolean deleteAsset(int assetId) {
        try (Connection conn = DBUtil.getDBConnection()) {
            String query = "DELETE FROM assets WHERE asset_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, assetId);


            int count = ps.executeUpdate();
            
            if(count>0)
            	return true;
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    /**
	   * @Author:Shrinidhii Muthukumaran
	   * @param assetId
	   * @param employeeId
	   * @param allocationDate
	   * @return boolean
	   */
    @Override
    public boolean allocateAsset(int assetId, int employeeId, String allocationDate)
        throws AssetNotFoundException, AssetNotMaintainException {

        try (Connection conn = DBUtil.getDBConnection()) {

            // asset existence check
            String checkAsset = "SELECT COUNT(*) FROM assets WHERE asset_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(checkAsset)) {
                ps.setInt(1, assetId);
                ResultSet rs = ps.executeQuery();

                if (rs.next() && rs.getInt(1) == 0) {
                    throw new AssetNotFoundException("Asset with ID " + assetId + " not found.");
                }
            }

            // maintenance check
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

            // insert allocation
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
            
        }
        return false;
    }


    /**
	 * @Author: Shrinidhii Muthukumaran
	 * @param assetId
	 * @param employeeId
	 * @param returnDate
	 * @return boolean
	 */
    @Override
    public boolean deallocateAsset(int assetId, int employeeId, String returnDate) {
        try (Connection conn = DBUtil.getDBConnection()) {
            String query = "UPDATE asset_allocations SET return_date = ? WHERE asset_id = ? AND employee_id = ? AND return_date IS NULL";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDate(1, Date.valueOf(returnDate));
            ps.setInt(2, assetId);
            ps.setInt(3, employeeId);

            int count = ps.executeUpdate();
            
            if(count>0)
            	return true;
            return false;

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
            ps.setDate(2, Date.valueOf(maintenanceDate));
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
	public boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate)
	        throws AssetNotFoundException, AssetNotMaintainException {

	    try (Connection conn = DBUtil.getDBConnection()) {

	        // Check if asset exists
	        String checkAsset = "SELECT COUNT(*) FROM assets WHERE asset_id = ?";
	        try (PreparedStatement ps = conn.prepareStatement(checkAsset)) {
	            ps.setInt(1, assetId);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next() && rs.getInt(1) == 0) {
	                throw new AssetNotFoundException("Asset with ID " + assetId + " not found.");
	            }
	        }

	        // Check if asset has been maintained in the last 2 years
	        String checkMaintenance = "SELECT MAX(maintenance_date) FROM maintenance_records WHERE asset_id = ?";
	        try (PreparedStatement ps = conn.prepareStatement(checkMaintenance)) {
	            ps.setInt(1, assetId);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                Date lastMaintained = rs.getDate(1);

	                // Check if last maintenance is null or older than 2 years from today's date
	                if (lastMaintained == null || lastMaintained.toLocalDate().isBefore(LocalDate.now().minusYears(2))) {
	                    throw new AssetNotMaintainException("Asset with ID " + assetId + " has not been maintained in the last 2 years.");
	                }
	            } else {
	                // No maintenance record found
	                throw new AssetNotMaintainException("No maintenance record found for asset ID " + assetId + ".");
	            }
	        }

	        // Insert reservation
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
