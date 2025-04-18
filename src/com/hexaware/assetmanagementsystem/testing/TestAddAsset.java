package com.hexaware.assetmanagementsystem.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.hexaware.assetmanagementsystem.entity.Asset;
import com.hexaware.assetmanagementsystem.service.AssetManagementServiceImp;
import com.hexaware.assetmanagementsystem.service.IAssetManagementService;

class TestAddAsset {

	@Test
	void testAddAsset() {
		IAssetManagementService addAsset = new AssetManagementServiceImp();
		
		Asset asset = new Asset(18, "Laptop", "Dell", "DL1234",Date.valueOf("2025-05-23"),"IT" ,"in use",1);

		boolean result=addAsset.addAsset(asset);
		
		assertTrue(result, "Asset should be added successfully.");

	}

}
