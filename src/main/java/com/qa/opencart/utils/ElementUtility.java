package com.qa.opencart.utils;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.frameworkexception.FrameException;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.sql.Driver;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ElementUtility {

    //EncapsulationMethod
    final WebDriver driver;
    private JavaScriptUtility jsUtil; //POM_10 34:19
    final int DEFAULT_TIME_OUT = 5;

    //Constructor
    public ElementUtility(WebDriver driver) {
        this.driver = driver;
        jsUtil = new JavaScriptUtility(this.driver); //POM_10 34:19
    }

    public void doSendKeys(By locator, String value) {
        if (value == null) {
            System.out.println("Null values are not allowed");
            throw new FrameException("VALUECANNOTBENULL");
        }

        doClear(locator); //Added on Lesson 7_ClickElement
        getElement(locator).sendKeys(value);
    }

    //------LESSON 26----------//
    public WebElement getElement(By locator, int timeOut) {
        WebElement element = waitForElementVisible(locator, timeOut);
        if (Boolean.parseBoolean(DriverFactory.highlightElement)) { // POM_10 38:35 => Connected to JavascriptUtility. This can be removed
            jsUtil.flash(element); //This is good for client to see where is your driver
        }
        return element;
        // This was before jsUtil is added "return waitForElementVisible(locator, timeOut);"
    }

    //------LESSON 7 & 9-----------//
    public WebElement getElement(By locator) {
        //return driver.findElement(locator);

        WebElement element = null; // Added on Lesson9_ElementNotPresentConcept
        try {
            element = driver.findElement(locator);
            System.out.println("Element is found with locator:" + locator);
        } catch (NoSuchElementException e) {
            System.out.println("Element is not found using this locator..." + locator);
            waitForElementVisible(locator, DEFAULT_TIME_OUT);
            element = driver.findElement(locator);
        }

        if (Boolean.parseBoolean(DriverFactory.highlightElement)) { // POM_10 34:30 => Connected to JavascriptUtility. This can be removed
            jsUtil.flash(element); //This is good for client to see where is your driver
        } //
        return element;
    }

    //-------LESSON7----------//

    public void doClick(By locator) { //Lesson 7_Click Element
        getElement(locator).click();
    }

    //--------LESSON 26---------//
    public void doClick(By locator, int timeOut) {
        checkElementClickable(locator, timeOut).click();
    }

    public void doClear(By locator) { //Lesson7_Click Element
        getElement(locator).clear();
    }

    //--------LESSON 8---------//

    public String doGetElementText(By locator) { //Lesson8_Locator Concept
        return getElement(locator).getText();
    }

    //--------LESSON 9---------//

    public boolean checkElementIsDisplayed(By locator) { //Lesson9_ElementIsDisplayed
        return getElement(locator).isDisplayed();
    }

    public String doGetAttributeValue(By locator, String attrName) { //Lesson9_GetAttributeConcept
        return getElement(locator).getAttribute(attrName);
    }

    //---------LESSON 10--------//

    public List<WebElement> getElements(By locator) { //Lesson10_TotalImage
        return driver.findElements(locator);
    }

    public int getElementsCount(By locator) { //Lesson10_TotalImage
        return getElements(locator).size();
    }

    public List<String> getElementAttributeValue(By locator, String attrName) { //Lesson10_TotalImage
        List<WebElement> eleList = getElements(locator);
        List<String> eleAttrList = new ArrayList<String>(); //0

        for (WebElement e : eleList) {
            String attrValue = e.getAttribute((attrName));
            //System.out.println(attrValue);
            eleAttrList.add(attrValue);
        }
        return eleAttrList;
    }

    //--------LESSON 10---------//

    public List<String> getElementsTextList(By locator) { //Lesson10_GetElementsSection
        List<WebElement> elementsLinkList = getElements(locator);
        List<String> elementsTextList = new ArrayList<String>(); //0

        for (WebElement e : elementsLinkList) {
            String text = e.getText();
            elementsTextList.add(text);
        }
        return elementsTextList;
    }

    public void clickElementFromPageSection(By locator, String elementText) { //Lesson10_GetElementsSection
        List<WebElement> elementsList = getElements(locator);

        for (WebElement e : elementsList) {
            String text = e.getText();
            System.out.println(text);
            if (text.equals(elementText)) {
                e.click();
                break;
            }
        }
    }

    //--------LESSON 11---------// //Lesson11_GoogleSearch
    public void search(String searchKey, By searchLocator, String suggestName, By suggestions) throws InterruptedException {
        doSendKeys(searchLocator, searchKey);
        Thread.sleep(3000);

        List<WebElement> suggList = getElements(suggestions);
        System.out.println("Suggestions List: " + suggList.size());

        if (suggList.size() > 0) {

            for (WebElement e : suggList) {
                String text = e.getText();
                if (text.length() > 0) {
                    System.out.println(text);
                    if (text.contains(suggestName)) {
                        e.click();
                        break;
                    }
                } else {
                    System.out.println("blank values -- No Suggestions");
                    break;
                }
            }
        } else {
            System.out.println("No Search Suggestions found");
        }
    }

    public boolean IsElementDisplayed(By locator) { //Lesson11_FindElementAndFindElementException
        List<WebElement> elementList = getElements(locator);
        if (elementList.size() > 0) {
            System.out.println("Element is present on the page");
            return true;
        } else {
            return false;
        }
    }

    //**************************DROP DOWN Utils**************************//
    //--------LESSON 12---------//

    public void doSelectDropDownByIndex(By locator, int index) { //L12_DropDownSelection
        Select select = new Select(getElement(locator));
        select.selectByIndex(index);
    }

    public void doSelectDropDownByValue(By locator, String value) { //L12_DropDownSelection
        Select select = new Select(getElement(locator));
        select.selectByValue(value);
    }

    public void doSelectDropDownByVisibleText(By locator, String text) { //L12_DropDownSelection
        Select select = new Select(getElement(locator));
        select.selectByVisibleText(text);
    }


    //L12_DropDownWithoutSelectMethod
    public int getDropDrownValueCount(By locator) {
        return getAllDropDownOptions(locator).size();
    }

    public List<String> getAllDropDownOptions(By locator) { //L12_DropDownWithoutSelectMethod
        Select select = new Select(getElement(locator));
        List<WebElement> optionsList = select.getOptions();
        List<String> optionsValueList = new ArrayList<String>();
        System.out.println("Total Value: " + optionsList.size());

        for (WebElement e : optionsList) {
            String text = e.getText();
            System.out.println(text);
            optionsValueList.add(text);
        }
        return optionsValueList;
    }

    public boolean doSelectDropDownValue(By locator, String dropDownValue) { //L12_DropDownWithoutSelectMethod
        boolean flag = false;
        Select select = new Select(getElement(locator));
        List<WebElement> optionsList = select.getOptions();
        System.out.println("Total Value: " + optionsList.size());

        for (WebElement e : optionsList) {
            String text = e.getText();
            System.out.println(text);
            if (text.equals(dropDownValue)) {
                flag = true;
                e.click();
                break;
            }

        }
        if (flag == false) {
            System.out.println(dropDownValue + " is not present in the dropdown " + locator);
        }
        return flag;
    }

    public boolean doSelectDropDownWithoutSelect(By locator, String value) {
        boolean flag = false;
        List<WebElement> optionsList = getElements(locator);
        for (WebElement e : optionsList) {
            String text = e.getText();
            if (text.equals(value)) {
                flag = true;
                e.click();
                break;
            }
        }
        if (flag == false) {
            System.out.println(value + " is not present in the dropdown " + locator);
        }
        return flag;
    }

    //**************************FRAMES IFRAMES**************************//
    //--------LESSON 19---------//
    public void getFrame(By frame) {
        driver.switchTo().frame(driver.findElement(frame));
    }

    //**************************ACTION CLASS UTILS**************************//
    //--------LESSON 21---------//

    public void doActionsClick(By locator) {
        Actions act = new Actions(driver);
        act.click(getElement(locator)).build().perform();
    }

    //--------LESSON 26---------//
    public void doActionsClick(By locator, int timeOut) {
        Actions act = new Actions(driver);
        act.click(checkElementClickable(locator, timeOut)).build().perform();
    }

    public void doActionsSendKeys(By locator, String value) {
        Actions act = new Actions(driver);
        act.sendKeys(getElement(locator), value).build().perform();
    }

    public void doDragAndDrop(By sourceLocator, By targetLocator) {
        Actions action = new Actions(driver);
        action.dragAndDrop(getElement(sourceLocator), getElement(targetLocator)).build().perform();
    }

    public void doContextClick(By locator) {
        Actions action = new Actions(driver);
        action.contextClick(getElement(locator)).build().perform();
    }

    public void doMoveToElement(By locator) {
        Actions action = new Actions(driver);
        action.moveToElement(getElement(locator)).build().perform();
    }

    public void handleTwoLevelMenu(By parentMenu, By childMenu) throws InterruptedException {
        doMoveToElement(parentMenu);
        Thread.sleep(2000);
        doClick(childMenu);
    }

    public void handleTwoLevelMenu(By parentMenu, String childMenuLinkText) throws InterruptedException {
        doMoveToElement(parentMenu);
        Thread.sleep(2000);
        doClick(By.linkText(childMenuLinkText));
    }

    public void multiLevelMenuChildHandle(By parentMenuLocator, String Level2LinkText, String Level3LinkText,
                                          String Level4LinkText) throws InterruptedException {
        WebElement level1 = getElement(parentMenuLocator);
        Actions act = new Actions(driver);

        act.moveToElement(level1).build().perform();
        Thread.sleep(1000);
        //To Pause Website: Source Element = fn + f8

        WebElement level2 = getElement(By.linkText(Level2LinkText));
        act.moveToElement(level2).build().perform();
        Thread.sleep(1000);

        WebElement level3 = getElement(By.linkText(Level3LinkText));
        act.moveToElement(level3).build().perform();
        Thread.sleep(1000);

        doClick(By.linkText(Level4LinkText));
    }

    //**************************WAIT UTILS**************************//

    //--------LESSON 27---------//
    //******Website Title*****//
    public String WaitForTitleContainsAndCapture(String titleFraction, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
            return driver.getTitle();
        } else {
            System.out.println("Title not present within the given timeout : " + timeOut);
            return null;
        }
    }
    //******Wait for Windows*****//
    public Boolean WaitForTotalWindows(int totalWindowsToBe, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.numberOfWindowsToBe(totalWindowsToBe));
    }

    public void waitForFrameAndSwitchToItByIDOrName(int timeOut, String idOrName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
    }

    public String WaitForTitleFullTitleAndCapture(String titleValue, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        if (wait.until(ExpectedConditions.titleIs(titleValue))) {
            return driver.getTitle();
        } else {
            System.out.println("Title not present within the given timeout : " + timeOut);
            return null;
        }
    }
    //******URL*****//
    public String WaitForURLContainsAndCapture(String urlFraction, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
            return driver.getCurrentUrl();
        } else {
            System.out.println("Url not present within the given timeout : " + timeOut);
            return null;
        }
    }

    public String WaitForFullURLAndCapture(String urlValue, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        if (wait.until(ExpectedConditions.urlToBe(urlValue))) {
            return driver.getCurrentUrl();
        } else {
            System.out.println("Url not present within the given timeout : " + timeOut);
            return null;
        }
    }
    //******ALERT POPUP*****//


    public Alert waitForAlertJsPopUWithFluentWait( int timeOut, int pollingTime) { // Lesson 28
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver) //ALT-7 or Ctrl+Shift+I
                .withTimeout(Duration.ofSeconds(timeOut))
                .ignoring(NoAlertPresentException.class)
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .withMessage("-----TimeOut is Done .. element is not found");

        return wait.until(ExpectedConditions.alertIsPresent());
    }
    //--------LESSON 27---------//
    public Alert WaitForAlertJsPopUp(int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public String AlertJsGetText(int timeOut){
        return WaitForAlertJsPopUp(timeOut).getText();
    }

    public void AlertAccept(int timeOut){
        WaitForAlertJsPopUp(timeOut).accept();
    }

    public void AlertDismiss(int timeOut){
        WaitForAlertJsPopUp(timeOut).dismiss();
    }

    public void EnterAlertValue(String value, int timeOut){
        WaitForAlertJsPopUp(timeOut).sendKeys(value);
    }

    //--------LESSON 26---------//
    /**
     * presenceOfElementLocated**
     * An expectation for checking that an element is present on the DOM of the page.
     * This does not necessarily mean that the element is visible on the page
     *
     * @param locator locator
     * @param timeOut Timeout
     */

    public WebElement waitForElementPresenceBy(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        WebElement element =  wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        if (Boolean.parseBoolean(DriverFactory.highlightElement)){ // POM_10 34:30 => Connected to JavascriptUtility. This can be removed
            jsUtil.flash(element); //This is good for client to see where is your driver
        }

        return element;

        //Before this was just
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        //return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * visibilityOfElementLocated
     * An expectation for checking that an element is present on the DOM of a page and visible on the page.
     * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
     *
     * @param locator locator
     * @param timeOut Timeout
     */
    public WebElement waitForElementVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    //--------LESSON 27---------//
    //******ADDING INTERVAL TIME*****//

    //DefaultTime is 500ms IntervalTIme
    public WebElement waitForElementVisible(By locator, int timeOut, int intervalTime) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * presenceOfAllElementsLocatedBy
     * An expectation for checking that there is at least one element present on a webpage
     *
     * @param locator locator
     * @param timeOut timeOut
     * @return return
     */
    public List<WebElement> waitForElementsPresence(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    /**
     * visibilityOfAllElementsLocatedBy
     * An expectation for checking that all ELEMENTS present on the web page that match the locator are visible.
     * Visibility means that the elements are not only displayed but also have a height and width that is greater than 0.
     * @param locator locator
     * @param timeOut timeOut
     * @return return
     */
    public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    /**
     * An expectation for checking an element is visible and enabled such that you can click it.
     * @param locator locator
     * @param timeOut timeOut
     */
    public void clickElementWhenReady(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * An expectation for checking an element is visible and enabled such that you can click it.
     * @param locator locator
     * @param timeOut timeOut
     */
    public WebElement checkElementClickable(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));

    }

    //--------LESSON 27---------//
    //******FRAMES WITH WAIT*****//

    public void waitForFrameAndSwitchToItFluentWait(By locator, int timeOut, int pollingTime, String idOrName) { //Lesson 28
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver) //ALT-7 or Ctrl+Shift+I
                .withTimeout(Duration.ofSeconds(timeOut))
                .ignoring(NoSuchFrameException.class)
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .withMessage("-----TimeOut is Done .. element is not found");

         wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
    }

    public void waitForFrameAndSwitchToItByIndex(int timeOut, int frameIndex) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex)) ;
    }

    public void waitForFrameAndSwitchToItByFrameElement(int timeOut, WebElement frameElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
    }
    public void waitForFrameAndSwitchToItByFrameLocator(int timeOut, By frameLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator)) ;
    }


    //--------LESSON 28---------//
    //******FLUENT WAIT*****//
    public WebElement waitForElementVisibleWithFluentWait(By locator, int timeOut, int pollingTime) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver) //ALT-7 or Ctrl+Shift+I
                .withTimeout(Duration.ofSeconds(timeOut))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class)
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .withMessage("-----TimeOut is Done .. element is not found");

        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForElementPrecenseWithFluentWait(By locator, int timeOut, int pollingTime) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver) //ALT-7 or Ctrl+Shift+I
                .withTimeout(Duration.ofSeconds(timeOut))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class)
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .withMessage("-----TimeOut is Done .. element is not found");

        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    //******CUSTOM WAIT******//
    public WebElement retryingElement(By locator, int timeOut) {

        WebElement element = null;
        int attempts = 0;

        while (attempts < timeOut) {
            try {
                element = getElement(locator);
                System.out.println("Element is found... " + locator + " in attempt :" + attempts);
                break;
            } catch (NoSuchElementException e) {
                System.out.println("Element is not found... " + locator + " in attempt :" + attempts);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            attempts++;
        }
        if (element == null) {
            System.out.println("Element is not found .. tried for " + timeOut + "seconds" + " interval of 500 milliseconds");
        }

        return element;
    }

    public WebElement retryingElement(By locator, int timeOut, int pollingTime) {

        WebElement element = null;
        int attempts = 0;

        while (attempts < timeOut) {
            try {
                element = getElement(locator);
                System.out.println("Element is found... " + locator + " in attempt :" + attempts);
                break;
            } catch (NoSuchElementException e) {
                System.out.println("Element is not found... " + locator + " in attempt :" + attempts);

                try {
                    Thread.sleep(pollingTime);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            attempts++;
        }
        if (element == null) {
            System.out.println("Element is not found .. tried for " + timeOut + "seconds" + " interval of 500 milliseconds");
        }
        return element;
    }

    //--------LESSON 29---------//
    //******PAGE LOAD****//
    public Boolean isPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete'"))
                .toString(); //"true"
        return Boolean.parseBoolean(flag);
    }

    public void waitForPageLoad(int timeOut) {

        long endTime = System.currentTimeMillis() + timeOut;
        while(System.currentTimeMillis() < endTime) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String pageState = js.executeScript("return document.readyState").toString();
            if(pageState.equals("complete")) {
                System.out.println("PAGE DOM is fully loaded now ...");
                break;
            }
            else {
                System.out.println("PAGE IS not loaded .." + pageState);
            }
        }
    }




}


