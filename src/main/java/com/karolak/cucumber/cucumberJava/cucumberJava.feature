Feature: CucumberJava

  Scenario: Properly login with proper pass

    Given Open nazwa.pl website

    When Input properly pass

    Then Check properly login


  Scenario: Wrong login with good password

    Given Open nazwa.pl website

    When Input wrong login with good password

    Then Check login gone wrong

  Scenario: Proper login with bad password

    Given Open nazwa.pl website

    When Input proper login with bad password

    Then Check login gone wrong

  Scenario: Empty submit

    Given Open nazwa.pl website

    When Empty input box submit

    Then Check alert is displayed

  Scenario: Proper login and password with wrong letter length

    Given Open nazwa.pl website

    When Input password with bad letter length and proper login

    Then Check login gone wrong






