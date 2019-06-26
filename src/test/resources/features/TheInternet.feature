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

  Scenario: Tick a checkbox
    Given I go on the Internet
    When I go to the Checkboxes section on the Internet
    And I tick checkbox 1
    Then checkbox 1 will be ticked

  # TODO: Below 2 may be obsolete - check WebBrowser.scala's Checkbox
  Scenario: Tick a checkbox if not already ticked (already ticked)
    Given I go on the Internet
    When I go to the Checkboxes section on the Internet
    And checkbox 2 is ticked
    And I tick checkbox 2 if unticked
    Then checkbox 2 will be ticked

  Scenario: Tick a checkbox if not already ticked (not ticked)
    Given I go on the Internet
    When I go to the Checkboxes section on the Internet
    And checkbox 1 is unticked
    And I tick checkbox 1 if unticked
    Then checkbox 1 will be ticked

  Scenario: Untick a checkbox
    Given I go on the Internet
    When I go to the Checkboxes section on the Internet
    And I untick checkbox 2
    Then checkbox 2 will be unticked

  # TODO: Below 2 may be obsolete - check WebBrowser.scala's Checkbox
  Scenario: Untick a checkbox if already ticked (not ticked)
    Given I go on the Internet
    When I go to the Checkboxes section on the Internet
    And checkbox 1 is unticked
    And I untick checkbox 1 if ticked
    Then checkbox 1 will be unticked

  Scenario: Untick a checkbox if already ticked (already ticked)
    Given I go on the Internet
    When I go to the Checkboxes section on the Internet
    And checkbox 2 is ticked
    And I untick checkbox 2 if ticked
    Then checkbox 2 will be unticked
