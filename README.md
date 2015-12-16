<<<<<<< HEAD
# Collavate-REST-API

## Prerequisites
To run this quickstart, you'll need:
 - Java 1.7 or greater.
 - Apache Maven 3.2.3 or Greater
 - Access to the internet and a web browser.
 - A Google account with Google Drive enabled.

## Step 1: Turn on the Drive API
 - Use [this wizard](https://console.developers.google.com/flows/enableapi?apiid=drive) to create or select a project in the Google Developers Console and automatically turn on the API. Click the Go to credentials button to continue.
 - At the top of the page, select the OAuth consent screen tab. Select an Email address, enter a Product name if not already set, and click the Save button.
 - Back on the Credentials tab, click the Add credentials button and select OAuth 2.0 client ID.
 - Select the application type Other and click the Create button.
 - Copy Client Id and Secret generated and then click OK to dismiss the resulting dialog.

## Step 2: Setup the project
 - In your working directory, run the following commands to create a new project structure:

    **mvn archetype:generate -DgroupId=com.collavate -DartifactId=ApiUser -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false**

   This will create the project named ApiUser

 - Now open **ApiUser/pom.xml** and replace it with following : 
	
	[pom.xml](https://drive.google.com/a/nicefact.com/file/d/0B9NWQND1ujwcTzRCeHdINkNpNFE/view)

 - replace the contents of ApiUser/src/main/java/com/collavate/App.java with following : 

	[App.java](https://drive.google.com/a/nicefact.com/file/d/0B9NWQND1ujwcRkJnTWZKeGR5QWs/view)

 - Replace your client id and secret in the above java code and save the file.

 - Run the following commands : 
    - **cd ApiUser**
	- **mvn clean install**

	This command will compile the project and create the required jar files in target folder.

## Step 3: Run the sample
Build and run the quickstart with the command:

**java -cp target\ApiUser-1.0-SNAPSHOT-jar-with-dependencies.jar com.collavate.App**

As the sample will run, it will print the AuthUrl on console
 - Copy the URL from the console and manually open it in your browser.
 - If you are not already logged into your Google account, you will be prompted to log in. If you are logged into multiple Google accounts, you will be asked to select one account to use for the authorization.
 - Click the Accept button.
 - It will redirect you to google page with parameter ‘code’, copy the value of this parameter ‘code’ and paste it to the console.
 - Then enter the method name you want to execute from the code, it’ll ask you the parameter if required.
 - You will get the response of the api. 



## Notes 
 - You can change the code of App.java to test the other api methods which are commented initially.
 - The authorization flow in this example is designed for a command-line application. For information on how to perform authorization in a web application, see Using OAuth 2.0 for Web Server Applications.


=======
# CV_Sample_APIs
CP Sample Apis
>>>>>>> origin/master
