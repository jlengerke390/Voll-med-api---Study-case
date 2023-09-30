package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.DatosAgendaConsulta;
import med.voll.api.domain.consulta.DatosDetalleConsulta;
import med.voll.api.domain.consulta.agendaDeConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private agendaDeConsultaService service;

    @PostMapping
    @Transactional
    public ResponseEntity agendar (@RequestBody @Valid DatosAgendaConsulta datos) {

        service.agendar(datos);

        return ResponseEntity.ok(new DatosDetalleConsulta(null, null, null, null));
    }
}
