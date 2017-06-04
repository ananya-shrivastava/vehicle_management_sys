import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;

public class AddNewEntry extends JInternalFrame {

Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
private JLabel empNo, Sname, Fname, Lname, Gender, Designation, telephone, lblEmplPic, email, address, DOB;
private JTextField txtEmpNo, txtSname, txtFname, txtLname, txtDesignation, txttelephone, txtemail, txtaddress;
private JButton jButton1;
private JButton jButton2, AddPic;
private JButton Clear, Next;
private JPanel jPanel1, pics;
private JPanel jPanel3;
private JPanel jPanel4;
private JPanel jPanel5;
private DateButton dob;
private JComboBox cbogender;
private static JTextArea txtInfo = new JTextArea(15, 40);
private Connection dbconn;
private static String info;
final JFileChooser fc = new JFileChooser();
String getPicture;

public AddNewEntry() {

super("Add New Driver",false,true,false,true);
setDefaultCloseOperation(javax.swing.JFrame.HIDE_ON_CLOSE);
empNo = new JLabel("Employee Number ");
Sname = new JLabel("Surname ");
Fname = new JLabel("First Name ");
Lname = new JLabel("Last Name ");
Gender = new JLabel("Gender ");
Designation = new JLabel("Designation ");
telephone = new JLabel("Telephone Number");
email = new JLabel("E-mail Address");
address = new JLabel("Address");
DOB = new JLabel("DOB");
empNo.setFont(new Font("monospaced", Font.BOLD, 20));
empNo.setBounds(4, 3, 110, 32);
Sname.setFont(new Font("monospaced", Font.BOLD, 20));
Sname.setBounds(4, 3, 110, 32);
Fname.setFont(new Font("monospaced", Font.BOLD, 20));
Lname.setFont(new Font("monospaced", Font.BOLD, 20));
Gender.setFont(new Font("monospaced", Font.BOLD, 20));
Designation.setFont(new Font("monospaced", Font.BOLD, 20));
telephone.setFont(new Font("monospaced", Font.BOLD, 20));
email.setFont(new Font("monospaced", Font.BOLD, 20));
address.setFont(new Font("monospaced", Font.BOLD, 20));
DOB.setFont(new Font("monospaced", Font.BOLD, 20));
lblEmplPic = new JLabel(new ImageIcon(ClassLoader.getSystemResource("Images/defaultpic.png")));
txtEmpNo = new JTextField(15);
txtEmpNo.setBounds(124, 3, 190, 32);
txtSname = new JTextField(15);
txtSname.setBounds(124, 3, 190, 32);
txtFname = new JTextField(15);
txtLname = new JTextField(15);
cbogender = new JComboBox();
txtDesignation = new JTextField(15);
txttelephone = new JTextField(15);
txtemail = new JTextField(15);
txtaddress = new JTextField(15);

jButton1 = new JButton("Add Record", new ImageIcon(ClassLoader.getSystemResource("Images/addnew.png")));
jButton2 = new JButton("Cancel", new ImageIcon(ClassLoader.getSystemResource("Images/exit.png")));
Clear = new JButton("Clear", new ImageIcon(ClassLoader.getSystemResource("Images/clear.png")));
AddPic = new JButton("Select pic");
jButton1.setForeground(Color.white);
jButton1.setBackground(new Color(4, 1, 8));
jButton1.setFont(new Font("monospaced", Font.BOLD, 16));
//jButton1.setBounds();

jButton2.setForeground(Color.white);
jButton2.setBackground(new Color(4, 1, 8));
//jButton2.setBounds(WIDTH, WIDTH, WIDTH, WIDTH);
jButton2.setFont(new Font("monospaced", Font.BOLD, 16));
Clear.setForeground(Color.white);
Clear.setBackground(new Color(4, 1, 8));
Clear.setFont(new Font("monospaced", Font.BOLD, 16));
AddPic.setForeground(Color.white);
AddPic.setBackground(new Color(4, 1, 8));
AddPic.setFont(new Font("monospaced", Font.BOLD, 16));
dob = new DateButton();
dob.setForeground(Color.red);

pics = new JPanel();
pics.setPreferredSize(new Dimension(150, 190));
pics.add(lblEmplPic);
pics.add(AddPic);
pics.setBackground(new Color(219, 180, 8));
//pics.setSize(200, 100);
jPanel1 = new JPanel(new java.awt.GridLayout(10, 2));
jPanel1.setPreferredSize(new Dimension(400, 350));
jPanel1.add(empNo);
jPanel1.add(txtEmpNo);
jPanel1.add(Sname);
jPanel1.add(txtSname);
jPanel1.add(Fname);
jPanel1.add(txtFname);
jPanel1.add(Lname);
jPanel1.add(txtLname);
jPanel1.add(Gender);
jPanel1.add(cbogender);

jPanel1.add(DOB);
jPanel1.add(dob);
jPanel1.add(telephone);
jPanel1.add(txttelephone);
jPanel1.add(email);
jPanel1.add(txtemail);
jPanel1.add(address);
jPanel1.add(txtaddress);
jPanel1.add(Designation);
jPanel1.add(txtDesignation);
//jPanel1.setSize(300,350);
jPanel1.setBackground(new Color(201, 239, 252));

jPanel4 = new JPanel();
jPanel4.add(jButton1);
jPanel4.add(jButton2);
jPanel4.add(Clear);
jPanel4.setBackground(new Color(201, 239, 252));
jPanel3 = new JPanel();
jPanel3.add(jPanel1);
jPanel3.add(pics);
jPanel3.setBackground(new Color(201, 239, 252));
jPanel3.add(jPanel4);
add(jPanel3);
setSize(790, 550);
setResizable(false);
cbogender.addItem("Male");
cbogender.addItem("Female");
cbogender.setBackground(Color.WHITE);
setLocation((screen.width - 500) / 2, ((screen.height - 350) / 2));
try {
Statement s = DBConnection.getDBConnection().createStatement();
} catch (Exception excp) {
excp.printStackTrace();
}
generator();
txtSname.addKeyListener(new KeyAdapter() {

public void keyTyped(KeyEvent e) {
char c = e.getKeyChar();
if (!(Character.isLetter(c) ||
(c == KeyEvent.VK_BACK_SPACE) ||
(c == KeyEvent.VK_SPACE) ||
(c == KeyEvent.VK_DELETE))) {

getToolkit().beep();
JOptionPane.showMessageDialog(null, "This Field Only acept text", "Error",
JOptionPane.DEFAULT_OPTION);
e.consume();
}
}
});
txtFname.addKeyListener(new KeyAdapter() {

public void keyTyped(KeyEvent e) {
char c = e.getKeyChar();
if (!(Character.isLetter(c) ||
(c == KeyEvent.VK_BACK_SPACE) ||
(c == KeyEvent.VK_SPACE) ||
(c == KeyEvent.VK_DELETE))) {

getToolkit().beep();
JOptionPane.showMessageDialog(null, "This Field Only acept text", "Error",
JOptionPane.DEFAULT_OPTION);
e.consume();
}
}
});
txtLname.addKeyListener(new KeyAdapter() {

public void keyTyped(KeyEvent e) {
char c = e.getKeyChar();
if (!(Character.isLetter(c) ||
(c == KeyEvent.VK_BACK_SPACE) ||
(c == KeyEvent.VK_SPACE) ||
(c == KeyEvent.VK_DELETE))) {

getToolkit().beep();
JOptionPane.showMessageDialog(null, "This Field Only acept text", "Error",
JOptionPane.DEFAULT_OPTION);
e.consume();
}
}
});


txtDesignation.addKeyListener(new KeyAdapter() {

public void keyTyped(KeyEvent e) {
char c = e.getKeyChar();
if (!(Character.isLetter(c) ||
(c == KeyEvent.VK_BACK_SPACE) ||
(c == KeyEvent.VK_SPACE) ||
(c == KeyEvent.VK_DELETE))) {
txtDesignation.requestFocus();
getToolkit().beep();
JOptionPane.showMessageDialog(null, "This Field Only acept text", "Error",
JOptionPane.DEFAULT_OPTION);
e.consume();
}
}
});
txttelephone.addFocusListener(new FocusAdapter() {

public void focusLost(FocusEvent e) {
JTextField textField =
(JTextField) e.getSource();
String content = textField.getText();
if (content.length() != 0) {
try {
Integer.parseInt(content);
} catch (NumberFormatException nfe) {
getToolkit().beep();
JOptionPane.showMessageDialog(null, "Invalid data entry", "Error",JOptionPane.DEFAULT_OPTION);
textField.requestFocus();
txttelephone.setText("");
}
}
}
});
jButton1.addActionListener(new ActionListener() {

public void actionPerformed(ActionEvent e) {
if (txtEmpNo.getText() == null ||
txtEmpNo.getText().equals("")) {
JOptionPane.showMessageDialog(null, "Enter Employee Number",
"Error", JOptionPane.DEFAULT_OPTION);
txtEmpNo.requestFocus();
return;
}

if (txtSname.getText() == null ||
txtSname.getText().equals("")) {
JOptionPane.showMessageDialog(null, "Enter Employee Surname ",
"Error", JOptionPane.DEFAULT_OPTION);
txtSname.requestFocus();
return;
}

if (txtFname.getText() == null ||
txtFname.getText().equals("")) {
JOptionPane.showMessageDialog(null, "Enetr Employee First Name",
"Error", JOptionPane.DEFAULT_OPTION);
txtFname.requestFocus();
return;
}

if (txtLname.getText() == null ||
txtLname.getText().equals("")) {
JOptionPane.showMessageDialog(null, "Enetr Employee Last Name",
"Error", JOptionPane.DEFAULT_OPTION);
txtLname.requestFocus();
return;
}
if (txttelephone.getText() == null ||
txttelephone.getText().equals("")) {
JOptionPane.showMessageDialog(null, "Enter telphone number",
"Error", JOptionPane.DEFAULT_OPTION);
txttelephone.requestFocus();
return;
}
if (txtemail.getText() == null ||
txtemail.getText().equals("")) {
JOptionPane.showMessageDialog(null, "Enter E-mail address",
"Error", JOptionPane.DEFAULT_OPTION);
txtemail.requestFocus();
return;
}
if (txtaddress.getText() == null ||
txtaddress.getText().equals("")) {
JOptionPane.showMessageDialog(null, "Enter Address","Error", JOptionPane.DEFAULT_OPTION);
txtaddress.requestFocus();
return;
}

if (txtDesignation.getText() == null ||
txtDesignation.getText().equals("")) {
JOptionPane.showMessageDialog(null, "Enter Employee designation","Error", JOptionPane.DEFAULT_OPTION);
txtDesignation.requestFocus();
return;
}

try {
Statement statement = DBConnection.getDBConnection().createStatement();
{
String temp = "INSERT INTO Emp (EmpNo, Sname, Fname, Lname, Gender,DOB,Designation,Telephone,E_Mail,Address) VALUES ('" +
txtEmpNo.getText() + "', '" +
txtSname.getText() + "', '" +
txtFname.getText() + "', '" +
txtLname.getText() + "', '" +
cbogender.getSelectedItem() + "', '" +
dob.getText() + "', '" +
txtDesignation.getText() + "', '" +
txttelephone.getText() + "', '" +
txtemail.getText() + "', '" +
txtaddress.getText() + "')";
try {
lblEmplPic.setIcon(new ImageIcon("Employees/" + txtEmpNo.getText() + ".png"));
} catch (Exception p) {
}
int result = statement.executeUpdate(temp);
String ObjButtons[] = {"Yes", "No"};
int PromptResult = JOptionPane.showOptionDialog(null, "Record succesfully added.Do you want to add another?",
"tobiluoch", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
if (PromptResult == 0) {
generator();

txtSname.setText("");
txtFname.setText("");
txtLname.setText("");

txtDesignation.setText("");
txttelephone.setText("");
txtemail.setText("");

txtaddress.setText("");

} else {
new Employee().setVisible(true);
setVisible(false);

}
}

} catch (SQLException sqlex) {
sqlex.printStackTrace();


}
}
});

jButton2.addActionListener(new java.awt.event.ActionListener() {

public void actionPerformed(java.awt.event.ActionEvent e) {
setVisible(true);
dispose();
}
});
AddPic.addActionListener(new ActionListener() {

public void actionPerformed(ActionEvent e) {
openFile();
}
});
Clear.addActionListener(new java.awt.event.ActionListener() {

public void actionPerformed(java.awt.event.ActionEvent e) {

txtSname.setText("");
txtFname.setText("");
txtLname.setText("");
txtDesignation.setText("");
txttelephone.setText("");
txtemail.setText("");
txtaddress.setText("");

}
});

jPanel5 = new javax.swing.JPanel(new java.awt.BorderLayout());

jPanel5.add(jPanel3, BorderLayout.CENTER);
jPanel5.add(jPanel4, BorderLayout.SOUTH);
getContentPane().add(jPanel5);
pack();

}

private void generator() {

try {
ResultSet rst = DBConnection.getDBConnection().createStatement(
ResultSet.TYPE_SCROLL_INSENSITIVE,
ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT empNo FROM Emp");
txtEmpNo.setText("1000");
while (rst.next()) {
String s;
int number = rst.getInt(1);
number = number + 1;

s = "" + number;
txtEmpNo.setText(s);

}
} catch (Exception n) {
JOptionPane.showMessageDialog(null,"Error on operation");
}
}

private void openFile() {
int returnVal = fc.showOpenDialog(AddNewEntry.this);

if (returnVal == JFileChooser.APPROVE_OPTION) {
File dialog = fc.getSelectedFile();
getPicture = dialog.getPath();
lblEmplPic.setIcon(new ImageIcon(getPicture));
}
}
}