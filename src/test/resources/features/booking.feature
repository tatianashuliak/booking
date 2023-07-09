Feature: Search on booking.com

  Scenario: Search by city criteria
    Given User is looking for hotel in 'London' city
    When User does search
    Then Hotel 'Holiday Inn London Camden Lock, an IHG Hotel' should be on the first page

  @smoke
  Scenario: Search by city and rating criteria
    Given User is looking for hotel in 'London' city
    When User does search
    Then Rating of 'K Hotel Kensington' is '7.6'

  Scenario Outline: Search by different city criteria
    Given User is looking for hotel in '<City>' city
    When User does search
    Then Hotel '<Hotel>' should be on the first page
    Examples:
      | City       | Hotel                          |

      | London     | Holiday Inn London Camden Lock |
      | Washington | citizenM Washington DC NoMa    |
      | Madrid     | Hostal Abel Victoriano         |