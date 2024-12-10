package com.paylocity.pageObjects;

import com.paylocity.utilities.ActionLibrary;
import com.paylocity.utilities.NetPayCalculator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BenefitsDashboardPage {
    WebDriver driver;
    ActionLibrary actionLibrary = new ActionLibrary();

    public static String employeeFirstName;
    public static String employeeLastName;
    public static int employeeDependents;

    public BenefitsDashboardPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    @FindBy(css = ".navbar-brand")
    public WebElement headerText;

    @FindBy(xpath = "//a[text()='Log Out']")
    public WebElement logOutLink;

    @FindBy(id = "add")
    public WebElement addEmployeeButton;

    // Employee details field

    @FindBy(id = "firstName")
    public WebElement employeeFirstNameInput;

    @FindBy(id = "lastName")
    public WebElement employeeLastNameInput;

    @FindBy(id = "dependants")
    public WebElement employeeDependentsInput;

    @FindBy(id = "addEmployee")
    public WebElement employeeRecordAddButton;

    @FindBy(id = "updateEmployee")
    public WebElement employeeRecordUpdateButton;

    @FindBy(id = "deleteEmployee")
    public WebElement employeeRecordDeleteButton;

    public boolean isHeaderTextDisplayed(){
        return headerText.isDisplayed();
    }

    public boolean isLogOutLinkDisplayed(){
        return logOutLink.isDisplayed();
    }

    public boolean isAddEmployeeButtonDisplayed(){
        return addEmployeeButton.isDisplayed();
    }


    public void clickAddEmployeeButton() {
        actionLibrary.clickElement(addEmployeeButton);
    }

    public void clickEmployeeRecordAddButton() {
        actionLibrary.clickElement(employeeRecordAddButton);
    }

    public void clickEmployeeRecordUpdateButton() {
        actionLibrary.clickElement(employeeRecordUpdateButton);
    }



    public void enterEmployeeDetails(String firstName, String lastName, int dependents) {
        actionLibrary.enterText(employeeFirstNameInput,firstName);
        actionLibrary.enterText(employeeLastNameInput,lastName);
        actionLibrary.enterText(employeeDependentsInput,String.valueOf(dependents));
        employeeFirstName = firstName;
        employeeLastName = lastName;
        employeeDependents = dependents;
    }

    public void selectEdit() {
        WebElement elemnt = this.driver.findElement(By.xpath("//tr/td[text()='Anthony']/following-sibling::td/i[@class='fas fa-edit']"));
        elemnt.click();

        WebElement element2 = this.driver.findElement(By.cssSelector(".btn-secondary"));
        element2.click();


        WebElement elemnt1 = this.driver.findElement(By.xpath("//tr/td[text()='Anthony']/following-sibling::td/i[@class='fas fa-times']"));
        elemnt1.click();
        try{
            Thread.sleep(3000);
        }catch (Exception e){

        }


    }

    public boolean isEmployeeRecordAdded() {
        try{
            Thread.sleep(3000);
        }catch (Exception e){

        }
        WebElement employeeDataRecord = this.driver.findElement(By.xpath("//tr/td[text()='"+employeeLastName+"']"));
        return employeeDataRecord.isDisplayed();
    }

    public void verifyNetPay(){
        WebElement element = this.driver.findElement(By.xpath("//tr/td[text()='"+employeeLastName+"']//following-sibling::td[5]"));
        Double actualNetPay = Double.valueOf(element.getText());
        NetPayCalculator netPayCalculator = new NetPayCalculator(employeeDependents);
        Double expectedNetPay = Math.round(netPayCalculator.calculateNetPay() *100)/100D;
        Assert.assertEquals(expectedNetPay,actualNetPay);
    }

    public void selectEditForAnEmployeeRecord(){
        WebElement element = this.driver.findElement(By.xpath("//tr/td[text()='"+employeeLastName+"']/following-sibling::td/i[@class='fas fa-edit']"));
        actionLibrary.clickElement(element);
    }

    public void enterDependents(int dependents) {
        actionLibrary.enterText(employeeDependentsInput,String.valueOf(dependents));
        employeeDependents = dependents;
    }

    public void verifyDependentsValueIsUpdated(int expectedDependentsCount) {
        try{
            Thread.sleep(3000);

        }catch (Exception e){
        }
        WebElement element = this.driver.findElement(By.xpath("//tr/td[text()='"+employeeLastName+"']//following-sibling::td[1]"));
        Assert.assertEquals(expectedDependentsCount, Integer.parseInt(element.getText()));
        this.verifyNetPay();
    }

    public void selectDeleteAnEmployeeRecord() {
        WebElement element = this.driver.findElement(By.xpath("//tr/td[text()='"+employeeLastName+"']/following-sibling::td/i[@class='fas fa-times']"));
        actionLibrary.clickElement(element);
        actionLibrary.clickElement(employeeRecordDeleteButton);
    }

    public void verifyEmployeeDataIsDeleted() {
        try {
            Thread.sleep(2000);
            WebElement element = this.driver.findElement(By.xpath("//tr/td[text()='"+employeeLastName+"']"));
            Assert.assertFalse(element.isDisplayed());
        }catch (Exception e){

        }
    }
}
