package vista;

import controlador.aereopuerto.ControladorAereopuerto;
import controlador.tda.lista.ListaEnlazada;
import controlador.tda.lista.exception.PosicionException;
import javax.swing.JOptionPane;

import vista.modelo.ModeloTablaPersona;

// @author samaelhg
public class FrmAereopuerto extends javax.swing.JDialog {

    private ControladorAereopuerto CA;
    private ModeloTablaPersona mtp = new ModeloTablaPersona();

    public FrmAereopuerto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jPanel2.setVisible(false);
        jPanel4.setVisible(false);
        jPanel5.setVisible(false);
        jButton1.setEnabled(false);
    }

    private void limpiar() {
        txtlat.setText("");
        txtlong.setText("");
        txtnombre.setText("");
        CA.setPersona(null);
        jButton1.setEnabled(false);
        cargarTabla();
    }

    private void crear() {

        Integer nro = Integer.parseInt(cbxnro.getSelectedItem().toString());
        CA = new ControladorAereopuerto(nro);
        jPanel2.setVisible(true);
        jPanel4.setVisible(true);
        jPanel5.setVisible(true);
        String[] aux = {""};
        jlista.setListData(aux);
        cargarTabla();
        cargarComboVertice();
    }

    private void cargarTabla() {
        mtp.setGrafo(CA.getGrafoAereopuerto());
        mtp.fireTableStructureChanged();
        mtp.fireTableDataChanged();
        tbltabla.setModel(mtp);
        tbltabla.updateUI();
        System.out.println(CA.getAereopuerto().toString());
    }

    private void cargarVista() {
        Integer fila = -1;
        fila = tbltabla.getSelectedRow();
        if (fila >= 0) {
            try {
                CA.setPersona(CA.getGrafoAereopuerto().obtenerEtiqueta(fila + 1));
                txtnombre.setText(CA.getAereopuerto().getNombre());
                txtlat.setText(String.valueOf(CA.getAereopuerto().getUbicacion().getLatitud()));
                txtlong.setText(String.valueOf(CA.getAereopuerto().getUbicacion().getLongitud()));
                jButton1.setEnabled(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Escoja una fila de la tabla", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificar() {
        if (txtnombre.getText().trim().length() == 0
                || txtlong.getText().trim().length() == 0
                || txtlat.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Datos incompletos", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Integer pos = CA.getGrafoAereopuerto().obtenerCodigo(CA.getAereopuerto());
                CA.getAereopuerto().setNombre(txtnombre.getText());
                CA.getAereopuerto().getUbicacion().setLatitud(Double.parseDouble(txtlat.getText()));
                CA.getAereopuerto().getUbicacion().setLongitud(Double.parseDouble(txtlong.getText()));
                if (CA.getGrafoAereopuerto().modificar(CA.getGrafoAereopuerto().obtenerEtiqueta(pos), CA.getAereopuerto())) {
                    cargarComboVertice();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "SE HA MODIFICADO CORRECTAMENTE!!", "OK", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "NO SE PUDO MODIFICAR", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cargarComboVertice() {
        cbxorigen.removeAllItems();
        cbxdestino.removeAllItems();
        try {
            for (int i = 1; i <= CA.getGrafoAereopuerto().numVertices(); i++) {
                cbxorigen.addItem(CA.getGrafoAereopuerto().obtenerEtiqueta(i).toString());
                cbxdestino.addItem(CA.getGrafoAereopuerto().obtenerEtiqueta(i).toString());
            }
        } catch (Exception e) {
            System.out.println("ERROR EN CARGAR COMBO");
        }
    }

    private void adyacencia() {
        Integer origen = (cbxorigen.getSelectedIndex() + 1);
        Integer destino = (cbxdestino.getSelectedIndex() + 1);

        if (origen == destino) {
            JOptionPane.showMessageDialog(null, "ESCOJA CLIENTES DIFERENTES", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Double distancia = CA.calcularDistancia(CA.getGrafoAereopuerto().obtenerEtiqueta(origen), CA.getGrafoAereopuerto().obtenerEtiqueta(destino));
                CA.getGrafoAereopuerto().insertarAristaE(CA.getGrafoAereopuerto().obtenerEtiqueta(origen), CA.getGrafoAereopuerto().obtenerEtiqueta(destino), distancia);
                System.out.println("----------------");
                System.out.println(CA.getGrafoAereopuerto().toString());
                System.out.println("----------------");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void camino() {
        Integer origen = (cbxorigen.getSelectedIndex() + 1);
        Integer destino = (cbxdestino.getSelectedIndex() + 1);

        if (origen == destino) {
            JOptionPane.showMessageDialog(null, "ESCOJA CLIENTES DIFERENTES", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                ListaEnlazada<Integer> lista = CA.getGrafoAereopuerto().Dijsktra(origen);
                String[] aux = new String[lista.getSize()];
                for (int i = 0; i < lista.getSize(); i++) {
                    aux[i] = CA.getGrafoAereopuerto().obtenerEtiqueta(lista.obtenerDato(i)).toString();
                }
                jlista.setListData(aux);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtlat = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txtlong = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltabla = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbxnro = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cbxorigen = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        cbxdestino = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlista = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        xdtxt = new javax.swing.JTextArea();
        Recorrido = new javax.swing.JComboBox<>();
        txtvert = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(null);

        jLabel1.setText("Nombre Aereopuerto");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(20, 30, 150, 15);

        jLabel2.setText("Latitud");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(20, 110, 110, 15);

        jLabel3.setText("Longitud");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(20, 70, 110, 15);
        jPanel2.add(txtlat);
        txtlat.setBounds(170, 110, 190, 19);
        jPanel2.add(txtnombre);
        txtnombre.setBounds(170, 30, 190, 19);
        jPanel2.add(txtlong);
        txtlong.setBounds(170, 70, 190, 19);

        jButton1.setText("MODIFICAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(200, 140, 110, 25);

        tbltabla.setModel(new javax.swing.table.DefaultTableModel(
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
        tbltabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbltablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbltabla);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(390, 10, 270, 160);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 90, 680, 190);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(null);

        jLabel4.setText("Aereopuertos");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(20, 20, 150, 15);

        cbxnro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "4", "5", "6", "7", "8", "9", "10" }));
        jPanel3.add(cbxnro);
        cbxnro.setBounds(230, 20, 130, 24);

        jButton2.setText("CREAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);
        jButton2.setBounds(560, 20, 90, 25);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(10, 10, 680, 60);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(null);

        jLabel5.setText("Origen");
        jPanel4.add(jLabel5);
        jLabel5.setBounds(20, 20, 90, 15);

        cbxorigen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(cbxorigen);
        cbxorigen.setBounds(120, 10, 130, 24);

        jButton3.setText("VINCULAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3);
        jButton3.setBounds(560, 10, 90, 25);

        cbxdestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(cbxdestino);
        cbxdestino.setBounds(400, 10, 130, 24);

        jLabel6.setText("Destino");
        jPanel4.add(jLabel6);
        jLabel6.setBounds(300, 20, 90, 15);

        jPanel1.add(jPanel4);
        jPanel4.setBounds(10, 300, 680, 60);

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(null);

        jButton4.setText("Dijsktra");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4);
        jButton4.setBounds(240, 50, 80, 25);

        jlista.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jlista);

        jPanel5.add(jScrollPane2);
        jScrollPane2.setBounds(10, 10, 210, 110);

        xdtxt.setColumns(20);
        xdtxt.setLineWrap(true);
        xdtxt.setRows(5);
        xdtxt.setWrapStyleWord(true);
        jScrollPane3.setViewportView(xdtxt);

        jPanel5.add(jScrollPane3);
        jScrollPane3.setBounds(350, 58, 190, 60);

        Recorrido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Recorrido", "Profundidad", "Anchura" }));
        Recorrido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RecorridoActionPerformed(evt);
            }
        });
        jPanel5.add(Recorrido);
        Recorrido.setBounds(350, 20, 170, 24);
        jPanel5.add(txtvert);
        txtvert.setBounds(580, 50, 80, 30);

        jLabel7.setText("vertice inicial");
        jPanel5.add(jLabel7);
        jLabel7.setBounds(580, 20, 100, 15);

        jPanel1.add(jPanel5);
        jPanel5.setBounds(10, 370, 680, 130);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 10, 700, 510);

        setSize(new java.awt.Dimension(734, 564));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        crear();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tbltablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbltablaMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            //Datos a mostrar en pantalla
            cargarVista();
        }
    }//GEN-LAST:event_tbltablaMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        modificar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        adyacencia();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        camino();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void RecorridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecorridoActionPerformed
        try {
            recorridos();
        } catch (Exception ex) {
           
        }
    }//GEN-LAST:event_RecorridoActionPerformed
    private void recorridos() throws PosicionException, Exception {
        int vertice = Integer.parseInt(txtvert.getText());
        if (vertice == 0) {

        } else {
            if (Recorrido.getItemAt(0).equalsIgnoreCase("Anchura")) {
                xdtxt.setText(" "+CA.getGrafoAereopuerto().recorridoAnchura(vertice));
                
            } else if (Recorrido.getItemAt(1).equalsIgnoreCase("Profundidad")) {
                xdtxt.setText(" "+CA.getGrafoAereopuerto().recorridoProfundidad(vertice));
            }
        }

    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmAereopuerto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAereopuerto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAereopuerto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAereopuerto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmAereopuerto dialog = new FrmAereopuerto(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Recorrido;
    private javax.swing.JComboBox<String> cbxdestino;
    private javax.swing.JComboBox<String> cbxnro;
    private javax.swing.JComboBox<String> cbxorigen;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> jlista;
    private javax.swing.JTable tbltabla;
    private javax.swing.JTextField txtlat;
    private javax.swing.JTextField txtlong;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtvert;
    private javax.swing.JTextArea xdtxt;
    // End of variables declaration//GEN-END:variables
}
