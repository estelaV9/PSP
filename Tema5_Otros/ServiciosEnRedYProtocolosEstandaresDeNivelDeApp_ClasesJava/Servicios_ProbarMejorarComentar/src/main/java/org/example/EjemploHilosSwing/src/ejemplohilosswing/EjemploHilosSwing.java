/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplohilosswing;

/**
 *
 * @author participante
 */
public class EjemploHilosSwing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("VENTANA PRINCIPAL CREADA");
        /* CREAR Y MOSTRAR EL FORMULARIO */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // CREAR UNA INSTANCIA DE LA VENTANA CON HILOS Y HACERLA VISIBLE
                new VentanaConHilos().setVisible(true);
            }
        });
    }

}
