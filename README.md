# JavaFX Starter

This project is meant to be a simple starter for JavaFX 17 under Maven. 
It's not comprehensive, because it doesn't build to a runnable jar... yet.

It is not assumed that you have Maven installed on your own machine,
this project only runs in the Maven tab in IntelliJ (I'm assuming your are using that).

Instructions:

1. Fork this project (top right corner, click Fork - This creates a new 
   repository in your own github account)
2. Clone your new repo
3. One-time-action: move the file src/main/resources/settings.xml to %HOMEPATH%/.m2 (yes with the dot)   
4. Open up the Maven tab (on the right side of your screen)
5. Perform: LifeCycle > clean + install
6. Perform: Plugins > javafx > javafx:run
7. Customize to your own needs
