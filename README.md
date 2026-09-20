# Nakivo Login Module - Test Automation Framework

This repository contains a Selenium Automation Testing framework built to test the Login functionality of **Nakivo Backup & Replication** using Page Object Model (POM) and Data-Driven Testing (DDT).

---

## 👤 Candidate Profile

- **Full Name:** Nguyễn Thị Thúy Quỳnh
- **Position Applied:** Performance QA Engineer
- **GitHub Repository:** [automation-test-nakivo](https://github.com/testportal00000-cmyk/automation-test-nakivo)

---

## 🏗️ Technical Architecture & Stack

- **Programming Language:** Java 17
- **Build Tool:** Apache Maven
- **Test Runner:** TestNG (`testng.xml`)
- **Automation Tool:** Selenium WebDriver (v4.x)
- **Design Pattern:** Page Object Model (POM)
- **Data-Driven Testing (DDT):** OpenCSV (reading test data from `.csv`)
- **Design Strategy:** Stable locators targeting ExtJS dynamic DOM (`name`, `placeholder`, relative XPath).

---

## 📂 Project Structure

```text
automation-test-nakivo/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   └── java/
    │       ├── pages/
    │       │   └── LoginPage.java            # Page Object holding ExtJS web elements & actions
    │       └── utils/
    │           └── ReaderCSVData.java        # Utility to read CSV test data
    └── test/
        ├── java/
        │   └── tests/
        │       ├── BaseTest.java             # WebDriver setup, teardown, and baseURL configuration
        │       ├── LoginTest.java            # Core functional & UI test cases
        │       └── DynamicLoginTest.java     # Data-driven test cases executing via CSV
        └── resources/
            ├── testng.xml                    # TestNG suite runner file
            └── testdata/
                └── test_data_login.csv       # Test dataset (valid, invalid, boundary cases)

## Test Environment & Configuration
Target Application: Nakivo Backup & Replication
Base URL: https://localhost:4443/c/login
Browser: Google Chrome

## How to Execute Tests
Prerequisites:
1. Installed Java JDK 17 and Apache Maven.
2. Google Chrome installed.
3. Nakivo service running on local port 4443.

##Execution Command
1. Run complete suite via Maven:
mvn clean test
2. Or execute via TestNG suite runner file:
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
