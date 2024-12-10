package com.paylocity.stepdef;

import com.paylocity.pageObjects.LoginPage;
import com.paylocity.utilities.ReadConfigValues;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class LoginImpl {

    LoginPage loginPage = new LoginPage(Hooks.driver);

   @Given("^the user logs in with (valid credentials)$")
   public void user_logs_in_with_valid_credentials(String option) {
       loginPage.login(ReadConfigValues.getProperty("username"),ReadConfigValues.getProperty("password"));
   }

}
