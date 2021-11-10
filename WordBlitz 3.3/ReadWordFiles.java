import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Gets all the words from the files and adds them to 
 * their corresponding ArrayList. Word files must be in the same directory.
 * Implement this code into the game.
 * 
 * @author Jaylen Cheung
 * @version 1.0.0
 */
public class ReadWordFiles {
    public static ArrayList<ArrayList<String>> readWordFiles() {
        ArrayList<String> nouns = new ArrayList<String>();
        ArrayList<String> verbs = new ArrayList<String>();
        ArrayList<String> adjectives = new ArrayList<String>();
        ArrayList<ArrayList<String>> lst = new ArrayList<ArrayList<String>>();
        lst.add(nouns);
        lst.add(verbs);
        lst.add(adjectives);
        String[] types = {"nouns.txt", "verbs.txt", "adjectives.txt"};
        try {
            for (int i = 0; i < 3; i++) {
                File file = new File(types[i]);
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    lst.get(i).add(scanner.nextLine());
                }
                scanner.close();
            }
        } catch (FileNotFoundException ignored) {}
        return lst;
    }
}
