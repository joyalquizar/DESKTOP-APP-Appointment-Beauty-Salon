/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package BeautySalon;

import static BeautySalon.DBConnection.getConnection;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author effel joy alquizar
 */
public class Add_specialist extends javax.swing.JInternalFrame {
private String selectedImagePath;


    /**
     * Creates new form Add_specialist
     */
    java.sql.Connection con;
    Statement st;
    PreparedStatement pst;
    
    
    public Add_specialist() {
        initComponents();
        loadAdd_specialist();
        
         this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
    BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
    ui.setNorthPane(null);
    }
public void loadAdd_specialist() {
    String sql = "SELECT id, Username, Email, Number, Birthday, Gender, Position, Description, Image FROM admin";


    try (
        java.sql.Connection conn = getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        java.sql.ResultSet rs = pst.executeQuery()
    ) {
        DefaultTableModel model = new DefaultTableModel(
    new String[]{"ID","USERNAME", "EMAIL", "NUMBER", "BIRTHDAY", "GENDER", "POSITION", "DESCRIPTION", "IMAGE"}, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int column) {
                return column == 8 ? ImageIcon.class : Object.class;
            }
        };

       
        while (rs.next()) {
    int id = rs.getInt("id");
    String username = rs.getString("USERNAME");
    String email = rs.getString("EMAIL");
    String number = rs.getString("NUMBER");
    Date birthday = rs.getDate("BIRTHDAY");
    String gender = rs.getString("GENDER");
    String position = rs.getString("POSITION");
    String description = rs.getString("DESCRIPTION");
    byte[] imageBytes = rs.getBytes("IMAGE");

    ImageIcon icon = null;
    if (imageBytes != null) {
        Image img = Toolkit.getDefaultToolkit().createImage(imageBytes);
        Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImg);
    }

    model.addRow(new Object[]{id, username, email, number, birthday, gender, position, description, icon});
}


        SpecialistTable.setModel(model);
        SpecialistTable.setRowHeight(100);

        // 👇 Set custom image renderer
        SpecialistTable.getColumnModel().getColumn(8).setCellRenderer(new DefaultTableCellRenderer() {
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
        JOptionPane.showMessageDialog(null, "Failed to load specialists: " + e.getMessage());
    }
}
public class DBConnection {
    private java.sql.Connection conn;

    public DBConnection(java.sql.Connection conn) {
        this.conn = conn;
    }

    public java.sql.Connection getSqlConnection() {
        return conn;
    }
}
public void loadSpecialistTable() {
    DefaultTableModel model = (DefaultTableModel) SpecialistTable.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bpmsdb", "root", "");
        String sql = "SELECT id, Username, Email, Number, Birthday, Gender, Position, Description , Image FROM admin";

        Statement stmt = conn.createStatement();
        java.sql.ResultSet rs = stmt.executeQuery(sql);

        int no = 1;
        while (rs.next()) {
    Vector<Object> row = new Vector<>();
    row.add(rs.getInt("id")); // column 0: ID (for internal use)
    row.add(no++);            // column 1: Display Row #
    row.add(rs.getString("Username"));
    row.add(rs.getString("Email"));
    row.add(rs.getString("Number"));
    row.add(rs.getDate("Birthday"));
    row.add(rs.getString("Gender"));
    row.add(rs.getString("Position"));
    row.add(rs.getString("Description"));
    row.add(rs.getBytes("Image")); // use getBytes if saving image BLOB
    model.addRow(row);
}



        rs.close();
        stmt.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading specialist data.");
    }
}

   
private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
    loadAdd_specialist();
    // 👈 Triggers every time the frame is opened
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jimage = new javax.swing.JPanel();
        imagee = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jTextname = new javax.swing.JTextField();
        jTextemail = new javax.swing.JTextField();
        jTextnumber = new javax.swing.JTextField();
        jTextdescription = new javax.swing.JTextField();
        jComboposition = new javax.swing.JComboBox<>();
        jCombogender = new javax.swing.JComboBox<>();
        date = new com.toedter.calendar.JDateChooser();
        jButton2saveee = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        SpecialistTable = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1526, 871));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(1526, 871));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(253, 238, 253));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jimage.setBackground(new java.awt.Color(153, 0, 102));
        jimage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jimage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imagee.setBackground(new java.awt.Color(255, 255, 255));
        imagee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102)));
        imagee.setOpaque(true);
        jimage.add(imagee, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 260, 210));

        jPanel3.add(jimage, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 23, 280, 230));

        jButton1.setBackground(new java.awt.Color(51, 51, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("BROWSE");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 271, 146, 35));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 204, 204));

        jLabel18.setFont(new java.awt.Font("Imprint MT Shadow", 1, 48)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 153));
        jLabel18.setText("STYLIST EMPLOYEE");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(263, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jLabel18))
        );

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 950, -1));

        jPanel6.setBackground(new java.awt.Color(255, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 960, 30));

        jTextname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 153), 2));
        jPanel4.add(jTextname, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 112, 170, 30));

        jTextemail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel4.add(jTextemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 170, 30));

        jTextnumber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel4.add(jTextnumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 112, 180, 30));

        jTextdescription.setForeground(new java.awt.Color(51, 51, 51));
        jTextdescription.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextdescription.setText("description..");
        jTextdescription.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 153), 2));
        jPanel4.add(jTextdescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 120, 180, 100));

        jComboposition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HAIRSTYLIST", "NAIL TECHNICIANS", "MASSAGER", "MAKE-UP ARTIST", "RECEPTIONIST", " ", " " }));
        jComboposition.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jComboposition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCombopositionActionPerformed(evt);
            }
        });
        jPanel4.add(jComboposition, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 190, 180, 30));

        jCombogender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MALE", "FEMALE", "TRANSGENDER", " " }));
        jCombogender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel4.add(jCombogender, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 112, 180, 30));

        date.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel4.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 180, 30));

        jButton2saveee.setBackground(new java.awt.Color(153, 0, 102));
        jButton2saveee.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2saveee.setForeground(new java.awt.Color(255, 255, 255));
        jButton2saveee.setText("SAVE");
        jButton2saveee.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2saveee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2saveeeActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2saveee, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 230, 40));

        jButton4.setBackground(new java.awt.Color(153, 0, 0));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("DELETE");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 242, 230, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("NAME :");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("EMAIL :");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("BIRTHDAY :");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("NUMBER : ");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("GENDER :");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("POSITION :");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("DESCRIPTION :");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 100, -1, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 958, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(227, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1520, 330));

        SpecialistTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        SpecialistTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "NO.", "USERNAME", "EMAIL", "NUMBER", "BIRTHDAY", "GENDER", "POSITION", "DESCRIPTION", "IMAGE"
            }
        ));
        SpecialistTable.setGridColor(new java.awt.Color(255, 204, 204));
        SpecialistTable.setShowGrid(true);
        SpecialistTable.setSurrendersFocusOnKeystroke(true);
        SpecialistTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SpecialistTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(SpecialistTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 1290, 460));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 1150));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCombopositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCombopositionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombopositionActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Select Image");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg");
    fileChooser.setFileFilter(filter);

    int result = fileChooser.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        selectedImagePath = selectedFile.getAbsolutePath();

        ImageIcon icon = new ImageIcon(
            new ImageIcon(selectedImagePath).getImage().getScaledInstance(imagee.getWidth(), imagee.getHeight(), Image.SCALE_SMOOTH)
        );
        imagee.setIcon(icon);
    }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2saveeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2saveeeActionPerformed
       
    String username = jTextname.getText();
String email = jTextemail.getText();
String number = jTextnumber.getText();
Date birthday = date.getDate();
String gender = jCombogender.getSelectedItem().toString();
String position = jComboposition.getSelectedItem().toString();
String description = jTextdescription.getText();

if (username.isEmpty() || email.isEmpty() || number.isEmpty() || birthday == null || gender.isEmpty() || selectedImagePath == null) {
    JOptionPane.showMessageDialog(this, "Please complete all fields and choose an image.");
    return;
}

// Convert image to byte array
byte[] imageBytes = getImageBytes(new File(selectedImagePath));

if (imageBytes == null) {
    JOptionPane.showMessageDialog(this, "Error reading image. Please select a valid image.", "Image Error", JOptionPane.ERROR_MESSAGE);
    return;
}

try {
    java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bpmsdb", "root", "");

    // ✅ Removed clientid (assuming AUTO_INCREMENT)
    String sql = "INSERT INTO admin (Username, Email, Number, Birthday, Gender, Position, Description, Image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement pst = conn.prepareStatement(sql);

    pst.setString(1, username);
    pst.setString(2, email);
    pst.setString(3, number);
    pst.setDate(4, new java.sql.Date(birthday.getTime()));
    pst.setString(5, gender);
    pst.setString(6, position);
    pst.setString(7, description);
    pst.setBytes(8, imageBytes);

    pst.executeUpdate();
    JOptionPane.showMessageDialog(this, "Info's saved successfully.");

    // ✅ Refresh the table
    refreshSpecialistTable(); // or loadAdd_specialist()

    pst.close();
    conn.close();
    clearForm();
} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Error saving client: " + e.getMessage());
}
    }
private byte[] getImageBytes(File imageFile) {
    try {
        return Files.readAllBytes(imageFile.toPath());
    } catch (IOException ex) {
        ex.printStackTrace();
        return null;
    }
}

public void refreshSpecialistTable() {
    String sql = "SELECT * FROM admin";

    try (
        java.sql.Connection conn = getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        java.sql.ResultSet rs = pst.executeQuery()
    ) {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID","USERNAME", "EMAIL", "NUMBER", "BIRTHDAY", "GENDER", "POSITION", "DESCRIPTION", "IMAGE"}, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int column) {
                return column == 8 ? ImageIcon.class : Object.class;
            }
        };

        while (rs.next()) {
            int id = rs.getInt("id"); // ✅ FIXED: was "NO" 
            String username = rs.getString("USERNAME");
            String email = rs.getString("EMAIL");
            String number = rs.getString("NUMBER");
            Date birthday = rs.getDate("BIRTHDAY");
            String gender = rs.getString("GENDER");
            String position = rs.getString("POSITION");
            String description = rs.getString("DESCRIPTION");

            byte[] imageBytes = rs.getBytes("IMAGE");

            ImageIcon icon = null;
            if (imageBytes != null) {
                Image img = Toolkit.getDefaultToolkit().createImage(imageBytes);
                Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                icon = new ImageIcon(scaledImg);
            }

            model.addRow(new Object[]{id,username, email, number, birthday, gender, position, description, icon});
        }

        SpecialistTable.setModel(model);
        SpecialistTable.setRowHeight(100);

        // 👇 Set custom image renderer
        SpecialistTable.getColumnModel().getColumn(8).setCellRenderer(new DefaultTableCellRenderer() {
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
        JOptionPane.showMessageDialog(null, "Failed to load specialists: " + e.getMessage());
    }

    

    }//GEN-LAST:event_jButton2saveeeActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
            int selectedRow = SpecialistTable.getSelectedRow();

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
            int specialistId = (int) SpecialistTable.getValueAt(selectedRow, 0);


            // Connect to database and delete the record
           // Connect to database and delete the record
java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bpmsdb", "root", "");
String deleteQuery = "DELETE FROM admin WHERE id = ?";
PreparedStatement pstmt = conn.prepareStatement(deleteQuery); // <-- use conn here
pstmt.setInt(1, specialistId );

int rowsAffected = pstmt.executeUpdate();

if (rowsAffected > 0) {
    // Record deleted successfully

                // Remove from JTable
                DefaultTableModel model = (DefaultTableModel) SpecialistTable.getModel();
                model.removeRow(selectedRow);

                // Clear form fields (corrected methods for your components)
                jTextname.setText("");
                jTextemail.setText("");
                jTextnumber.setText("");
                date.setDate(null);
                jCombogender.setSelectedIndex(-1); // Use this for JComboBox
                jComboposition.setSelectedIndex(-1); // Use this for JComboBox
                date.setDate(null); // Use null to clear JDateChooser
                jTextdescription.setText(""); // Assuming jtime is a JTextField or JFormattedTextField

                imagee.setIcon(null);

                JOptionPane.showMessageDialog(this, "Item deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete item from database.");
            }

            pstmt.close();
            conn.close();

          } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Failed to load specialists: " + e.getMessage());
    }
    }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void SpecialistTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SpecialistTableMouseClicked
        int row = SpecialistTable.getSelectedRow();
        if (row != -1) {
            // Assuming column mapping:
            // 0 = CLIENTID, 1 = NAME, 2 = EMAIL, 3 = NUMBER, 4 = BIRTHDAY,
            // 5 = GENDER, 6 = POSITION, 7 = DESCRIPTION, 8 = IMAGE

            jTextname.setText(SpecialistTable.getValueAt(row, 1).toString());
            jTextemail.setText(SpecialistTable.getValueAt(row, 2).toString());
            jTextnumber.setText(SpecialistTable.getValueAt(row, 3).toString());

            Object birthdayObj = SpecialistTable.getValueAt(row, 4);
            if (birthdayObj instanceof Date) {
                date.setDate((Date) birthdayObj);
            }

            jCombogender.setSelectedItem(SpecialistTable.getValueAt(row, 5).toString());
            jComboposition.setSelectedItem(SpecialistTable.getValueAt(row, 6).toString());
            jTextdescription.setText(SpecialistTable.getValueAt(row, 7).toString());

            Object iconObj = SpecialistTable.getValueAt(row, 8);
            if (imagee != null && iconObj instanceof ImageIcon) {
                ImageIcon icon = (ImageIcon) iconObj;
                Image image = icon.getImage().getScaledInstance(imagee.getWidth(), imagee.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(image);
                imagee.setIcon(resizedIcon);
                imagee.setText("");
            } else {
                if (imagee != null) {
                    imagee.setIcon(null);
                    imagee.setText("");
                }
            }
        }

    }//GEN-LAST:event_SpecialistTableMouseClicked
private void clearForm() {
    jTextname.setText("");
    jTextemail.setText("");
    jTextnumber.setText("");
    date.setDate(null);
    jCombogender.setSelectedIndex(0);
    jComboposition.setSelectedIndex(0);
    jTextdescription.setText("");
    imagee.setIcon(null);
    imagee.setText("");
    selectedImagePath = null;
}

public ImageIcon resizeImage(byte[] imageBytes, JLabel label) {
    int width = label.getWidth();
    int height = label.getHeight();

    // Provide fallback dimensions if not laid out yet
    if (width <= 0) width = 150; // or any default width
    if (height <= 0) height = 150; // or any default height

    ImageIcon imageIcon = new ImageIcon(imageBytes);
    Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return new ImageIcon(image);
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable SpecialistTable;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JLabel imagee;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2saveee;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jCombogender;
    private javax.swing.JComboBox<String> jComboposition;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextdescription;
    private javax.swing.JTextField jTextemail;
    private javax.swing.JTextField jTextname;
    private javax.swing.JTextField jTextnumber;
    private javax.swing.JPanel jimage;
    // End of variables declaration//GEN-END:variables

   
}
