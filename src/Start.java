import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CRUD crud = new CRUD();
        try(scanner){
            System.out.println("Введите команду: ");
            while (scanner.hasNext() ){
                String command = scanner.nextLine().toLowerCase().trim();
                switch (command){
                    case "create" : crud.create();
                    break;
                    case "read" : crud.read();
                    break;
                    case "update" : crud.update();
                    break;
                    case "delete" : crud.delete();
                    break;
                    case "showall" : crud.showAll();
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

