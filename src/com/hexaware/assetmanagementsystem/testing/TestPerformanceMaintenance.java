package com.hexaware.assetmanagementsystem.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.hexaware.assetmanagementsystem.entity.MaintenanceRecord;
import com.hexaware.assetmanagementsystem.service.AssetManagementServiceImp;
import com.hexaware.assetmanagementsystem.service.IAssetManagementService;

class TestPerformanceMaintenance {

	@Test
	void testPerformMaintenance() {
		
		
		IAssetManagementService maintenanceService = new AssetManagementServiceImp();
		
		boolean result = maintenanceService.performMaintenance(2, "2025-10-10","keyboard change", 700.00);
		
		assertTrue(result, "Maintenance record should be added successfully.");
		
	}

}
