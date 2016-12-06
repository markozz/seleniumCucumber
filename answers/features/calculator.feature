Feature: rekenmachine24

  Scenario: sum functionality
    Given I am at "http://www.rekenmachine24.nl/"
    And I maximize the screen
    When I sum 5 and 8
    And press "="
    Then I expect the result is 13

  Scenario: minus functionality
    Given I am at "http://www.rekenmachine24.nl/"
    And I maximize the screen
    When I substract 3 from 5
    And press "="
    Then I expect the result is 2

  Scenario: deviding functionality
    Given I am at "http://www.rekenmachine24.nl/"
    And I maximize the screen
    When I enter the calculation 10 / 2
    And press "="
    Then I expect the result is 5