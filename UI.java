import java.sql.SQLException;
import java.util.Scanner;

public class UI {

    private final Scanner reader;
    private ToDoList list;
    private ToDoListDAO tdldao;

    public UI() throws SQLException {
        this.tdldao = new ToDoListDAO();
        this.reader = new Scanner(System.in);
        this.list = tdldao.returnList();
    }

    public void start() {
        this.list.printToDoList();
        while (true) {
            System.out.println("");
            System.out.println("1: Add a to-do item.");
            System.out.println("2. Remove a to-do item.");
            System.out.println("3. Print a list of my to-do items.");
            System.out.println("4. Quit and save");
            System.out.print("Type the number of desired action: ");

            String input = reader.nextLine();

            if (input.equals("4")) {
                System.out.println("Quitting!");
                break;
            } else if (input.equals("1")) {
                System.out.println("What would you like to add?");
                String add = reader.nextLine();
                ToDo item = new ToDo(add);
                this.list.addToDo(item);
                this.tdldao.addToDB(item.toString());
            }

            else if (input.equals("2")) {
                if (this.list.getList().size() < 1) {
                    System.out.println();
                    System.out.println("Nothing to delete!");
                }
                else {
                    System.out.print("Type the index of the item you wish to remove: ");
                    try {
                        int remove = Integer.parseInt(reader.nextLine());
                        this.tdldao.deleteFromDB(this.list.getList().get(remove - 1).toString());
                        this.list.removeToDo(remove);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Please enter a number.");
                    } catch (IndexOutOfBoundsException ib) {
                        System.out.println("Please enter a number between or equal to 1 or " + this.list.getList().size() + ".");
                    }
                }
            }

            else if (input.equals("3")) {
                System.out.println("");
                this.list.printToDoList();
            }
        }
    }
}

