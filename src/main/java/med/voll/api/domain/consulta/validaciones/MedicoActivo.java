package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendaConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoActivo implements ValidadorDeConsultas{
    @Autowired
    private MedicoRepository medicoRepository;
    public void validar(DatosAgendaConsulta datos){

        if(datos.idMedico() == null){
            return;
        }

        var medicoActivo = medicoRepository.findActivoBy(datos.idMedico());
        if(!medicoActivo){
            throw new ValidationException("No se puede agendar citas con medicos inactivos en el sistema");
        }
    }
}
