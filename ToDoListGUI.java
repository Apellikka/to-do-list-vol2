import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ToDoListGUI {
    private JButton addTaskButton;
    private JButton quitButton;
    private JButton removeTaskButton;
    private JTextField taskTextField;
    private JList list1;
    private JPanel listPanel;
    private JPanel topPanel;
    private JPanel mainPanel;
    private ToDoListDAO tdldao = new ToDoListDAO();
    private ToDoList list = tdldao.returnList();

    public ToDoListGUI() throws SQLException {

        DefaultListModel model = new DefaultListModel();

        for (int i = 0; i < list.getList().size(); i++) {
            model.addElement(list.getList().get(i));
        }
        list1.setModel(model);

        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskTextField.getText();
                model.addElement(task);
                list1.setModel(model);
                tdldao.addToDB(task);
            }

        });
        removeTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int remove = list1.getSelectedIndex();
                model.removeElementAt(remove);
                String removeFromDB = list.getList().get(remove).toString();
                tdldao.deleteFromDB(removeFromDB);
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }


    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("ToDoListGUI");
        frame.setPreferredSize(new Dimension(350, 350));
        frame.setContentPane(new ToDoListGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
