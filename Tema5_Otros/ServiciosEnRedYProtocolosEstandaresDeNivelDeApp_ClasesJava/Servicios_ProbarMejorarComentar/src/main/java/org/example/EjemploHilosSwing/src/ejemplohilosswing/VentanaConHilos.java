/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplohilosswing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author participante
 */
public class VentanaConHilos extends Ventana implements ActionListener{

    // CONSTRUCTOR DE LA CLASE "VentanaConHilos" QUE HEREDA DE "Ventana"
    public VentanaConHilos() {
        super(); // LLAMA AL CONSTRUCTOR DE LA CLASE PADRE "Ventana"
        this.btnPulsame.addActionListener(this); // AÑADE EL ACTIONLISTENER AL BOTON "PULSAME"
        this.btnCancelar.addActionListener(this); // AÑADE EL ACTIONLISTENER AL BOTON "CANCELAR"
        this.pbBarraProgreso.setMinimum(0); // ESTABLECE EL VALOR MINIMO DE LA BARRA DE PROGRESO
        this.pbBarraProgreso.setMaximum(100); // ESTABLECE EL VALOR MAXIMO DE LA BARRA DE PROGRESO
    }

    // METODO PARA EJECUTAR LA TAREA SIN HILOS, LO QUE "CONGELARIA" LA VENTANA
    public void ejecutarSinHilos() throws InterruptedException{
        /* SIMPLEMENTE LLAMAMOS A LA TAREA LARGA Y VEREMOS
        QUE LA VENTA SE "CONGELA" */
        System.out.println("Ejecutando sin hilos");
        TareaMuyLarga tarea=new TareaMuyLarga(this.pbBarraProgreso); // CREA UNA INSTANCIA DE LA TAREA MUY LARGA
        tarea.execute(); // EJECUTA LA TAREA EN UN HILO DE FONDO
    }

    // METODO PARA EJECUTAR LA TAREA CON HILOS, DE MANERA QUE NO CONGELE LA VENTANA
    public void ejecutarConHilos(){
        System.out.println("Ejecutando con hilos");
        TareaMuyLarga tarea=new TareaMuyLarga(this.pbBarraProgreso); // CREA UNA INSTANCIA DE LA TAREA MUY LARGA
        tarea.execute(); // EJECUTA LA TAREA EN UN HILO DE FONDO
    }

    // METODO PARA LIMPIAR LA SELECCION DE LOS RADIOBUTTONS
    public void limpiarRadios(){
        this.radioNoUsarHilos.setSelected(false); // DESSELECCIONA EL RADIOBUTTON "NO USAR HILOS"
        this.radioUsarHilos.setSelected(false); // DESSELECCIONA EL RADIOBUTTON "USAR HILOS"
    }

    // IMPLEMENTACION DEL METODO "actionPerformed" DE LA INTERFACE "ActionListener"
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==this.btnPulsame){ // SI SE HACE CLICK EN EL BOTON "PULSAME"

            try {
                if (this.radioNoUsarHilos.isSelected()){ // SI SE SELECCIONA "NO USAR HILOS"
                    ejecutarSinHilos(); // EJECUTA LA TAREA SIN HILOS
                }
                if (this.radioUsarHilos.isSelected()){ // SI SE SELECCIONA "USAR HILOS"
                    ejecutarConHilos(); // EJECUTA LA TAREA CON HILOS
                }
            } catch (InterruptedException ex) {
                System.out.println("¡¡Hubo un error en Thread.sleep()!!"); // MUESTRA UN MENSAJE DE ERROR EN CASO DE EXCEPCION
            }
        }
        if (e.getSource()==this.btnCancelar){ // SI SE HACE CLICK EN EL BOTON "CANCELAR"
            System.out.println("Cancelando..."); // MUESTRA UN MENSAJE INDICANDO QUE SE CANCELA
        }

    }
}
