Feature: Api testing
  @api
  Scenario Outline: First test positive, Other negative
    When I request post with id:<post_id>
    Then response status is <status>
    And response key:title contains <postTitle>
    Examples: Data
      | post_id | postTitle | status |
      | 1       | sunt       | 200    |
      | 2       | FAIL       | 200    |
      | 105     | Lorem      | 404    |

  Scenario Outline: All tests positive
    Given title:<title>, body:<body>, userId:<user_id>
    When I send post request
    Then response status is <status>
    And response key:body contains <body>
    And response key:title contains <title>
    And response key:userId contains <user_id>
    Examples: Data
      | title   | body      | user_id | status |
      | title1  | body1     | 1       | 201    |
      | title 2 | body body | -1      | 201    |
      | 123     | 123       | abc     | 201    |