
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginScreen extends JFrame {

    private JLabel lblUsername,  lblPasswd,  lblCat,bg;
    public JTextField txtUser;
    private JPasswordField txtPasswd;
    private JButton btnLogin,  btnCancel,btnnew;
    private JComboBox cmbCat;
    private Connection con;
    private Image img;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    public LoginScreen() {
        super("System Login");
        this.getContentPane().setLayout(null);
        this.setSize(410, 350);
       
        this.setResizable(false);
        this.setLocation((screen.width - 500) / 2, ((screen.height - 350) / 2));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //bg=new JLabel(new ImageIcon("images\\se4.jpg"));
        //this.add(bg);
        //this.setBackground(Color.yellow);
        this.getContentPane().setBackground(new Color(243, 225, 193));
        lblUsername = new JLabel("Username");
        lblPasswd = new JLabel("Password");
        txtUser = new JTextField();
        txtPasswd = new JPasswordField();
        lblCat = new JLabel("Login As");
        cmbCat = new JComboBox();
        cmbCat.addItem("Manager");
        cmbCat.addItem("Supervisor");
        cmbCat.addItem("Booking Clerk");
         cmbCat.addItem("User");
         
        btnLogin = new JButton("Login", new ImageIcon(ClassLoader.getSystemResource("images\\Login.png")));
        btnLogin.setBackground(new Color(4, 1, 8));
        btnLogin.setForeground(Color.white);
        btnLogin.setFont(new Font("monospaced", Font.BOLD, 16));
        
        btnCancel = new JButton("Cancel", new ImageIcon(ClassLoader.getSystemResource("images\\Cancel.png")));
        btnCancel.setForeground(Color.white);
        btnCancel.setBackground(new Color(4, 1, 8));
        btnCancel.setFont(new Font("monospaced", Font.BOLD, 16));
        
        btnnew = new JButton("New user", new ImageIcon(ClassLoader.getSystemResource("images\\sign.png")));
        btnnew.setBackground(new Color(4, 1, 8));
        btnnew.setForeground(Color.white);
        btnnew.setFont(new Font("monospaced", Font.BOLD, 16));
        
        lblUsername.setBounds(40, 30, 100, 32);
        lblPasswd.setBounds(40, 75, 100, 32);
        lblCat.setBounds(40, 120, 100, 33);
        txtUser.setBounds(150, 30, 230, 31);
        txtPasswd.setBounds(150, 75, 230, 31);
        cmbCat.setBounds(150, 120, 230, 31);
        btnLogin.setBounds(50, 200, 150, 33);
        btnCancel.setBounds(210, 200, 150, 33);
        btnnew.setBounds(100, 250, 150, 33);
        cmbCat.setBackground(Color.WHITE);
        lblUsername.setFont(new Font("monospaced", Font.BOLD, 20));
        lblPasswd.setFont(new Font("monospaced", Font.BOLD, 20));
        lblCat.setFont(new Font("monospaced", Font.BOLD, 20));
        cmbCat.setFont(new Font("monospaced", Font.BOLD, 20));
        txtUser.setFont(new Font("monospaced", Font.CENTER_BASELINE, 20));
        txtPasswd.setFont(new Font("monospaced", Font.CENTER_BASELINE, 20));
        this.add(lblUsername);
        this.add(txtUser);
        this.add(lblPasswd);
        this.add(txtPasswd);
        this.add(btnLogin);
        this.add(btnCancel);
        this.add(lblCat);
        this.add(cmbCat);
        this.add(btnLogin);
        this.add(btnCancel);
        this.add(btnnew);
        
        ButtonListener listener = new ButtonListener();
        btnLogin.addActionListener(listener);
        btnCancel.addActionListener(listener);
        btnnew.addActionListener(listener);
        con = DBConnection.getDBConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(null, "Error on establishing database connection", "Error", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }//constructor closed

    public void login() {
        String username = txtUser.getText();
        String password = txtPasswd.getText();
        String SQL;
        String category = cmbCat.getSelectedItem().toString();
        SQL = "SELECT * FROM users WHERE username='" + username + "'  AND password='" +
                password + "'AND Category='" + category + "'";
        try {
            Statement stmt = con.createStatement();
            stmt.execute(SQL);
            ResultSet rs = stmt.getResultSet();
            boolean recordfound = rs.next();
            if (recordfound) {
                LoadMDIWindow();                
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "The system could not log you in.\n" +
                        " Please make sure your username and password are correct", "Login Failure", JOptionPane.INFORMATION_MESSAGE);
                txtUser.setText("");
                txtPasswd.setText("");
                txtUser.requestFocus();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error on login operation", "Login Error", JOptionPane.ERROR_MESSAGE);
        }//try catch closed
    }//Login() closed
        public void LoadMDIWindow() {
        if (cmbCat.getSelectedItem().equals("Manager")) {
            new MDIWindow().LoginManager();            
        } else if (cmbCat.getSelectedItem().equals("Supervisor")) {
            new MDIWindow().LoginSupervisor();
        } 
        else if(cmbCat.getSelectedItem().equals("User"))
        {
        new MDIWindow().LoginUser();
        }
        else {
            new MDIWindow().LoginClerk();
        }
    }//LoginValidity() closed
        
    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnLogin) {
                if (txtUser.getText() == null || txtUser.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter username", "Missing field", JOptionPane.DEFAULT_OPTION);
                    txtUser.requestFocus();
                    return;
                }
                if (txtPasswd.getText() == null || txtPasswd.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter password", "Missing field", JOptionPane.DEFAULT_OPTION);
                    txtPasswd.requestFocus();
                    return; 
                }
                login();
            } else if (e.getSource() == btnCancel) {
                System.exit(0);
            }

            else if(e.getSource() == btnnew)
            {
                //String s1;
                newlogin frm= new newlogin();
                setVisible(false);
            //desktop.add(frm);
            //frm.setVisible(true);
            }

//if else closed
        }//actionPerformed() closed
    }//ButtonListner class closed

}//LoginScreen class closed

