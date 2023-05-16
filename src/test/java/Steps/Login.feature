@login
Feature: Login Scenerios
  Success Login

  @CaseId:TC-001
  @mert
  Scenario: 1- Login success with credentials

    When click "Login" button
    Given enter "mertSuccess" credential
    When click "Login" button
    And close "AutoSync"
    And click "Let's Start Using Lifebox" button
    And click "Navigation Bar Back" button
 #   Then see "Photos and Videos" tab
 #   And click first image
 #   And click "Back" button

  @mert
  Scenario: 2- Logout
    When click "Header Settings" button
    And scroll down
    And click "Log Out" button
    Then click "OK" button




