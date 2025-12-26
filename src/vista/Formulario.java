package vista;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.Vehiculo;

/**
 *
 * @author Henry
 */
public class Formulario extends javax.swing.JFrame {
    
    private Vehiculo[] registro; //Crear arreglo de objetos 
            
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Formulario.class.getName());
    DefaultTableModel modelo = new DefaultTableModel();
    
    public Formulario() {
        initComponents();
        this.setLocationRelativeTo(this);
        registro = new Vehiculo[0];
        inicio();
        String[] encabezados = {"Placa", "Modelo", "Capacidad", "Tipo", "Estado", "Valor Sgro"};
        
        modelo.setColumnIdentifiers(encabezados);
        
        tblDatos.setModel(modelo);
    }
    
    public void insertar(String placa, String modelo, double capacidad, String tipo, String estado){
        registro = redimensionar();
        registro[registro.length -1 ] = new Vehiculo(placa, modelo, capacidad, tipo, estado);
        
        JOptionPane.showMessageDialog(this, "Registro exitoso", "CONFIRMACION", JOptionPane.INFORMATION_MESSAGE);
        
        lblContador.setText(registro.length + "");
        
        limpiar();
        mostrar();
    }
    
    public void editar(int indice){
        txtPlaca.setText(registro[indice].getPlaca());
        txtModelo.setText(registro[indice].getModelo());
        txtCapacidad.setText(registro[indice].getCapacidad()+"");
        cbxTipo.setSelectedItem(registro[indice].getTipo());
        String estado = registro[indice].getEstado();

        if ("Activo".equalsIgnoreCase(estado)) {
            rdbActivo.setSelected(true);
        } else if ("En mantenimiento".equalsIgnoreCase(estado)) {
            rdbMantenimiento.setSelected(true);
        } else {
            estadoVehiculo.clearSelection(); // Ninguno seleccionado si el valor no coincide
        }
        
        txtModelo.requestFocus();
        btnActualizar.setEnabled(true);
        txtPlaca.setEnabled(false);
        btnEnviar.setEnabled(false);
        btnConsultar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        
    }
    
    public void mostrar(){//mostrar la info guardad en la tabla 
        
        if(registro != null){
        
            modelo.setRowCount(0);//esto evita que se vuelvan a poner datos que ya estan en la tabla, limpia la tabla

            for (int i = 0; i < registro.length; i++) {
                modelo.addRow(new Object[]{
                registro[i].getPlaca(),
                registro[i].getModelo(),
                registro[i].getCapacidad(),
                registro[i].getTipo(),
                registro[i].getEstado(),
                calcularValorEstimado(registro[i]) 
            });
            }

        }
    }
    
    public void limpiar(){
        txtPlaca.setText("");
        txtModelo.setText("");
        txtCapacidad.setText("");
        cbxTipo.setSelectedIndex(-1);
        estadoVehiculo.clearSelection();
    }
    
    public double calcularValorEstimado(Vehiculo v){
        double valorBase = 0;
        double capacidad = v.getCapacidad();
        String tipo = v.getTipo();
        double incremento = 0;

        switch(tipo){
            case "Camion": 
                valorBase = 1500000; 
            break;
            case "Furgoneta": 
                valorBase = 1000000; 
            break;
            case "Camioneta": 
                valorBase = 500000; 
            break;
        }

        if(capacidad > 1 && capacidad <= 5){
            incremento = valorBase * 0.10;
        } else if(capacidad > 5){
            incremento = valorBase * 0.20;
        }

        return valorBase + incremento;
    }

    
    public int buscar(String placa){  
        
        for (int i = 0; i < registro.length; i++) {
            
            if (placa.equals(registro[i].getPlaca())) {
                return i;
            }       
        }
        
        return -1;
    }
    
    public void actualizar(String modelo, double capacidad, String tipo, String estado){
        registro[tblDatos.getSelectedRow()].setModelo(modelo);
        registro[tblDatos.getSelectedRow()].setCapacidad(capacidad);
        registro[tblDatos.getSelectedRow()].setTipo(tipo);
        registro[tblDatos.getSelectedRow()].setEstado(estado);
        JOptionPane.showMessageDialog(this, "El registro se ha actualizado exitosamente");
        inicio();
    }
    
    public void inicio(){
        txtPlaca.setEnabled(true);
        btnEnviar.setEnabled(true);
        btnConsultar.setEnabled(true);
        btnModificar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(true);
        mostrar();
        lblContador.setText(registro.length + "");
    }
    
    public Vehiculo[] redimensionar(){
        Vehiculo[] aux = new Vehiculo[registro.length + 1];   
        
        for (int i = 0; i < registro.length; i++) { // pasar los datos de registro para aux
            aux[i] = registro[i];
        }
        return aux;
    }
    
    public void eliminar(int indice){
        Vehiculo[] aux = new Vehiculo[registro.length-1];
        
        for (int i = 0; i < registro.length; i++) {
            if(i < indice){
                aux[i] = registro[i];
            }else if(i > indice){
                aux[i-1] = registro[i];
            }
        }
        
        registro = aux;
       
        JOptionPane.showMessageDialog(this, "Registro eliminado");
        inicio();
    }
    
    public boolean verificar(){
        if(txtPlaca.getText().trim().isEmpty() || txtModelo.getText().trim().isEmpty() || txtCapacidad.getText().trim().isEmpty()
                || cbxTipo.getSelectedIndex() == -1 || estadoVehiculo.getSelection() == null){
            return true;
        }else{
            return false;
        }
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        estadoVehiculo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblPlaca = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        lblModelo = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        lblCapacidad = new javax.swing.JLabel();
        txtCapacidad = new javax.swing.JTextField();
        lblTipo = new javax.swing.JLabel();
        cbxTipo = new javax.swing.JComboBox<>();
        lblEstado = new javax.swing.JLabel();
        rdbActivo = new javax.swing.JRadioButton();
        rdbMantenimiento = new javax.swing.JRadioButton();
        btnEnviar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatos = new javax.swing.JTable();
        lblRegistrados = new javax.swing.JLabel();
        lblContador = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        lblAnuncio = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion de vehiculos\n");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setMinimumSize(new java.awt.Dimension(611, 498));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTitulo.setText("GESTION DE VEHICULOS");
        jPanel1.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, -1));

        lblPlaca.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblPlaca.setText("PLACA: ");
        jPanel1.add(lblPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, 30));

        txtPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPlacaActionPerformed(evt);
            }
        });
        jPanel1.add(txtPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 340, 30));

        lblModelo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblModelo.setText("MODELO:");
        jPanel1.add(lblModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, 30));
        jPanel1.add(txtModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 340, 30));

        lblCapacidad.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblCapacidad.setText("CAPACIDAD:");
        jPanel1.add(lblCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, 30));

        txtCapacidad.setForeground(new java.awt.Color(102, 102, 102));
        txtCapacidad.setText("Ingrese la capcidad del vehiculo en kilogramos");
        txtCapacidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCapacidadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCapacidadFocusLost(evt);
            }
        });
        txtCapacidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapacidadActionPerformed(evt);
            }
        });
        jPanel1.add(txtCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 340, 30));

        lblTipo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTipo.setText("TIPO:");
        jPanel1.add(lblTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 60, 30));

        cbxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Camion", "Furgoneta", "Camioneta"}));
        jPanel1.add(cbxTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, -1, 30));

        lblEstado.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblEstado.setText("ESTADO:");
        jPanel1.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 90, 30));

        estadoVehiculo.add(rdbActivo);
        rdbActivo.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        rdbActivo.setText("Activo");
        jPanel1.add(rdbActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, -1, 50));

        estadoVehiculo.add(rdbMantenimiento);
        rdbMantenimiento.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        rdbMantenimiento.setText("En mantenimiento");
        rdbMantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbMantenimientoActionPerformed(evt);
            }
        });
        jPanel1.add(rdbMantenimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 310, -1, 50));

        btnEnviar.setBackground(new java.awt.Color(0, 255, 153));
        btnEnviar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnEnviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-subir-32.png"))); // NOI18N
        btnEnviar.setText("ENVIAR");
        btnEnviar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 170, -1));

        btnConsultar.setBackground(new java.awt.Color(0, 255, 153));
        btnConsultar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-búsqueda-30.png"))); // NOI18N
        btnConsultar.setText("CONSULTAR");
        btnConsultar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        jPanel1.add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 230, -1));

        btnModificar.setBackground(new java.awt.Color(0, 255, 153));
        btnModificar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-crear-nuevo-32.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 430, -1, -1));

        btnEliminar.setBackground(new java.awt.Color(0, 255, 153));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-basura-llena-30.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, -1, -1));

        tblDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblDatos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 480, 450));

        lblRegistrados.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblRegistrados.setText("Registros:");
        jPanel1.add(lblRegistrados, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 470, -1, 40));

        lblContador.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblContador.setText("0");
        jPanel1.add(lblContador, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 470, -1, 40));

        btnActualizar.setBackground(new java.awt.Color(0, 255, 153));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-reiniciar-30.png"))); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, -1, -1));

        lblAnuncio.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblAnuncio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Anuncio.png"))); // NOI18N
        jPanel1.add(lblAnuncio, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 520, -1, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 992, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdbMantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbMantenimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbMantenimientoActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        String placa = JOptionPane.showInputDialog(this, "INGRESE LA PLACA A BUSCAR ");
        
        int pos = buscar(placa);
        
        if (pos != -1) {
            modelo.setRowCount(0);
            modelo.addRow(new Object[]{registro[pos].getPlaca(),
                                        registro[pos].getModelo(),
                                        registro[pos].getCapacidad(),    
                                        registro[pos].getTipo(),
                                        registro[pos].getEstado(),
                                        calcularValorEstimado(registro[pos])});
            
        }else{
            JOptionPane.showMessageDialog(this, "No se encontraron datos en la busqueda");
        }
        
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int op = JOptionPane.showConfirmDialog(this, "¿Esta seguro de que desea eliminar este registro?", "Eliminar registro", JOptionPane.WARNING_MESSAGE);
        
        if(tblDatos.getSelectedRow() != -1){
            
            if(op == JOptionPane.YES_OPTION){
                eliminar(tblDatos.getSelectedRow());
            }else{
                JOptionPane.showMessageDialog(this, "No se ha realizado ningun cambio");
            }
        }else{
          JOptionPane.showMessageDialog(this, "Debes seleccionar un registro");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        String placa = txtPlaca.getText();
        String modelo = txtModelo.getText();
        String capacidadString = txtCapacidad.getText(); //Un auxiliar de los buenos
        String tipo = (String) cbxTipo.getSelectedItem();
        String estado = "";
        
        if (rdbActivo.isSelected()) {
            estado = "Activo";
        }else if(rdbMantenimiento.isSelected()){
            estado = "En mantenimiento";
        }
        
        if(verificar()){
            JOptionPane.showMessageDialog(this, "Faltan campos por llenar");
        }else{
            double capacidad = Double.parseDouble(capacidadString);
            insertar(placa, modelo, capacidad, tipo, estado);
        }
        
        if (txtCapacidad.getText().trim().isEmpty()) {
            txtCapacidad.setForeground(new Color(102,102,102));
            txtCapacidad.setText("Ingrese la capacidad del vehiculo en kilogramos");
        }
        
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void txtPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPlacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlacaActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        String modelo = txtModelo.getText();
        String capacidadString = txtCapacidad.getText(); //Un auxiliar de los buenos xxdxd
        String tipo = (String) cbxTipo.getSelectedItem();
        String estado = "";
        
        if (rdbActivo.isSelected()) {
            estado = "Activo";
        }else if(rdbMantenimiento.isSelected()){
            estado = "En mantenimiento";
        }
        
        if(verificar()){
            JOptionPane.showMessageDialog(this, "Faltan campos por llenar");
        }else{
            double capacidad = Double.parseDouble(capacidadString);
            actualizar(modelo, capacidad, tipo, estado);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if(tblDatos.getSelectedRow() != -1){
            editar(tblDatos.getSelectedRow());
        }else{
          JOptionPane.showMessageDialog(this, "Debes seleccionar un registro");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void txtCapacidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCapacidadFocusGained
        txtCapacidad.setText("");
        txtCapacidad.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtCapacidadFocusGained

    private void txtCapacidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCapacidadFocusLost
        if (txtCapacidad.getText().trim().isEmpty()) {
            txtCapacidad.setForeground(new Color(102,102,102));
            txtCapacidad.setText("Ingrese la capacidad del vehiculo en kilogramos");
        }
    }//GEN-LAST:event_txtCapacidadFocusLost

    private void txtCapacidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapacidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapacidadActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Formulario().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbxTipo;
    private javax.swing.ButtonGroup estadoVehiculo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnuncio;
    private javax.swing.JLabel lblCapacidad;
    private javax.swing.JLabel lblContador;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblModelo;
    private javax.swing.JLabel lblPlaca;
    private javax.swing.JLabel lblRegistrados;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JRadioButton rdbActivo;
    private javax.swing.JRadioButton rdbMantenimiento;
    private javax.swing.JTable tblDatos;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtPlaca;
    // End of variables declaration//GEN-END:variables
}
