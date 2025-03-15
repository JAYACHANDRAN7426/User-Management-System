package ui;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class frame1 {
	public static int vup(String s,String p) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/userdata";
		String user = "root";
		String pass = "1234";
		String str = "SELECT * FROM users WHERE name = '" + s + "'";
		Connection con = DriverManager.getConnection(url, user, pass);
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str);
	    
		if(rst.next()) {
			if(rst.getString("password").equals(p)){
				return 2;
			}
			return 1;
		}
		else {
			return 0;
		}
		
		
	}
	 public frame1(){
		    
	        JFrame f = new JFrame("Clickable Text Example");
	        
	        
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        f.setSize(1000, 1000);
	        ImageIcon background = new ImageIcon("C:/Users/User/Downloads/img.jpg"); 
	        JLabel label1 = new JLabel(background);
	        
	        label1.setBounds(0, 0,0,0);

	        // Add the label to the frame
	        f.setContentPane(label1);
	 

	        JLabel label = new JLabel("Sign In");
	        JLabel idl = new JLabel("User name");
	        JLabel  passl = new JLabel("Password");
	        JLabel  pw = new JLabel("Wrong!");
	        JLabel  uw = new JLabel("User name not found");
	        JButton button = new JButton("Log in");
	        TickIcon tick1 = new TickIcon();
			WrongIcon wrong1 = new WrongIcon();
			WrongIcon wrong2 = new WrongIcon();
			
	        
	        JTextField user=new JTextField("Enter your username");
	        user.setForeground(Color.GRAY);
	        JTextField textField=new JTextField("Enter your password");
	        textField.setForeground(Color.GRAY);
	        
	        
	       idl.setBounds(300, 200, 100, 50);
	       idl.setFont(new Font("Arial", Font.PLAIN, 20)); 
	       passl.setBounds(300, 300, 100, 50);
	       passl.setFont(new Font("Arial", Font.PLAIN, 20));
	      
	       label.setBounds(525, 500, 100, 50);
	        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
	        label.setForeground(Color.lightGray);
	        label.setFont(new Font("Arial", Font.PLAIN, 20	)); 
	       
	        
	        button.setBounds(300, 400, 500, 70);
	        button.setBackground(Color.BLUE);   // Set background color
	        button.setForeground(Color.WHITE);  
	        button.setFont(new Font("Arial", Font.PLAIN, 20)); 
	        
	        user.setBounds(500, 200, 300, 50);
	        user.setFont(new Font("Arial", Font.PLAIN, 20)); 
	        textField.setBounds(500, 300, 300, 50);
	        textField.setFont(new Font("Arial", Font.PLAIN, 20)); 
	        label.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	
	               new Signin();
	               f.dispose();
	              
	            }
	        });
	       user.addFocusListener(new FocusListener() {
	            @Override
	            public void focusGained(FocusEvent e) {
	                if (user.getText().equals("Enter your username")) {
	                    user.setText(""); // Clear text when clicked
	                    user.setForeground(Color.BLACK); // Change text color
	                }
	            }

	            @Override
	            public void focusLost(FocusEvent e) {
	                if (user.getText().isEmpty()) {
	                    user.setText("Enter your username"); // Restore placeholder if empty
	                    user.setForeground(Color.GRAY);
	                }
	            }
	        });
	       textField.addFocusListener(new FocusListener() {
	            @Override
	            public void focusGained(FocusEvent e) {
	                if (textField.getText().equals("Enter your password")) {
	                    textField.setText(""); // Clear text when clicked
	                    textField.setForeground(Color.BLACK); // Change text color
	                }
	            }

	            @Override
	            public void focusLost(FocusEvent e) {
	                if (textField.getText().isEmpty()) {
	                    textField.setText("Enter your password"); // Restore placeholder if empty
	                    textField.setForeground(Color.GRAY);
	                }
	            }
	        });
	        button.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	String u=user.getText();
	            	String p=textField.getText();
	               try {
					if(check(u,p)) {
						   new dashboard(u);
						   f.dispose();
					   }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            }
	            public  boolean check(String u, String p) throws SQLException {
	            	int flag=vup(u,p);
					if(1==flag) {
						uw.setText("Username  founded");
						uw.setBounds(500,250,200,30);
						wrong1.setBounds(0,0,0,0);
						tick1.setBounds(630,255, 30, 30);
						pw.setBounds(500, 350, 100, 30);
						wrong2.setBounds(550,355,30,30);
						return false;
						
					}
					else if(0==flag) {
						pw.setBounds(flag, flag, flag, flag);
						wrong2.setBounds(flag, flag, flag, flag);
						uw.setText("Username not found");
						uw.setBounds(500,250,200,30);
						tick1.setBounds(0,0,0,0);
						wrong1.setBounds(630,255, 30, 30);
					    return false;
					}
					else {
						return true;
					}
						
				
				}
              
            

			
	        });
	      
	        f.setResizable(false);
	        f.add(label);
	        f.add(textField);
	        f.add(user);
	        f.add(idl);
	        f.add(wrong1);
	        f.add(pw);
	        f.add(uw);
	        f.add(tick1);
	        f.add(wrong2);
	        f.add(passl);
	        f.add(button);
	        f.setLayout(null);
	        f.setVisible(true);
	        
	    }
	 public static void main(String[] args) {
		new frame1();
	}
	
}
