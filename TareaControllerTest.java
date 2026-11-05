import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TareaControllerTest {
    private TareaController controlador;

   @BeforeEach
    void setUp() {
        AlmacenarTareas almacenamiento = new AlmacenamientoMemoria();
        controlador = new TareaController(almacenamiento);
    }


    @Test
    void testAgregarVariosElementosYRevisarIds() {
        Tarea t = controlador.agregarTarea("atender estudiantes","Ximena");
        Tarea t2 = controlador.agregarTarea("atender estudiantes","Luis Diego");
        Tarea t3 = controlador.agregarTarea("atender estudiantes","william");
        
        assertEquals(0, t.getId());
        assertEquals(1, t2.getId());
        assertEquals(2, t3.getId());

        assertNotEquals(t.getId(), t2.getId());
    }

    @Test
    void validarCambioDeEstado() {
        Tarea t3 = controlador.agregarTarea("atender estudiantes","william");
        assertFalse(t3.getCompletada());
        assertDoesNotThrow(() ->controlador.completarTarea(t3.getId()));

        assertTrue(t3.getCompletada());
        
    }

    @Test 
    void CompletarTareaInexistenteLanzaExcepcion(){
        assertThrows(IllegalArgumentException.class, () ->controlador.completarTarea(99));

    }

    @Test 
    void buscarTareasInexistentesYDevuelveNull(){
        AlmacenarTareas almacenamiento = new AlmacenamientoMemoria();
        assertNull(almacenamiento.buscarPorId(99));
    }

    @Test 
    void CompletarTareaYaCompletadaLanzaExcepcion(){
        Tarea t6 = controlador.agregarTarea("titulo", "descripcion");
        controlador.completarTarea(t6.getId());
        assertThrows(IllegalStateException.class, ()->controlador.completarTarea(t6.getId()));
    }

    @Test 
    void agregarTareaDevuelveUnObjeto(){
        Tarea t7 = controlador.agregarTarea("Estudiar", "Java");
        assertNotNull(t7);
        
    }

    @Test 
    void EliminarTareaExistente(){
        Tarea t8 = controlador.agregarTarea("EJercicio","terminar POO");
        controlador.eliminarTarea(t8.getId());
        AlmacenarTareas almacenamiento = new AlmacenamientoMemoria();
        assertNull(almacenamiento.buscarPorId(t8.getId()));
    }

    @Test 
    void EliminarTareaInexistenteLanzaExcepcion(){
        assertThrows(IllegalArgumentException.class, () ->controlador.eliminarTarea(99));
    }







}
