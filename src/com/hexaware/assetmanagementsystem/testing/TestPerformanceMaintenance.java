package com.hexaware.assetmanagementsystem.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.hexaware.assetmanagementsystem.entity.MaintenanceRecord;
import com.hexaware.assetmanagementsystem.exception.AssetNotFoundException;
import com.hexaware.assetmanagementsystem.service.AssetManagementServiceImp;
import com.hexaware.assetmanagementsystem.service.IAssetManagementService;


/**
 * @author Rajalakshmi Ganesh
 * desc: Testing for asset maintenance record insertion
 * Date: 18-04-2025
 */
class TestPerformanceMaintenance {

	@Test
	void testPerformMaintenance() throws AssetNotFoundException {
		
		
		IAssetManagementService maintenanceService = new AssetManagementServiceImp();
		
		boolean result = maintenanceService.performMaintenance(2, "2025-10-10","keyboard change", 700.00);
		
		assertTrue(result, "Maintenance record should be added successfully.");
		
	}

}



