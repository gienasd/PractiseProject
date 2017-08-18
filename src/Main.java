import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main<T,U> {
    public static void main(String[] args) {
        new Main<>();
    }
    private Scanner scanner;
    private List<Person> personList = new ArrayList<>();
    File file = new File("file.bin");

    Main() {
        scanner = new Scanner(System.in);
        personList = Utils.parsePeopleFromFile(Paths.get("personlist.txt"));
        start();
    }

    public void start(){

        String command;
        System.out.println("Witamy, program wczytał istniejącą listę pracowników. W czym mogę Ci pomóc :)");

        do {
            System.out.println("Wpisz komendę: ");
            System.out.println("1 - jeśli chcesz dodać osobę po numerze PESEL");
            System.out.println("2 - jeśli chcesz dodać osobę po numerze NIP");
            System.out.println("3 - jeśli chcesz dodać osobę po dacie urodzenia");
            System.out.println("4 - jeśli chcesz dodać osobę po wieku");
            System.out.println("5 - jeśli chcesz dodać osobę po wysokości miesięcznej pensji");
            System.out.println("6 - jeżeli chcesz wyświetlić listę pracowników");
            System.out.println("7 - jeżeli chcesz nadpisać w pliku listę pracowników");
            System.out.println("8 - jeżeli chcesz serializować plik z listą pracowników");
            System.out.println("exit - jeżeli chcesz wyjść z programu!");
            command = scanner.nextLine();
            parseCommand(command);
        }while (!command.equalsIgnoreCase("exit"));
    }

    private void parseCommand(String command) {
        switch (command){
            case "1":{
                addByPesel();
                break;
            }
            case "2":{
                addByNip();
                break;
            }
            case "3":{
                addByDateOfBirth();
                break;
            }
            case "4":{
                addByAge();
                break;
            }
            case "5":{
                addByPension();
                break;
            }
            case "6":{
                showWorkers();
                break;
            }
            case "7":{
                Utils.savePeopleToFile(Paths.get("personlist.txt"), personList);
                System.out.println("Udało się bezpiecznie nadpisać plik :)");
                break;
            }
            case "8":{
                toBin();
                break;
            }
            case "exit":{
                System.out.println("Dziękuje za używanie programu");
                break;
            }
            default:
                System.out.println("Nie znam takiej komendy!");
        }

    }

    private void toBin() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(personList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showWorkers() {
        System.out.println("Lista pracowników:\n" + personList);
    }

    private void addByPension() {
        Person<String, Double> person = new Person<>();
        System.out.print("Podaj imię: ");
        person.setT(scanner.nextLine());
        System.out.print("Podaj stawkę miesięczną: ");
        double pension = Double.parseDouble(scanner.nextLine());
        person.setU(pension);
        personList.add(person);
        System.out.println("Udało się dodać osobę!");
    }

    private void addByAge() {
        Person<String, Integer> person = new Person<>();
        System.out.print("Podaj imię: ");
        person.setT(scanner.nextLine());
        System.out.print("Podaj wiek: ");
        int age = Integer.parseInt(scanner.nextLine());
        person.setU(age);
        personList.add(person);
        System.out.println("Udało się dodać osobę!");

    }

    private void addByDateOfBirth() {
        Person<String, String> person = new Person<>();
        System.out.print("Podaj imię: ");
        person.setT(scanner.nextLine());
        String dateOfBirth;
        do {
            System.out.print("Podaj poprawną datę urodzenia w formacie DD-MM-RRRR: ");
            dateOfBirth = scanner.nextLine();
        }while (!Pattern.matches("([0-2]{1}[0-9]{1}-[0]{1}[1-9]{1}-[1-2]{1}[0|9]{1}\\d{2})|([0-2]{1}[0-9]{1}-[1]{1}[0-2]{1}-[1-2]{1}[0|9]{1}\\d{2})", dateOfBirth));
        person.setU(dateOfBirth);
        personList.add(person);
        System.out.println("Udało się dodać osobę!");
    }

    private void addByNip() {
        Person<String, String> person = new Person<>();
        System.out.print("Podaj imię: ");
        person.setT(scanner.nextLine());
        String nip;
        do {
            System.out.print("Podaj poprawny NIP: ");
            nip = scanner.nextLine();
        }while (!Pattern.matches("(\\d{3}-\\d{3}-\\d{2}-\\d{2})|(\\d{3}-\\d{2}-\\d{2}-\\d{3})", nip));
        person.setU(nip);
        personList.add(person);
        System.out.println("Udało się dodać osobę!");
    }

    private void addByPesel() {
        Person<String, String> person = new Person<>();
        System.out.print("Podaj imię: ");
        person.setT(scanner.nextLine());
        String pesel;
        do {
            System.out.print("Podaj poprawny pesel: ");
            pesel = scanner.nextLine();
        }while (!Pattern.matches("(\\d{2}[0]{1}[1-9]{1}[0-2]{1}\\d{6})|(\\d{2}[1]{1}[0-2]{1}[0-2]{1}\\d{6})", pesel));
        person.setU(pesel);
        personList.add(person);
        System.out.println("Udało się dodać osobę!");
    }
}
