@dashboard
Feature: Freight Time Calculator

  Background:
    Given the user logs in with valid credentials
    And the user should land on Paylocity Benefits Dashboard page

  @addAnEmployee
  Scenario Outline: User should be able to add an employee record
    When the user selects Add Employee
    Then the user should be able to enter employee details as First Name "<firstName>" Last Name as "<lastName>" and dependents as <dependents>
    And the user confirms to add employee
    Then the user should see the employee details in the table
    And the benefit cost calculation should be correct
    Examples:
      |firstName|lastName|dependents|
      |Amit     |Verma   |1         |


  @editAnEmployee
 Scenario Outline: User should be able to edit an employee record
    When the user selects Add Employee
    Then the user should be able to enter employee details as First Name "<firstName>" Last Name as "<lastName>" and dependents as <dependents>
    And the user confirms to add employee
    Then the user should see the employee details in the table
    And the user select to Edit an employee record
    And the user update employee dependent to <updatedDependent>
    Then the user dependents should be updated in the employee table to <updatedDependent>
    Examples:
      |firstName|lastName|dependents|updatedDependent|
      |Amit     |Verma   |1         |2               |

    @deleteAnEmployee
    Scenario Outline: User should be able to delete an employee
      When the user selects Add Employee
      And the user should be able to enter employee details as First Name "<firstName>" Last Name as "<lastName>" and dependents as <dependents>
      And the user confirms to add employee
      And the user should see the employee details in the table
      And the user select to delete an employee record
      Then the employee data should be deleted
      Examples:
        |firstName|lastName|dependents|
        |Amit     |Verma   |1         |
