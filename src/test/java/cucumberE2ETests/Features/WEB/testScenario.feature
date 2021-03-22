Feature: Web test

  Scenario Outline: Test search for game, check if picture and link are shown properly
    Given I am on the main page
    When I enter search term: <game_name>
    Then Search results for link: <game_name> should appear
    And Search results for image: <game_name> should appear
    Examples: Data
      | game_name         |
      | Prophecy          |
      | Gloomhaven        |
      | Terraforming Mars |

  Scenario: Test if sign in is available and it contains field username and password
    Given I am on the main page
    When I click on Sign in button
    Then popup is shown
    And it contains field username
    And it contains field password
    When I enter username and password
      | username | password |
      | name     | pass     |

  Scenario Outline: Test FAQ page and searching
    Given I am on the FAQ page
    Then search box is present
    And BoardGameGeek FAQ article is present
    When I search for <search_option>
    Then List of results with <search_result> is shown
    Examples: Data
      | search_option | search_result       |
      | API           | BGG_XML_API2        |
      | contest       | Official_Contests   |
      | contest       | Unofficial_Contests |