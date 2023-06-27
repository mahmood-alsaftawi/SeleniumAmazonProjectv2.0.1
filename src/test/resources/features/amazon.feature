Feature: Amazon Test

  Scenario: amazon.ca page loads properly, then click on hamburger menu
    Given I launch chrome browser
    When I navigate to amazon.ca
    And verify hamburger menu exists
    Then click on hamburger menu
    And verify hamburger menu opens
    Then click on Kindle
    Then click on Kindle under Kindle E-Readers
    Then click Buy Now
    And verify user is asked for email or phone number

