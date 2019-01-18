Feature: An example feature

  @smoke
  Scenario: Finding websites about cats
    Given I go on Google
    When I search for cats
    Then I will see results showing websites of cats