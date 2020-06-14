Feature: Test google search feature

  @Task5
  Scenario: google search
    Given I am on the Google search page
    When a keyword "Selenium" is entered in input field
    Then the first one should contain "Selenium"
    And close browser