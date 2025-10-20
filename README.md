# README

## ✅ Overview
This Android application is built using modern Android development practices. It includes login functionality, profile pages, navigation between screens, and optional API integration using Retrofit and a Postman Mock Server.



## 🛠 Tech Stack
| Feature | Technology |
|---------|------------|
| Language | Kotlin |
| Architecture | MVVM |
| API Integration | Retrofit + OkHttp |
| Dependency Injection | Hilt (if enabled) |
| UI | XML Layouts, Fragments/Activities |
| Testing | JUnit, Espresso |
| Mock Server | Postman |

## Project Structure
app/
├── src/
│   ├── main/
│   │   ├── java/com/yourpackage/
│   │   │   ├── data/              # Repositories, data sources, API handlers
│   │   │   ├── model/             # Data models (e.g., User, Response)
│   │   │   ├── network/           # Retrofit API interface, ApiClient
│   │   │   ├── ui/                # Activities / Fragments (Login, Profile, etc.)
│   │   │   ├── viewmodel/         # ViewModels for UI logic
│   │   │   └── di/                # Dependency Injection (Hilt modules)
│   │   ├── res/
│   │   │   ├── layout/            # XML layouts (activity_login.xml, fragment_home.xml)
│   │   │   ├── drawable/          # Icons, images, vector assets
│   │   │   └── values/            # strings.xml, colors.xml, themes.xml
│   │   ├── AndroidManifest.xml    # App configuration + permissions
│   │   └── assets/                # Fonts, JSON files (if any)
│   ├── test/                      # Unit Tests (JUnit)
│   └── androidTest/               # UI Tests (Espresso)
├── build.gradle                    # Module-level Gradle config
├── settings.gradle                 # Project settings
└── local.properties                # SDK location (DO NOT upload publicly)

## 1. Install Requirements

1. Android Studio (Latest version)

2. Android SDK (API 24 or above)

3. Gradle plugin 8.0+(gradle 17 was used when building the project)

4. Kotlin support enabled

## Setup Steps

1. Extract/Clone the project.

2. Open Android Studio → Open → Select project folder.

3. Allow Gradle to Sync.

4. If SDK errors occur, set sdk.dir in local.properties.

5. Connect emulator or Android device.

6. lick Run ▶ to build and install the app.

## API / Postman Mock Server (If Using APIs)

1. Open Postman → Import Mock API Collection.

2. Start the Mock Server.

3. Copy its Base URL (e.g., https://mockapi.postman.com/.../).

4. In Android Studio, go to:

network/ApiClient.kt  or RetrofitInstance.kt


Replace:

private const val BASE_URL = "YOUR_MOCK_URL_HERE"


5. Rebuild & Run the project.

## How to Use the App
1. Login Page

2. Enter uni location which is sydney

3. Enter user "pranish" and Password "8069126"

4. Click Login to access the home screen.

5. Home/Dashboard

6. Displays main app options/screens.



## Testing Guide
Unit Tests

Location:

app/src/test/java/com/yourpackage/


To run:

Right-click the test folder → Run All Tests

UI Tests (Espresso)

Location:

app/src/androidTest/java/com/yourpackage/


To run:

Right-click → Run 'AndroidTest'




