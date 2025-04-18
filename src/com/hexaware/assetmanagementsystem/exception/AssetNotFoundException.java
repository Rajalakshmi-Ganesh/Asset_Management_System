package com.hexaware.assetmanagementsystem.exception;

/**
 *@Author: Rajalakshmi Ganesh
 *@Desc: Asset Not Found Exception(custom exception) 
 * Date: 12-04-2025
*/
public class AssetNotFoundException extends Exception{
	
	 public AssetNotFoundException(String mssg) {
		 
	        super(mssg);
	    }
}
