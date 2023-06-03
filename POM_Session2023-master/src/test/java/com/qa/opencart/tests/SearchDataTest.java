package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataproviders.ProductDataProviders;
import com.qa.opencart.pojo.Product;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchDataTest extends BaseTest {

    @BeforeClass
    public void searchSetup() {
        accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

//    @DataProvider(name = "productData") => "Move to ProductDataProviders"
//    public Object[][] getProductData() {
//        return new Object[][]{
//
//                {new Product("Macbook", "MacBook Pro", 4)},
//                {new Product("iMac", "iMac", 3)},
//                {new Product("Samsung", "Samsung Galaxy Tab 10.1", 7)},
//                {new Product("Samsung", "Samsung SyncMaster 941BW", 1)}
//        };
//    }


    @Test(dataProvider = "productData", dataProviderClass = ProductDataProviders.class)//Mapping the Data
    public void searchProductCountTest(Product product) {
        resultsPage = accPage.doSearch(product.getSearchKey()); //searchKey was "Macbook"
        Assert.assertTrue(resultsPage.getProductResultCount() > 0);
    }

    @Test(dataProvider = "productData", dataProviderClass = ProductDataProviders.class)//Mapping the Data
    public void searchPageTitleTest(Product product) {
        resultsPage = accPage.doSearch(product.getSearchKey()); //searchKey was "Macbook"
        String actSearchTitle = resultsPage.getResultPageTitle(product.getSearchKey()); //searchKey was "Macbook"
        System.out.println("Search Page Title : " + actSearchTitle);
        Assert.assertEquals(actSearchTitle, "Search - " + product.getSearchKey()); //searchKey was "Macbook"
    }



    @Test(dataProvider = "productData", dataProviderClass = ProductDataProviders.class) //Mapping the Data
    public void selectProductTest(Product product) {
        resultsPage = accPage.doSearch(product.getSearchKey()); //searchKey was "Macbook"
        productInfoPage = resultsPage.selectProduct(product.getProductName()); //productName was "Macbook Pro"
        String actProductHeaderName = productInfoPage.getProductHeaderName();
        System.out.println("Actual Product Name : " + actProductHeaderName);
        Assert.assertEquals(actProductHeaderName, product.getProductName()); //productName was "Macbook Pro"
    }


    @Test(dataProvider = "productData", dataProviderClass = ProductDataProviders.class)
    public void productImagesTest(Product product) {
        resultsPage = accPage.doSearch(product.getSearchKey()); //searchKey was "Macbook"
        productInfoPage = resultsPage.selectProduct(product.getProductName()); //productName was "Macbook Pro"
        int actProductImageCount = productInfoPage.getProductImageCount();
        System.out.println("Actual Product image Count : " + actProductImageCount);
        Assert.assertEquals(actProductImageCount, product.getProductImages()); //expectedImagesCount was
    }
}

