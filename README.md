MyLuxuryPantry

My Android application coded entirely through Java which makes general pantry management for cooking enthusiasts (and your average adult who manages their groceries, of course) a breeze. The app tracks pantry ingredients and suggests recipes strictly based on what ingredients are readily available in the pantry's inventory.

SQLite shall be utilized for this application simply for its versatility in regard to offline-first capabilities, which is helpful for the fact that handling a user's pantry data is personal and should be readily available, even when offline.

Features Implemented
Pantry Management: Full CRUD (Create, Read, Update, Delete) for ingredients. 
Recipe Seeding: Automatically provides the new user with 15 recipes on first launch. 
Strict-Matching Algorithm: Only suggests recipes if the user has every single one of the required ingredients. The interface isn't case-sensitive and cares not for punctuation (e.g., "Tomato" matches "Tomatoes").
Settings: Acts as a toggle for alerts which send expiring-soon messages using SharedPreferences.

Steps for the setup and running of the app:
1. Extract the files from the submitted zip.
2. Open the project in Android Studio.
3. Allow Gradle to sync.
4. Run the app on an emulator or physical Android device (Minimum SDK: API 24).