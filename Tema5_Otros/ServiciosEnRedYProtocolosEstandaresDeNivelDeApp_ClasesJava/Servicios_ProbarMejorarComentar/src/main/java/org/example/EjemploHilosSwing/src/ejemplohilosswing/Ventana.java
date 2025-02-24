package ejemplohilosswing;

public class Ventana extends javax.swing.JFrame {

    // CONSTRUCTOR DE LA VENTANA, INICIALIZA LOS COMPONENTES
    public Ventana() {
        initComponents(); // INICIALIZA LOS COMPONENTES DE LA INTERFAZ GRÁFICA
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoUsoHilos = new javax.swing.ButtonGroup(); // GRUPO PARA LOS BOTONES DE RADIO (USAR HILOS O NO)
        btnPulsame = new javax.swing.JButton(); // BOTON PARA INICIAR LA TAREA
        pbBarraProgreso = new javax.swing.JProgressBar(); // BARRA DE PROGRESO PARA MOSTRAR EL AVANCE
        btnCancelar = new javax.swing.JButton(); // BOTON PARA CANCELAR LA TAREA
        radioNoUsarHilos = new javax.swing.JRadioButton(); // RADIOBUTTON PARA SELECCIONAR NO USAR HILOS
        radioUsarHilos = new javax.swing.JRadioButton(); // RADIOBUTTON PARA SELECCIONAR USAR HILOS

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); // ESTABLECE LA ACCION AL CERRAR LA VENTANA

        btnPulsame.setText("Pulsame"); // TEXTO DEL BOTON "PULSAME"

        btnCancelar.setText("Cancelar"); // TEXTO DEL BOTON "CANCELAR"

        grupoUsoHilos.add(radioNoUsarHilos); // AÑADIMOS EL RADIOBUTTON "NO USAR HILOS" AL GRUPO
        radioNoUsarHilos.setText("No usar hilos"); // TEXTO DEL RADIOBUTTON "NO USAR HILOS"

        grupoUsoHilos.add(radioUsarHilos); // AÑADIMOS EL RADIOBUTTON "USAR HILOS" AL GRUPO
        radioUsarHilos.setText("Usar hilos"); // TEXTO DEL RADIOBUTTON "USAR HILOS"

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout); // ESTABLECE EL LAYOUT DE LA VENTANA

        // CONFIGURACION DEL LAYOUT HORIZONTAL
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(pbBarraProgreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)) // BARRA DE PROGRESO
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(150, 150, 150)
                                                                .addComponent(btnPulsame)) // BOTON "PULSAME"
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(152, 152, 152)
                                                                .addComponent(btnCancelar)) // BOTON "CANCELAR"
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(150, 150, 150)
                                                                .addComponent(radioNoUsarHilos)) // RADIOBUTTON "NO USAR HILOS"
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(150, 150, 150)
                                                                .addComponent(radioUsarHilos))) // RADIOBUTTON "USAR HILOS"
                                                .addGap(0, 153, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        // CONFIGURACION DEL LAYOUT VERTICAL
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnPulsame) // UBICA EL BOTON "PULSAME"
                                .addGap(36, 36, 36)
                                .addComponent(radioNoUsarHilos) // UBICA EL RADIOBUTTON "NO USAR HILOS"
                                .addGap(1, 1, 1)
                                .addComponent(radioUsarHilos) // UBICA EL RADIOBUTTON "USAR HILOS"
                                .addGap(18, 18, 18)
                                .addComponent(pbBarraProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE) // UBICA LA BARRA DE PROGRESO
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                                .addComponent(btnCancelar) // UBICA EL BOTON "CANCELAR"
                                .addContainerGap())
        );

        pack(); // AJUSTA LOS COMPONENTES A LA VENTANA
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true); // INICIA Y MUESTRA LA VENTANA
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton btnCancelar; // DECLARACION DEL BOTON "CANCELAR"
    protected javax.swing.JButton btnPulsame; // DECLARACION DEL BOTON "PULSAME"
    protected javax.swing.ButtonGroup grupoUsoHilos; // DECLARACION DEL GRUPO DE BOTONES DE RADIO
    protected javax.swing.JProgressBar pbBarraProgreso; // DECLARACION DE LA BARRA DE PROGRESO
    protected javax.swing.JRadioButton radioNoUsarHilos; // DECLARACION DEL RADIOBUTTON "NO USAR HILOS"
    protected javax.swing.JRadioButton radioUsarHilos; // DECLARACION DEL RADIOBUTTON "USAR HILOS"
    // End of variables declaration//GEN-END:variables
}
