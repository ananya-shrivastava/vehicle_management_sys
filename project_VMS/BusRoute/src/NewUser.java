import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class NewUser extends JInternalFrame {

private JLabel lblUsername, lblPassword, lblConfirmMsg, lblName, lblCategory;
private JPasswordField txtPassword, txtCPassword;
private JTextField txtUsername, txtName;
private JButton btnSave, btnCancel;
private JComboBox cmbCategory;
Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

public NewUser() {
super("Adding New User", false, true, false, true);
this.setSize(450, 330);
this.setLocation((screen.width - 500) / 2, ((screen.height - 350) / 2));
this.setLayout(null);
this.setBackground(new Color(201, 239, 252));
lblName = new JLabel("Name");
lblCategory = new JLabel("Category");
lblUsername = new JLabel("Username");
lblPassword = new JLabel("Password");
lblConfirmMsg = new JLabel("Cnf-Pass");
txtName = new JTextField();
cmbCategory = new JComboBox();
txtUsername = new JTextField();
txtPassword = new JPasswordField();
txtCPassword = new JPasswordField();
btnSave = new JButton("Save", new ImageIcon(ClassLoader.getSystemResource("images/save.png")));
btnCancel = new JButton("Cancel", new ImageIcon(ClassLoader.getSystemResource("images/cancel.png")));
btnSave.setBackground(new Color(4, 1, 8));
btnSave.setForeground(Color.white);
btnSave.setFont(new Font("monospaced", Font.BOLD, 16));
btnCancel.setBackground(new Color(4, 1, 8));
btnCancel.setForeground(Color.white);
btnCancel.setFont(new Font("monospaced", Font.BOLD, 16));
cmbCategory.setBackground(Color.WHITE);

lblUsername.setFont(new Font("monospaced", Font.BOLD, 20));
lblConfirmMsg.setFont(new Font("monospaced", Font.BOLD, 20));
lblPassword.setFont(new Font("monospaced", Font.BOLD, 20));
lblCategory.setFont(new Font("monospaced", Font.BOLD, 20));
cmbCategory.setFont(new Font("monospaced", Font.BOLD, 20));
txtUsername.setFont(new Font("monospaced", Font.CENTER_BASELINE, 20));
txtPassword.setFont(new Font("monospaced", Font.CENTER_BASELINE, 20));
lblName.setFont(new Font("monospaced",Font.BOLD,20));
cmbCategory.addItem("Manager");
cmbCategory.addItem("Booking Clerk");
cmbCategory.addItem("Supervisor");

lblName.setBounds(30, 20, 150, 32);
this.add(lblName);
txtName.setBounds(150, 20, 260, 32);
this.add(txtName);
lblCategory.setBounds(30, 60, 100, 32);
this.add(lblCategory);
cmbCategory.setBounds(150, 60, 260, 32);
this.add(cmbCategory);
lblUsername.setBounds(30, 100, 100, 32);
this.add(lblUsername);
txtUsername.setBounds(150, 100, 260, 32);
this.add(txtUsername);
lblPassword.setBounds(30, 140, 100, 32);
this.add(lblPassword);
txtPassword.setBounds(150, 140, 260, 32);
this.add(txtPassword);
lblConfirmMsg.setBounds(30, 180, 110, 32);
this.add(lblConfirmMsg);
txtCPassword.setBounds(150, 180, 260, 32);
this.add(txtCPassword);
btnSave.setBounds(30, 250, 180, 33);
this.add(btnSave);
btnCancel.setBounds(220, 250, 180, 33);
this.add(btnCancel);

txtName.addKeyListener(new KeyAdapter() {

public void keyTyped(KeyEvent e) {
char c = e.getKeyChar();
if (!(Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE) ||
(c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_DELETE))) {
getToolkit().beep();
JOptionPane.showMessageDialog(null, "Invalid Character", "ERROR", JOptionPane.ERROR_MESSAGE);
e.consume();
}
}
});
txtUsername.addKeyListener(new KeyAdapter() {

public void keyTyped(KeyEvent e) {
char c = e.getKeyChar();
if (!(Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE) || (Character.isDigit(c)) || (c == KeyEvent.VK_DELETE))) {
getToolkit().beep();
JOptionPane.showMessageDialog(null, "Invalid Character", "ERROR", JOptionPane.ERROR_MESSAGE);
e.consume();
}
}
});
btnSave.addActionListener(new java.awt.event.ActionListener() {

public void actionPerformed(ActionEvent e) {
if (txtName.getText() == null || txtName.getText().equals("")) {
JOptionPane.showMessageDialog(null, "Enter Name", "Missing fields", JOptionPane.DEFAULT_OPTION);
txtName.requestFocus();
return;
}
if (txtUsername.getText() == null || txtUsername.getText().equals("")) {
JOptionPane.showMessageDialog(null, "Enter Username", "Missing fields", JOptionPane.DEFAULT_OPTION);
txtUsername.requestFocus();
return;
}
if (txtPassword.getText() == null || txtPassword.getText().equals("")) {
JOptionPane.showMessageDialog(null, "Enter Password", "Missing fields", JOptionPane.DEFAULT_OPTION);
txtPassword.requestFocus();
return;
}
if (txtCPassword.getText() == null || txtCPassword.getText().equals("")) {
JOptionPane.showMessageDialog(null, "Confirm your password", "Missing fields", JOptionPane.DEFAULT_OPTION);
txtCPassword.requestFocus();
return;
}
if (!txtPassword.getText().equals(txtCPassword.getText())) {
JOptionPane.showMessageDialog(null, "Passwords do not match.", "ERROR", JOptionPane.DEFAULT_OPTION);
txtCPassword.requestFocus();
return;
}
try {
Statement stmt = DBConnection.getDBConnection().createStatement();
String sql = "INSERT INTO users (Name,Category,username, password) VALUES ('" +
txtName.getText() + "', '" + cmbCategory.getSelectedItem() + "', '" +
txtUsername.getText() + "', '" + txtPassword.getText() + "')";
int result = stmt.executeUpdate(sql);
if (result > 0) {
JOptionPane.showMessageDialog(null, "User details is succesfully added", "SUCCESS", JOptionPane.DEFAULT_OPTION);
dispose();
}//if closed
} catch (Exception in) {
JOptionPane.showMessageDialog(null, "Error on database updation", "Updation failed", JOptionPane.ERROR_MESSAGE);
}//try catch closed
}
});

btnCancel.addActionListener(new ActionListener() {

public void actionPerformed(ActionEvent e) {
dispose();
}
});
}//constructor closed
}//class closed
