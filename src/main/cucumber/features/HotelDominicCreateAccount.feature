Feature: Hotel Dominic create accounts

  Scenario Outline: user can create a account
    Given an open browser with qloapps.coderslab.pl
    When user click on "Zaloguj sie" button
    And User write a email to create account field
    And user click on create an account button
    And user fill a first name field with <firstName>
    And user fill a last name field with <lastName>
    And user fill a password field with <password>
    And user pick up a date of birth
    And user click on register button
    Then user should see a confirm of creating account
    And browser should be close
    Examples:
      | firstName | lastName | password        |
      | Jan       | Kowalski | Test12345       |
      | Krzysztof | Jezyna   | zeSzczecina1234 |
      | Pawel     | Burak    | ChrzanTarty12   |