package ui;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Signin {
	public static void inset(String n,String e,String p,int a,String place) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/userdata";
		String user = "root";
		String pass = "1234";
		String sql= "INSERT INTO users (name, email, password,age,place) VALUES (?, ?, ?,?,?)";
		Connection con = DriverManager.getConnection(url, user, pass);
		PreparedStatement stmt = con.prepareStatement(sql);
	
		stmt.setString(1, n);
        stmt.setString(2, e);
        stmt.setString(3, p);
        stmt.setInt(4, a);
        stmt.setString(5, place);
        int rowsInserted = stmt.executeUpdate();
        
	}
	public static boolean isv(String email) {
		 String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(email);
	        return matcher.matches();
	    }
	public static boolean isp(String p) {
		if(p.isEmpty()||p.equals("Enter your password")) {
			return false;
		}
		return true;
	    }
	
	public static boolean isu(String s) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/userdata";
		String user = "root";
		String pass = "1234";
		String str = "SELECT * FROM users WHERE name = '" + s + "'";
		Connection con = DriverManager.getConnection(url, user, pass);
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str);
		if(rst.next() || s.isEmpty() || s.equals("Enter your username")) {
			return false;
		}
		else {
			return true;
		}
		
	}
   public Signin() {
	   JFrame f=new JFrame();
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       f.setSize(1000, 1000);
       ImageIcon background = new ImageIcon("C:/Users/User/Downloads/img.jpg"); 
       JLabel label1 = new JLabel(background);
       label1.setBounds(0, 0, f.getWidth(), f.getHeight());
	   JLabel log= new JLabel("Login");
	   // Add the label to the frame
       f.setContentPane(label1);
       JLabel email = new JLabel("Email");
       JLabel  us = new JLabel("User name");
       JLabel pass = new JLabel("Password");
       JButton button = new JButton("Sign in");
       JLabel ve= new JLabel("Login");
		JLabel vp= new JLabel("Login");
		JLabel vu= new JLabel("Login");
		TickIcon tick = new TickIcon();
		WrongIcon wrong = new WrongIcon();
		TickIcon tick1 = new TickIcon();
		WrongIcon wrong1 = new WrongIcon();
		TickIcon tick2 = new TickIcon();
		WrongIcon wrong2 = new WrongIcon();

       
       
       JTextField user=new JTextField("Enter your username");
       user.setForeground(Color.GRAY);
       JTextField emailt=new JTextField("Enter your email");
       emailt.setForeground(Color.GRAY);
       JTextField passt=new JTextField("Enter your password");
       passt.setForeground(Color.GRAY);
       
       
       log.setFont(new Font("Arial", Font.PLAIN, 20)); 
       log.setForeground(Color.lightGray);
       email.setBounds(300, 300, 100, 50);
       email.setFont(new Font("Arial", Font.PLAIN, 20)); 
       us.setBounds(300, 200, 100, 50);
       us.setFont(new Font("Arial", Font.PLAIN, 20)); 
       pass.setBounds(300, 400, 100, 50);
       pass.setFont(new Font("Arial", Font.PLAIN, 20)); 
       log.setBounds(525, 600,100, 50);
       log.setCursor(new Cursor(Cursor.HAND_CURSOR));
       
       button.setBounds(300, 500, 500, 70);
       button.setBackground(Color.BLUE);   // Set background color
       button.setForeground(Color.WHITE);  
       button.setFont(new Font("Arial", Font.PLAIN, 20)); 
       
       user.setBounds(500, 200, 300, 50);
       user.setFont(new Font("Arial", Font.PLAIN, 20)); 
       emailt.setBounds(500, 300, 300, 50);
       emailt.setFont(new Font("Arial", Font.PLAIN, 20)); 
       passt.setBounds(500, 400, 300, 50);
       passt.setFont(new Font("Arial", Font.PLAIN, 20)); 
       
       button.addMouseListener(new MouseAdapter() {
       public  void mouseClicked(MouseEvent e) {
    	   boolean a = false;
    	   String namev=user.getText(),en=emailt.getText(),pv=passt.getText();
    	   
    	   try {
			 a=Check(namev,en,pv);
		} 
    	   
    	   catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	   
    	   if(a) {
    		   try {
    			   boolean s=true;
    			   String place="";
    			   int number=0;
    		        // Validate inputs and display the collected information
    		        while(s) {
    		        	  place = JOptionPane.showInputDialog(f, "Enter your location:");

    		             // Show a message dialog with the user's response
    		             if (place != null && !place.trim().isEmpty()) {
    		                 JOptionPane.showMessageDialog(f, "Your location is: " + place);
    		                 s=false;
    		             } else {
    		                 JOptionPane.showMessageDialog(f, "No location entered.");
    		                 s=true;
    		             }
    		        }
    		        s=true;
    		        while(s) {
    		       	 

    		            // Show a message dialog with the user's response
    		       	String input = JOptionPane.showInputDialog("Enter a number:");

    		        // Check if input is a valid number
    		        try {
    		            number = Integer.parseInt(input.trim());
    		            JOptionPane.showMessageDialog(null, "Your age: " + number);
    		            s=false;
    		        } catch (NumberFormatException ex) {
    		            JOptionPane.showMessageDialog(null, "Invalid age! Please enter a valid age.");
    		            
    		        }
    		            
    		       }
				inset(namev,en,pv,number,place);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		   new dashboard(namev);
    		  f.dispose();
    	   }
       }

	private boolean Check(String u, String e, String p) throws SQLException {
		int flag=3;
		if(isv(e)) {
			ve.setText("Email is Valide");
		 ve.setBounds(500,350 ,150 ,30 );
		 wrong.setBounds(0, 0, 0, 0);
		 tick.setBounds(605, 355, 30, 30);
		}
		else {
			ve.setText("Email is not valide");
			ve.setBounds(500,350 ,150 ,30 );
			tick.setBounds(0, 0, 0, 0);
			wrong.setBounds(605, 355, 30, 30);
			flag--;
		}
		if(isu(u)) {
			vu.setText("username is available");
		 vu.setBounds(500,250 ,250 ,30 );
		 wrong1.setBounds(0, 0, 0, 0);
		 tick1.setBounds(631, 255, 30, 30);
		}
		else {
			vu.setText("username is not available");
			vu.setBounds(500,250 ,250 ,30 );
			tick1.setBounds(0, 0, 0,0);
			wrong1.setBounds(644, 255, 30, 30);
			flag--;
		}
		if(isp(p)) {
			vp.setText("Confirm");
		 vp.setBounds(500,450 ,100 ,30 );
		 wrong2.setBounds(0, 0, 0, 0);
		 tick2.setBounds(570, 455, 30, 30);
		}
		else {
			vp.setText("please enter your password");
			vp.setBounds(500,450 ,250 ,30 );
			tick2.setBounds(0, 0, 0,0);
			wrong2.setBounds(665, 455, 30, 30);
			flag--;
		}
		if(flag==3) {
		
		return true;
		}
		return false;
	}

	
	
       });
       
       log.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
           	
              new frame1();
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
       emailt.addFocusListener(new FocusListener() {
           @Override
           public void focusGained(FocusEvent e) {
               if (emailt.getText().equals("Enter your email")) {
            	   emailt.setText(""); // Clear text when clicked
            	   emailt.setForeground(Color.BLACK); // Change text color
               }
           }

           @Override
           public void focusLost(FocusEvent e) {
               if (emailt.getText().isEmpty()) {
            	   emailt.setText("Enter your email"); // Restore placeholder if empty
            	   emailt.setForeground(Color.GRAY);
               }
           }
       });
       passt.addFocusListener(new FocusListener() {
           @Override
           public void focusGained(FocusEvent e) {
               if (passt.getText().equals("Enter your password")) {
                   passt.setText(""); // Clear text when clicked
                   passt.setForeground(Color.BLACK); // Change text color
               }
           }

           @Override
           public void focusLost(FocusEvent e) {
               if (passt.getText().isEmpty()) {
            	   passt.setText("Enter your password"); // Restore placeholder if empty
            	   passt.setForeground(Color.GRAY);
               }
           }
       });

       
      
       
 
       f.add(pass);
       f.add(email);
       f.add(us);
       f.add(log);
       f.add(passt);
       f.add(emailt);
       f.add(user);
       f.add(ve);
       f.add(tick);
       f.add(wrong);
       f.add(tick1);
       f.add(wrong1);
       f.add(vu);
       f.add(tick2);
       f.add(wrong2);
       f.add(vp);
       f.add(button);
       f.setResizable(false);
       f.setLayout(null);
       f.setVisible(true);
       
   }
}
