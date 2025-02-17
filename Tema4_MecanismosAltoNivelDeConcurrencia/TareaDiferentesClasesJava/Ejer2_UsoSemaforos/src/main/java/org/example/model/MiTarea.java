package org.example.model;

// CLASE QUE IMPLEMENTA LA TAREA QUE CADA HILO EJECUTA
public class MiTarea implements Runnable {

    private RecursoCompartido recurso; // INSTANCIA DEL RECURSO COMPARTIDO
    private String nombreThread; // NOMBRE DEL HILO

    // CONSTRUCTOR DE LA CLASE MiTarea
    public MiTarea(RecursoCompartido recurso, String nombreThread) {
        this.recurso = recurso; // ASIGNA EL RECURSO A LA TAREA
        this.nombreThread = nombreThread; // ASIGNA EL NOMBRE DEL HILO
    }

    // IMPLEMENTACION DEL METODO RUN() QUE EJECUTA LA LOGICA DE LA TAREA
    @Override
    public void run() {
        // EL HILO ACCEDE AL RECURSO
        recurso.accederRecurso(nombreThread);
    }
}
