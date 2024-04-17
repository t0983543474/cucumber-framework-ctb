Feature: Verify Feature on Landing Page

  @CTB
  Scenario: Verify langing page without login
    Then Landing page is display without logged


  @CTB
  Scenario Outline: Verify langing page with login
    Given User go to Login Page
    When User input email "<findemail>" not exists and find account
    And User select not account huyndai yet option
    And User enter normal acc username "<email>" and password is "<password>"
    Then User login successfully with normal account
#    And Landing page is display with logged

    Examples:
      |findemail          | email                |password |
      | trangle@gmail.com | trang113@yopmail.com | 1234qwer|
