Feature: Remove article

  Scenario: Try to remove an existent article by its owner
    Given the article with id 1 with owner "owner1"
    When the article with id 1 is removed by the owner "owner1"
    Then the status for the article with id 1 has changed to "REMOVED"