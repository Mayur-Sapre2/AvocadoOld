@Extent
Feature: Setting Menu

Background:
Given Open chrome browser and enter url
When Title of page is Avocado
Then Enter username and password
And Click on login button	

@General_Settings
Scenario: General Settings-Create New Configuration Service
And Click on Setting menu
Then Set orchestrator url 
And Set pico segment and server per pico segment
And Set polling interval and polling interval for ui
And Set application discovery only mode
And Click on "Configure \"Service Now\"" button
And Enter service url,config name,password and confirm password
And Select domain access and sub-domain access
And Click on "ADD" Button
Then click "SAVE" Button
Then Verify configuration name in configuration list

#@General_Settings_Negative
#Scenario: General Settings-Create New Configuration Service
#And Click on Setting menu
#Then Set orchestrator url 
#And Set pico segment and server per pico segment
##Then Verify error message for "pico segment"
#And Set polling interval and polling interval for ui

#@General_Settings
#Scenario: General Settings-Create New Configuration Service
#And Click on Setting menu
#And Click on "Configure \"Service Now\"" Button
#And Right click on configuration name
#Then delete service

@plugin_installation
Scenario: Plugin Installation-Register New Plugin
Given Navigate to plugin installation
And Click register new plugin Button
And Enter plugin related details
And Set domain and subdomain
And Set plugin execution mode
And Set log file properties
Then Verify newly created plugin

#@plugin_installation
#Scenario: Plugin Installation-Upload New Package
#Given Navigate to plugin installation
#And Click on "Packages List" tab
#And Click on "UPLOAD PACKAGE" Button
#And Set Operating System Type
#And Set Opearting System Name
#And Set Operating System Version
#And Upload Plugin Package File
#And Upload Installer File
#Then Click on "UPLOAD" Button
#Then Verify uploaded package
