@GeneralStore
Feature: Automating General Store App

  Scenario: User opens General Store App
    Given User opens the General Store App

  Scenario: User Selects Country
    When User selects "India" as country
    Then Verifying if the selected country is displayed

  Scenario: User Tries to Login Without Name Input
    When User tries to login without entering the name
    Then Verifying that the user is unable to login

  Scenario: User logins With Name
    When User enters the name "Pallavi" to login
    Then Verifying that user has logged in

  Scenario: User Adds Products to Cart
    When User adds products to cart
    Then Verifying that there is one item in cart

  Scenario: User Goes to Cart
    When User goes to cart
    Then Verifying if products in cart are correct

  Scenario: User Searches on the Web
    When User searches on Web
    Then Verifying if search results are displayed
