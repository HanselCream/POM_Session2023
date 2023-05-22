package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class ProductInformationPageTest extends BaseTest {

    @BeforeClass
    public void accPageSetup(){
        accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test
    public void productInfoTest(){
        resultsPage = accPage.doSearch("Macbook");
        productInfoPage = resultsPage.selectProduct("MacBook Pro");
        Map<String, String> productInfoMap = productInfoPage.getProductInfo();
        System.out.println(productInfoMap);

        //HashMap
        //{Brand=Apple, Availability=In Stock, Product Name : =MacBook Pro, Ex Tax=$2,000.00,
        // Product Code=Product 18, ProductPrice=$2,000.00, Reward Points=800}

        //LinkedHashMap
        //{Brand=Apple, Product Code=Product 18, Reward Points=800, Availability=In Stock,
        // ProductPrice=$2,000.00, ExTaxPrice=$2,000.00, Product Name=MacBook Pro}

        //TreeMap
        //{Availability=In Stock, Brand=Apple, ExTaxPrice=$2,000.00, Product Code=Product 18,
        // Product Name=MacBook Pro, ProductPrice=$2,000.00, Reward Points=800}


        softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
        softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
        softAssert.assertEquals(productInfoMap.get("Product Name"), "MacBook Pro");
        softAssert.assertEquals(productInfoMap.get("Reward Points"), "800");
        softAssert.assertEquals(productInfoMap.get("Availability"), "In Stock");
        softAssert.assertEquals(productInfoMap.get("ProductPrice"), "$2,000.00");
        softAssert.assertAll(); //MANDATORY
    }





}
