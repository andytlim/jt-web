/** README **/
This template is the basic structure for web apps we develop on the Business Intelligence Team.

---------NEED TO FIX---------
2. dispatcher-servlet.xml is being called even though we are setting it in the AppConfig, there should be a way to disable it without having to create an empty file...
3. Setup /resources for documents/files

[[ Code Organization ]]
/
	bower.json - stores bower dependencies (bower install <name> --save)
	build.gradle - stores project build properties
	dependencies.gradle - stores gradle dependencies 
	gradle.properties - stores tiaa-gradle properties
	minime.json - stores custom js/css build mapping
	package.json - contains react related dependencies
	tasks.gradle - contains custom gradle tasks
/src
	/test
		/java - test code goes here
	
/src
	/main
		/java - controllers, models, services, repositories go here
			/com
				/jt
					/web
						/conf
						/controller
						/model
						/repo
						/service
						/utility
		/resources
			application.properties - list of static values for project
			log4j.properties - logging properties, set to show only INFO by default
		/webapp
			/META-INF, 
			/WEB-INF
				/assets - css/images/fonts/js
					/bower_components - bower installed libraries
					/css
						/dist - include files
						/src - src files
					/js
						/dist - include files
						/src - src files		
					/fonts
					/images
					/react
						/jsx - src files
						/build - include files
				/templates - jsps go here
					/global - header.jsp + other things that should be global includes
					/[baseContext] - any new base context with a view, should have an equivalent folder path i.e. url http://localhost/user/arc -> /user folder with index.jsp
					/index.jsp - your home page basically
			
[[ Web App Architecture ]]
Front-End
	- React (View Framework)
	- Bower (Dependency Manager)
	
Back-End
	- Java 6+
	- JSTL (Template Engine)
	- Gradle (Build, Dependency Manager)
	- Spring 3 (Web Dev Framework)
	
[[ How To Get Started ]]
1. Install
	- Eclipse
	- Gradle
	- npm
	- git
	- Install gradle plugin, rtc plugin for eclipse
	
2. Create New Project
	- Check out Java-Template-TIAA-Spring3-Servlet2.5 from RTC
	- Load it
	- Disconnect it
	- Rename the project
	- Rename the packages from com.jt.web to [domainName].[projectName]
	- Update the annotation @ComponentScan in com.jt.web.[projectName].conf.ApplicationConfig to point to your new package name
	- Update the <init-param></init-param> tags to point to your new configuration path in web.xml 
	- Update gradle.properties and build.gradle with your new project name
	- Remove any classes you do not want and this README.txt
	- Run gradle refresh/clean/build
	- Run npm install & bower install
	
