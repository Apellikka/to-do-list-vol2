import java.util.ArrayList;

public class ToDoList {

    private ArrayList<ToDo> toDoList;

    public ToDoList() {
        this.toDoList = new ArrayList<>();
    }

    public void addToDo(ToDo toDo) {
        this.toDoList.add(toDo);
    }

    public void removeToDo(int toDo) {
        try {
            this.toDoList.remove(toDo - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please enter a number between or equal to 1 or " + this.toDoList.size() + ".");
        }
    }

    public void printToDoList() {
        if (toDoList.isEmpty()) {
            System.out.println("List is empty.");
        } else {
            int i = 1;
            for (ToDo todo : toDoList) {
                System.out.println(i + ": " + todo);
                i++;
            }
        }
    }
    public ArrayList<ToDo> getList() {
        return this.toDoList;
    }
}



