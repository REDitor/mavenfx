package nl.inholland.javafx.Data;

import nl.inholland.javafx.Model.Theatre.Movie;
import nl.inholland.javafx.Model.Theatre.MovieShowing;
import nl.inholland.javafx.Model.Theatre.Room;
import nl.inholland.javafx.Model.User.Admin;
import nl.inholland.javafx.Model.User.User;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

public class Database {
    private static final String PATH = "src/main/resources/%s";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private List<User> users;
    private List<Movie> movies;
    private Room room1;
    private Room room2;

    public Room getRoom1() {
        return room1;
    }

    public Room getRoom2() {
        return room2;
    }

    public Database() {
        users = new ArrayList<>();
        users.addAll(getUsers());
        room1 = new Room(1, 200);
        room2 = new Room(2, 100);
        movies = getMovies();
        //region NOTE (Original Code in case of empty .csv file):
        // addMovie(new Movie("No time to lie", 12.00, Duration.ofMinutes(Long.parseLong("125"))));
        // addMovie(new Movie("The Addams Family 19", 9.00, Duration.ofMinutes(Long.parseLong("92"))));
        //endregion
        addInitialShowings();
    }

    //region Rooms/Showings
    private void addInitialShowings() {
        //Room1
        room1.addShowing(
                new MovieShowing(
                        LocalDateTime.parse("09-10-2021 20:00", dateTimeFormatter),
                        movies.get(0),
                        room1.getNumberOfSeats(),
                        room1
                )
        );
        room1.addShowing(
                new MovieShowing(
                        LocalDateTime.parse("10-10-2021 22:30", dateTimeFormatter),
                        movies.get(1),
                        room1.getNumberOfSeats(),
                        room1
                )
        );

        //Room2
        room2.addShowing(
                new MovieShowing(
                        LocalDateTime.parse("09-10-2021 20:00", dateTimeFormatter),
                        movies.get(1),
                        room1.getNumberOfSeats(),
                        room2
                )
        );
        room2.addShowing(
                new MovieShowing(
                        LocalDateTime.parse("09-10-2021 22:00", dateTimeFormatter),
                        movies.get(0),
                        room1.getNumberOfSeats(),
                        room2
                )
        );
    }

//    public void writeShowings(List<MovieShowing> showingsRoom1, List<MovieShowing> showingsRoom2, File file) throws IOException {
//
//
//        List<MovieShowing> allShowings = new ArrayList<>();
//        allShowings.addAll(showingsRoom1);
//        allShowings.addAll(showingsRoom2);
//        FileOutputStream fos = new FileOutputStream(new File(file));
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        for (MovieShowing showing : allShowings) {
//            oos.writeObject(showing);
//        }
//
//    }

    public void addShowing(MovieShowing showing, Room room) {
        room.addShowing(showing);
    }
    //endregion

    //region Users
    public List<User> getUsers() {
        List<User> dbUsers = new ArrayList<>();
        try {
            List<String> strings = Files.readAllLines(Paths.get(String.format(PATH, "users.csv")));
            for (String line : strings) {
                if (line.split(",")[2].equals("admin")) {
                    dbUsers.add(
                            new Admin(
                                    line.split(",")[0],
                                    line.split(",")[1]
                            )
                    );
                } else {
                    dbUsers.add(
                            new User(
                                    line.split(",")[0],
                                    line.split(",")[1]
                            )
                    );
                }
            }

        } catch (FileNotFoundException fnfe) {
            //TODO: messagedialog?
        } catch (IOException ioe) {
            //...
        }

        return dbUsers;
    }
    //endregion

    //region Movies
    public List<Movie> getMovies() {
        return readMovies();
    }

    public List<String> getMovieTitles() {
        List<String> movieTitles = new ArrayList<>();
        for (Movie movie : getMovies()) {
            movieTitles.add(movie.getTitle());
        }
        return movieTitles;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
        writeMovies(movies);
    }

    public List<Movie> readMovies() {
        List<Movie> dbMovies = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(String.format(PATH, "movies.csv")))) {
            while (true) {
                try {
                    Movie movie = (Movie) ois.readObject();
                    dbMovies.add(movie);
                } catch (EOFException eofe) {
                    break;
                }
            }
        } catch (FileNotFoundException fnfe) {
            //.
        } catch (ClassNotFoundException cnfe) {

        } catch (IOException ioe) {
            //.....
        }
        return dbMovies;
    }

    public void writeMovies(List<Movie> movies) {
        try (FileOutputStream fos = new FileOutputStream(String.format(PATH, "movies.csv"))) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Movie movie : movies) {
                oos.writeObject(movie);
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    //endregion
}