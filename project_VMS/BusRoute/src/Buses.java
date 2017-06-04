import java.awt.*;
import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

public class Buses extends JPanel implements Printable{

private JScrollPane jsp;
private static JTable taleEmpList;
private JButton btnAddEntry, btnRefresh, btnUpdate, btnClose, btnPrint;
Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
private Statement stmt;
JFrame JFParentFrame;
private JPanel tablePanel;
private JPanel buttonPanel;
//private JButton searchButton;
private static int rowCnt = 0;
private static int selectedRow;
private static JTextArea txtInfo = new JTextArea(15, 40);
private Connection dbconn;
private static String info;

public Buses() {
setSize(1000, 400);
setLayout(new BorderLayout());
taleEmpList = new JTable(new AbstractTable());
javax.swing.table.TableColumn column = null;
for (int i = 0; i < 8; i++) {
column = taleEmpList.getColumnModel().getColumn(i);
}
jsp = new JScrollPane(taleEmpList);
tablePanel = new JPanel(new BorderLayout());
tablePanel.add(jsp, BorderLayout.CENTER);

btnAddEntry = new JButton("Add Entry", new ImageIcon(ClassLoader.getSystemResource("Images/addnew.png")));
btnRefresh = new JButton("Refresh", new ImageIcon(ClassLoader.getSystemResource("Images/refresh.png")));
btnUpdate = new JButton("Update", new ImageIcon(ClassLoader.getSystemResource("Images/update.png")));
btnPrint = new JButton("Print", new ImageIcon(ClassLoader.getSystemResource("Images/print.png")));
btnClose = new JButton("Close", new ImageIcon(ClassLoader.getSystemResource("Images/exit.png")));

buttonPanel = new JPanel(new FlowLayout());
buttonPanel.add(btnAddEntry);
buttonPanel.add(btnUpdate);
buttonPanel.add(btnRefresh);
buttonPanel.add(btnPrint);
buttonPanel.add(btnClose);
add(tablePanel, BorderLayout.CENTER);
add(buttonPanel, BorderLayout.PAGE_END);
try {
stmt = DBConnection.getDBConnection().createStatement();
} catch (Exception excp) {
JOptionPane.showMessageDialog(null, "Error on database connection", "Statement error", JOptionPane.ERROR_MESSAGE);
}//try catch closed
load_taleEmpList();
// reloaded();
btnAddEntry.addActionListener(new ActionListener() {

public void actionPerformed(java.awt.event.ActionEvent e) {
//AddNewEntry frm=new AddNewEntry();
AddEntry frm = new AddEntry();
MDIWindow.desktop.add(frm);
frm.setVisible(true);
try{
frm.setSelected(true);
}catch(Exception ex){}
}
});

btnUpdate.addActionListener(new ActionListener() {

public void actionPerformed(java.awt.event.ActionEvent e) {
UpdateEntry frm = new UpdateEntry(taleEmpList.getValueAt(getSelectedRow(), 0).toString(),
taleEmpList.getValueAt(getSelectedRow(), 1).toString(),
taleEmpList.getValueAt(getSelectedRow(), 2).toString(),
taleEmpList.getValueAt(getSelectedRow(), 3).toString(),
taleEmpList.getValueAt(getSelectedRow(), 4).toString(),
taleEmpList.getValueAt(getSelectedRow(), 5).toString(),
taleEmpList.getValueAt(getSelectedRow(), 6).toString(),
taleEmpList.getValueAt(getSelectedRow(), 7).toString());
// taleEmpList.getValueAt(getSelectedRow(), 8).toString(),
//taleEmpList.getValueAt(getSelectedRow(), 9).toString());
MDIWindow.desktop.add(frm);
frm.setVisible(true);
try{
frm.setSelected(true);
}catch(Exception ex){}
}
});
btnClose.addActionListener(new ActionListener() {

public void actionPerformed(ActionEvent e) {
setVisible(false);
}
});
btnPrint.addActionListener(new ActionListener() {

public void actionPerformed(ActionEvent evt) {
//employee_report frm = new employee_report();
Bus_Details frm = new Bus_Details();
MDIWindow.desktop.add(frm);
frm.setVisible(true);
try {
frm.setSelected(true);
} catch (Exception ex) {
}
}
});
btnRefresh.addActionListener(new ActionListener() {

public void actionPerformed(java.awt.event.ActionEvent e) {
load_taleEmpList();
// reloaded();
}
});
tablePanel.add(buttonPanel, java.awt.BorderLayout.SOUTH);
tablePanel.setPreferredSize(new java.awt.Dimension(750, 450));
tablePanel.add(buttonPanel, java.awt.BorderLayout.SOUTH);
tablePanel.setPreferredSize(new java.awt.Dimension(1000, 300));
tablePanel.setBackground(new java.awt.Color(200, 200, 200));
tablePanel.setBounds(2, 200, 770, 2);
add(tablePanel);
}

public static int getSelectedRow() {
taleEmpList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
javax.swing.ListSelectionModel rowSel = taleEmpList.getSelectionModel();
rowSel.addListSelectionListener(new ListSelectionListener() {

public void valueChanged(ListSelectionEvent e) {
if (e.getValueIsAdjusting()) {
return;
}
javax.swing.ListSelectionModel sel = (javax.swing.ListSelectionModel) e.getSource();
if (!sel.isSelectionEmpty()) {
selectedRow = sel.getMinSelectionIndex();
}
}
});
return selectedRow;
}

public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
Graphics2D g2 = (Graphics2D) g;
g2.setColor(Color.black);
int fontHeight = g2.getFontMetrics().getHeight();
int fontDesent = g2.getFontMetrics().getDescent();

//leave room for page number
double pageHeight = pageFormat.getImageableHeight() - fontHeight;
double pageWidth = pageFormat.getImageableWidth();
double tableWidth = (double) taleEmpList.getColumnModel().getTotalColumnWidth();
double scale = 1;
if (tableWidth >= pageWidth) {
scale = pageWidth / tableWidth;
}

double headerHeightOnPage =
taleEmpList.getTableHeader().getHeight() * scale;
double tableWidthOnPage = tableWidth * scale;

double oneRowHeight = ( taleEmpList.getRowHeight() +
taleEmpList.getRowMargin()) * scale;
int numRowsOnAPage =
(int) ((pageHeight - headerHeightOnPage) / oneRowHeight);
double pageHeightForTable = oneRowHeight * numRowsOnAPage;
int totalNumPages = (int) Math.ceil(((double) taleEmpList.getRowCount()) / numRowsOnAPage);
if (pageIndex >= totalNumPages) {
return NO_SUCH_PAGE;
}

g2.translate(pageFormat.getImageableX(),
pageFormat.getImageableY());
g2.drawString("Page: " + (pageIndex + 1), (int) pageWidth / 2 - 35,
(int) (pageHeight + fontHeight - fontDesent));//bottom center

g2.translate(0f, headerHeightOnPage);
g2.translate(0f, -pageIndex * pageHeightForTable);
if (pageIndex + 1 == totalNumPages) {
int lastRowPrinted = numRowsOnAPage * pageIndex;
int numRowsLeft = taleEmpList.getRowCount() - lastRowPrinted;
g2.setClip(0, (int) (pageHeightForTable * pageIndex),
(int) Math.ceil(tableWidthOnPage),
(int) Math.ceil(oneRowHeight * numRowsLeft));
} else {
g2.setClip(0, (int) (pageHeightForTable * pageIndex),
(int) Math.ceil(tableWidthOnPage),
(int) Math.ceil(pageHeightForTable));
}
g2.scale(scale, scale);
taleEmpList.paint(g2);
g2.scale(1 / scale, 1 / scale);
g2.translate(0f, pageIndex * pageHeightForTable);
g2.translate(0f, -headerHeightOnPage);
g2.setClip(0, 0, (int) Math.ceil(tableWidthOnPage),
(int) Math.ceil(headerHeightOnPage));
g2.scale(scale, scale);
taleEmpList.getTableHeader().paint(g2);//paint header at top

return Printable.PAGE_EXISTS;
}

class AbstractTable extends AbstractTableModel {

private String[] columnNames = {"RegNo", "BusNo", "Model", "Capacity",
"Date purchased", "Insurance Status", "Date Insured", "Expiry Date"
};
private Object[][] data = new Object[50][50];

public int getColumnCount() {
return columnNames.length;
}

public int getRowCount() {
return data.length;
}

public String getColumnName(int col) {
return columnNames[col];
}

public Object getValueAt(int row, int col) {
return data[row][col];
}

public void setValueAt(Object value, int row, int col) {
data[row][col] = value;
fireTableCellUpdated(row, col);
}
}//AbstrctTable Class closed
public void load_taleEmpList() {
try {
Statement statement = DBConnection.getDBConnection().createStatement();
String temp = ("SELECT * FROM Buses ORDER BY BusNo");
int Numrow = 0;
ResultSet result = statement.executeQuery(temp);
while (result.next()) {
taleEmpList.setValueAt(result.getString(1), Numrow, 0);
taleEmpList.setValueAt(result.getString(2), Numrow, 1);
taleEmpList.setValueAt(result.getString(3), Numrow, 2);
taleEmpList.setValueAt(result.getString(4), Numrow, 3);
taleEmpList.setValueAt(result.getString(5), Numrow, 4);
taleEmpList.setValueAt(result.getDate(6), Numrow, 5);
taleEmpList.setValueAt(result.getString(7), Numrow, 6);
taleEmpList.setValueAt(result.getString(8), Numrow, 7);
// taleEmpList.setValueAt(result.getString(9), Numrow, 8);
//taleEmpList.setValueAt(result.getString(10), Numrow, 9);
Numrow++;
}
} catch (SQLException sqlex) {
txtInfo.append(sqlex.toString());
}
}//load_taleEmpList() closed
/* public void reloaded() {
try {
Statement statement = DBConnection.getDBConnection().createStatement();
String sql = ("SELECT * FROM Buses ORDER BY BusNo");
int Numrow = 0;
ResultSet result = stmt.executeQuery(sql);
while (result.next()) {
taleEmpList.setValueAt(result.getString(1).trim(), Numrow, 0);
taleEmpList.setValueAt(result.getString(2).trim(), Numrow, 1);
taleEmpList.setValueAt(result.getString(3).trim(), Numrow, 2);
taleEmpList.setValueAt(result.getString(4).trim(), Numrow, 3);
taleEmpList.setValueAt(result.getDate(5), Numrow, 4);
taleEmpList.setValueAt(result.getString(6).trim(), Numrow, 5);
taleEmpList.setValueAt(result.getDate(7), Numrow, 6);
taleEmpList.setValueAt(result.getDate(8), Numrow, 7);
//sdf.format(7);
Numrow++;
}//while closed
} catch (SQLException sqlex) {
JOptionPane.showMessageDialog(null, "Error on retrieving values", "Error", JOptionPane.ERROR_MESSAGE);
}//try catch closed
}*/
}//class closed

