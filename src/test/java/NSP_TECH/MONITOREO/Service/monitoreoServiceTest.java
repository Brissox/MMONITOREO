package NSP_TECH.MONITOREO.Service;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import NSP_TECH.MONITOREO.model.monitoreo;
import NSP_TECH.MONITOREO.repository.monitoreoRepository;
import NSP_TECH.MONITOREO.services.monitoreoServices;


public class monitoreoServiceTest {

    @Mock
    private monitoreoRepository monitoreorepository;
    
    @InjectMocks
    private monitoreoServices monitoreoservices;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    
    @Test
    public void testBuscarTodo(){
    java.util.List<monitoreo> lista = new  ArrayList<>();

    monitoreo monit1 = new monitoreo();
    monitoreo monit2 = new monitoreo();

    monit1.setId_monitoreo(11L);
    monit1.setComponente("Ventas");
    monit1.setMensaje("Evento monitoreo");
    monit1.setTipo_evento("Error");
    monit1.setNivel_alerta("Critico");
    monit1.setFecha_evento(LocalDateTime.now());

    monit2.setId_monitoreo(12L);
    monit2.setComponente("Pagos");
    monit2.setMensaje("Evento monitoreo");
    monit2.setTipo_evento("Error");
    monit2.setNivel_alerta("Critico");
    monit2.setFecha_evento(LocalDateTime.now().minusDays(1));

    lista.add(monit1);
    lista.add(monit2);

    when(monitoreorepository.findAll()).thenReturn(lista);

    java.util.List<monitoreo> resultadoBusqueda = monitoreoservices.BuscarTodoMonitoreo();

    assertEquals(2,resultadoBusqueda.size());
    verify(monitoreorepository, times(1)).findAll();

}

    @Test
    public void testBuscarUnMonitoreo(){
    monitoreo monit1 = new monitoreo();

    monit1.setId_monitoreo(11L);
    monit1.setComponente("Ventas");
    monit1.setMensaje("Evento monitoreo");
    monit1.setTipo_evento("Error");
    monit1.setNivel_alerta("Critico");
    monit1.setFecha_evento(LocalDateTime.now());

    when(monitoreorepository.findById(11L)).thenReturn(Optional.of(monit1));

    monitoreo monitBuscado = monitoreoservices.BuscarUnMonitoreo(11L);
    assertEquals(11L,monitBuscado.getId_monitoreo());
    verify(monitoreorepository, times(1)).findById(11L);

    }



    @Test
    public void testGuardarMonitoreo(){
        monitoreo m = new monitoreo();
    m.setId_monitoreo(11L);
    m.setComponente("Ventas");
    m.setMensaje("Evento monitoreo");
    m.setTipo_evento("Error");
    m.setNivel_alerta("Critico");
    m.setFecha_evento(LocalDateTime.now());

        when(monitoreorepository.save(any())).thenReturn(m);

        monitoreo monitoreoGuardados = monitoreoservices.GuardarMonitoreo(m);
        assertEquals(11L, monitoreoGuardados.getId_monitoreo());
        verify(monitoreorepository, times(1)).save(m);

    }


/*

    @Test
    public void testEliminarEnvio(){
        Long id = 11L;
        doNothing().when(enviorepository).deleteById(id);

        enviosservices.Eliminar***(id);

        verify(enviosrepository.times(1)).deleteById(id);

    }
*/
}
