package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

    //INTEGERS CONSTANT
    public static final int SHORT_DEFAULT_WAIT = 5; //ALT-F7  to see usage
    public static final int MEDIUM_DEFAULT_WAIT = 10;
    public static final int LONG_DEFAULT_WAIT = 10;

    //ShortCut CTRL-N
    // LoginPage
    public static final String LOGIN_PAGE_TITLE_VALUE = "Account Login";
    public static final String LOGIN_PAGE_URL_FRACTION_VALUE = "route=account/login";
    public static final String LOGIN_ERROR_MESSAGE = "Warning: No match for E-Mail Address and/or Password";


    //AccountPage
    public static final String ACCOUNTS_PAGE_TITLE_VALUE = "My Account";



    //AccountPageTest
    //LIST CONSTANT
    public static final List<String> EXP_ACCOUNTS_HEADER_LIST =
            Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");

    public static final String USER_REG_SUCCESS_MSG = "Your Account Has Been Created!";

    //EXCEL_UTIL
    //public static final String TEST_DATA_SHEET_PATH = ".src/main/resources/testdata/OpenCartTestData.xlsx"; //=> ExcelUtil

    //************Sheet Names**************//
    public static final String REGISTER_SHEET_NAME = "register";
    public static final String INCORRECT_LOGIN_SHEET_NAME = "incorrectlogin";




}
