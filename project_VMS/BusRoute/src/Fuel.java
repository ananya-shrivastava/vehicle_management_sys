
import java.awt.*;
import java.text.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;
import javax.swing.plaf.metal.*;

public class Fuel extends JInternalFrame {

    private JLabel label1,  label2,  label3,  label4,  label5,  label6,  label7,  label8;
    private JTextField text1,  text2,  text3,  text4,  text6,text5;
    private JButton button1,  button2,  button3,  button4,button5;
    private JPanel panel1,  panel2,  panel3;
    private JComboBox combo1,  combo2;
    private DateButton dob;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    public float n1,n2,n3;
    public String s1,s2;
    public Fuel() {
        super("Passenger Details", false, true, false, true);
        label1 = new JLabel("Vehicle_no");
        label2 = new JLabel("Type");
        label3 = new JLabel("Quantity");
        //label4 = new JLabel("Amount");
        button5 = new JButton("Amount");
        label5 = new JLabel("Date_OF_Fill");
        //label6 = new JLabel("Description");
        //label7 = new JLabel("To");
        label1.setFont(new Font("monospaced", Font.BOLD, 20));
        label2.setFont(new Font("monospaced", Font.BOLD, 20));
        label3.setFont(new Font("monospaced", Font.BOLD, 20));
        //label4.setFont(new Font("monospaced", Font.BOLD, 20));
        //label5.setFont(new Font("monospaced", Font.BOLD, 20));
        //label6.setFont(new Font("monospaced", Font.BOLD, 20));
        //label7.setFont(new Font("monospaced", Font.BOLD, 20));
        //label8=new JLabel("Amount Paid");
        combo1 = new JComboBox();
        combo2 = new JComboBox();
        text1 = new JTextField(20);
        text2 = new JTextField(20);
        //text3 = new JTextField(20);
        //text4 = new JTextField(20);
        //text5=new JTextField(20);
        //text6 = new JTextField(20);
        text1.setFont(new Font("monospaced", Font.BOLD, 20));
        text2.setFont(new Font("monospaced", Font.BOLD, 20));
       // text3.setFont(new Font("monospaced", Font.BOLD, 20));
        //text4.setFont(new Font("monospaced", Font.BOLD, 20));
        //text5.setFont(new Font("monospaced", Font.BOLD, 20));
        //text6.setFont(new Font("monospaced", Font.BOLD, 20));
        dob = new DateButton();
        dob.setForeground(Color.red);
        
        setCbx();
        button1 = new JButton("Add New", new ImageIcon(ClassLoader.getSystemResource("Images/addnew.png")));
        button1.setBackground(new Color(4, 1, 8));
        button1.setForeground(Color.white);
        button1.setFont(new Font("monospaced", Font.BOLD, 20));
       
        button2 = new JButton("Cancel", new ImageIcon(ClassLoader.getSystemResource("Images/exit.png")));
         button2.setBackground(new Color(4, 1, 8));
        button2.setForeground(Color.white);
        button2.setFont(new Font("monospaced", Font.BOLD, 20));
        button3 = new JButton("Clear", new ImageIcon(ClassLoader.getSystemResource("Images/clear.png")));
         button3.setBackground(new Color(4, 1, 8));
        button3.setForeground(Color.white);
        text2.setEditable(false);
        button3.setFont(new Font("monospaced", Font.BOLD, 20));
        
        button5.setBackground(new Color(4, 1, 8));
        button5.setForeground(Color.white);
        button5.setFont(new Font("monospaced", Font.BOLD, 20));
        button5.setPreferredSize(new Dimension(3,3));
        combo1.setBackground(Color.white);
        combo2.setBackground(Color.white);
        combo2.addItem("Diesel");
        combo2.addItem("Petrol");
        panel1 = new JPanel(new GridLayout(7, 2));
        panel1.setPreferredSize(new Dimension(350, 250));
        panel1.add(label1);
         label1.setFont(new Font("monospaced", Font.BOLD, 20));
        panel1.add(combo1);
         text1.setFont(new Font("monospaced", Font.BOLD, 20));
        panel1.add(label2);
        label2.setFont(new Font("monospaced", Font.BOLD, 20));
        panel1.add(combo2);
        text2.setFont(new Font("monospaced", Font.BOLD, 20));
        panel1.add(label3);
        panel1.add(text1);
        label3.setFont(new Font("monospaced", Font.BOLD, 20));
        //text3.setFont(new Font("monospaced", Font.BOLD, 20));
       //panel1.add(label4);
        panel1.add(button5);
        panel1.add(text2);
        //label4.setFont(new Font("monospaced", Font.BOLD, 20));
        //text4.setFont(new Font("monospaced", Font.BOLD, 20));
        panel1.add(label5);
        label5.setFont(new Font("monospaced", Font.BOLD, 20));
        //text3.setFont(new Font("monospaced", Font.BOLD, 20));
        panel1.add(dob);
        //s1=text1.getText();
        //n1=Float.parseFloat(s1);
       /* if( combo2.getSelectedItem()=="Diesel")
        {
            n3=n1*62;
            text2.setText(""+n3);
        }
        else
        {    n2=n1*62;
            text2.setText(""+n2);
            
        }*/
        //panel1.add(label6);
         //label6.setFont(new Font("monospaced", Font.BOLD, 20));
        //panel1.add(text5);
        //panel1.add(label7);
         //label7.setFont(new Font("monospaced", Font.BOLD, 20));
        //panel1.add(combo2);
        //panel1.add(label8);panel1.add(text6);
        
        panel2 = new JPanel();
        panel2.add(button1);
        panel2.add(button2);
        panel2.add(button3);

        panel3 = new JPanel();
        panel3.setPreferredSize(new Dimension(550, 400));
        panel3.add(panel1);
        panel3.add(panel2);
         panel1.setBackground(new Color(201, 239, 252));
          //pane.setBackground(new Color(180, 219, 6));
           panel2.setBackground(new Color(201, 239, 252));
          panel3.setBackground(new Color(201, 239, 252));
        add(panel3);
        setSize(500, 350);
        setLocation((screen.width - 500) / 2, ((screen.height - 350) / 2));
        //generator();

        text1.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent e) {
                JTextField textField =
                        (JTextField) e.getSource();
                String content = textField.getText();
                if (content.length() != 0) {
                    try {
                        Integer.parseInt(content);
                    } catch (NumberFormatException nfe) {
                        getToolkit().beep();
                        JOptionPane.showMessageDialog(null, "Invalid data entry", "Error", JOptionPane.DEFAULT_OPTION);
                        textField.requestFocus();
                        text1.setText("");
                    }
                }
            }
        });

       /* text2.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isLetter(c) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {

                    getToolkit().beep();
                    JOptionPane.showMessageDialog(null, "This Field Only acept text", "Error", JOptionPane.DEFAULT_OPTION);
                    e.consume();
                }
            }
        });*/

        /*text4.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent e) {
                JTextField textField =
                        (JTextField) e.getSource();
                String content = textField.getText();
                if (content.length() != 0) {
                    try {
                        Integer.parseInt(content);
                    } catch (NumberFormatException nfe) {
                        getToolkit().beep();
                        JOptionPane.showMessageDialog(null, "Invalid data entry", "Error",
                                JOptionPane.DEFAULT_OPTION);
                        textField.requestFocus();
                        text4.setText("");
                    }
                }
            }
        });*/


        button1.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (text1.getText() == null ||
                        text1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Passenger number", "Error", JOptionPane.DEFAULT_OPTION);
                    text1.requestFocus();
                    return;
                }
                if (text2.getText() == null ||
                        text2.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter passenger name", "Error",
                            JOptionPane.DEFAULT_OPTION);
                    text2.requestFocus();
                    return;
                }
                
                /*
                if (text3.getText() == null ||
                        text3.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Address Field is required", "Error",
                            JOptionPane.DEFAULT_OPTION);
                    text3.requestFocus();
                    return;
                }
                if (text4.getText() == null ||
                        text4.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter telephone number", "Error",
                            JOptionPane.DEFAULT_OPTION);
                    text4.requestFocus();
                    return;
                }
                if (text5.getText() == null ||
                        text5.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter the Description please", "Error",
                            JOptionPane.DEFAULT_OPTION);
                    text5.requestFocus();
                    return;
                }*/



                try {
                    Statement statement = DBConnection.getDBConnection().createStatement();
                    {
                        String temp = "INSERT INTO Fuel(VEH_NO, TYPE,QUANTITY,AMOUNT,DATE_OF_FILL) VALUES ('" +
                                combo1.getSelectedItem() + "', '" +
                                    combo2.getSelectedItem() +  "', '" +
                                text1.getText() + "', '" +
                                text2.getText() + "', '" +
                                dob.getText() + "')";

                        int result = statement.executeUpdate(temp);
                        String ObjButtons[] = {"Yes", "No"};
                        int PromptResult = JOptionPane.showOptionDialog(null, "Record succesfully added.Do you want to add another?",
                                "Success", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
                        if (PromptResult == 0) {
                            //generator();
                            text2.setText("");
                            //text3.setText("");
                            //text4.setText("");
                            //text5.setText("");
                        } else {
                            setVisible(false);
                        }
                    }

                } catch (SQLException sqlex) {
                    sqlex.printStackTrace();
                }
            }
        });
        button2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
         button5.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                 s1=text1.getText();
        n1=Float.parseFloat(s1);
        if( combo2.getSelectedItem()=="Diesel")
        {
            n3=n1*62;
            text2.setText(""+n3);
        }
        else
        {    n2=n1*74;
            text2.setText(""+n2);
            
        }
            }
        });
        button3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                text1.setText("");
                text2.setText("");
                //text3.setText("");
                //text4.setText("");
                //text5.setText("");
            }
        });
    }

    private void setCbx() {
        try {
            ResultSet rst = DBConnection.getDBConnection().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT BUSNO FROM BUSES");

            while (rst.next()) {
                combo1.addItem(rst.getString(1));
                //combo2.addItem(rst.getString(2));

            }
        } catch (Exception n) {
            n.printStackTrace();
        }
    }

  /* private void generator() {

        try {
            ResultSet rst = DBConnection.getDBConnection().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT Pass_No FROM Passenger");
            while (rst.next()) //text1.setText("1000");
            {
                String s;
                int number = rst.getInt(1);
                number = number + 1;

                s = "" + number;
                text1.setText(s);

            }
        } catch (Exception n) {
            n.printStackTrace();
        }


    }*/


}