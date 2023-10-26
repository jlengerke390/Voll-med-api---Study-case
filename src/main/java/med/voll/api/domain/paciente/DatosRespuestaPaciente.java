package med.voll.api.domain.paciente;


import med.voll.api.domain.direccion.DatosDireccionPacientes;

public record DatosRespuestaPaciente(
        Long id,
        String nombre,
        String email,
        String telefono,
        DatosDireccionPacientes direccion

) {
}
