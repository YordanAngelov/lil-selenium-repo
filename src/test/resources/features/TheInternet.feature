@smoke
Feature: An example feature

  As a software tester
  I want to automate things on the Internet (test automation practice site)
  So that I am more awesome

  Scenario: Uploading a file
    Given I go on the Internet
    When I go to the File Upload section on the Internet
    And I upload a file
    Then I will see that the file has been uploaded

  Scenario: Downloading a file
    Given I go on the Internet
    When I go to the File Download section on the Internet
    And I download a file
    Then I will see that the file has been downloaded

  Scenario: Selecting an option from a dropdown
    Given I go on the Internet
    When I go to the Dropdown section on the Internet
    And I select an option from the dropdown
    Then that option will be selected

  Scenario: Switching to a new window
    Given I go on the Internet
    When I go to the Multiple Windows section on the Internet
    And I open a new window
    Then I will be on the new window
