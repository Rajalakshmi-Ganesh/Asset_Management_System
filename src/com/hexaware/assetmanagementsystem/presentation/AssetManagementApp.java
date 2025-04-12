package com.hexaware.assetmanagementsystem.presentation;

import java.util.*;

import com.hexaware.assetmanagementsystem.service.*;

public class AssetManagementApp {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String args[]) {
		
		IAssetManagementService service = new AssetManagementServiceImp(); 
		
		boolean flag  = true;
		
		while(flag) {
			
			System.out.println("Asset Management System");
	        System.out.println("6. Perform Maintenance");
	        System.out.println("7. Reserve Asset");
	        System.out.println("8. Withdraw Reservation");
	        System.out.println("0. Exit");
	        
	        System.out.print("Enter your choice: ");
	        
	        int choice = sc.nextInt();
	        
	        switch (choice) {
	        
            case 6:
            	
                System.out.print("Enter Asset ID: ");
                int assetId1 = sc.nextInt();
                sc.nextLine(); 

                System.out.print("Enter Maintenance Date(YYYY-MM-DD): ");
                String date = sc.nextLine();

                System.out.print("Enter Description: ");
                String desc = sc.nextLine();

                System.out.print("Enter Maintenance Cost: ");
                double cost = sc.nextDouble();

                boolean result6 = service.performMaintenance(assetId1, date, desc, cost);
                System.out.println(result6 ? " Maintenance recorded" : "Failed to record maintenance");
                break;

            case 7:
            	
                System.out.print("Enter Asset ID: ");
                int assetId2 = sc.nextInt();

                System.out.print("Enter Employee ID: ");
                int empId = sc.nextInt();
                sc.nextLine(); 

                System.out.print("Enter Reservation Date(YYYY-MM-DD): ");
                String resDate = sc.nextLine();

                System.out.print("Enter Start Date(YYYY-MM-DD): ");
                String startDate = sc.nextLine();

                System.out.print("Enter End Date(YYYY-MM-DD): ");
                String endDate = sc.nextLine();

                boolean result7 = service.reserveAsset(assetId2, empId, resDate, startDate, endDate);
                System.out.println(result7 ? "Reservation successful!" : "Failed to reserve asset");
                break;

            case 8:
            	
                System.out.print("Enter Reservation ID to withdraw: ");
                int resId = sc.nextInt();
                boolean result8 = service.withdrawReservation(resId);
                
                System.out.println(result8 ? "Reservation withdrawn" : "Reservation withrawal failed");
                break;

            case 0:
            	
            	flag = false;

				System.out.println("Thank you , Visit Again..");				
                break;

            default:
                System.out.println("⚠️ Invalid choice. Please select from the menu.");
	        }
	        
	        
		}
		
	}

}
