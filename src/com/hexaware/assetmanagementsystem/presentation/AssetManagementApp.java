package com.hexaware.assetmanagementsystem.presentation;

import java.util.*;

import java.sql.Date;
import com.hexaware.assetmanagementsystem.service.*;
import com.hexaware.assetmanagementsystem.entity.*;
import com.hexaware.assetmanagementsystem.exception.AssetNotFoundException;
import com.hexaware.assetmanagementsystem.exception.AssetNotMaintainException;
import com.hexaware.assetmanagementsystem.exception.EmployeeNotFoundException;
import com.hexaware.assetmanagementsystem.exception.InvalidStatusException;
import com.hexaware.assetmanagementsystem.exception.ReservationNotFoundException;

public class AssetManagementApp {
    
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String args[]) {
        
        IAssetManagementService service = new AssetManagementServiceImp(); 
        boolean flag  = true;
        
        while(flag) {
            System.out.println("\n--- Asset Management System ---");
            System.out.println("1. Add Asset");
            System.out.println("2. Update Asset");
            System.out.println("3. Delete Asset");
            System.out.println("4. Allocate Asset");
            System.out.println("5. Deallocate Asset");
            System.out.println("6. Perform Maintenance");
            System.out.println("7. Reserve Asset");
            System.out.println("8. Withdraw Reservation");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); 
            
            switch (choice) {
            
            case 1:
            	
            	try {
            		
                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Type: ");
                String type = sc.nextLine();

                System.out.print("Enter Serial Number: ");
                String serialNumber = sc.nextLine();

                System.out.print("Enter Purchase Date (yyyy-mm-dd): ");
                String purchaseDateStr = sc.nextLine();
                Date purchaseDate = Date.valueOf(purchaseDateStr);

                System.out.print("Enter Location: ");
                String location = sc.nextLine();

                System.out.print("Enter Status (in use/decomissioned/under maintenance): ");
                String status = sc.nextLine();

                System.out.print("Enter Owner ID: ");
                int ownerId = sc.nextInt();

                Asset asset = new Asset(0, name, type, serialNumber, purchaseDate, location, status, ownerId);
                boolean isAdded = service.addAsset(asset);
                System.out.println(isAdded ? "Asset added successfully!" : "Failed to add asset.");
                
            	}catch (EmployeeNotFoundException | InvalidStatusException e) {
					// TODO: handle exception
            		System.out.println("Error: " + e.getMessage());
				}
            
                break;
                
            case 2:
            	try {
                System.out.print("Enter Asset ID to update: ");
                int updateId = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Name: ");
                String upName = sc.nextLine();

                System.out.print("Enter Type: ");
                String upType = sc.nextLine();

                System.out.print("Enter Serial Number: ");
                String upSerial = sc.nextLine();

                System.out.print("Enter Purchase Date (yyyy-mm-dd): ");
                String upPurchaseStr = sc.nextLine();
                Date upPurchase = Date.valueOf(upPurchaseStr);

                System.out.print("Enter Location: ");
                String upLocation = sc.nextLine();

                System.out.print("Enter Status: ");
                String upStatus = sc.nextLine();

                System.out.print("Enter Owner ID: ");
                int upOwnerId = sc.nextInt();

                Asset updatedAsset = new Asset(updateId, upName, upType, upSerial, upPurchase, upLocation, upStatus, upOwnerId);
                boolean isUpdated = service.updateAsset(updatedAsset);
                System.out.println(isUpdated ? "Asset updated!" : "Failed to update asset.");
            	}
            	catch(AssetNotFoundException | EmployeeNotFoundException e) {
            		System.out.println("Error: " + e.getMessage());
            	}
                break;
                
            case 3:
            	try {
                System.out.print("Enter Asset ID to delete: ");
                int deleteId = sc.nextInt();
                
                boolean isDeleted = service.deleteAsset(deleteId);
                System.out.println(isDeleted ? "Asset deleted!" : "Asset deletion failed.");
            	}
            	catch(AssetNotFoundException e) {
            		System.out.println("Error: " + e.getMessage());
            	}
                break;
                
            case 4:
            	
            	try {
                    System.out.print("Enter Asset ID: ");
                    int aId = sc.nextInt();

                    System.out.print("Enter Employee ID: ");
                    int eId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Allocation Date (YYYY-MM-DD): ");
                    String allocDateStr = sc.nextLine();

                    boolean result4 = service.allocateAsset(aId, eId, allocDateStr);
                    System.out.println(result4 ? "Asset allocated!" : "Allocation failed.");

                } catch (AssetNotFoundException | AssetNotMaintainException | EmployeeNotFoundException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case 5:
            	try {
                System.out.print("Enter Asset ID: ");
                int daId = sc.nextInt();

                System.out.print("Enter Employee ID: ");
                int empId1 = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Return Date (YYYY-MM-DD): ");
                String retDateStr = sc.nextLine();

                boolean result5 = service.deallocateAsset(daId, empId1, retDateStr);
                System.out.println(result5 ? "Asset deallocated!" : "Deallocation failed.");
            	}
            	catch(AssetNotFoundException e) {
            		System.out.println("Error: " + e.getMessage());
            	}
                break;
                
            case 6:
            	try {
                System.out.print("Enter Asset ID: ");
                int assetId1 = sc.nextInt();
                sc.nextLine(); 

                System.out.print("Enter Maintenance Date (YYYY-MM-DD): ");
                String date = sc.nextLine();

                System.out.print("Enter Description: ");
                String desc = sc.nextLine();

                System.out.print("Enter Maintenance Cost: ");
                double cost = sc.nextDouble();

                boolean result6 = service.performMaintenance(assetId1, date, desc, cost);
                System.out.println(result6 ? "Maintenance recorded." : "Failed to record maintenance.");
            	}
            	catch(AssetNotFoundException e) {
            		System.out.println("Error: " + e.getMessage());
            	}
                break;

            case 7:
            	
            	try {
                    System.out.print("Enter Asset ID: ");
                    int assetId2 = sc.nextInt();

                    System.out.print("Enter Employee ID: ");
                    int empId2 = sc.nextInt();
                    sc.nextLine(); 

                    System.out.print("Enter Reservation Date (YYYY-MM-DD): ");
                    String resDate = sc.nextLine();

                    System.out.print("Enter Start Date (YYYY-MM-DD): ");
                    String startDate = sc.nextLine();

                    System.out.print("Enter End Date (YYYY-MM-DD): ");
                    String endDate = sc.nextLine();

                    boolean result7 = service.reserveAsset(assetId2, empId2, resDate, startDate, endDate);
                    System.out.println(result7 ? "Reservation successful!" : "Failed to reserve asset.");
                    
                } catch (AssetNotFoundException | AssetNotMaintainException | EmployeeNotFoundException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case 8:
            	
            	try {
                System.out.print("Enter Reservation ID to withdraw: ");
                int resId = sc.nextInt();
                
                boolean result8 = service.withdrawReservation(resId);
                
					System.out.println(result8 ? "Reservation withdrawn." : "Reservation withdrawal failed.");
					
				} catch (ReservationNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("Error: " + e.getMessage());
				}
                
                break;

            case 0:
            	
                flag = false;
                System.out.println("Thank you! Visit Again.");
                
                break;

            default:
            	
                System.out.println("Invalid choice. Please select again.");
                break;
            }
        }
    }
}
