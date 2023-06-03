package com.qa.opencart.dataproviders;

import com.qa.opencart.pojo.Product;
import org.testng.annotations.DataProvider;

public class ProductDataProviders {


    //MASTER DATA => SearchDataTest.java
    @DataProvider(name = "productData")
    public Object[][] getProductData() {
        return new Object[][]{

                {new Product("Macbook", "MacBook Pro", 4)},
                {new Product("iMac", "iMac", 3)},
                {new Product("Samsung", "Samsung Galaxy Tab 10.1", 7)},
                {new Product("Samsung", "Samsung SyncMaster 941BW", 1)}
        };
    }

    //INDIVIDUAL PROVIDERS => SearchTest.java

    @DataProvider(name = "productDataWithImage")
    public Object[][] getProductImagesTestData() {
        return new Object[][]{

                {"Macbook", "MacBook Pro", 4},
                {"iMac", "iMac", 3},
                {"Samsung", "Samsung SyncMaster 941BW", 1},
                {"Samsung", "Samsung Galaxy Tab 10.1", 7},
        };
    }

    @DataProvider(name = "productDataWithName")
    public Object[][] getProductTestData() {
        return new Object[][]{

                {"Macbook", "MacBook Pro"},
                {"iMac", "iMac"},
                {"Samsung", "Samsung SyncMaster 941BW"},
                {"Samsung", "Samsung Galaxy Tab 10.1"},
        };
    }

    @DataProvider(name = "productDataWithSearchKey")
    public Object[][] getProductSearchKeyData() {
        return new Object[][]{

                {"Macbook"},
                {"iMac"},
                {"Samsung"}
        };
    }
}
