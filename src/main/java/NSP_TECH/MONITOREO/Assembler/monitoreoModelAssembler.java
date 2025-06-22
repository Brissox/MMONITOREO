package NSP_TECH.MONITOREO.Assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import NSP_TECH.MONITOREO.controller.monitoreoController;
import NSP_TECH.MONITOREO.model.monitoreo;

@Component
public class monitoreoModelAssembler implements RepresentationModelAssembler<monitoreo, EntityModel<monitoreo>> {

    @Override
    public EntityModel<monitoreo> toModel(monitoreo m) {
        return EntityModel.of(
            m,
            linkTo(methodOn(monitoreoController.class).BuscarMonitoreo(m.getId_monitoreo())).withRel("LINKS"),
            linkTo(methodOn(monitoreoController.class).ListarTodo()).withRel("todos-los-monitoreos")
        );
    }


}
