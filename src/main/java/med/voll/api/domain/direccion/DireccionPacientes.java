package med.voll.api.domain.direccion;

import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DireccionPacientes {
    private String calle;
    private Integer numero;
    private String complemento;
    private String distrito;
    private String ciudad;
    private String urbanizacion;
    private String zip;
    private String provincia;

    public DireccionPacientes(DatosDireccionPacientes direccion) {
        this.calle = direccion.calle();
        this.numero = direccion.numero();
        this.complemento = direccion.complemento();
        this.distrito = direccion.distrito();
        this.ciudad = direccion.ciudad();
        this.urbanizacion = direccion.urbanizacion();
        this.zip = direccion.zip();
        this.provincia = direccion.provincia();
    }

    public void actualizarDatos(DatosDireccionPacientes direccion) {
        if(direccion.calle() != null){
            this.calle = direccion.calle();
        }
        if(direccion.numero() != null){
            this.numero = direccion.numero();
        }
        if(direccion.complemento() != null){
            this.complemento = direccion.complemento();
        }
        if(direccion.distrito() != null){
            this.distrito = direccion.distrito();
        }
        if(direccion.ciudad() != null){
            this.ciudad = direccion.ciudad();
        }
        if(direccion.urbanizacion() != null){
            this.urbanizacion = direccion.urbanizacion();
        }
        if(direccion.zip() != null){
            this.zip = direccion.zip();
        }
        if(direccion.provincia() !=null) {
            this.provincia = direccion.provincia();
        }
    }
}
