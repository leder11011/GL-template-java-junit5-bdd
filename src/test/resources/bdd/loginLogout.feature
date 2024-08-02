Feature: Test webshop

  Scenario: Run tests
    Given initialize driver
    When locate home page
    Then check title
    Then cookie handling
    Then login testuser
    Then shutdown driver