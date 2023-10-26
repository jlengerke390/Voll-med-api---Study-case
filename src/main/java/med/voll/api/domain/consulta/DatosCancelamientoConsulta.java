package med.voll.api.domain.consulta;

public record DatosCancelamientoConsulta(Long idConsulta , MotivoCancelamiento motivo) {

    public DatosCancelamientoConsulta(Consulta consulta){

        this(consulta.getId(), consulta.getMotivoCancelamiento());

    }
}
