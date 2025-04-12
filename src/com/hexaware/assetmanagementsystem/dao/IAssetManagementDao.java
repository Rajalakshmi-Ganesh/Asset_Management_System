package com.hexaware.assetmanagementsystem.dao;


import com.hexaware.assetmanagementsystem.entity.Asset;

/**
 *@Author: Rajalakshmi Ganesh
 *@Author: Shrinidhii Muthukumaran 
 * Date: 12-04-2025
*/

public interface IAssetManagementDao {
	
	
	/**
	 * @author Shrinidhii Muthukumaran 
	 * @param asset
	 * @return
	 */
	  boolean addAsset(Asset asset);
	  
	  

	  /**
		 * @author Shrinidhii Muthukumaran 
		 * @param Asset 
		 * @return
		 */
	  boolean updateAsset(Asset asset);
	  
	  
	  /**
		 * @author Shrinidhii Muthukumaran 
		 * @param assetId
		 * @return
		 */
	  boolean deleteAsset(int assetId);
	  
	  
	  /**
		 * @author Shrinidhii Muthukumaran 
		 * @param assetID
		 * @param employeeId
		 * @param allocationDate
		 * @return
		 */
	  boolean allocateAsset(int assetId, int employeeId, String allocationDate);
	  
	  
	  /**
		 * @author Shrinidhii Muthukumaran 
		 * @param assetID
		 * @param employeeId
		 * @param allocationDate
		 * @return
		 */
	  boolean deallocateAsset(int assetId, int employeeId, String returnDate);
	
	  //---------------------------------------------------------------------
	/**
	 * @author Rajalakshmi Ganesh
	 * @param assetId
	 * @param maintenanceDate
	 * @param description
	 * @param cost
	 * @return
	 */
	boolean performMaintenance(int assetId, String maintenanceDate, String description, double cost);
	
	
	/**
	 * @author Rajalakshmi Ganesh
	 * @param assetId
	 * @param employeeId
	 * @param reservationDate
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate);
	 
	
	/**
	 * @author Rajalakshmi Ganesh
	 * @param reservationId
	 * @return
	 */
	boolean withdrawReservation(int reservationId);

}
