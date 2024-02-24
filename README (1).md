
I'm...let's go with Mellow haha.  Today I will be showcasing my first ever game...Space Survial. It's well like Asteroids but less fun lol.


Space Survival 

This is a Java project that was inspired Attari's Asteroid in the 1980s. This project was an inspiration of this popular drawing from its design and most game mechanics. It responds to keyboard input by the user and only uses the mouse to start and exit the game. (See Game Specifications for detailed controls and game insight)


Lessons Learned

Definitely learned alot. Particularl!y I learnt how vast the java.awt.* Library was, which stands for Abstract Window Toolskit. It was responsible for 90% of my project pertaining to game entities like buttons,screen color and mapping user input. Really interesting stuff.

I also came across the whole concept of threading, which is like automation but with less control since the OS dictates how it runs. I utilized this concept in simulating asteroids falling down and thus independent of the player and unrelated game attributes like points. But definitely responsive of game events like collisions (ship object intersecting with asteroid object)


Acknowledgements

 - [ZetCode](https://zetcode.com/gfx/java2d/shapesandfills/) 
Was super grateful for the insights that was found in their article. It really abstracted some game concepts for me that seemed hard to grasp. In particular, the whole convection of JPanels (JFrames are lighter but we don't want to have that fight right now) and JButtons and getting objects drawn on them which would then contain them project to the screen. 

 - [Java Code Geeks](https://examples.javacodegeeks.com/java-development/desktop-java/javafx/javafx-2d-shape-example/) Was basically my Bible for seeing how different shape objects interact with each other and various and varying parameters demanded of each. It was here I final able to ,logically confirm my  conceptions of combining simple shapes into one uniquely shaped Shaps entity

- [Study Tonight].
(https://www.studytonight.com/java/java-awt.php) Study Tonight provides a detailed and simple high level explanation of how the java.awt.* library works and how it is structured with it's many sunglasses of gui components e.g JTextfields, ActionListener, JFrame etc.
 

 Environment Variables

To run this project, you will need the latest version of  
Java (https://www.java.com/download/ie_manual.jsp)


t
To confirm a successful install can run a command line prompt 

``` java --version ```

If Java not is installed you may get the message  "Java is not recognized as an internal or external command", which indicates that Java is not installed. If the system displays a Java version number, remove the old Java installation before proceeding. And install the latest version  of Java JDK (https://www.oracle.com/java/technologies/downloads/)

Now once that is done, you should be good to go. Make sure to run 

```java --version``` 

Once successful, it should state the layer version and builds.

If you still have issues (https://www.oracle.com/java/technologies/downloads/#jdk21-windows) 


## Feedback

If you have any feedback, please reach out to me on Github @ mellowCodez or via email @ mellowmarcus1@gmail.com 


Optimizations

What optimizations did you make in your code? E.g. refactors, performance improvements, accessibility


