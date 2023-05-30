package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProductInformationPage {

    private final WebDriver driver;
    private final ElementUtility eleUtil;
    private final By productHeader = By.xpath("//div[@id='content']//h1");
    private final By productImage = By.xpath("//a[@class='thumbnail']/img");
    private final By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
    private final By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
    private final By quantity = By.id("input-quantity");
    private final By addToCartBtn = By.id("button-cart");

    //HASHMAP

    private Map<String, String> productInfoMap;


    public ProductInformationPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtility(this.driver);
    }

    public String getProductHeaderName() {
        return eleUtil.doGetElementText(productHeader);
    }

    public int getProductImageCount() {
        return eleUtil.waitForElementsVisible(productImage, 10).size();
    }



    //THIS IS AS A WHOLE getProductMetaData & getProductPricingData
    //NOTES @ONE NOTE
    public Map<String, String> getProductInfo() {
        //productInfoMap = new HashMap<String, String>(); // does not guarantee any specific order of its elements. //  fast key-value lookup
        //productInfoMap = new LinkedHashMap<String, String>(); //maintains the correct oder. // slightly slower and consume memory
        productInfoMap = new TreeMap<String, String>(); //Alphabetical Order

        getProductMetaData();
        getProductPricingData();
        //getProductPricingSampleData();=>We can use this or getProductPricingData() just a different syntax same result
        productInfoMap.put("Product Name", getProductHeaderName());
        return productInfoMap;
    }


    //Brand: Apple
    //Product Code: Product 18
    //Reward Points: 800
    //Availability: In Stock
    private void getProductMetaData() {
        List<WebElement> metaList = eleUtil.getElements(productMetaData);
        for (WebElement e : metaList) {
            String metaText = e.getText();
            String[] metaInfo = metaText.split(":");
            String key = metaInfo[0].trim();
            String value = metaInfo[1].trim();
            productInfoMap.put(key, value);

        }
    }


    //$2,000.00
    //Ex Tax: $2,000.00
    private void getProductPricingData() {
        List<WebElement> priceList = eleUtil.getElements(productPriceData);
        String priceValue = priceList.get(0).getText();
        String exTaxPrice = priceList.get(1).getText();
        String exTaxPriceValue = exTaxPrice.split(":")[1].trim();
        productInfoMap.put("ProductPrice", priceValue);
        productInfoMap.put("ExTaxPrice", exTaxPriceValue);

        }


    //CAN BE DELETED SAMPLE BY HANS
//    private void getProductPricingSampleData() {
//        List<WebElement> priceList = eleUtil.getElements(productPriceData);
//        String priceValue = priceList.get(0).getText();
//        String exTaxPrice = priceList.get(1).getText();
//        String exTaxPriceValue[] = exTaxPrice.split(":");
//        String key = exTaxPriceValue[0].trim();
//        String value = exTaxPriceValue[1].trim();
//
//        productInfoMap.put("ProductPrice", priceValue);
//        productInfoMap.put(key, value);
//    }

}
