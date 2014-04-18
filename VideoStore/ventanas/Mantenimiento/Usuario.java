/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videostore.Ventanas.Mantenimiento;

import VideoStore.conexion.Conexion;
import VideoStore.ventanas.VentanaPrincipal;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anthon
 */
public class Usuario extends javax.swing.JFrame implements Mantenible {

    DefaultTableModel  model;
    
    public Usuario() {
        Conexion.connection = Conexion.getConnection();
        initComponents();
        setResizable(false);
        setTitle("Tabla-Ususario");
        deshabilitar();
        listar();
    }
    
     public  void deshabilitar()
    {
        txtUsuario_id.setEditable(false);
     
    }
       
   public  void limpiar()
      {   
        txtUsuario_id.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtUsuario.setText("");
        txtContraseña.setText("");
        
      }
   
   public  void habilitar()
      {
        //txtID.setEditable(true);
        txtNombre.setEditable(true);
        txtApellido.setEditable(true);
        txtUsuario.setEditable(true);
        txtContraseña.setEditable(true);
       
       }
     
    public void listar()
     {
         try{
           Conexion.connection = Conexion.getConnection();
           String [] titulos = {"ID_USUARIO","USUARIO","CONTRASEÑA","NOMBRE","APELLIDO"};
           String sql = "select * from usuario";
           model = new DefaultTableModel(null,titulos);
           Conexion.stm = Conexion.connection.createStatement();
           Conexion.rs = Conexion.stm.executeQuery(sql);
           
           String [] fila = new String[5];
            
           while(Conexion.rs.next()){
            fila [0]= Conexion.rs.getString("ID_USUARIO");    
            fila [1]= Conexion.rs.getString("USUARIO"); 
            fila [2]= Conexion.rs.getString("CONTRASEÑA");
            fila [3]= Conexion.rs.getString("NOMBRE");
            fila [4]= Conexion.rs.getString("APELLIDO");
            
            
            model.addRow(fila);
           }
           JtableUSUARIO.setModel(model);
         }catch(Exception e)
         {
           e.printStackTrace();
         }
     
     }
           
      public void insetar()
       {
                try{
          String sql = "insert into usuario (USUARIO,CONTRASEÑA,NOMBRE,APELLIDO)"+ 
                  "values(?,?,?,?)";  
               
          Conexion.ps = Conexion.connection.prepareCall(sql);
         // ps.setInt(1, Integer.parseInt(txtID.getText()));
          Conexion.ps.setString(1, txtUsuario.getText());
          Conexion.ps.setString(2, txtContraseña.getText());
          Conexion.ps.setString(3, txtNombre.getText());
          Conexion.ps.setString(4, txtApellido.getText());
         
         
       
     int n = Conexion.ps.executeUpdate();
            if(n>0){
              JOptionPane.showMessageDialog(null, "Datos insertados" );
            }
         } catch(Exception e)
         {
             JOptionPane.showMessageDialog(null,"Error al insertar"+ e.getMessage());
         }
                
       } 
       
               public  void eliminar()
    {
             try{
        int fila = JtableUSUARIO.getSelectedRow();
        String sql = "delete from USUARIO where ID_USUARIO =" + JtableUSUARIO.getValueAt(fila,0); 
        Conexion.stm = Conexion.connection.createStatement();
        
        int n = Conexion.stm.executeUpdate(sql);
        if(n>0){
          limpiar();
          listar();
          JOptionPane.showMessageDialog(null,"Usuario eliminado");
        }
        }catch(Exception e){
            
         JOptionPane.showMessageDialog(null,"Error al eliminar" + e.getMessage());
        }
        
   }
       
    @Override
       public void actualizar()
            {
              try{
            int fila = JtableUSUARIO.getSelectedRow();
            
            String sql = "update usuario set ID_USUARIO = ?,"+  "USUARIO = ?,"+ "CONTRASEÑA =?,"+ "NOMBRE =?,"+ "APELLIDO =? " 
                    +"where ID_USUARIO  = " + JtableUSUARIO.getValueAt(fila, 0);
              
               String dao = (String) JtableUSUARIO.getValueAt(fila, 0);
               Conexion.ps = Conexion.connection.prepareStatement(sql);
               
             // Conexion.ps.setString(1, txtID.getText());
              Conexion.ps.setString(2, txtUsuario.getText());
              Conexion.ps.setString(3, txtContraseña.getText());
              Conexion.ps.setString(4, txtNombre.getText());
              Conexion.ps.setString(5, txtApellido.getText());
              
             
              Conexion.ps.setString(1,dao);
               
               int n = Conexion.ps.executeUpdate();
               
               if(n>0){
               
                 limpiar();
                 listar();
                   JOptionPane.showMessageDialog(null,"Usuario Actualizado" );
                      }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al Actualizar Usuario" + e.getMessage());
        }
            
            }
    
    
     public void insertarusuarioreciclaje()
      {
                         try{
          String sql = "insert into usuarioreciclaje (ID_USUARIO,USUARIO,CONTRASEÑA,NOMBRE,APELLIDO)"+
                  "values(?,?,?,?,?)";
          Conexion.ps = Conexion.connection.prepareCall(sql);
          Conexion.ps.setInt(1, Integer.parseInt(txtUsuario_id.getText()));
          Conexion.ps.setString(2, txtUsuario.getText());
          Conexion.ps.setString(3, txtContraseña.getText());
          Conexion.ps.setString(4, txtNombre.getText());
          Conexion.ps.setString(5, txtApellido.getText());
        
       
     int n = Conexion.ps.executeUpdate();
            if(n>0){
             // JOptionPane.showMessageDialog(null, "Datos insertados" );
            }
         } catch(Exception e)
         {
            // JOptionPane.showMessageDialog(null,"Error al insertar"+ e.getMessage());
         }
            
     }
     
     
         public  void eliminarusuarioreciclaje()
            {
                 int fila = JtableUSUARIO.getSelectedRow();
             try{
                  //int fila = jTable1.getSelectedRow();
                  String sql = "delete from usuarioreciclaje where ID_USUARIO <>" + JtableUSUARIO.getValueAt(fila,0); 
                  Conexion.stm = Conexion.connection.createStatement();
        
                    int n = Conexion.stm.executeUpdate(sql);
                     if(n>0){
                       listar();
                        //JOptionPane.showMessageDialog(null,"Usuario eliminado");
                  }
             }catch(Exception e){
            
                    // JOptionPane.showMessageDialog(null,"Error al eliminar" + e.getMessage());
              }
            }
       
         public void insertarEnUsuarioDesdeUsuarioreciclaje()
         {
         
               try{
                     String sql = "insert into usuario ()"+
                        "select ID_USUARIO,USUARIO,CONTRASEÑA,NOMBRE,APELLIDO from usuarioreciclaje ";
                            Conexion.ps = Conexion.connection.prepareCall(sql);
  
                   int n = Conexion.ps.executeUpdate();
                     if(n>0){
                     listar();
                     limpiar();
                      // JOptionPane.showMessageDialog(null, "Datos insertados" );
                   }
                   } catch(Exception e)
                   {
                     // JOptionPane.showMessageDialog(null,"Error al insertar"+ e.getMessage());
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

        jLabel1 = new javax.swing.JLabel();
        txtUsuario_id = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        JtableUSUARIO = new javax.swing.JTable();
        BnAgregar = new javax.swing.JButton();
        BnModificar = new javax.swing.JButton();
        BnEliminar = new javax.swing.JButton();
        BnCancelar = new javax.swing.JButton();
        BnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("ID");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));
        getContentPane().add(txtUsuario_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 30, -1));

        jLabel2.setText("USUARIO");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));

        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 130, -1));

        jLabel3.setText("CONTRASEÑA");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, -1, -1));

        txtContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContraseñaKeyTyped(evt);
            }
        });
        getContentPane().add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 110, -1));

        jLabel4.setText("NOMBRE");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, -1, -1));

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 90, -1));

        jLabel5.setText("APELLIDO");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, -1, -1));

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });
        getContentPane().add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 60, 100, -1));

        JtableUSUARIO.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID_USUARIO", "USUARIO", "CONTRASEÑA", "NOMBRE", "APELLIDO"
            }
        ));
        JtableUSUARIO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JtableUSUARIOMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JtableUSUARIO);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 790, 170));

        BnAgregar.setText("  Agregar  ");
        BnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BnAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(BnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, -1, -1));

        BnModificar.setText("  Modificar  ");
        BnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(BnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, -1, -1));

        BnEliminar.setText("  Eliminar  ");
        BnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(BnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 370, -1, -1));

        BnCancelar.setText("    Cancelar  ");
        BnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(BnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 370, -1, -1));

        BnSalir.setText("    Salir  ");
        BnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(BnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 370, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BnAgregarActionPerformed
        
        
           if(txtUsuario.getText().length() == 0 || txtContraseña.getText().length() == 0 || txtNombre.getText().length() ==0 || txtApellido.getText().length() ==0 ){

     {
            JOptionPane.showMessageDialog(null, "Hay campos vacios.\n Por favor Rellene los campos vacios", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }
    }
        insetar();
        deshabilitar();
        listar();
        limpiar(); 
    }//GEN-LAST:event_BnAgregarActionPerformed

    private void BnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_BnEliminarActionPerformed

    private void BnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BnModificarActionPerformed
        actualizar();
    }//GEN-LAST:event_BnModificarActionPerformed

    private void JtableUSUARIOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtableUSUARIOMouseClicked
        
              if(evt.getButton()==1){
          int fila = JtableUSUARIO.getSelectedRow();
          try{ 
              habilitar();
              String sql = "select * from usuario where  ID_USUARIO =" + JtableUSUARIO.getValueAt(fila,0);
               Conexion.stm = Conexion.connection.createStatement();
               Conexion.rs = Conexion.stm.executeQuery(sql);
               Conexion.rs.next();
               txtUsuario_id.setText(Conexion.rs.getString("ID_USUARIO"));
               txtUsuario.setText(Conexion.rs.getString("USUARIO"));
               txtContraseña.setText(Conexion.rs.getString("CONTRASEÑA"));
               txtNombre.setText(Conexion.rs.getString("NOMBRE"));
               txtApellido.setText(Conexion.rs.getString("APELLIDO"));
          
              }catch(Exception ex){
              
                  ex.printStackTrace();
              }
        }
              insertarusuarioreciclaje();
              eliminarusuarioreciclaje();
    }//GEN-LAST:event_JtableUSUARIOMouseClicked

    private void BnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BnCancelarActionPerformed
       insertarEnUsuarioDesdeUsuarioreciclaje();
    }//GEN-LAST:event_BnCancelarActionPerformed

    private void BnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BnSalirActionPerformed
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BnSalirActionPerformed

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
          if(txtUsuario.getText().length()>=15) evt.consume();
       
        char car = evt.getKeyChar();    
        
        if((car<'a' || car>'z') && (car<'A' || car>'Z'))
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void txtContraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaKeyTyped
       if(txtContraseña.getText().length()>=15) evt.consume();
       
       char car = evt.getKeyChar(); 
       
        if(((car<'a' || car>'z') && (car<'A' || car>'Z')) && (car<'0' || car>'9'))
        {
            evt.consume();
        }
       
    }//GEN-LAST:event_txtContraseñaKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
          if(txtNombre.getText().length()>=30) evt.consume();
       
        char car = evt.getKeyChar();    
        
        if((car<'a' || car>'z') && (car<'A' || car>'Z') 
         && (car!=(char)KeyEvent.VK_SPACE))
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        if(txtApellido.getText().length()>=30) evt.consume();
       
        char car = evt.getKeyChar();    
        
        if((car<'a' || car>'z') && (car<'A' || car>'Z') 
         && (car!=(char)KeyEvent.VK_SPACE))
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtApellidoKeyTyped

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
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuario().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BnAgregar;
    private javax.swing.JButton BnCancelar;
    private javax.swing.JButton BnEliminar;
    private javax.swing.JButton BnModificar;
    private javax.swing.JButton BnSalir;
    private javax.swing.JTable JtableUSUARIO;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txtUsuario_id;
    // End of variables declaration//GEN-END:variables

    @Override
    public void insertar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

  

    
}
