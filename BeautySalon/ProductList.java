/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeautySalon;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Void
 */
public class ProductList {
    private String ServiceName;
    private int Cost;
    private String description;
    private String mimage;

    public ProductList(String ServiceName, int Cost, String description, String image) {
        this.ServiceName = ServiceName;
        this.Cost = Cost;
        this.description = description;
        this.mimage = image;
    }

    ProductList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getBrand() {
        return ServiceName;
    }

    public void setBrand(String ServiceName) {
        this.ServiceName = ServiceName;
    }
    public int getPrice() {
        return Cost;
    }

    public void setPrice(int Cost) {
        this.Cost = Cost;
    }
    public String getDescription(String trim) {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMimage() {
        //return new ImageIcon(new ImageIcon(mimage).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        return mimage;
    }

    public void setMimage(String mimage) {
        this.mimage = mimage;
    }
    
    
    
}
