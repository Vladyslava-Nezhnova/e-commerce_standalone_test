@tag
Feature: Error Validation

  @tag2
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed
    Examples:
      | name                  | password    |  |
      | vldnezhnova@gmail.com | Testing2023 |  |