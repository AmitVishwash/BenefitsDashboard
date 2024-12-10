package com.paylocity.pageObjects;

import com.paylocity.utilities.ActionLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;
    ActionLibrary actionLibrary = new ActionLibrary();

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="Username")
    private WebElement userNameInput;

    @FindBy(id="Password")
    private WebElement passwordInput;

    @FindBy(css=".btn-primary")
    private WebElement loginButton;

    public void login(String username, String password){
        actionLibrary.enterText(userNameInput,username);
        actionLibrary.enterText(passwordInput,password);
        actionLibrary.clickElement(loginButton);
    }

}
