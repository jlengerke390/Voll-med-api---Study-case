package med.voll.api.domain.paciente;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.DireccionPacientes;

@Entity(name="Paciente")
@Table(name = "pacientes")
@EqualsAndHashCode(of = "id")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean activo;
    private String nombre;
    private String email;
    private String documento;
    private String telefono;

    @Embedded
    private DireccionPacientes direccion;

    public Paciente(DatoRegistroPaciente datos){
        this.activo = true;
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.telefono = datos.telefono();
        this.documento = datos.documento();
        this.direccion = new DireccionPacientes(datos.direccion());

    }

    public void actulizarDatos(DatosActualizarPaciente datosActualizarPaciente) {
        if(datosActualizarPaciente.nombre() != null){
            this.nombre = datosActualizarPaciente.nombre();
        }
        if(datosActualizarPaciente.documento() != null){
            this.documento = datosActualizarPaciente.documento();
        }
        if(datosActualizarPaciente.direccion() != null){
            this.direccion.actualizarDatos(datosActualizarPaciente.direccion());
        }
    }

    public void desactivarMedico() {
        this.activo = false;

    }
}
