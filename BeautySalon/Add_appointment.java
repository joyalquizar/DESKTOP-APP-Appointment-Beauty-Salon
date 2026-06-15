/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package BeautySalon;


import com.toedter.calendar.JDateChooser;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import BeautySalon.DBConnection;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.github.lgooddatepicker.components.TimePicker;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.time.LocalTime;
import java.sql.Time;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;














/**
 *
 * @author effel joy alquizar
 */
public class Add_appointment extends javax.swing.JInternalFrame {

   

  
    private byte[] imageBytes = null;

   private DefaultTableModel tableModel;
    private int selectedId;

 

  
public void refreshAdd_appointmentFromDatabase() {
       
        loadAppointmentTable();
     
        
        // Re-query DB and reload table
 // Re-query DB and reload table
}

  


public Connection connectToDatabase() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Or "com.mysql.jdbc.Driver" for older versions
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/bpmsdb", "root", "" // adjust DB name/user/pass as needed
        );
        return con;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}


    /**
     * Creates new form Add_appointment
     */
    public Add_appointment() {
        initComponents();
        

          
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
    BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
    ui.setNorthPane(null);
 try {
        con = connectToDatabase(); // FIXED
    } catch (Exception ex) {
        Logger.getLogger(Add_appointment.class.getName()).log(Level.SEVERE, null, ex);
    }

    refreshAppointmentTable();
}

      
private byte[] getImageBytes(ImageIcon icon) {
    if (icon == null) return null;

    try {
        Image image = icon.getImage();
        BufferedImage bufferedImage = new BufferedImage(
                image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos); // You can also try "png"
        return baos.toByteArray();
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
}




        
        
// Service and time options (example)
  java.sql.Connection con;
    Statement st;
    PreparedStatement pst;
    Connection conn = DBConnection.getConnection();
    
public Time convertToSqlTimeFormat(String timeString) throws ParseException {
    // Normalize: remove spaces, make uppercase
    timeString = timeString.replaceAll("\\s+", "").toUpperCase(); // "1:30 am" → "1:30AM"

    SimpleDateFormat sdf = new SimpleDateFormat("h:mma", Locale.ENGLISH); // matching "1:30AM"
    sdf.setLenient(false);
    java.util.Date date = sdf.parse(timeString);
    return new java.sql.Time(date.getTime());
}



public void loadAdd_appointment() {
    String sql = "SELECT * FROM addappointment";

    try (
        Connection conn = getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        java.sql.ResultSet rs = pst.executeQuery()
    ) {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "Name", "Email", "Service", "Date", "Time", "Number", "Image"}, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int column) {
                return column == 7 ? ImageIcon.class : Object.class;
            }
        };

        while (rs.next()) {
            int id = rs.getInt("No");
            String name = rs.getString("Name");
            String email = rs.getString("Email");
            String service = rs.getString("Services");
            Date date = rs.getDate("Date");
            String time = rs.getString("Time");
            String number = rs.getString("Number");
            byte[] imageBytes = rs.getBytes("Image");

            ImageIcon icon = null;
            if (imageBytes != null) {
                Image img = Toolkit.getDefaultToolkit().createImage(imageBytes);
                Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                icon = new ImageIcon(scaledImg);
            }

            model.addRow(new Object[]{id, name, email, service, date, time, number, icon});
        }

        AppointmentTable.setModel(model);
        AppointmentTable.setRowHeight(90);

        // 👇 Set custom image renderer
        AppointmentTable.getColumnModel().getColumn(7).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                if (value instanceof ImageIcon) {
                    JLabel label = new JLabel((ImageIcon) value);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    return label;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Failed to load appointments: " + e.getMessage());
    }
}


   public void loadAppointmentTable() {
    DefaultTableModel model = (DefaultTableModel) AppointmentTable.getModel();
    model.setRowCount(0); // Clear existing rows

    String sql = "SELECT * FROM addappointment";

    try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); java.sql.ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            Object[] row = {
                rs.getInt("ID"), // Or the column index (e.g., rs.getInt(1))
                rs.getString("Name"),
                rs.getString("Email"),
                rs.getString("Services"),
                rs.getDate("Date"),
                rs.getTime("Time"),
                rs.getString("Number")
            };
            model.addRow(row);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Failed to load appointments: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

    /**
     *
     */
    


    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jtxtname = new javax.swing.JTextField();
        jtxtemail = new javax.swing.JTextField();
        jservices = new javax.swing.JComboBox<>();
        jdate = new com.toedter.calendar.JDateChooser();
        jtime = new com.github.lgooddatepicker.components.TimePicker();
        jtxtnumber = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4SAve = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1pic = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        AppointmentTable = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1280, 790));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 790));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));

        jLabel18.setText("APPOINTMENT");
        jLabel18.setFont(new java.awt.Font("Imprint MT Shadow", 1, 48)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 153));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 1290, -1));

        jPanel5.setBackground(new java.awt.Color(255, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 153)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 280, 1290, 40));

        jtxtname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102)));
        jtxtname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtnameActionPerformed(evt);
            }
        });
        jPanel3.add(jtxtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 247, 30));

        jtxtemail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102)));
        jPanel3.add(jtxtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 250, 30));

        jservices.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Facial ", "Facial Massage", "Charcol Facial", "Deluxe Pedicure", "Normal Menicure", "U-Shape Hair Cut", "Layer Haircut", "Rebonding", "Hair Botox", "Loreal Hair Color(Full)", "Foot Spa", "Massage", "Body Spa", "Nail Extension", "Eye Extension", "Ear Candling", " " }));
        jservices.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102)));
        jPanel3.add(jservices, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 260, 30));

        jdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 153)));
        jPanel3.add(jdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, 260, 30));

        jtime.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 153)));
        jPanel3.add(jtime, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 110, 260, 30));

        jtxtnumber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102)));
        jPanel3.add(jtxtnumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 180, 260, 30));

        jButton3.setText("DELETE");
        jButton3.setBackground(new java.awt.Color(204, 51, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 240, 220, 30));

        jButton4SAve.setText("SAVE");
        jButton4SAve.setBackground(new java.awt.Color(204, 0, 102));
        jButton4SAve.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4SAve.setForeground(new java.awt.Color(255, 255, 255));
        jButton4SAve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4SAveActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4SAve, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, 220, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("NAME : ");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("EMAIL :");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("SERVICES : ");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("DATE :");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("TIME :");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 90, -1, 10));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("NUMBER:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 160, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 153)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1pic.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 153)));
        jLabel1pic.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jLabel1pic, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 170, 120));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 90, 190, 140));

        jButton1.setText("BROWSE");
        jButton1.setBackground(new java.awt.Color(51, 51, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 240, -1, -1));

        AppointmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "NO.", "NAME", "EMAIL", "SERVICES", "DATE", "TIME", "NUMBER", "Image"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        AppointmentTable.setGridColor(new java.awt.Color(255, 153, 153));
        AppointmentTable.setShowGrid(true);
        AppointmentTable.setSurrendersFocusOnKeystroke(true);
        AppointmentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AppointmentTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(AppointmentTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1304, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1295, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 830));

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void formWindowOpened(java.awt.event.WindowEvent evt) {
    refreshAppointmentTable();
}
private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
    loadAppointmentTable();
    
}
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       int selectedRow = AppointmentTable.getSelectedRow();

if (selectedRow == -1) {
    JOptionPane.showMessageDialog(this, "Please select an item to delete.");
    return;
}

int response = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to delete the selected item?",
        "Confirm Delete",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.WARNING_MESSAGE
);

if (response == JOptionPane.OK_OPTION) {
    try {
        // Get the selected appointment ID from the table (assuming it's in the first column)
        int apointId = (int) AppointmentTable.getValueAt(selectedRow, 0);

        // Connect to database and delete the record
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bpmsdb", "root", "");
        String deleteQuery = "DELETE FROM addappointment WHERE id = ?";
        PreparedStatement pstmt = con.prepareStatement(deleteQuery);
        pstmt.setInt(1, apointId);

        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            // Remove from JTable
            DefaultTableModel model = (DefaultTableModel) AppointmentTable.getModel();
            model.removeRow(selectedRow);

            // Clear form fields (corrected methods for your components)
            jtxtname.setText("");
            jtxtemail.setText("");
            jservices.setSelectedIndex(-1); // Use this for JComboBox
            jdate.setDate(null); // Use null to clear JDateChooser
            jtime.setText(""); // Assuming jtime is a JTextField or JFormattedTextField
            jtxtnumber.setText("");
            jLabel1pic.setIcon(null);

            JOptionPane.showMessageDialog(this, "Item deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete item from database.");
        }

        pstmt.close();
        con.close();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
    }

}


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        try {
            File file = fileChooser.getSelectedFile();
            imageBytes = Files.readAllBytes(file.toPath());

            // Resize image based on jLabel1pic's dimensions
            ImageIcon originalIcon = new ImageIcon(imageBytes);
            Image scaledImage = originalIcon.getImage().getScaledInstance(
                jLabel1pic.getWidth(), jLabel1pic.getHeight(), Image.SCALE_SMOOTH
            );
            jLabel1pic.setIcon(new ImageIcon(scaledImage));
            jLabel1pic.setText("");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4SAveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4SAveActionPerformed
     String name = jtxtname.getText();
    String email = jtxtemail.getText();
    String service = (String) jservices.getSelectedItem();
    Date date = jdate.getDate();
    LocalTime localTime = jtime.getTime();
    String number = jtxtnumber.getText();

    if (name.isEmpty() || email.isEmpty() || service.isEmpty() || date == null || localTime == null || number.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill in all the fields", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    Time sqlTime = Time.valueOf(localTime);

    ImageIcon imageIcon = (ImageIcon) jLabel1pic.getIcon();
    byte[] image = getImageBytes(imageIcon);

    if (image == null) {
        JOptionPane.showMessageDialog(this, "Please select a valid image", "Image Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String sql = "INSERT INTO addappointment (Name, Email, Services, Date, Time, Number, Image) VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setString(1, name);
        pst.setString(2, email);
        pst.setString(3, service);
        pst.setDate(4, new java.sql.Date(date.getTime()));
        pst.setTime(5, sqlTime);
        pst.setString(6, number);
        pst.setBytes(7, image);

      int result = pst.executeUpdate();
if (result > 0) {
    JOptionPane.showMessageDialog(null, "Appointment saved successfully.");
    loadAppointmentTable();  // Reload JTable with new data

      
            // 🔁 Refresh full table from database
            refreshAppointmentTable();

            // 🧹 Clear the form
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save appointment", "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error saving appointment: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}
private void refreshAppointmentTable() {
    String sql = "SELECT * FROM addappointment";

    try (
        Connection conn = getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        java.sql.ResultSet rs = pst.executeQuery()
    ) {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "Name", "Email", "Service", "Date", "Time", "Number", "Image"}, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int column) {
                return column == 7 ? ImageIcon.class : Object.class;
            }
        };

        while (rs.next()) {
            int id = rs.getInt("ID");
            String name = rs.getString("Name");
            String email = rs.getString("Email");
            String service = rs.getString("Services");
            Date date = rs.getDate("Date");
            String time = rs.getString("Time");
            String number = rs.getString("Number");
            byte[] imageBytes = rs.getBytes("Image");

            ImageIcon icon = null;
            if (imageBytes != null) {
                Image img = Toolkit.getDefaultToolkit().createImage(imageBytes);
                Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                icon = new ImageIcon(scaledImg);
            }

            model.addRow(new Object[]{id, name, email, service, date, time, number, icon});
        }

        AppointmentTable.setModel(model);
        AppointmentTable.setRowHeight(100);

        // 🔁 Add image column renderer again
        AppointmentTable.getColumnModel().getColumn(7).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                if (value instanceof ImageIcon) {
                    JLabel label = new JLabel((ImageIcon) value);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    return label;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Failed to load data: " + e.getMessage());
    }
}




    class ImageRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = new JLabel();
            if (value != null) {
                String imagePath = (String) value;
                ImageIcon imageIcon = new ImageIcon(imagePath);
                Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Resize image
                label.setIcon(new ImageIcon(image));
            }
            label.setHorizontalAlignment(JLabel.CENTER);
            return label;
        }
    

    }//GEN-LAST:event_jButton4SAveActionPerformed

    private void clearForm() {
        jtxtname.setText("");
        jtxtemail.setText("");
        jservices.setSelectedIndex(0);
        jdate.setDate(null);
        jtime.setText("");
        jtxtnumber.setText("");
        jLabel1pic.setIcon(null);
        jLabel1pic.setText("No Image");
        imageBytes = null;
    }
    private void jtxtnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtnameActionPerformed

    private void AppointmentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AppointmentTableMouseClicked
   AppointmentTable.getSelectionModel().addListSelectionListener(e -> {
        int row = AppointmentTable.getSelectedRow();
  
    if (row != -1) {
        jtxtname.setText(AppointmentTable.getValueAt(row, 1).toString());
        jtxtemail.setText(AppointmentTable.getValueAt(row, 2).toString());
        jservices.setSelectedItem(AppointmentTable.getValueAt(row, 3).toString());
        jdate.setDate((Date) AppointmentTable.getValueAt(row, 4));
        jtime.setText(AppointmentTable.getValueAt(row, 5).toString());
        jtxtnumber.setText(AppointmentTable.getValueAt(row, 6).toString());

        Object iconObj = AppointmentTable.getValueAt(row, 7);
        if (iconObj instanceof ImageIcon) {
            ImageIcon originalIcon = (ImageIcon) iconObj;
            Image image = originalIcon.getImage().getScaledInstance(
                jLabel1pic.getWidth(), jLabel1pic.getHeight(), Image.SCALE_SMOOTH
            );
            jLabel1pic.setIcon(new ImageIcon(image));
            jLabel1pic.setText("");
        } else {
            jLabel1pic.setIcon(null);
            jLabel1pic.setText("No Image");
        }
    }
});
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Add_appointment().setVisible(true));
    
    }//GEN-LAST:event_AppointmentTableMouseClicked

   private Connection getConnection() {
        return DBConnection.getConnection();
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable AppointmentTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4SAve;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel1pic;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdate;
    private javax.swing.JComboBox<String> jservices;
    private com.github.lgooddatepicker.components.TimePicker jtime;
    private javax.swing.JTextField jtxtemail;
    private javax.swing.JTextField jtxtname;
    private javax.swing.JTextField jtxtnumber;
    // End of variables declaration//GEN-END:variables

   

    

 
  

  
}
