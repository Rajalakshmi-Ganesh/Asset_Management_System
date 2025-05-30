package com.hexaware.assetmanagementsystem.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.hexaware.assetmanagementsystem.entity.Asset;
import com.hexaware.assetmanagementsystem.exception.EmployeeNotFoundException;
import com.hexaware.assetmanagementsystem.exception.InvalidStatusException;
import com.hexaware.assetmanagementsystem.service.AssetManagementServiceImp;
import com.hexaware.assetmanagementsystem.service.IAssetManagementService;

/**
 * @author Shrinidhii Muthukumaran
 * desc: Testing for asset insertion
 * Date: 18-04-2025
 */

class TestAddAsset {

	@Test
	void testAddAsset() throws EmployeeNotFoundException, InvalidStatusException {
		IAssetManagementService addAsset = new AssetManagementServiceImp();
		
		Asset asset = new Asset(18, "Laptop", "Dell", "DL1234",Date.valueOf("2025-05-23"),"IT" ,"in use",1);

		boolean result=addAsset.addAsset(asset);
		
		assertTrue(result, "Asset should be added successfully.");

	}

}
