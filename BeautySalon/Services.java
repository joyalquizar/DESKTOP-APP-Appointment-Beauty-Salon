/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package BeautySalon;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.awt.Component;
import com.github.lgooddatepicker.components.TimePicker;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalTime;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


/**
 *
 * @author effel joy alquizar
 */
public class Services extends javax.swing.JInternalFrame {
  public void refreshTableServicess() {
    // your code to fetch from DB and update TablePages
    // this should be similar to refreshTablePages() in Add_services
}

   // pass reference

  java.sql.Connection con;
    Statement st;
    PreparedStatement pst;
  

    public Services() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        
        ui.setNorthPane(null);
        
            loadServices(); // Load services from the database

 // initialize your custom scrollable panel
    }

    // ✅ Setup method for custom scrollable area
    
 public void refreshServicesFromDatabase() {
    loadServices(); // Re-query DB and reload table
}
 /**
     * Creates new form Services
     */
public void loadServices() {
    try {
        DefaultTableModel model = (DefaultTableModel) TablePages.getModel();
        model.setRowCount(0); // Clear existing rows

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bpmsdb", "root", "");
        String sql = "SELECT * FROM tblservices";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();


        int count = 1;
while (rs.next()) {
    int id = rs.getInt("ID"); // Or use String.valueOf(...) if you need it as a String
    String ServiceName = rs.getString("ServiceName");
    String price = rs.getString("Cost");
    String desc = rs.getString("Description");
    byte[] imageBytes = rs.getBytes("Image");

    ImageIcon icon = new ImageIcon(new ImageIcon(imageBytes).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
          String formattedPrice =  "₱" + price  ;
model.addRow(new Object[]{count++, ServiceName, formattedPrice, desc, icon});

DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
TablePages.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
// Font and row height
TablePages.setFont(new Font("Segoe UI", Font.PLAIN, 14));
TablePages.setRowHeight(30);
JTableHeader header = TablePages.getTableHeader();
header.setFont(new Font("Segoe UI", Font.BOLD, 15));
header.setOpaque(true);
header.setBackground(new Color(204, 0, 153)); // Fuchsia-like color
header.setForeground(Color.BLACK);

// Column widths
TablePages.getColumnModel().getColumn(0).setPreferredWidth(50);   // No.
TablePages.getColumnModel().getColumn(1).setPreferredWidth(150);  // Service
TablePages.getColumnModel().getColumn(2).setPreferredWidth(80);   // Price
TablePages.getColumnModel().getColumn(3).setPreferredWidth(200);  // Description
TablePages.getColumnModel().getColumn(4).setPreferredWidth(90);   // Image




}

        rs.close();
        pst.close();
        con.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error loading services: " + e.getMessage());
    }

TablePages.setRowHeight(100);
TablePages.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof ImageIcon) {
            return new JLabel((ImageIcon) value);
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
});

TablePages.setRowHeight(100);
TablePages.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
    @Override
    public void setValue(Object value) {
        if (value instanceof ImageIcon) {
            setIcon((ImageIcon) value);
            setText("");
        } else {
            setIcon(null);
            super.setValue(value);
        }
    }
});
TablePages.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent evt) {
        int row = TablePages.rowAtPoint(evt.getPoint());
        if (row >= 0) {
            String serviceName = TablePages.getValueAt(row, 1).toString(); // assuming 1 is the name column
            String price = TablePages.getValueAt(row, 2).toString();
            String description = TablePages.getValueAt(row, 3).toString();

            // You can display details or open another window
            JOptionPane.showMessageDialog(null, "Your item selected:\n" + serviceName + "\n" + price + "\n" + description);
        }
    }
});
}
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNo = new javax.swing.JTextField();
        txtService = new javax.swing.JTextField();
        txtCost = new javax.swing.JTextField();
        txtAbout = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        btnSelect = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton5clear = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        searchField = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablePages = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        timePicker1 = new com.github.lgooddatepicker.components.TimePicker();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(1520, 870));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(250, 233, 250));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("NO :");
        jPanel10.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        txtNo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102)));
        txtNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoActionPerformed(evt);
            }
        });
        jPanel10.add(txtNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 80, 30));

        txtService.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtService.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtService.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102)));
        jPanel10.add(txtService, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 220, 30));

        txtCost.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCost.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCost.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102)));
        jPanel10.add(txtCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 220, 30));

        txtAbout.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAbout.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102)));
        txtAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAboutActionPerformed(evt);
            }
        });
        jPanel10.add(txtAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 220, 50));

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102)));

        lblImage.setBackground(new java.awt.Color(245, 214, 232));
        lblImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        lblImage.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, 210));

        btnSelect.setText("SELECT");
        btnSelect.setBackground(new java.awt.Color(153, 0, 102));
        btnSelect.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSelect.setForeground(new java.awt.Color(255, 255, 255));
        btnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectActionPerformed(evt);
            }
        });
        jPanel10.add(btnSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 440, 100, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("SERVICE :");
        jPanel10.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("COST :");
        jPanel10.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("ABOUT :");
        jPanel10.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, -1));

        jButton5clear.setText("CLEAR");
        jButton5clear.setBackground(new java.awt.Color(204, 0, 0));
        jButton5clear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5clear.setForeground(new java.awt.Color(255, 255, 255));
        jButton5clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5clearActionPerformed(evt);
            }
        });
        jPanel10.add(jButton5clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 440, -1, 30));

        jPanel6.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 320, 480));

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));

        searchField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton9.setText("SEARCH");
        jButton9.setBackground(new java.awt.Color(153, 0, 102));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel18.setText("OUR SERVICES");
        jLabel18.setFont(new java.awt.Font("Imprint MT Shadow", 1, 48)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 153));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel18)
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        jPanel6.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 920, 90));

        jPanel2.setBackground(new java.awt.Color(255, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));

        TablePages.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        TablePages.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No.", "Service Name ", "Cost", "Description", "Image"
            }
        ));
        TablePages.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TablePages.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        TablePages.setGridColor(new java.awt.Color(255, 0, 153));
        TablePages.setShowGrid(true);
        TablePages.setSurrendersFocusOnKeystroke(true);
        TablePages.setToolTipText("");
        TablePages.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablePagesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablePages);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 916, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 920, 610));

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));

        jButton6.setText("NEW BOOK");
        jButton6.setBackground(new java.awt.Color(153, 51, 0));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton8.setText("DELETE");
        jButton8.setBackground(new java.awt.Color(204, 0, 0));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(618, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel6.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 710, 920, 70));

        jPanel5.setBackground(new java.awt.Color(250, 241, 245));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 2));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("BOOK NOW");
        jLabel2.setFont(new java.awt.Font("Perpetua Titling MT", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 51, 0));
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 130, -1));

        jLabel3.setText("DATE :");
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 54, -1));

        jLabel4.setText("STYLIST :");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jSpinner1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102)));
        jSpinner1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel5.add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 210, 30));

        jDateChooser1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102)));
        jDateChooser1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel5.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 210, 30));

        timePicker1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102)));
        timePicker1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel5.add(timePicker1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 210, 30));

        jButton3.setText("CLEAR");
        jButton3.setBackground(new java.awt.Color(204, 0, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 80, 30));

        jButton4.setText("SEE TOTAL");
        jButton4.setBackground(new java.awt.Color(51, 0, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 100, 30));

        jLabel5.setText("TIME :");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel6.setText("QUANTITY :");
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Co. Alquizar, Effel Joy ", "Co. Dejarlo, Daisy", "Co. Gacusan, Angela", "Co. Joseph ", "Co. Odtojan, Meljean", "Co. Villena, Nicole" }));
        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel5.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 210, 30));

        jPanel6.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 320, 280));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1360, 830));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAboutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAboutActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        String search = searchField.getText(); // replace with your actual search field
    DefaultTableModel model = (DefaultTableModel) TablePages.getModel();
    model.setRowCount(0); // clear previous results

    String query = "SELECT * FROM tblservices WHERE id LIKE ? OR ServiceName LIKE ?";
    
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bpmsdb", "root", "");
         PreparedStatement pst = con.prepareStatement(query)) {

        pst.setString(1, "%" + search + "%");
        pst.setString(2, "%" + search + "%");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String serviceName = rs.getString("ServiceName");
            String cost = rs.getString("Cost");
            String desc = rs.getString("Description");

            // For image (stored as BLOB)
            byte[] imgBytes = rs.getBytes("Image");
            ImageIcon icon = null;
            if (imgBytes != null) {
                ImageIcon originalIcon = new ImageIcon(imgBytes);
                Image scaledImg = originalIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                icon = new ImageIcon(scaledImg);
            }

            model.addRow(new Object[]{id, serviceName, cost, desc, icon});
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Search failed: " + ex.getMessage());
    }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void txtNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoActionPerformed

    private void TablePagesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablePagesMouseClicked
        int selectedRow = TablePages.getSelectedRow();
if (selectedRow >= 0) {
  
    String no = TablePages.getValueAt(selectedRow, 0).toString();
    String service = TablePages.getValueAt(selectedRow, 1).toString();
    String cost = TablePages.getValueAt(selectedRow, 2).toString();
    String about = TablePages.getValueAt(selectedRow, 3).toString();

  
    txtNo.setText(no);
    txtService.setText(service);
    txtCost.setText(cost);
    txtAbout.setText(about);


    Object imageObject = TablePages.getValueAt(selectedRow, 4);
    if (imageObject instanceof ImageIcon) {
        ImageIcon icon = (ImageIcon) imageObject;
        Image img = icon.getImage();
        int width = lblImage.getWidth() > 0 ? lblImage.getWidth() : 120;
        int height = lblImage.getHeight() > 0 ? lblImage.getHeight() : 120;

        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage.setVerticalAlignment(SwingConstants.CENTER);
        lblImage.setIcon(scaledIcon);
        lblImage.setText(""); 
    } else {
        lblImage.setIcon(null);
        lblImage.setText("No Image");
    }
}

    

    }//GEN-LAST:event_TablePagesMouseClicked

    private void jButton5clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5clearActionPerformed
        int response = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to clear the items?",
        "Confirm Clear",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.WARNING_MESSAGE
);

if (response == JOptionPane.OK_OPTION) {
    txtNo.setText("");
    txtService.setText("");
    txtCost.setText("");
    txtAbout.setText("");
    lblImage.setIcon(null);
}

    
    }//GEN-LAST:event_jButton5clearActionPerformed

    private void btnSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectActionPerformed
       String no = txtNo.getText();
    String service = txtService.getText();
    String cost =  txtCost.getText();
    String about = txtAbout.getText();

    // Show pop-up with selected service info
    String message = "Selected Services:\n" +
                     "No: " + no + "\n" +
                     "Service: " + service + "\n" +
                     "Cost: " + cost + "\n" +
                     "About: " + about;

    JOptionPane.showMessageDialog(null, message, "Successfully Service Selected", JOptionPane.INFORMATION_MESSAGE);

    // Data remains in textfields - no reset here
    // You can also store these values in variables if you want to use them in BOOK NOW step

    
    }//GEN-LAST:event_btnSelectActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    String no = txtNo.getText();
String service = txtService.getText();
String cost = txtCost.getText();
String about = txtAbout.getText();
Icon image = lblImage.getIcon();

// Booking details
String date = ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText();
String time = timePicker1.getText(); // ✅ use the UI time picker (already placed in form)

int quantity = (Integer) jSpinner1.getValue();
// Calculate total
int total = Integer.parseInt(cost.replace("₱", "").trim()) * quantity;
String specialist = jComboBox1.getSelectedItem().toString();


// Open new summary frame
BookingSummaryFrame summary = new BookingSummaryFrame(
    no, service, cost, about, image, date, time, quantity,specialist ,total );
summary.setLocationRelativeTo(null); // Center the pop-up on screen
summary.setVisible(true);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       int response = JOptionPane.showConfirmDialog(
    this,
    "Are you sure you want to make a new Booking?",
    "Confirm Booking",
    JOptionPane.OK_CANCEL_OPTION,
    JOptionPane.WARNING_MESSAGE
);

if (response == JOptionPane.OK_OPTION) {
    // Clear text fields
    txtNo.setText("");
    txtService.setText("");
    txtCost.setText("");
    txtAbout.setText("");

    // Clear image
    lblImage.setIcon(null);
    lblImage.setText("No Image"); // Optional: add placeholder text

    // Clear JDateChooser
    jDateChooser1.setDate(null);

    // Clear time picker (assuming it's from a library like LGoodDatePicker)
    timePicker1.setTime(null);

    // Clear JSpinner
    jSpinner1.setValue(0); // Or whatever default value makes sense

    // Clear JComboBox
    jComboBox1.setSelectedIndex(-1); // Unselects any item
}


    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int response = JOptionPane.showConfirmDialog(
    this,
    "Are you sure you want to Delete all Selected Items?",
    "Confirm Delete",
    JOptionPane.OK_CANCEL_OPTION,
    JOptionPane.WARNING_MESSAGE
);

if (response == JOptionPane.OK_OPTION) {
    // Clear text fields
    txtNo.setText("");
    txtService.setText("");
    txtCost.setText("");
    txtAbout.setText("");

    // Clear image
    lblImage.setIcon(null);
    lblImage.setText("No Image"); // Optional: add placeholder text

    // Clear JDateChooser
    jDateChooser1.setDate(null);

    // Clear time picker (assuming it's from a library like LGoodDatePicker)
    timePicker1.setTime(null);

    // Clear JSpinner
    jSpinner1.setValue(0); // Or whatever default value makes sense

    // Clear JComboBox
    jComboBox1.setSelectedIndex(-1); // Unselects any item
}

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int response = JOptionPane.showConfirmDialog(
    this,
    "Are you sure you want to Delete all Selected Items?",
    "Confirm Delete",
    JOptionPane.OK_CANCEL_OPTION,
    JOptionPane.WARNING_MESSAGE
);
        if (response == JOptionPane.OK_OPTION) {
            // Clear JDateChooser
    jDateChooser1.setDate(null);

    // Clear time picker (assuming it's from a library like LGoodDatePicker)
    timePicker1.setTime(null);

    // Clear JSpinner
    jSpinner1.setValue(0); // Or whatever default value makes sense

    // Clear JComboBox
    jComboBox1.setSelectedIndex(-1); // Unselects any item
    }//GEN-LAST:event_jButton3ActionPerformed
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablePages;
    private javax.swing.JButton btnSelect;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5clear;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JTextField searchField;
    private com.github.lgooddatepicker.components.TimePicker timePicker1;
    private javax.swing.JTextField txtAbout;
    private javax.swing.JTextField txtCost;
    private javax.swing.JTextField txtNo;
    private javax.swing.JTextField txtService;
    // End of variables declaration//GEN-END:variables

    void refreshTableBooking() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}