/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VideoStore.Login;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
public class PanelImagen extends javax.swing.JPanel {
public PanelImagen(){
//this.setSize(400,280);
this.setSize(273, 184);
}
@Override
public void paintComponent (Graphics g){
Dimension tamanio = getSize();
ImageIcon imagenFondo = new ImageIcon(getClass().getResource("ImagenLogin.jpg"));
g.drawImage(imagenFondo.getImage(),0,0,tamanio.width, tamanio.height, null);
setOpaque(false);
super.paintComponent(g);
}
}