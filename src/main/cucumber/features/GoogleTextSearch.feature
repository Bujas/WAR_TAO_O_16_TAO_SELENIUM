Feature: Google search

  Scenario Outline: user can search any keyword
    Given I am on Google search page
    When a keyword <keyword> is entered in input field
    Then the first one should contain <keyword>
    And close browser

    Examples:
    |keyword|
    |Selenium|
    |Test|