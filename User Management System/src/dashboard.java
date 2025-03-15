package ui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class dashboard {
    int wf = 0, hf = 0;
    JFrame frame;
    DefaultTableModel model;
    JTable table;
    JScrollPane scrollPane;

    public dashboard(String name) {
        frame = new JFrame("Dashboard");
        getdata data = null;
        try {
            data = new getdata(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setLayout(null);

        JLabel u = new JLabel("Name"), e = new JLabel("Email"), a = new JLabel("Age"), p = new JLabel("Place");
        JLabel un = new JLabel(name), em = new JLabel(data.getemail()), age = new JLabel(data.getage()), pl = new JLabel(data.getplace());
        Choice choice = new Choice();
        choice.add("Name");
        choice.add("Age");
        choice.add("Place");

        JLabel se = new JLabel("Search By:");
        se.setFont(new Font("Arial", Font.PLAIN, 20));
        u.setFont(new Font("Arial", Font.PLAIN, 20));
        e.setFont(new Font("Arial", Font.PLAIN, 20));
        a.setFont(new Font("Arial", Font.PLAIN, 20));
        p.setFont(new Font("Arial", Font.PLAIN, 20));
        un.setFont(new Font("Arial", Font.PLAIN, 20));
        em.setFont(new Font("Arial", Font.PLAIN, 20));
        age.setFont(new Font("Arial", Font.PLAIN, 20));
        pl.setFont(new Font("Arial", Font.PLAIN, 20));

        JPanel box = new JPanel();
        box.setLayout(null);
        box.setBackground(Color.WHITE);
        box.setBounds(150, 200, 400, 300);

        u.setBounds(20, 30, 100, 30);
        e.setBounds(20, 80, 100, 30);
        a.setBounds(20, 130, 100, 30);
        p.setBounds(20, 180, 100, 30);
        un.setBounds(120, 30, 100, 30);
        em.setBounds(120, 80, 400, 30);
        age.setBounds(120, 130, 100, 30);
        pl.setBounds(120, 180, 100, 30);

        box.add(p);
        box.add(a);
        box.add(e);
        box.add(u);
        box.add(un);
        box.add(age);
        box.add(em);
        box.add(pl);

        JTextField tf = new JTextField();
        JButton searchButton = new JButton("\uD83D\uDD0D");

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                Dimension size = frame.getSize();
                wf = size.width;
                hf = size.height;
                panel.setBounds(0, 0, wf / 2, hf);
                tf.setBounds((wf / 2) + 40, 60, 600, 50);
                searchButton.setBounds((wf / 2) + 650, 60, 60, 50);
                se.setBounds((wf / 2) + 40, 110, 100, 30);
                choice.setBounds((wf / 2) + 150, 100, 100, 50);
                scrollPane.setBounds((wf / 2) + 40, 160, 700, 300);
            }
        });

        // Setup JTable and model
        model = new DefaultTableModel(new String[]{"Name", "Age", "Place"}, 0);
        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 18));
        table.getTableHeader().setBackground(Color.LIGHT_GRAY);
        table.setGridColor(Color.GRAY);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane = new JScrollPane(table);
       
        frame.add(scrollPane);

        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String searchText = tf.getText();
                String selected = choice.getSelectedItem();
                model.setRowCount(0);

                ResultSet rst = null;
                try {
                    if (selected.equals("Name")) {
                        rst = getname(searchText);
                    } else if (selected.equals("Place")) {
                        rst = getplace(searchText);
                    } else {
                        rst = getage(searchText);
                    }

                    boolean found = false;
                    while (rst.next()) {
                        model.addRow(new Object[]{rst.getString("name"), rst.getInt("age"), rst.getString("place")});
                        found = true;
                    }
                    if (!found) {
                        JOptionPane.showMessageDialog(frame, "No User Found", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        panel.add(box);
        frame.add(se);
        frame.add(choice);
        frame.add(panel);
        frame.add(tf);
        frame.add(searchButton);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    public ResultSet getname(String un) throws SQLException {
        return executeQuery("SELECT * FROM users WHERE name = '" + un + "'");
    }

    public ResultSet getplace(String un) throws SQLException {
        return executeQuery("SELECT * FROM users WHERE place = '" + un + "'");
    }

    public ResultSet getage(String un) throws SQLException {
        return executeQuery("SELECT * FROM users WHERE age = '" + un + "'");
    }

    private ResultSet executeQuery(String query) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/userdata";
        String user = "root";
        String pass = "1234";
        Connection con = DriverManager.getConnection(url, user, pass);
        Statement stm = con.createStatement();
        return stm.executeQuery(query);
    }
}
