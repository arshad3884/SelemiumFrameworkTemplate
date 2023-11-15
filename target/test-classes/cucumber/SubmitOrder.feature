
@tag
Feature: Purchase the order from ecommerce website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce page
	
	
  @Regression
  Scenario Outline: Positive test of submiting the order
    Given Logged in with username <name> and password <password>
    When I add the product <productName> to cart
    And checkout <productName> and submit the order
    Then I verify the message "THANKYOU FOR THE ORDER." is displayed on confirmation page

    Examples: 
      | name  								| password 	| productName |
      | alpha@yopmail.com 		| Alpha247* | ZARA COAT 3 |
      | alpha3884@yopmail.com | Alpha247* | ADIDAS ORIGINAL |
