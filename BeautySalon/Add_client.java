/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package BeautySalon;

import static BeautySalon.DBConnection.getConnection;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author effel joy alquizar
 */
public class Add_client extends javax.swing.JInternalFrame {
    
    java.sql.Connection con;
    Statement st;
    PreparedStatement pst;
    /**
     * Creates new form Add_client
     */
    public Add_client() {
      
        initComponents();
        loadAdd_client();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
    BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
    ui.setNorthPane(null);
   

    }
    public void loadAdd_client() {
        String sql = "SELECT * FROM client";

        try (
            Connection conn = getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery()
        ) {
            DefaultTableModel model = new DefaultTableModel(
                new String[]{"CLIENTID", "NAME", "EMAIL", "NUMBER", "BIRTHDAY", "IMAGE"}, 0) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }

                @Override
                public Class<?> getColumnClass(int column) {
                    return column == 5 ? ImageIcon.class : Object.class;
                }
            };

            while (rs.next()) {
                int clientid = rs.getInt("CLIENTID");
                String name = rs.getString("NAME");
                String email = rs.getString("EMAIL");
                String number = rs.getString("NUMBER");
                Date birthday = rs.getDate("BIRTHDAY");         
                byte[] imageBytes = rs.getBytes("IMAGE");

                ImageIcon icon = null;
                if (imageBytes != null) {
                    Image img = Toolkit.getDefaultToolkit().createImage(imageBytes);
                    Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(scaledImg);
                }

                model.addRow(new Object[]{clientid, name, email,  number, birthday, icon});
            }

            ClientTable.setModel(model);
            ClientTable.setRowHeight(100);

            // 👇 Set custom image renderer
            ClientTable.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
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

    public void loadClientTable() {
        DefaultTableModel model = (DefaultTableModel) ClientTable.getModel();
        model.setRowCount(0); // Clear existing rows

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bpmsdb", "root", "");
            String sql = "SELECT clientid, Name, Email, Number, Birthday FROM client";
            Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getString("CLIENTID"));
                row.add(rs.getString("NAME"));
                row.add(rs.getString("EMAIL"));
                row.add(rs.getString("NUMBER"));
                row.add(rs.getDate("BIRTHDAY"));
                model.addRow(row);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading client data.");
        }
    }
private String selectedImagePath;

private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
    loadAdd_client(); // 👈 Triggers every time the frame is opened
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtClientID = new javax.swing.JTextField();
        txtNumber = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtBirthday = new com.toedter.calendar.JDateChooser();
        btnSave = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ClientTable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1280, 790));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 790));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 153), 2));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 976, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 980, 30));

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));

        jLabel18.setFont(new java.awt.Font("Imprint MT Shadow", 1, 48)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 153));
        jLabel18.setText("CLIENT");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 967, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel18))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 60));

        txtClientID.setForeground(new java.awt.Color(153, 0, 102));
        txtClientID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel2.add(txtClientID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 90, 30));

        txtNumber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel2.add(txtNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, 140, 30));

        txtName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel2.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 170, 30));

        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        jPanel2.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 170, 30));

        txtBirthday.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel2.add(txtBirthday, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 130, 150, 30));

        btnSave.setBackground(new java.awt.Color(153, 0, 153));
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("SAVE");
        btnSave.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel2.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 230, 30));

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
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 190, 230, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("CLIENT ID :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("NAME :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("EMAIL :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("NUMBER :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 110, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("BIRTHDAY :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 110, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 980, 280));

        ClientTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        ClientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "CLIENTID", "NAME", "EMAIL", "NUMBER", "BIRTHDAY", "IMAGE"
            }
        ));
        ClientTable.setGridColor(new java.awt.Color(255, 153, 153));
        ClientTable.setInheritsPopupMenu(true);
        ClientTable.setSelectionBackground(new java.awt.Color(204, 204, 204));
        ClientTable.setSelectionForeground(new java.awt.Color(51, 51, 51));
        ClientTable.setShowGrid(true);
        ClientTable.setSurrendersFocusOnKeystroke(true);
        ClientTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClientTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ClientTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 1280, 490));

        jPanel5.setBackground(new java.awt.Color(255, 238, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(204, 0, 153));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImage.setBackground(new java.awt.Color(255, 255, 255));
        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102)));
        lblImage.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblImage.setOpaque(true);
        jPanel6.add(lblImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 180));

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 22, 230, 200));

        jButton1.setBackground(new java.awt.Color(0, 51, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("BROWSE");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 233, 141, 31));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 270, 280));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Select Image");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg");
    fileChooser.setFileFilter(filter);

    int result = fileChooser.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        selectedImagePath = selectedFile.getAbsolutePath();

        ImageIcon icon = new ImageIcon(new ImageIcon(selectedImagePath).getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH));
        lblImage.setIcon(icon);
    }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
   
     String clientId = txtClientID.getText();
    String name = txtName.getText();
    String email = txtEmail.getText();
    String number = txtNumber.getText();
    Date birthday = txtBirthday.getDate(); // JDateChooser component

    if (clientId.isEmpty() || name.isEmpty() || email.isEmpty() || number.isEmpty() || birthday == null || selectedImagePath == null) {
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
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bpmsdb", "root", "");
        String sql = "INSERT INTO client (clientid, Name, Email, Number, Birthday, Image) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, clientId);
        pst.setString(2, name);
        pst.setString(3, email);
        pst.setString(4, number);
        pst.setDate(5, new java.sql.Date(birthday.getTime()));
        pst.setBytes(6, imageBytes);  // ✅ Correct way to insert image

        pst.executeUpdate();

        JOptionPane.showMessageDialog(this, "Client saved successfully.");

        // ✅ Update your client table (or appointment table)
        loadAdd_client(); // ✅ loads full data including image // or refreshClientTable()

refreshClientTable();
        pst.close();
        conn.close();
        clearForm();
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error saving client: " + e.getMessage());
    }
}

    

private void refreshClientTable() {
    String sql = "SELECT * FROM client";

    try (
        Connection conn = getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        java.sql.ResultSet rs = pst.executeQuery()
    ) {
        DefaultTableModel model = new DefaultTableModel(
           new String[]{"CLIENTID", "NAME", "EMAIL", "NUMBER", "BIRTHDAY", "IMAGE"}, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int column) {
                return column == 5 ? ImageIcon.class : Object.class;
            }
        };

        while (rs.next()) {
            int clientid = rs.getInt("CLIENTID");
            String name = rs.getString("NAME");
            String email = rs.getString("EMAIL");
            String number = rs.getString("NUMBER");
            Date birthday = rs.getDate("BIRTHDAY");         
            byte[] imageBytes = rs.getBytes("IMAGE");

            ImageIcon icon = null;
            if (imageBytes != null) {
                Image img = Toolkit.getDefaultToolkit().createImage(imageBytes);
                Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                icon = new ImageIcon(scaledImg);
            }

            model.addRow(new Object[]{clientid, name, email,  number, birthday, icon});
        }

        ClientTable.setModel(model);
        ClientTable.setRowHeight(100);

        // 👇 Set custom image renderer
        ClientTable.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
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
private byte[] getImageBytes(File file) {
    try {
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        while ((read = fis.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        fis.close();
        return baos.toByteArray();
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }


    }//GEN-LAST:event_btnSaveActionPerformed

    private void ClientTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ClientTableMouseClicked
       int row = ClientTable.getSelectedRow();
    if (row != -1) {
        // Assuming column 0 = CLIENTID, 1 = NAME, 2 = EMAIL, 3 = NUMBER, 4 = BIRTHDAY, 5 = IMAGE
        txtClientID.setText(ClientTable.getValueAt(row, 0).toString());
        txtName.setText(ClientTable.getValueAt(row, 1).toString());
        txtEmail.setText(ClientTable.getValueAt(row, 2).toString());
        txtNumber.setText(ClientTable.getValueAt(row, 3).toString());

        Object birthdayObj = ClientTable.getValueAt(row, 4);
        if (birthdayObj instanceof Date) {
            txtBirthday.setDate((Date) birthdayObj);
        }

        Object iconObj = ClientTable.getValueAt(row, 5);
        if (iconObj instanceof ImageIcon) {
            ImageIcon icon = (ImageIcon) iconObj;
            
            // Resize the image to fit the label size (assuming label size is fixed, e.g., 150x150)
            Image image = icon.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(image);
            
            lblImage.setIcon(resizedIcon);
            lblImage.setText(""); // Clear label text if previously used
        } else {
            lblImage.setIcon(null);
            lblImage.setText("No Image");
        }
    }
}
    public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new Add_appointment().setVisible(true));
    }//GEN-LAST:event_ClientTableMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int selectedRow = ClientTable.getSelectedRow();

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
        int clientId = (int) ClientTable.getValueAt(selectedRow, 0);

        // Connect to database and delete the record
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bpmsdb", "root", "");
        String deleteQuery = "DELETE FROM client WHERE clientid = ?";
        PreparedStatement pstmt = con.prepareStatement(deleteQuery);
        pstmt.setInt(1, clientId );

        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            // Remove from JTable
            DefaultTableModel model = (DefaultTableModel) ClientTable.getModel();
            model.removeRow(selectedRow);

            // Clear form fields (corrected methods for your components)
            txtClientID.setText("");
            txtName.setText("");
            txtEmail.setText("");
            txtNumber.setText("");
            txtBirthday.setDate(null); // Use null to clear JDateChooser
           
            lblImage.setIcon(null);

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

    }//GEN-LAST:event_jButton4ActionPerformed
private void clearForm() {
    txtClientID.setText("");
    txtName.setText("");
    txtEmail.setText("");
    txtNumber.setText("");
    txtBirthday.setDate(null); // for JDateChooser
    lblImage.setIcon(null); // clear the image preview
    selectedImagePath = null; // reset the selected image path
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ClientTable;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImage;
    private com.toedter.calendar.JDateChooser txtBirthday;
    private javax.swing.JTextField txtClientID;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNumber;
    // End of variables declaration//GEN-END:variables

    private byte[] getImageBytes(ImageIcon imageIcon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  

        
    }

