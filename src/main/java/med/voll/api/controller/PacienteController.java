package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DatosDireccionPacientes;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPaciente> registrarPaciente(@RequestBody @Valid DatoRegistroPaciente datos, UriComponentsBuilder uriComponentsBuilder){
       Paciente paciente = repository.save(new Paciente(datos));
        DatosRespuestaPaciente datosRespuestaPaciente = new DatosRespuestaPaciente(paciente.getId(), paciente.getNombre(),
                paciente.getEmail(), paciente.getTelefono(), new DatosDireccionPacientes(paciente.getDireccion().getCalle(),
                paciente.getDireccion().getDistrito(), paciente.getDireccion().getCiudad(), paciente.getDireccion().getNumero(),
                paciente.getDireccion().getComplemento(), paciente.getDireccion().getUrbanizacion(), paciente.getDireccion().getZip(),
                paciente.getDireccion().getProvincia()));

        URI url = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaPaciente);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoPaciente>> listadoPacientes(@PageableDefault(size = 10, sort = {"nombre"})  Pageable paginacion){
        return ResponseEntity.ok(repository.findAllByActivoTrue(paginacion).map(DatosListadoPaciente::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPaciente> actualizarPaciente(@RequestBody @Valid DatosActualizarPaciente datosActulizarPaciente){
        var paciente = repository.getReferenceById(datosActulizarPaciente.id());
        paciente.actulizarDatos(datosActulizarPaciente);
        return ResponseEntity.ok(new DatosRespuestaPaciente(paciente.getId(), paciente.getNombre(),
                paciente.getEmail(), paciente.getTelefono(), new DatosDireccionPacientes(paciente.getDireccion().getCalle(),
                paciente.getDireccion().getDistrito(), paciente.getDireccion().getCiudad(), paciente.getDireccion().getNumero(),
                paciente.getDireccion().getComplemento(), paciente.getDireccion().getUrbanizacion(), paciente.getDireccion().getZip(),
                paciente.getDireccion().getProvincia())));
    }
    //DELETE LOGICO
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<DatosRespuestaPaciente> retornaDatosPaciente(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        var datosPaciente = new DatosRespuestaPaciente(paciente.getId(), paciente.getNombre(),
                paciente.getEmail(), paciente.getTelefono(), new DatosDireccionPacientes(paciente.getDireccion().getCalle(),
                paciente.getDireccion().getDistrito(), paciente.getDireccion().getCiudad(), paciente.getDireccion().getNumero(),
                paciente.getDireccion().getComplemento(), paciente.getDireccion().getUrbanizacion(), paciente.getDireccion().getZip(),
                paciente.getDireccion().getProvincia()));
        return ResponseEntity.ok(datosPaciente);
    }
}
