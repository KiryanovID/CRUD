package actual;

import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CountryDAO crud = new CountryDAO();
        ConsoleIO consoleIO = new ConsoleIO();
        try(scanner){
            System.out.println("Введите команду: ");
            while (scanner.hasNext() ){
                String command = scanner.nextLine().toLowerCase().trim();
                switch (command){
                    case "create" : consoleIO.create();
                    break;
                    case "read" : consoleIO.read();
                    break;
                    case "update" : consoleIO.update();
                    break;
                    case "delete" : consoleIO.delete();
                    break;
                    case "showall" : consoleIO.showAll();
                    break;
                    case "exit" : return;
                    default:
                        System.out.println("Не известная команда! \n");
                        System.out.println("Введите команду: ");
                        break;
                }
            }
        }
    }
}

