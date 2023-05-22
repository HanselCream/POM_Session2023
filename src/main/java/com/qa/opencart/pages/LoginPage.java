package com.qa.opencart.pages;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class LoginPage {

    private final WebDriver driver;
    private ElementUtility eleUtil;

    //1. Constructor of the Page Class
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtility(this.driver);
    }

    //2 private By locators:
    private final By emailId = By.id("input-email");
    private final By password = By.id("input-password");
    private final By loginBtn = By.xpath("//input[@value='Login']");
    private final By forgottenPwdLink = By.linkText("Forgotten Password");
    private final By footersLinks = By.xpath("//footer//a");

    //3. Page Actions/Methods:
    public String getLoginPageTitle() {
        return eleUtil.WaitForTitleContainsAndCapture(AppConstants.LOGIN_PAGE_TITLE_VALUE, AppConstants.SHORT_DEFAULT_WAIT);
    }

    public String getLoginPageUrl() {
        return eleUtil.WaitForURLContainsAndCapture(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE, AppConstants.SHORT_DEFAULT_WAIT);
    }

    public boolean isForgotPwdLinkExist() {
        return eleUtil.checkElementIsDisplayed(forgottenPwdLink);
    }

    public List<String> getFooterLinkList() {
        List<WebElement> footerLinksList = eleUtil.waitForElementsVisible(footersLinks, AppConstants.MEDIUM_DEFAULT_WAIT);
        List<String> footerTextList = new ArrayList<String>();
        for(WebElement e : footerLinksList) {
            String text = e.getText();
            footerTextList.add(text);
        }
        return footerTextList;
    }

    public AccountsPage doLogin(String userName, String pwd) {
        eleUtil.waitForElementVisible(emailId, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(userName);
        eleUtil.doSendKeys(password, pwd);
        eleUtil.doClick(loginBtn);
        //return the next landing page -- AccountsPage -- This is called: Page Chaining Model
        return new AccountsPage(driver);
    }











}
