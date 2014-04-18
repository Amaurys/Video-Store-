/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VideoStore.ventanas;

import VideoStore.conexion.Conexion;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GogoMix
 */
public class ArticuloAlquilado extends javax.swing.JFrame {

    DefaultTableModel model;
    /**
     * Creates new form ArticuloAlquilado
     */
    public ArticuloAlquilado() {
        initComponents();
        listar();
    }
    
     public void listar()
     {
         try{
           Conexion.connection = Conexion.getConnection();
           String [] titulos = {"ID_ARTICULO","FECHA_ALQUILER","FECHA_ENTREGA","ESTADO","ID_CLIENTE"};
           String sql = "select * from Articulo_Alquilado";
           model = new DefaultTableModel(null,titulos);
           Conexion.stm = Conexion.connection.createStatement();
           Conexion.rs = Conexion.stm.executeQuery(sql);
           
           String [] fila = new String[5];
            
           while(Conexion.rs.next()){
            fila [0]= Conexion.rs.getString("ID_ARTICULO");    
            fila [1]= Conexion.rs.getString("FECHA_ALQUILER"); 
            fila [2]= Conexion.rs.getString("FECHA_ENTREGA");
            fila [3]= Conexion.rs.getString("ESTADO");
            fila [4]= Conexion.rs.getString("ID_CLIENTE");
            
            
            model.addRow(fila);
           }
          jTable1.setModel(model);
         }catch(Exception e)
         {
           e.printStackTrace();
         }
     
     }
     
     public  void eliminar()
    {
             try{
        int fila = jTable1.getSelectedRow();
        String sql = "delete from Articulo_alquilado where ID_ARTICULO =" + jTable1.getValueAt(fila,0); 
        Conexion.stm = Conexion.connection.createStatement();
        
        int n = Conexion.stm.executeUpdate(sql);
        if(n>0){
          
          listar();
          JOptionPane.showMessageDialog(null,"Usuario eliminado");
        }
        }catch(Exception e){
            
         JOptionPane.showMessageDialog(null,"Error al eliminar" + e.getMessage());
        }
        
   }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnSalir = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Articulo", "Fecha de Entrega", "Fecha de Expedicion", "Estado", "ID Cliente"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 690, 150));

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 280, 80, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, -1, -1));

        btnCancelar.setText("Cancelar");
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 280, 90, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

     private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        
              if(evt.getButton()==1){
          int fila = jTable1.getSelectedRow();
          try{ 
              
              String sql = "select * from Articulo_alquilado where  ID_ARTICULO =" + jTable1.getValueAt(fila,0);
               Conexion.stm = Conexion.connection.createStatement();
               ResultSet rs = Conexion.stm.executeQuery(sql);
               rs.next();
               
              }catch(Exception ex){
              
                  ex.printStackTrace();
              }
        }
    }  
     
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ArticuloAlquilado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ArticuloAlquilado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ArticuloAlquilado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ArticuloAlquilado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ArticuloAlquilado().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}