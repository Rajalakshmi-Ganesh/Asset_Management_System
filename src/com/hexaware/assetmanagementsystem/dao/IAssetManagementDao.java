package com.hexaware.assetmanagementsystem.dao;


/**
 *@Author: Rajalakshmi Ganesh
 *@Author: Shrinidhii Muthukumaran 
 * Date: 12-04-2025
*/

public interface IAssetManagementDao {
	
	
	
	/**
	 * @author Rajalakshmi Ganesh
	 * @param assetId
	 * @param maintenanceDate
	 * @param description
	 * @param cost
	 * @return boolean
	 */
	boolean performMaintenance(int assetId, String maintenanceDate, String description, double cost);
	
	
	/**
	 * @author Rajalakshmi Ganesh
	 * @param assetId
	 * @param employeeId
	 * @param reservationDate
	 * @param startDate
	 * @param endDate
	 * @return boolean
	 */
	boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate);
	 
	
	/**
	 * @author Rajalakshmi Ganesh
	 * @param reservationId
	 * @return boolean
	 */
	boolean withdrawReservation(int reservationId);

}
