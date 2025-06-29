package NSP_TECH.MONITOREO.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NSP_TECH.MONITOREO.model.monitoreo;
import NSP_TECH.MONITOREO.repository.monitoreoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class monitoreoServices {

    @Autowired
    private monitoreoRepository monitoreorepository;

    public List<monitoreo> BuscarTodoMonitoreo(){
        return monitoreorepository.findAll();
    }
    public monitoreo BuscarUnMonitoreo(Long id_monitoreo){
        return monitoreorepository.findById(id_monitoreo).get();
    }
    public monitoreo GuardarMonitoreo(monitoreo monitoreo){
        return monitoreorepository.save(monitoreo);

    }

    public void EliminarMonitoreo(Long id_monitoreo){
        monitoreorepository.deleteById(id_monitoreo);
    }

}
