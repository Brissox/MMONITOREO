package NSP_TECH.MONITOREO.model;


import java.time.LocalDateTime;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="monitoreo")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description="Monitoreo del sistema")


public class monitoreo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_MONITOREO")
    @Schema(description="identificador monitoreo", example="11")
    private Long id_monitoreo;

    @Column(name="COMPONENTE",nullable = false,length = 50)
    @Schema(description="elemento del sistema monitoreado", example="pago,login,inventario")
    private String componente;

    @Column(name="MENSAJE",nullable =false ,length = 255)
    @Schema(description="Mensaje del monitoreo", example="Evento Monitoreo #1")
    private String mensaje;

    @Column(name="TIPO_EVENTO",nullable = false ,length = 30)
    @Schema(description="clasificacion del evento", example="error, advertencia,informacion")
    private String tipo_evento;

    @Column(name="NIVEL_ALERTA",nullable= true, length=20)
    @Schema(description="grado de peligro o riesgo asociado a un evento", example="Critico,medio,bajo")
    private String nivel_alerta;

    @Column(name="FECHA_EVENTO",nullable= false)
    @Schema(description="dia especifico en el que ocurre el evento", example="2025-06-24T15:30")
    private LocalDateTime fecha_evento;

}
