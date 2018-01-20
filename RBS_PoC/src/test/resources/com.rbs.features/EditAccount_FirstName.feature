Feature: Checkout as Member 
	Login as Registered Customer
  	I want to edit Personal Information in My Account page
  	So that i can validate edited Details
  
@MemberCheckout 
Scenario: TC0002 
	Given I Login to Practice Site 
	When Go to Personal Information Page and Edit Firstname
	Then I Validate Edit Details Message	
	When Navigate to My Account Page
	And Navigate to Personal Information Page
	Then I Validate Edited Firstname