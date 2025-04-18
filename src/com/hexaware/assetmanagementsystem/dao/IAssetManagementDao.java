package com.hexaware.assetmanagementsystem.dao;


import com.hexaware.assetmanagementsystem.entity.Asset;
import com.hexaware.assetmanagementsystem.exception.*;

/**
 *@Author: Rajalakshmi Ganesh
 *@Author: Shrinidhii Muthukumaran 
 * Date: 12-04-2025
*/

public interface IAssetManagementDao {
	
	/**
	 * @Author: Shrinidhii Muthukumaran
	 * @param asset
	 * @return boolean
	 */
	  boolean addAsset(Asset asset) throws EmployeeNotFoundException,InvalidStatusException;
	  
	  /**
	   * @Author: Shrinidhii Muthukumaran
	   * @param asset
	   * @return boolean
	   */
	  boolean updateAsset(Asset asset) throws AssetNotFoundException,EmployeeNotFoundException ;
	  
	 /**
	  * @Author:Shrinidhii Muthukumaran
	  * @param assetId
	  * @return boolean
	  */
	  boolean deleteAsset(int assetId) throws AssetNotFoundException;
	  
	  
	  /**
	   * @Author:Shrinidhii Muthukumaran
	   * @param assetId
	   * @param employeeId
	   * @param allocationDate
	   * @return boolean
	   */
	  boolean allocateAsset(int assetId, int employeeId, String allocationDate)
		        throws AssetNotFoundException, AssetNotMaintainException,EmployeeNotFoundException;
	/**
	 * @Author: Shrinidhii Muthukumaran
	 * @param assetId
	 * @param employeeId
	 * @param returnDate
	 * @return boolean
	 */
	  boolean deallocateAsset(int assetId, int employeeId, String returnDate) throws AssetNotFoundException;
	
	/**
	 * @author Rajalakshmi Ganesh
	 * @param assetId
	 * @param maintenanceDate
	 * @param description
	 * @param cost
	 * @return boolean
	 */
	boolean performMaintenance(int assetId, String maintenanceDate, String description, double cost) throws AssetNotFoundException;
	
	
	/**
	 * @author Rajalakshmi Ganesh
	 * @param assetId
	 * @param employeeId
	 * @param reservationDate
	 * @param startDate
	 * @param endDate
	 * @return boolean
	 */
	boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate)
	        throws AssetNotFoundException, AssetNotMaintainException,EmployeeNotFoundException;
	/**
	 * @author Rajalakshmi Ganesh
	 * @param reservationId
	 * @return boolean
	 */
	boolean withdrawReservation(int reservationId)throws ReservationNotFoundException;

}
