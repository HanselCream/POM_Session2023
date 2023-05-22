package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultsPage {
        private final WebDriver driver;
        private ElementUtility eleUtil;
        private By resultProducts = By.xpath("//div/div[contains(@class,'product-layout')]"); //THIS IS FIXED

        //1. Constructor of the Page Class
        public ResultsPage(WebDriver driver) {
            this.driver = driver;
            eleUtil = new ElementUtility(this.driver);
        }

        //Page Actions
    public String getResultPageTitle(String searchKey){
        return eleUtil.WaitForTitleContainsAndCapture(searchKey, 5);
    }

    public int getProductResultCount() {
            int resultCount = eleUtil.waitForElementsVisible(resultProducts, 10).size();
            System.out.println("Product Search Result Count ==> " + resultCount);
            return resultCount;
    }

    public ProductInformationPage selectProduct(String productName) {
            By productNameLocator =By.linkText(productName);
            eleUtil.doClick(productNameLocator);
            return new ProductInformationPage(driver);
    }


    }

