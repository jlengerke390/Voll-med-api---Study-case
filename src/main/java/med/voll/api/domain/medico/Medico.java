package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;

@Entity(name="Medico")
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    private boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(DatoRegistroMedico datosRegistroMedico) {
        this.activo = true;
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.telefono = datosRegistroMedico.telefono();
        this.documento = datosRegistroMedico.documento();
        this.especialidad = datosRegistroMedico.especialidad();
        this.direccion = new Direccion(datosRegistroMedico.direccion());

    }

    public void actulizarDatos(DatosActulizarMedico datosActulizarMedico) {
        if(datosActulizarMedico.nombre() != null){
            this.nombre = datosActulizarMedico.nombre();
        }
        if(datosActulizarMedico.documento() != null){
            this.documento = datosActulizarMedico.documento();
        }
        if(datosActulizarMedico.direccion() != null){
            this.direccion = direccion.actulizarDatos(datosActulizarMedico.direccion());
        }
    }

    public void desactivarMedico() {
        this.activo = false;
    }
}
