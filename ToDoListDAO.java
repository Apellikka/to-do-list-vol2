import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class ToDoListDAO {

    private ToDoList list = new ToDoList();
    private ArrayList<String> fromDB;
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    private DatabaseConnection dbCon;

    public ToDoListDAO() throws SQLException {
        this.dbCon = new DatabaseConnection();
        this.fromDB = new ArrayList<>();
        this.fromDB = getFromDB();
    }


    public ToDoList returnList() {
        for (String line : fromDB) {
            ToDo todo = new ToDo(line);
            list.addToDo(todo);
        }
        return this.list;
    }


    public ArrayList<String> getFromDB() throws SQLException {
        try {
            this.con = dbCon.getConnection();
            this.stmt = con.prepareStatement("select * from todolist");
            this.rs = stmt.executeQuery();

            while (rs.next()) {
                fromDB.add(rs.getString("task"));
            }
        } finally {

            rs.close();
            stmt.close();
            con.close();
        }
        return fromDB;
    }

    public void addToDB(String task) {
        try {
            this.con = dbCon.getConnection();
            this.stmt = con.prepareStatement("INSERT INTO todolist(task) VALUES " + "(" + "\"" + task +"\")");
            stmt.executeUpdate();

            stmt.close();
            con.close();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    public void deleteFromDB(String task) {
        try {
            this.con = dbCon.getConnection();
            this.stmt = con.prepareStatement("DELETE FROM todolist WHERE task = " + "\"" + task + "\"");
            stmt.executeUpdate();

            stmt.close();
            con.close();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }
}
