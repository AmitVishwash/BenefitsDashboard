package com.paylocity.stepdef;

import com.github.javafaker.Faker;
import com.paylocity.pageObjects.BenefitsDashboardPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class BenefitsDashboardImpl {
    BenefitsDashboardPage benefitsDashboardPage = new BenefitsDashboardPage(Hooks.driver);

    Faker datafaker = new Faker();


    @When("the user selects Add Employee")
    public void user_select_add_employee(){
        benefitsDashboardPage.clickAddEmployeeButton();
    }

//    @Then("^the user should be able to enter employee details as")
//    public void user_should_be_able_to_enter_employee_details(String firstName, String lastName, int){
//        benefitsDashboardPage.enterEmployeeDetails();
//    }

    @Then("the user should be able to enter employee details as First Name {string} Last Name as {string} and dependents as {int}")
    public void the_user_should_be_able_to_enter_employee_details_as_first_name_last_name_as_and_dependents_as(String firstName, String lastName, int dependents) {
        benefitsDashboardPage.enterEmployeeDetails(firstName,lastName+datafaker.name().lastName(),dependents);
    }
    @And("the user confirms to add employee")
    public void user_confirms_to_add_employee() {
        benefitsDashboardPage.clickEmployeeRecordAddButton();
    }

    @And("the user update employee dependent to {int}")
    public void user_edit_employee_dependent(int updatedDependents){
        benefitsDashboardPage.enterDependents(updatedDependents);
        benefitsDashboardPage.clickEmployeeRecordUpdateButton();
    }

    @And("the user select to Edit an employee record")
    public void user_select_to_edit_an_employee_record(){
        benefitsDashboardPage.selectEditForAnEmployeeRecord();
    }

    @And("the user should see the employee details in the table")
    public void user_should_see_the_employee_details_in_the_table(){
        Assert.assertTrue(benefitsDashboardPage.isEmployeeRecordAdded());
    }

    @And("the benefit cost calculation should be correct")
    public void benefit_cost_calculation_should_be_correct(){
        benefitsDashboardPage.verifyNetPay();
    }
    @Then("^the user should land on (.*) page$")
    public void user_should_land_on_benefits_dashboard_page(String page){
        Assert.assertTrue(isBenefitsDashboardPageDisplayed());
    }

    @Then("the user dependents should be updated in the employee table to {int}")
    public void user_data_should_be_updated_in_the_employee_table(int expectedDependentsCount){
        benefitsDashboardPage.verifyDependentsValueIsUpdated(expectedDependentsCount);
    }

    @And("the user select to delete an employee record")
    public void user_select_to_delete_an_employee_record(){
        benefitsDashboardPage.selectDeleteAnEmployeeRecord();

    }

    @Then("the employee data should be deleted")
    public void employee_data_should_be_deleted(){
        benefitsDashboardPage.verifyEmployeeDataIsDeleted();
    }


    private boolean isBenefitsDashboardPageDisplayed(){
        Assert.assertTrue(benefitsDashboardPage.isHeaderTextDisplayed());
        Assert.assertTrue(benefitsDashboardPage.isLogOutLinkDisplayed());
        Assert.assertTrue(benefitsDashboardPage.isAddEmployeeButtonDisplayed());
        return true;
    }
}
