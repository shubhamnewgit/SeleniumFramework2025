package com.qa.openCart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	public static final String CONFIG_FILE_PATH = "./src/test/resources/config/config.properties";
	public static final String CONFIG_QA_FILE_PATH = "./src/test/resources/config/qa.properties";
	public static final String CONFIG_DEV_FILE_PATH = "./src/test/resources/config/dev.properties";
	public static final String CONFIG_UAT_FILE_PATH = "./src/test/resources/config/uat.properties";
	public static final String CONFIG_STAGE_FILE_PATH = "./src/test/resources/config/stage.properties";
	public static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/OpenCartRegTestData.xlsx";
	
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";
	
	
	public static final String ACCOUNT_PAGE_TITLE = "Account Login";
	public static final String ACC_PAGE_FRACTION_URL = "index.php?route=account/login";
	public static final List<String> ACC_PAGE_HEADERS_LIST = 
			Arrays.asList("New Customer", "Returning Customer");

	public static final String USER_REGISTER_SUCCESS_MSG = "Your Account Has Been Created!";

	
	
	//*********************SHEET NAME CONSTANT**********************************
	
	public static final String REGISTER_SHEET_NAME = "register";
}
