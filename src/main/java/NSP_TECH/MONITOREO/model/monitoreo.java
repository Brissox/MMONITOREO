package NSP_TECH.MONITOREO.model;

import java.util.Date;

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
    @Schema(description="aa")
    private Long id_monitoreo;

    @Column(name="COMPONENTE",nullable = false,length = 50)
    @Schema(description="aa")
    private String componente;

    @Column(name="MENSAJE",nullable =false ,length = 255)
    @Schema(description="aa")
    private String mensaje;

    @Column(name="TIPO_EVENTO",nullable = false ,length = 30)
    @Schema(description="aa")
    private String tipo_evento;

    @Column(name="NIVEL_ALERTA",nullable= true, length=20)
    @Schema(description="aa")
    private String nivel_alerta;

    @Column(name="FECHA_EVENTO",nullable= false)
    @Schema(description="aa")
    private Date fecha_evento;

}
