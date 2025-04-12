package com.hexaware.assetmanagementsystem.service;


public class AssetManagementServiceImp implements IAssetManagementService {

	@Override
	public boolean performMaintenance(int assetId, String maintenanceDate, String description, double cost) {
		// TODO Auto-generated method stub
		return false;
		
	}

	@Override
	public boolean reserveAsset(int assetId, int employeeId, String reservationDate, String startDate, String endDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean withdrawReservation(int reservationId) {
		// TODO Auto-generated method stub
		return false;
	}

}
