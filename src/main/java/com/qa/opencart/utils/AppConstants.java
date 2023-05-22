package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

    //TODO: INTEGERS CONSTANT
    public static final int SHORT_DEFAULT_WAIT = 5; //ALT-F7  to see usage
    public static final int MEDIUM_DEFAULT_WAIT = 10;
    public static final int LONG_DEFAULT_WAIT = 10;

    //ShortCut CTRL-N
    // TODO: Open [LoginPage]
    public static final String LOGIN_PAGE_TITLE_VALUE = "Account Login";
    public static final String LOGIN_PAGE_URL_FRACTION_VALUE = "route=account/login";


    //TODO: Open [AccountPage]
    public static final String ACCOUNTS_PAGE_TITLE_VALUE = "My Account";



    //AccountPageTest

    //LIST CONSTANT
    public static final List<String> EXP_ACCOUNTS_HEADER_LIST =
            Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");





}