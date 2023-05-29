package com.qa.opencart.pages;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AccountsPage {

    private final WebDriver driver;
    private ElementUtility eleUtil;

    //1. Constructor of the Page Class
    public AccountsPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtility(this.driver);
    }

    //2 private By locators:
    private final By logout = By.xpath("//div[@class='list-group']/a[text()='Logout']");
    private final By myAccount = By.linkText("My Account");
    private final By accHeaders = By.xpath("//div/h2");
    private final By search = By.name("search");
    private final By searchIcon = By.cssSelector("div#search button");

    //3. Page Actions/Methods
    public String getAccPageTitle() {
        return eleUtil.WaitForTitleContainsAndCapture(AppConstants.ACCOUNTS_PAGE_TITLE_VALUE, AppConstants.SHORT_DEFAULT_WAIT);
    }

    public boolean isLogoutLinkExist() {
        return eleUtil.checkElementIsDisplayed(logout);
    }

    public boolean isMyAccountLinkExist() {
        return eleUtil.checkElementIsDisplayed(myAccount);
    }

    public List<String> getAccountPageHeadersList() {
        List<WebElement> headersList = driver.findElements(accHeaders);
        List<String> headerValueList = new ArrayList<String>();
        for(WebElement e : headersList) {
            String text = e.getText();
            headerValueList.add(text);
        }
        return headerValueList;
    }

    public ResultsPage doSearch(String searchTerm) {
        eleUtil.waitForElementVisible(search, AppConstants.SHORT_DEFAULT_WAIT);
        eleUtil.doSendKeys(search, searchTerm);
        eleUtil.doClick(searchIcon);
        //return the next landing page -- AccountsPage -- This is called: Page Chaining Model
        return new ResultsPage(driver);
    }
}
