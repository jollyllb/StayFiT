Android StayFit App
~~~~~~~~~~~~~~~~~~~~

StayFit is a fitness related application which lets users to focus on fitness and maintain food balance. 
It basically provides information about food and workout regimes to the user depending upon the BMI category the user falls in.

StayFit also has a virtual trainer which notifies them about what food to have at what time along with workout routines.

StayFit also lets users to find Physical trainers using the Google Places API integrated in the application. 
Users can also share their progress in Facebook via the Facebook Share which is integrated using the Facebook SDK.

Features:

Firebase Implementation for Data storage and User authentication
Google maps to find the Physical Trainers nearby
Facebook Share to share the updates with friends.


This directory contains the full implementation of a fitness application for
the Android platform, demonstrating the basic facilities that applications
will use.  You can run the application either directly from the "test"
list in the app launcher (it is named StayFit App) or by selecting it from
the top list in the Sample Code app.



The files contained here:


AndroidManifest.xml

This XML file describes to the Android platform what your application can do.
It is a required file, and is the mechanism you use to show your application
to the user (in the app launcher's list), handle data types, etc.


src/*

Under this directory is the Java source for for your application.

/puneet/stayfit/MainActivity.java

This is the implementation of the "activity" feature described in
AndroidManifest.xml.  The path each class implementation is
{src/PACKAGE/CLASS.java}, where PACKAGE is com.example.ramya_ar.bookreviews
 and CLASS comes from the class in the <activity> tag.

MainActivity is used for allowing the users to register into the app using their email address and password. The activity also does data transaction with Firebase for storing user’s credentials. 

src/main/java/com/example/puneet/stayfit/LoginActivity.java

This is used to help the user login to the app using email address and password. The activity also does data transaction with Firebase for authorizing the user into the app. 

src/main/java/com/example/puneet/stayfit/UserinfoActivity.java

This is help user to enter his name, height, weight, age and fitness model. This information is then used to calculate the BMI.

src/main/java/com/example/puneet/stayfit/ProfileActivity.java

This is a simple activity  used to show the basic information of the user and his calculated BMI based on information given by him on UserinfoActivity. 

/puneet/stayfit/DashboardActivity.java

DashboardActivity is a meal planner activity that creates a customized mean plan for the user based on his BMI and interests.

/puneet/stayfit/WorkoutActivity.java


WorkoutActivity.java is a simple workout planner for the users. There are customized workout plans for users for each day of the week. The workout plans are based on users BMI.


/puneet/stayfit/MapsActivity.java

This is a main activity for integrating google API. This activity communications with google maps and finds the current location. 

/puneet/stayfit/SocialActivity.java

This activity is used for social media integration. Used Facebook API for authentication and sharing fitness progress and pictures.



res/*

Under this directory are the resources for your application.


res/layout/activity_main.xml

The res/layout/ directory contains XML files describing user interface
view hierarchies.  The activity_main.xml file here is used by
MainActivity.java to construct its UI.  The base name of each file
(all text before a '.' character) is taken as the resource name;
it must be lower-case.

res/layout/activity_login.xml

The activity_login.xml file here is used by LoginActivity.java to construct its UI.  

res/layout/activity_userinfo.xml


The activity_userinfo.xml file here is used by UserinfoActivity.java to construct its UI.  

res/layout/activity_profile.xml


The activity_profile.xml file here is used by ProfileActivity.java to construct its UI.  

res/layout/activity_dashboard.xml


The activity_dashboard.xml file here is used by DashboardActivity.java to construct its UI.  

res/layout/activity_workout.xml


The activity_workout.xml file here is used by WorkoutActivity.java to construct its UI.  


res/layout/activity_maps.xml


The activity_maps.xml file here is used by MapsActivity.java to construct its UI.  


res/layout/activity_social.xml


The activity_social.xml file here is used by MapsActivity.java to construct its UI.  



res/drawable

The res/drawable/ directory contains images and other things that can be
drawn to the screen.  These can be bitmaps (in .png or .jpeg format) or
special XML files describing more complex drawings. All the images used in the app are saved here and used from here.


These XML files describe additional resources included in the application.
They all use the same syntax; all of these resources could be defined in one
file, but we generally split them apart as shown here to keep things organized.

res/values/colors.xml

This contains the define web colors for the android app.


res/values/strings.xml

This is used to combine string resources with other simple resources in the one XML file, under one <resources> element.

res/values/styles.xml

A style resource defines the format and look for a UI. Customized styles and themes are defined in this file.

