Feature: Login to CTB Application

  Background:
    Given User go to Login Page

  @CTB
  Scenario Outline: Login valid with normal account

    When User input email "<findemail>" not exists and find account
    And User select not account huyndai yet option
    And User enter normal acc username "<email>" and password is "<password>"
    Then User login successfully with normal account

    Examples:
      |findemail          | email                |password |
      | trangle@gmail.com | trang113@yopmail.com | 1234qwer|

  @CTB
  Scenario Outline: Login valid with integrated account
    When User input email "<findemail>" not exists and find account
    And Redirect to huyndai signin page and then user input integrated account with email is "<email>" and password is "<password>"
    And User login successfully with integrated account

    Examples:
      |findemail          | email                |password |
      | bluep@hmi.com     | bluep@hmi.com        | P@ssw0rd|

