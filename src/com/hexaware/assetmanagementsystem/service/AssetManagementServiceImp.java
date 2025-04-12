package com.hexaware.assetmanagementsystem.service;

import com.hexaware.assetmanagementsystem.dao.*;

public class AssetManagementServiceImp implements IAssetManagementService {

	AssetManagementDaoImp dao = new AssetManagementDaoImp();
	
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
		// TODO Auto-generated method stub
		try {
			
			return dao.reserveAsset(assetId, employeeId, reservationDate, startDate, endDate);
			
		} catch (Exception e) {
			// TODO: handle exception
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
