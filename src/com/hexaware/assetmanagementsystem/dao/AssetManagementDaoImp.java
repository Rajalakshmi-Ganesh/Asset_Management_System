package com.hexaware.assetmanagementsystem.dao;

import com.hexaware.assetmanagementsystem.entity.Asset;

public class AssetManagementDaoImp implements IAssetManagementDao{

	@Override
	public boolean addAsset(Asset asset) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAsset(Asset asset) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAsset(int assetId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean allocateAsset(int assetId, int employeeId, String allocationDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deallocateAsset(int assetId, int employeeId, String returnDate) {
		// TODO Auto-generated method stub
		return false;
	}

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
