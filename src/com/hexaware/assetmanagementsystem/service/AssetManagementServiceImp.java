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
	
	
	
	@Override
	public boolean addAsset(Asset asset) throws EmployeeNotFoundException,InvalidStatusException{
		// TODO Auto-generated method stub
		
			
			return dao.addAsset(asset);
		
		
	}

	@Override
	public boolean updateAsset(Asset asset) throws AssetNotFoundException, EmployeeNotFoundException {
		// TODO Auto-generated method stub
		
			
			return dao.updateAsset(asset);
		
	}

	@Override
	public boolean deleteAsset(int assetId) throws AssetNotFoundException {
		// TODO Auto-generated method stub
		
			
			return dao.deleteAsset(assetId);
		
	}

	@Override
	public boolean allocateAsset(int assetId, int employeeId, String allocationDate) throws AssetNotFoundException,AssetNotMaintainException, EmployeeNotFoundException{
	    
	    	
	        return dao.allocateAsset(assetId, employeeId, allocationDate);
	        
	    
	   

	}


	@Override
	public boolean deallocateAsset(int assetId, int employeeId, String returnDate) throws AssetNotFoundException {
		// TODO Auto-generated method stub

		
			
			return dao.deallocateAsset(assetId, employeeId,returnDate);
		
	}

	
	
	@Override
	public boolean performMaintenance(int assetId, String maintenanceDate, String description, double cost) throws AssetNotFoundException {
		// TODO Auto-generated method stub
		
			
			return dao.performMaintenance(assetId, maintenanceDate, description, cost);
		
	}

	@Override
	public boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate) throws AssetNotMaintainException,AssetNotFoundException, EmployeeNotFoundException{
	    
	    	
	        return dao.reserveAsset(assetId, employeeId, reservationDate, startDate, endDate);
	        
	   
	}

	@Override
	public boolean withdrawReservation(int reservationId) throws ReservationNotFoundException {
		// TODO Auto-generated method stub
		
		
			
			return dao.withdrawReservation(reservationId);
		
	}

}
