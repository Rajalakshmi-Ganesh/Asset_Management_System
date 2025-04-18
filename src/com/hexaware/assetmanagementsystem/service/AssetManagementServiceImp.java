package com.hexaware.assetmanagementsystem.service;

import com.hexaware.assetmanagementsystem.dao.*;
import com.hexaware.assetmanagementsystem.entity.Asset;
import com.hexaware.assetmanagementsystem.exception.*;
/**
 *@Author: Rajalakshmi Ganesh
 *@Author: Shrinidhii Muthukumaran 
 *Desc:Asset Management system Service implementation
 * Date: 12-04-2025
*/
public class AssetManagementServiceImp implements IAssetManagementService {

	AssetManagementDaoImp dao = new AssetManagementDaoImp();
	
	
	/**
	 * @Author: Shrinidhii Muthukumaran
	 * @param asset
	 * @return boolean
	 */
	@Override
	public boolean addAsset(Asset asset) throws EmployeeNotFoundException,InvalidStatusException{
		// TODO Auto-generated method stub
		
			
			return dao.addAsset(asset);
		
		
	}

	/**
	   * @Author: Shrinidhii Muthukumaran
	   * @param asset
	   * @return boolean
	*/
	@Override
	public boolean updateAsset(Asset asset) throws AssetNotFoundException, EmployeeNotFoundException {
		// TODO Auto-generated method stub
		
			
			return dao.updateAsset(asset);
		
	}

   /**
   * @Author: Shrinidhii Muthukumaran
   * @param asset
   * @return boolean
   */
	@Override
	public boolean deleteAsset(int assetId) throws AssetNotFoundException {
		// TODO Auto-generated method stub
		
			
			return dao.deleteAsset(assetId);
		
	}

  /**
   * @Author:Shrinidhii Muthukumaran
   * @param assetId
   * @param employeeId
   * @param allocationDate
   * @return boolean
   */
	@Override
	public boolean allocateAsset(int assetId, int employeeId, String allocationDate) throws AssetNotFoundException,AssetNotMaintainException, EmployeeNotFoundException{
	    
	    	
	        return dao.allocateAsset(assetId, employeeId, allocationDate);
	        
	    
	   

	}

	/**
	 * @Author: Shrinidhii Muthukumaran
	 * @param assetId
	 * @param employeeId
	 * @param returnDate
	 * @return boolean
	 */
	@Override
	public boolean deallocateAsset(int assetId, int employeeId, String returnDate) throws AssetNotFoundException {
		// TODO Auto-generated method stub

		
			
			return dao.deallocateAsset(assetId, employeeId,returnDate);
		
	}

	
	/**
	 * @author Rajalakshmi Ganesh
	 * @param assetId
	 * @param maintenanceDate
	 * @param description
	 * @param cost
	 * @return boolean
	 */
	@Override
	public boolean performMaintenance(int assetId, String maintenanceDate, String description, double cost) throws AssetNotFoundException {
		// TODO Auto-generated method stub
		
			
			return dao.performMaintenance(assetId, maintenanceDate, description, cost);
		
	}

	/**
	 * @author Rajalakshmi Ganesh
	 * @param assetId
	 * @param employeeId
	 * @param reservationDate
	 * @param startDate
	 * @param endDate
	 * @return boolean
	 */
	@Override
	public boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate) throws AssetNotMaintainException,AssetNotFoundException, EmployeeNotFoundException{
	    
	    	
	        return dao.reserveAsset(assetId, employeeId, reservationDate, startDate, endDate);
	        
	   
	}

	/**
	 * @author Rajalakshmi Ganesh
	 * @param reservationId
	 * @return boolean
	 */
	@Override
	public boolean withdrawReservation(int reservationId) throws ReservationNotFoundException {
		// TODO Auto-generated method stub
		
		
			
			return dao.withdrawReservation(reservationId);
		
	}

}
