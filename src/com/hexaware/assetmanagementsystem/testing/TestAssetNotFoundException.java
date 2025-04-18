package com.hexaware.assetmanagementsystem.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hexaware.assetmanagementsystem.exception.AssetNotFoundException;
import com.hexaware.assetmanagementsystem.exception.AssetNotMaintainException;
import com.hexaware.assetmanagementsystem.service.AssetManagementServiceImp;
import com.hexaware.assetmanagementsystem.service.IAssetManagementService;

/**
 * @author Shrinidhii Muthukumaran
 * desc: Testing for asset not found exception.
 * Date: 18-04-2025
 */
class TestAssetNotFoundException {

	@Test
	void testAllocateAsset() {
		
		IAssetManagementService service = new AssetManagementServiceImp();
		
		assertThrows(AssetNotFoundException.class,()->{
			
			service.allocateAsset(20, 10, "2023-12-10");
			
		});
		
		
//		assertThrows(AssetNotMaintainException.class, () -> {
//            service.allocateAsset(10, 10, "2023-12-10");
//        });
	}

}
