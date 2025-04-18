package com.hexaware.assetmanagementsystem.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hexaware.assetmanagementsystem.exception.AssetNotFoundException;
import com.hexaware.assetmanagementsystem.exception.AssetNotMaintainException;
import com.hexaware.assetmanagementsystem.service.AssetManagementServiceImp;
import com.hexaware.assetmanagementsystem.service.IAssetManagementService;

class TestReserveAsset {

	@Test
	void testReserveAsset() {
		
		IAssetManagementService reservationService = new AssetManagementServiceImp();
		
		boolean result = false;
		try {
			result = reservationService.reserveAsset(9, 2,"2025-06-06","2025-07-06","2025-09-06");
		} catch (AssetNotFoundException | AssetNotMaintainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

	    assertTrue(result, "Asset should be reserved successfully.");
	}

}
