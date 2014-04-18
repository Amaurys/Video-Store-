/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videostore.Ventanas.caja;

import VideoStore.conexion.Conexion;
import VideoStore.ventanas.VentanaPrincipal;
import VideoStore.ventanas.caja.PanelCaja;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anthon
 */
public class Caja extends javax.swing.JFrame {
    
   DefaultTableModel  model;
  
    /**
     * Creates new form Caja
     */
    public Caja() {
        PanelCaja panelCaja = new PanelCaja();
        panelCaja.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelCaja.setLayout(new BorderLayout(0, 0));
        setContentPane(panelCaja);
        
        initComponents();
        setResizable(false);
        setTitle("Caja de Venta");
        //setSize(575,305);
            insertar();
            vender();
            // insertartotal();
            insertarCantidad();
          //insertCa();
       // obtenerID ();
            
    }
    
    void insertar()
    {
         if((txtCantidad_dia.getText()).length() == 0){
             
           try{
          String sql = "insert into FACTURA (ID_ARTICULO,NOMBRE_ARTICULO,PRECIO_ARTICULO,CANTIDAD, ESTADO)"+
                  "(select ID_ARTICULO, NOMBRE, PRECIO_VENTA, (select CANTIDAD from CANTIDADES where ID_ARTICULO =" 
                  + Integer.parseInt(txtID.getText())+ ")," +"Vendido" +"from ARTICULO where ID_ARTICULO = "  +Integer.parseInt(txtID.getText())+")" ; 
                   
                
          Conexion.ps = Conexion.connection.prepareCall(sql);
  
     int n = Conexion.ps.executeUpdate();
            if(n>0){
             // JOptionPane.showMessageDialog(null, "Datos insertados" );
            }
         } catch(Exception e)
         {
          // JOptionPane.showMessageDialog(null,"Error al insertar"+ e.getMessage());
         }
           
         }else
             {
               
                     try{
          String sql = "insert into FACTURA (ID_ARTICULO,NOMBRE_ARTICULO,PRECIO_ARTICULO,CANTIDAD,FECHA_ALQUILER,FECHA_ENTREGA,ESTADO)"+
                  "(select ID_ARTICULO, NOMBRE, PRECIO_ALQUILER, (select CANTIDAD from CANTIDADES where ID_ARTICULO =" 
                  + Integer.parseInt(txtID.getText())+ "),(SELECT FECHA_ALQUILER FROM VENTA_ALQUILER WHERE ID_ARTICULO <> 0),(SELECT FECHA_ENTREGA FROM VENTA_ALQUILER WHERE ID_ARTICULO <> 0),(SELECT ESTADO FROM VENTA_ALQUILER WHERE ID_ARTICULO <> 0)from ARTICULO where ID_ARTICULO = "  +Integer.parseInt(txtID.getText())+")" ; 
                   
                
          Conexion.ps = Conexion.connection.prepareCall(sql);
  
     int n = Conexion.ps.executeUpdate();
            if(n>0){
             // JOptionPane.showMessageDialog(null, "Datos insertados" );
            }
         } catch(Exception e)
         {
          // JOptionPane.showMessageDialog(null,"Error al insertar"+ e.getMessage());
         }
             }
    }
     
    void obtenerID ()
    {
     txtID.getText();
    }
    
    
    void limpiar()
    {
      txtID.setText("");
      txtCantidad.setText("");
      txtCantidad_dia.setText("");
      txtCliente_id.setText("");
      txtEstado.setText("");
      totalvendido.setText("");
      txtPago.setText("");
      txtCambio.setText("");
    }
    
     void insertarCantidad()
    {
          try{
          String sql = "insert into CANTIDADES (ID_ARTICULO,CANTIDAD)"+
                       "values(?,?)";

          Conexion.ps = Conexion.connection.prepareCall(sql);
          
            Conexion.ps.setInt(1, Integer.parseInt(txtID.getText()));
            Conexion.ps.setInt(2, Integer.parseInt(txtCantidad.getText()));       
          //ps.setString(3, txtPrecio.getText());
       
     int n = Conexion.ps.executeUpdate();
            if(n>0){
              //JOptionPane.showMessageDialog(null, "Datos insertados en cantidad" );
            }
         } catch(Exception e)
         {
            // JOptionPane.showMessageDialog(null,"Error al insertar cantidad"+ e.getMessage());
         }
        
    }
     
      void insertarventa_Alquiler()
    {
        if(txtCantidad_dia.getText().length() > 0){
          try{
          String sql = "insert into venta_Alquiler (ID_ARTICULO,FECHA_ALQUILER,FECHA_ENTREGA,ESTADO)"+
                       "values(?,(select CURDATE()),(select DATE_add(CURDATE(),interval  " + txtCantidad_dia.getText()+"  day)),?)";

            Conexion.ps = Conexion.connection.prepareCall(sql);
          
            Conexion.ps.setInt(1, Integer.parseInt(txtID.getText())); 
            Conexion.ps.setString(2, txtEstado.getText());  
           
       
     int n = Conexion.ps.executeUpdate();
            if(n>0){
              //JOptionPane.showMessageDialog(null, "Datos insertados en cantidad" );
            }
         } catch(Exception e)
         {
            // JOptionPane.showMessageDialog(null,"Error al insertar cantidad"+ e.getMessage());
         }
      }else{
             try{
          String sql = "insert into venta_Alquiler (ID_ARTICULO,ESTADO)"+
                       "values(?,?)";

            Conexion.ps = Conexion.connection.prepareCall(sql);
          
            Conexion.ps.setInt(1, Integer.parseInt(txtID.getText())); 
            Conexion.ps.setString(2, txtEstado.getText());  
           
       
     int n = Conexion.ps.executeUpdate();
            if(n>0){
              //JOptionPane.showMessageDialog(null, "Datos insertados en cantidad" );
            }
         } catch(Exception e)
         {
            // JOptionPane.showMessageDialog(null,"Error al insertar cantidad"+ e.getMessage());
         }
        }
        
    }
      
      void borrarventa_Alquiler()
      {
                try{
    
        String sql = "delete from venta_alquiler  where id_articulo <> 0" ;
        
        Conexion.stm = Conexion.connection.createStatement();
        
        int n = Conexion.stm.executeUpdate(sql);
        if(n>0){
           //JOptionPane.showMessageDialog(null,"Factura eliminada");
        }
        }catch(Exception e){  
           //JOptionPane.showMessageDialog(null,"Error al  factura" + e.getMessage());
        }
      }
      
    
    void insertarArticuloAlquilado()
    {
        if(txtCantidad_dia.getText().length() > 0){
            try{
            String sql = "insert into Articulo_alquilado (ID_ARTICULO,FECHA_ALQUILER,FECHA_ENTREGA,ESTADO,ID_CLIENTE)"+
                         "values(?,(select CURDATE()),(select DATE_add(CURDATE(),interval  " + txtCantidad_dia.getText()+"  day)),?,?)";

            Conexion.ps = Conexion.connection.prepareCall(sql);

              Conexion.ps.setInt(1, Integer.parseInt(txtID.getText()));

              Conexion.ps.setString(2, txtEstado.getText());  
              Conexion.ps.setString(3, txtCliente_id.getText());

       int n = Conexion.ps.executeUpdate();
              if(n>0){
                //JOptionPane.showMessageDialog(null, "Datos insertados en cantidad" );
              }
           } catch(Exception e)
           {
              // JOptionPane.showMessageDialog(null,"Error al insertar cantidad"+ e.getMessage());
           }
        }
    }
    
    void vender()
    {
        
            try{
                  Conexion.connection = Conexion.getConnection();
                  String [] titulos = {"Nombre_Articulo","Cantidad","Precio_producto","Fecha_alquiler","Fecha_entrega","Estado"};
                  String sql = "select nombre_articulo, cantidad, precio_articulo, fecha_alquiler, fecha_entrega, Estado from factura  " ;
                  model = new DefaultTableModel(null,titulos);
                  Conexion.stm = Conexion.connection.createStatement();
                  Conexion.rs = Conexion.stm.executeQuery(sql);

                  //"select nombre_producto, precio_producto from productos where producto_id = " + Integer.parseInt(txtID.getText());
                  String [] fila = new String[6];
                    // JOptionPane.showMessageDialog(null, "Producto vendido" );

                   while(Conexion.rs.next()){
                   fila [0]= Conexion.rs.getString("NOMBRE_ARTICULO");
                   fila [1]= Conexion.rs.getString("CANTIDAD");
                   fila [2]= Conexion.rs.getString("PRECIO_ARTICULO");
                   fila [3]= Conexion.rs.getString("FECHA_ALQUILER");
                   fila [4]= Conexion.rs.getString("FECHA_ENTREGA");
                   fila [5]= Conexion.rs.getString("ESTADO");

                   model.addRow(fila);
                  }
                  jTable1.setModel(model);
                }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Error al vender"+ e.getMessage());
                  //e.printStackTrace();
                }
    }

             void obtenertotal()
           {    

                try{
                  Conexion.connection = Conexion.getConnection();

                  String sql = "select total_pagar from total_vendido  " ;

                  Conexion.stm = Conexion.connection.createStatement();
                  Conexion.rs = Conexion.stm.executeQuery(sql);

                  String suma;
                  while(Conexion.rs.next()){
                      suma = Conexion.rs.getString("total_pagar"); 
                      totalvendido.setText(suma);
                  }


                    // JOptionPane.showMessageDialog(null, "total" );

                }catch(Exception e)
                {
                   // JOptionPane.showMessageDialog(null,"Error total "+ e.getMessage());
                  //e.printStackTrace();
                }
          
    }
      
      
     void insertartotal()
      {
          try{
          String sql = "insert into total_vendido (id_articulo,Total_Pagar)"+
                       " select id_articulo, sum(cantidad * precio_articulo) from factura where id_articulo <> 0";

          Conexion.ps = Conexion.connection.prepareCall(sql);
         
            int n = Conexion.ps.executeUpdate();
            if(n>0){
              //JOptionPane.showMessageDialog(null, "Datos insertados en  total" );
            }
         } catch(Exception e)
         {
             //JOptionPane.showMessageDialog(null,"Error al insertar total"+ e.getMessage());
         }
          
      }
     
       void borrarFactura()
       {
                try{
    
        String sql = "delete from factura  where id_articulo <> 0" ;
        
        Conexion.stm = Conexion.connection.createStatement();
        
        int n = Conexion.stm.executeUpdate(sql);
        if(n>0){
           //JOptionPane.showMessageDialog(null,"Factura eliminada");
        }
        }catch(Exception e){  
           //JOptionPane.showMessageDialog(null,"Error al  factura" + e.getMessage());
        }
       }
       
       void borrarCantidad()
       {
            try{
    
        String sql = "delete from cantidades  where id_articulo <> 0 " ;
        
        Conexion.stm = Conexion.connection.createStatement();
        
        int n = Conexion.stm.executeUpdate(sql);
        if(n>0){
         
          // JOptionPane.showMessageDialog(null,"Cantidad eliminada");
        }
        }catch(Exception e){
            
          // JOptionPane.showMessageDialog(null,"Error al  Cantidad" + e.getMessage());
        }
       }
       
       void borrarTotalvendido()
       {
           try{
    
        String sql = " delete from total_vendido where id_articulo <> 1 " ;
        
        Conexion.stm = Conexion.connection.createStatement();
        
        int n = Conexion.stm.executeUpdate(sql);
        if(n>0){
         
          // JOptionPane.showMessageDialog(null,"total_vendido eliminada");
        }
        }catch(Exception e){
            
          /// JOptionPane.showMessageDialog(null,"Error al  total_vendido" + e.getMessage());
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
        txtID = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        vender = new javax.swing.JButton();
        totalvendido = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtCambio = new javax.swing.JTextField();
        btnTotal = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtPago = new javax.swing.JTextField();
        btnCambio = new javax.swing.JButton();
        txtCantidad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCliente_id = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCantidad_dia = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText(" ID_articulo");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 70, -1));

        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });
        txtID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDKeyTyped(evt);
            }
        });
        getContentPane().add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 40, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre_producto", "Cantidad", "Precio_producto", "Fecha_alquiler", "fecha_entrega", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 670, 140));

        vender.setText("Aceptar");
        vender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venderActionPerformed(evt);
            }
        });
        getContentPane().add(vender, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        totalvendido.setEditable(false);
        getContentPane().add(totalvendido, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 270, 70, -1));

        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Cantidad de articulo");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 120, -1));

        txtCambio.setEditable(false);
        getContentPane().add(txtCambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 270, 60, -1));

        btnTotal.setText("Total-Venta");
        btnTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTotalActionPerformed(evt);
            }
        });
        getContentPane().add(btnTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 270, 100, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setText("Pago");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 230, 30, -1));

        txtPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPagoKeyTyped(evt);
            }
        });
        getContentPane().add(txtPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 230, 60, -1));

        btnCambio.setText("Cambio");
        btnCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambioActionPerformed(evt);
            }
        });
        getContentPane().add(btnCambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 270, 80, -1));

        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });
        getContentPane().add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 50, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText(" ID_cliente");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 70, -1));

        txtCliente_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliente_idActionPerformed(evt);
            }
        });
        txtCliente_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCliente_idKeyTyped(evt);
            }
        });
        getContentPane().add(txtCliente_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 40, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Cantidad_dia");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, 80, -1));

        txtCantidad_dia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidad_diaActionPerformed(evt);
            }
        });
        txtCantidad_dia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidad_diaKeyTyped(evt);
            }
        });
        getContentPane().add(txtCantidad_dia, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, 50, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Estado");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, 50, -1));

        txtEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstadoKeyTyped(evt);
            }
        });
        getContentPane().add(txtEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 60, 100, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void venderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venderActionPerformed
                    if(txtID.getText().length() == 0 || txtCantidad.getText().length() == 0 ){
                {
                      JOptionPane.showMessageDialog(null, " Por favor poner ID_producto\n y/o cantidad producto", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                }
    }
                    
     insertarventa_Alquiler();  
     insertarArticuloAlquilado();
     insertarCantidad();  
     insertar();
     vender();
     borrarventa_Alquiler();
     limpiar();
    }//GEN-LAST:event_venderActionPerformed

    private void btnTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTotalActionPerformed
        // TODO add your handling code here:
         insertartotal();
         obtenertotal();
    }//GEN-LAST:event_btnTotalActionPerformed

    private void btnCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambioActionPerformed
        // TODO add your handling code here:
        double suma =   Integer.parseInt(txtPago.getText()) - Double.parseDouble( totalvendido.getText());
        txtCambio.setText(String.valueOf(suma));
        
        borrarFactura();
        borrarCantidad();
        borrarTotalvendido();
    }//GEN-LAST:event_btnCambioActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
          int fila = jTable1.getSelectedRow();
          
         try{
        String sql = "delete from factura where nombre_articulo =" + jTable1.getValueAt(fila,0); 
        Conexion.stm = Conexion.connection.createStatement();
        
        int n = Conexion.stm.executeUpdate(sql);
        if(n>0){
          vender();
         // JOptionPane.showMessageDialog(null,"producto eliminado");
        }
        }catch(Exception e){
            
        // JOptionPane.showMessageDialog(null,"Error al eliminar" + e.getMessage());
        }
         VentanaPrincipal ventana = new VentanaPrincipal();
         ventana .setVisible(true);
         this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtCliente_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliente_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliente_idActionPerformed

    private void txtCantidad_diaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidad_diaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidad_diaActionPerformed

    private void txtIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDKeyTyped
         if(txtID.getText().length()>=5) evt.consume();
       
        char car = evt.getKeyChar();
        
        if (car<'0' || car>'9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtIDKeyTyped

    private void txtCliente_idKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliente_idKeyTyped
         if(txtCliente_id.getText().length()>=5) evt.consume();
       
        char car = evt.getKeyChar();
        
        if (car<'0' || car>'9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtCliente_idKeyTyped

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
         if(txtCantidad.getText().length()>=5) evt.consume();
       
        char car = evt.getKeyChar();
        
        if (car<'0' || car>'9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtCantidad_diaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidad_diaKeyTyped
         if(txtCantidad_dia.getText().length()>=5) evt.consume();
       
        char car = evt.getKeyChar();
        
        if (car<'0' || car>'9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidad_diaKeyTyped

    private void txtEstadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadoKeyTyped
         if(txtEstado.getText().length()>=9) evt.consume();
       
        char car = evt.getKeyChar();    
        
        if((car<'a' || car>'z') && (car<'A' || car>'Z'))
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtEstadoKeyTyped

    private void txtPagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagoKeyTyped
        if(txtPago.getText().length()>=8) evt.consume();
       
        char car = evt.getKeyChar();
        
        if (car<'0' || car>'9') {
            evt.consume();
        }
    }//GEN-LAST:event_txtPagoKeyTyped
   
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambio;
    private javax.swing.JButton btnTotal;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField totalvendido;
    private javax.swing.JTextField txtCambio;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCantidad_dia;
    private javax.swing.JTextField txtCliente_id;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtPago;
    private javax.swing.JButton vender;
    // End of variables declaration//GEN-END:variables

    public static void main(String [] args){
          
          new Caja();
    }

}
