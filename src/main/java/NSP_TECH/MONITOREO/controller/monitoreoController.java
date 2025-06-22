package NSP_TECH.MONITOREO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import NSP_TECH.MONITOREO.Assembler.monitoreoModelAssembler;
import NSP_TECH.MONITOREO.model.monitoreo;
import NSP_TECH.MONITOREO.services.monitoreoServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/Monitoreo")
public class monitoreoController {

    @Autowired
    private monitoreoServices monitoreoservices;
    
    @Autowired
    private monitoreoModelAssembler assembler;

   // ENDPOINT PARA BUSCAR TODOS LOS MONITOREOS
    @GetMapping

    @Operation(summary = "MONITOREOS", description = "Operacion que listar todo los monitoreos")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se listaron correctamente los monitoreos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = monitoreo.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro ningun de monitoreo", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))
    })

    public ResponseEntity<?> ListarTodo(){
        List<monitoreo> monitoreos = monitoreoservices.BuscarTodoMonitoreo();
        if (monitoreos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran dato");
        } else {
            return ResponseEntity.ok(assembler.toCollectionModel(monitoreos));
        }
    }

    // ENDPOINT PARA BUSCAR UN REGISTRO DE MONITOREO
    @GetMapping("/{ID_MONITOREO}")

    @Operation(summary = "MONITOREO", description = "Operacion que lista un monitoreo")
    @Parameters (value = {
        @Parameter (name="ID_MONITOREO", description= "ID del monitoreo que se buscara", in = ParameterIn.PATH, required= true)

    })

    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se lista correctamente el registro de monitoreo ", content = @Content(mediaType = "application/json", schema = @Schema(implementation = monitoreo.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro ningun registro de monitoreo", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))
    })

    public ResponseEntity<?> BuscarMonitoreo(@PathVariable Long ID_MONITOREO){

        try {
            monitoreo monitoreoBuscado = monitoreoservices.BuscarUnMonitoreo(ID_MONITOREO);
            return ResponseEntity.ok(assembler.toModel(monitoreoBuscado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra el registro");
        }
    }

// ENDPOINT PARA REGISTRAR UN MONITOREO
    @PostMapping
    @Operation(summary = "ENDPOINT QUE REGISTRA UN MONITOREO", description = "ENDPOINT QUE REGISTRA UN MONITOREO",requestBody= @io.swagger.v3.oas.annotations.parameters.RequestBody(description="MONITOREO QUE SE VA A REGISTRAR", required = true, content = @Content(schema = @Schema(implementation = monitoreo.class))))
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se registro correctamente el monitoreo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = monitoreo.class))),
        @ApiResponse(responseCode = "500", description = "Indica que no se logro registrar el monitoreo", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se puede registrar la solicitud ")))
    })

    public ResponseEntity<?> GuardarSoporte(@RequestBody monitoreo monitoreoGuardar){
    try {
            monitoreo monitoreoRegistrar = monitoreoservices.GuardarMonitoreo(monitoreoGuardar);
            return ResponseEntity.ok(assembler.toModel(monitoreoGuardar));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede registrar el monitoreo");
    }
    }

}
