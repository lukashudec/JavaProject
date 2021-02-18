Feature: Calendar merging
  Scenario: test
    Given calendars
      | calendar                 | bounds       |
      | 12:00-13:00,16:00-18:00  | 09:00-20:00  |
      | 10:00-11:45,12:30-14:30  | 10:00-18:30  |
    When merging calendars
    Then result is
      | calendar                                        | bounds       |
      | 10:00-11:45,12:00-13:00,12:30-14:30,16:00-18:00 | 10:00-18:30  |

  Scenario: test2
    Given calendars
      | calendar                 | bounds       |
      | 12:00-13:00,16:00-18:00  | 09:00-20:00  |
      | 10:00-11:45,12:30-14:30  | 10:00-18:30  |
    Then different creations are equal
    And possible events are [[14:30, 16:00], [18:00, 18:30]]


  Scenario: test3
    Given calendars
      | calendar                 | bounds       |
      | 12:00-13:00,16:00-18:00  | 09:00-20:00  |
    Then free time is [[09:00, 12:00], [13:00, 16:00], [18:00, 20:00]]

  Scenario Outline: test4
    Given calendar <calendar> with <bounds>
    Then toString returns <result>
    Examples: examples
      | calendar                 | bounds       | result                                            |
      | 12:00-13:00,16:00-18:00  | 09:00-20:00  | [[12:00, 13:00], [16:00, 18:00]] / [09:00, 20:00] |
      | 10:00-11:45,12:30-14:30  | 10:00-18:30  | [[10:00, 11:45], [12:30, 14:30]] / [10:00, 18:30] |

