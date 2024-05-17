@tag
  Feature: Purchase the order from Ecommerce website

    Background:
      Given I landed on Ecommerce Page

    @tag2
    Scenario Outline: Positive Test of Submitting the order
      Given Logged in with username <name> and password <password>
      When I add product <productName> to Cart
      And Checkout product <productName> and submit the order
      Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
      Examples:
        | name                  | password    | productName |
        | vldnezhnova@gmail.com | Testing2024 | ZARA COAT 3 |
