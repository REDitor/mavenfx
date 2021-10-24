# Additional Information

###Just to be sure:
        NOTE: the database movies are dependent on the 
        .csv file
        if for any reason the file is empty 
        there are 2 commented lines in the database 
        constructor
        which will write the 2 initial movies to the file.
        this probably wont be the case 
        but just to be sure :)

##Accounts:
NOTE: username and password are case-sensitive
###User #1 (Admin):
        username: Sander
        password: myPassword12*

###User #2 (Admin):
        username: Wimmelstein
        password: myPassword13*

###User #3 (Basic):
        username: Billy
        password: myPassword14*

###User #4 (Basic):
        username: Daniel
        password: myPassword15*

##Dependencies:
* 
        <dependency>
        <groupId>org.openjfx</groupId>
            <artifactId>javafx</artifactId>
            <version>${javafx.version}</version>
            <type>pom</type>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-controls</artifactId>
            <version>${javafx.version}</version>
        </dependency>
        <dependency>
            <groupId>org.jfxtras</groupId>
            <artifactId>jmetro</artifactId>
            <version>11.6.15</version>
        </dependency>
