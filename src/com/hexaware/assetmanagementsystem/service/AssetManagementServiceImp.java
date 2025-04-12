package com.hexaware.assetmanagementsystem.service;

import com.hexaware.assetmanagementsystem.dao.*;
import com.hexaware.assetmanagementsystem.entity.Asset;
import com.hexaware.assetmanagementsystem.exception.*;

public class AssetManagementServiceImp implements IAssetManagementService {

	AssetManagementDaoImp dao = new AssetManagementDaoImp();
	
	
	
	@Override
	public boolean addAsset(Asset asset) {
		// TODO Auto-generated method stub
		try {
			
			return dao.addAsset(asset);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateAsset(Asset asset) {
		// TODO Auto-generated method stub
		try {
			
			return dao.updateAsset(asset);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteAsset(int assetId) {
		// TODO Auto-generated method stub
		try {
			
			return dao.deleteAsset(assetId);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean allocateAsset(int assetId, int employeeId, String allocationDate) {
	    try {
	    	
	        return dao.allocateAsset(assetId, employeeId, allocationDate);
	        
	    } catch (AssetNotFoundException | AssetNotMaintainException e) {
	    	
	        e.printStackTrace();
	        return false;
	    }
	}


	@Override
	public boolean deallocateAsset(int assetId, int employeeId, String returnDate) {
		// TODO Auto-generated method stub

		try {
			
			return dao.deallocateAsset(assetId, employeeId,returnDate);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	
	
	@Override
	public boolean performMaintenance(int assetId, String maintenanceDate, String description, double cost) {
		// TODO Auto-generated method stub
		try {
			
			return dao.performMaintenance(assetId, maintenanceDate, description, cost);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate) {
	    try {
	    	
	        return dao.reserveAsset(assetId, employeeId, reservationDate, startDate, endDate);
	        
	    } catch (AssetNotFoundException | AssetNotMaintainException e) {
	    	
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean withdrawReservation(int reservationId) {
		// TODO Auto-generated method stub
		
		try {
			
			return dao.withdrawReservation(reservationId);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

}
