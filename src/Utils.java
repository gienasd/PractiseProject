import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<Person> parsePeopleFromFile(Path path){
        List<Person> personList = new ArrayList<>();

        try {
            Person person;
            for (String lines: Files.readAllLines(path)){
                String[] elements = lines.split(", ID: ");
                    person = new Person(elements[0], elements[1]);
                    personList.add(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personList;
    }

    public static void savePeopleToFile(Path path, List<Person> personList){
        List<String> lines = new ArrayList<>();

        for(Person person : personList){
            lines.add(person.toString());
        }
        try {
            Files.write(path, lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
