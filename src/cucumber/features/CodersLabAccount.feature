Feature: Create coders lab account

  @Task6
  Scenario: create coders lab account
    Given I am on the Coders Lab page
    When I click sign in
    And I enter following email address "qq41q@wp.pl" and submit
    And  I fill in personal information
      | Title       | Mr.               |
      | FirstName   | Adam              |
      | LastName    | Nowak             |
      | Password    | wlkje8hfowij12131 |
      | DateOfBirth | 12-10-1990        |
    And I register new user
    Then Account was created and I can see following information "Your account has been created."
    Then quit browser

  @Task7
  Scenario Outline: create coders lab account - parameters
    Given I am on the Coders Lab page
    When I click sign in
    And I enter following email address "<emailAddress>" and submit
    And I choose "<title>" title
    And I type first name "<firstName>" and last name"<lastName>"
    And I confirm email address
    And I type password "<password>"
    And I select date of birth "<dateOfBirth>"
    And I check newsletter checkbox
    And I check special offers checkbox
    And I register new user
    Then Account was created and I can see following information "Your account has been created."
    Then quit browser

    Examples:
      | title | firstName | lastName | password   | dateOfBirth | emailAddress        |
      | Mr.   | Jan       | Kowalski | uo0iu98798 | 12-3-1986   | ii800e01900er@wp.pl |

  @Task6a
  Scenario Outline: login coders lab account - success
    Given I am on the Coders Lab page
    When I click sign in
    And I login Coders Lab account with email address "<emailAddress>" and password "<password>"
    Then Home page was loaded for user "<firstName>"
    Then quit browser

    Examples:
      | emailAddress      | password         | firstName |
      | ii00e0900er@wp.pl | uo0iu98798       | Jan       |
      | qqq@wp.pl         | wlkjehfowij12131 | Adam      |


  @Task6a
  Scenario Outline: login coders lab account - success
    Given I am on the Coders Lab page
    When I click sign in
    And I login Coders Lab account with email address "<emailAddress>" and password "<password>"
    Then I am on login page
    And Following error message was displayed "<errorMessage>"
    Then quit browser

    Examples:
      | emailAddress | password   | errorMessage          |
      | 123@wp.pl    | uo0iu98798 | Authentication failed |
      | qqq@wp.pl    | 222        | Invalid password.     |