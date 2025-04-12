package com.hexaware.assetmanagementsystem.service;

import com.hexaware.assetmanagementsystem.dao.*;

public class AssetManagementServiceImp implements IAssetManagementService {

	AssetManagementDaoImp dao = new AssetManagementDaoImp();
	
	@Override
	public boolean performMaintenance(int assetId, String maintenanceDate, String description, double cost) {
		// TODO Auto-generated method stub
		return dao.performMaintenance(assetId, maintenanceDate, description, cost);
		
	}

	@Override
	public boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate) {
		// TODO Auto-generated method stub
		return dao.reserveAsset(assetId, employeeId, reservationDate, startDate, endDate);
	}

	@Override
	public boolean withdrawReservation(int reservationId) {
		// TODO Auto-generated method stub
		return dao.withdrawReservation(reservationId);
	}

}
