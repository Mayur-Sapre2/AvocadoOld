package com.avocado.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.avocado.base.Testbase;

public class Dashboard extends Testbase{
	@FindBy(xpath="//*[@class='app-category' and text()='Total Applications']")
	public WebElement totalApplications;
	
	@FindBy(xpath="//*[@class='app-category' and text()='Total Active Sessions']")
	public WebElement totalActiveSessions;
	
	@FindBy(xpath="//*[@class='app-category' and text()='Total Unattacked Sessions']")
	public WebElement totalUnattackedSessions;
	
	@FindBy(xpath="//*[@class='app-category' and text()='Total Domains']")
	public WebElement totalDomains;
	
	@FindBy(xpath="//*[contains(@class,'app-data-details')]//*[contains(text(),'Threats')]")
	public WebElement threatsDeteted;
	
	@FindBy(xpath="//*[contains(@class,'app-data-details')]//*[contains(text(),'Sessions')]")
	public WebElement sessionsAttacked;
	
	@FindBy(xpath="//*[contains(@class,'app-data-details')]//*[contains(text(),'Unsanctioned')]")
	public WebElement unsanctionedApplications;
	
	@FindBy(xpath="//*[@class='notification-bell']")
	public WebElement notification;
	
	@FindBy(xpath="//*[@class='profile-name']")
	public WebElement administrator;
	
	@FindBy(xpath="//*[@class='tab']//button[1]")
	public WebElement geographicalMapping;
	
	@FindBy(xpath="//*[@class='tab']//button[2]")
	public WebElement applicationDependencyMap;
	
	@FindBy(xpath="//*[contains(@class,'count-per-alert-graph')]//*[text()='Weekly']")
	public WebElement countPerAlertTypeWeekly;
	
	@FindBy(xpath="//*[contains(@class,'count-per-alert-graph')]//*[text()='Monthly']")
	public WebElement countPerAlertTypeMonthly;
	
	@FindBy(xpath="//*[contains(@class,'count-per-alert-graph')]//*[text()='Custom']")
	public WebElement countPerAlertTypeCustom;
	
	@FindBy(xpath="//*[contains(@class,'geo-area')]//*[text()='Weekly']")
	public WebElement attackPerGeoAreaWeekly;
	
	@FindBy(xpath="//*[contains(@class,'geo-area')]//*[text()='Monthly']")
	public WebElement attackPerGeoAreaMonthly;
	
	@FindBy(xpath="//*[contains(@class,'geo-area')]//*[text()='Custom']")
	public WebElement attackPerGeoAreaCustom;
	
	
	
	
}
