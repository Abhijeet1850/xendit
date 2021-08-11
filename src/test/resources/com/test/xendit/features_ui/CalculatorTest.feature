@test
Feature: Create Order Flow in BestBuy


  Scenario Outline: Test subtraction, division and CE functionalities
    Given I visit the online calculator url
    When I enter following values and press CE button
      | value1   | value2   | operator   |
      | <value1> | <value2> | <operator> |
    Then I should be able to see correct result
      | expected   |
      | <expected> |


    @start
    Examples:
      | value1 | value2 | operator | expected |
      | 750    | 150    | /        | 5        |

    Examples:
      | value1 | value2 | operator | expected |
      | 2500       | 1000      | -        | 1500        |
      | 400        | 20        | +        | 420         |
      | 6-         | 3-        | *        | 18          |
      | 60-        | 30        | *        | -1800       |
      | 9          | 0         | /        | Error       |
      | 0          | 9         | /        | 0           |
      | 9          | 3-        | /        | -3          |
      | 10         | 3         | /        | 3.33333333  |
      | 999999999  | 10        | *        | 10e+9       |
      | 1          | 100000000 | /        | 1e-8        |
      | 25         |           | sqrt     | 5           |
      | 10         |           | 1/x      | 0.1         |
      | 69.69      |           | sqrt     | 8.34805367  |
      | 99999      |           | %        | 999.99      |
      | 0.00000000 |           | 1/x      | Error       |
      | 0.00000009 |           | 1/x      | 11111111.1  |
      | 0          |           | %        | 0           |
      | 6          |           | 1/x      | 0.16666667  |
      | 987654321  | 123456789 | +        | 1.111111e+9 |

    @end
    Examples:
      | value1 | value2 | operator | expected |
      | 800    | 15     | *        | 12000    |