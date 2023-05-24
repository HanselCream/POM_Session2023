package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataproviders.ProductDataProviders;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {
    @BeforeClass
    public void searchSetup() {
        accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    /**
     * @DataProvider -
     * TestNG annotation used to supply test data to your test methods.
     * It allows you to create a method that serves as a data source, and
     * TestNG will run your test method multiple times with different data from the data provider.
     * This is DataDriven Method
     */
    //2D - Object Array
//    @DataProvider
//    public Object[][] getProductSearchKeyData() {
//        return new Object[][]{
//
//                {"Macbook"},
//                {"iMac"},
//                {"Samsung"}
//        };
//    }


    @Test(dataProvider = "productDataWithSearchKey", dataProviderClass = ProductDataProviders.class)//Mapping the Data => Before: dataProvider = "getProductSearchKeyData"
    public void searchProductCountTest(String searchKey) {
        resultsPage = accPage.doSearch(searchKey); //searchKey was "Macbook"
        Assert.assertTrue(resultsPage.getProductResultCount() > 0);
    }

    @Test(dataProvider = "productDataWithSearchKey", dataProviderClass = ProductDataProviders.class)//Mapping the Data => Before: "getProductSearchKeyData"
    public void searchPageTitleTest(String searchKey) {
        resultsPage = accPage.doSearch(searchKey); //searchKey was "Macbook"
        String actSearchTitle = resultsPage.getResultPageTitle(searchKey); //searchKey was "Macbook"
        System.out.println("Search Page Title : " + actSearchTitle);
        Assert.assertEquals(actSearchTitle, "Search - " + searchKey); //searchKey was "Macbook"
    }

    //
//    @DataProvider
//    public Object[][] getProductTestData() {
//        return new Object[][]{
//
//                {"Macbook", "MacBook Pro"},
//                {"iMac", "iMac"},
//                {"Samsung", "Samsung SyncMaster 941BW"},
//                {"Samsung", "Samsung Galaxy Tab 10.1"},
//        };
//    }

    @Test(dataProvider = "productDataWithName", dataProviderClass = ProductDataProviders.class) //Mapping the Data => Before: dataProvider = "getProductTestData"
    public void selectProductTest(String searchKey, String productName) {
        resultsPage = accPage.doSearch(searchKey); //searchKey was "Macbook"
        productInfoPage = resultsPage.selectProduct(productName); //productName was "Macbook Pro"
        String actProductHeaderName = productInfoPage.getProductHeaderName();
        System.out.println("Actual Product Name : " + actProductHeaderName);
        Assert.assertEquals(actProductHeaderName, productName); //productName was "Macbook Pro"
    }

//    @DataProvider
//    public Object[][] getProductImagesTestData() {
//        return new Object[][]{
//
//                {"Macbook", "MacBook Pro", 4},
//                {"iMac", "iMac", 3},
//                {"Samsung", "Samsung SyncMaster 941BW", 1},
//                {"Samsung", "Samsung Galaxy Tab 10.1", 7},
//        };
//    }


    @Test(dataProvider = "productDataWithImage", dataProviderClass = ProductDataProviders.class) //Mapping the Data => Before: dataProvider = "getProductImagesTestData"
    public void productImagesTest(String searchKey, String productName, int expectedImagesCount) {
        resultsPage = accPage.doSearch(searchKey); //searchKey was "Macbook"
        productInfoPage = resultsPage.selectProduct(productName); //productName was "Macbook Pro"
        int actProductImageCount = productInfoPage.getProductImageCount();
        System.out.println("Actual Product image Count : " + actProductImageCount);
        Assert.assertEquals(actProductImageCount, expectedImagesCount); //expectedImagesCount was "4"
    }
}
