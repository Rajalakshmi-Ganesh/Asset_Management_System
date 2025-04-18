package com.hexaware.assetmanagementsystem.exception;

/**
 *@Author: Rajalakshmi Ganesh
 *@Desc: Employee Not Found Exception(custom exception) 
 * Date: 18-04-2025
*/

public class EmployeeNotFoundException extends Exception{

	public EmployeeNotFoundException(String mssg) {
		super(mssg);
	}

	
}
