# Java automation project

Reasons for this project

* part of AT portfolio
* demonstration of Java + test frameworks knowledge
* learning Java + test frameworks + ( patterns, antipatterns, good practices etc. ) 
* playground / place for experimenting :)

## /src/main/java/
### calendar

* simple class , used as a target for unit testing
* originally created for learning Python and copied to Java
* needs rework from scratch

## /src/test/java
### cucumberE2ETests

* BDD tests
* web tests using selenium webDriver
* api tests using RestAssured
* unit tests using JUnit
* cucmber + JUnit (runner)

### E2ETests

* classic E2E web tests using selenium webDriver
* selenium + JUnit
* to better understand / show fluent PageObjectModel

### unitTests

* unit tests for calendar.py using JUnit

### locustTests

* simple performance test
* RunLocustTests -> starts server and slave node with defined tasks
* /tasks/ - tests scenarios

### appiumTests

* appium, jUnit, selenium
* depends on appium installation and settings (instal node.js, appium, androidStudo ; create virtual device, create snapshot of device etc.)
* starts appium server, emulator (snapshot) and appiumDriver
* driver is restarted (not recreated) after each tests - significant boot speed boost

## build and report
### Maven + Allure

* tests with 'Run' are excluded from build
* allure report should be generated 

## TO DO
### high priority
* CLEAN UP, FIX PATHS (f.e. CHROMEDRIVER), CMD (f.e. appium, locust)  
  * clean variable names (f.e. in unit tests)
* ~~rewrite CalendarClass -> CalendarCreator~~
  * ~~class for better time block representation~~
  * ~~list decorator for readable output~~
  * ~~cover with unit test~~
  * cover with BDD unit test
  * remove / archive CalendarClass + tests
* add docker with mySQL DB
  * ~~prepare populated docker mysql image~~

### medium priority
* expand reports with screenshots etc. 
* add support for screen checker (check if webpage image is matching expectation)
* dockerize Appium
  * dockerize emulator snapshot
* dockerize locust  

### low priority
* ? nosql + docker
* ? check Gattling as performance test alternative
* ? security Testing package / soemthing. (mostly security testing from Input POW (sql injections etc.))
* ? graddle
* ? hibernate

### done
* ~~prepare driver for injectionDep (pikocontainer)~~
* ~~add old E2E test~~
* ~~rework cucumber steps~~
* ~~appium support~~
  * ~~sample appium tests ( android )~~
* ~~locust performance test support~~
  * ~~nice way for time measures~~
* ~~add DB access~~
* ~~maven cleanup~~
* ~~allure reporting~~ 