Feature: Api testing

  @api
  Scenario Outline: all tests positive
    When Experimental request post with id:<post_id>
    Then Experimental status is <status>
    Examples: Data
      | post_id | status |
      | 1       | 200    |
      | 2       | 200    |
      | 105     | 404    |