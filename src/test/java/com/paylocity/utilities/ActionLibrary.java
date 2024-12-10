package com.paylocity.utilities;

import com.paylocity.stepdef.Hooks;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionLibrary {

    WebDriverWait webDriverWait= new WebDriverWait(Hooks.driver,15);;


    public void clickElement(WebElement element){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();



    }

    public void launchUrl() {
        Hooks.driver.get(ReadConfigValues.getProperty("hostUrl"));

    }

    public void enterText(WebElement element, String value) {
        if(webDriverWait.until(ExpectedConditions.visibilityOf(element)).isDisplayed()){
            element.clear();
            element.sendKeys(value);
        }else{
            throw new ElementNotVisibleException("Element is not visible:"+ new Throwable().getCause());
        }

    }


    public void scroll(){
        JavascriptExecutor js = (JavascriptExecutor) Hooks.driver;
        js.executeScript("javascript:window.scrollBy(250,350)");
    }

    public void scrollElementIntoView(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) Hooks.driver;
        js.executeScript("arguments[0].scrollIntoView(true);",element);
    }
    public void selectElement(WebElement element, String value){
        if(webDriverWait.until(ExpectedConditions.visibilityOf(element)).isDisplayed()){
            Select selectValues = new Select(element);
            selectValues.selectByValue(value);
        }else{
            throw new ElementNotSelectableException("Element is not selectable:"+ new Throwable().getCause());
        }
    }

    public void waitForElementToDisplay(WebElement element) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

}