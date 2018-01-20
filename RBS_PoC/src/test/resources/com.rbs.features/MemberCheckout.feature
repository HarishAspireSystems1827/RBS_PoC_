Feature: Checkout as Member 
	As a Registered Member, add Tees to cart 
  	I want to place order with Card Payment
  	So that i can validate placed order in the Order history Page
  
@MemberCheckout 
Scenario: TC0001 
	Given I Login to Practice Site 
	When I add tees to cart and Proceed to Shopping Cart 
	And Do the payment and Place the order 
	Then I Validate Placed Order in Order Confirmation Page 	
	When I Navigate to Order History Page from Order Confirmation Page
	Then I Validate Placed Order in Order History Page