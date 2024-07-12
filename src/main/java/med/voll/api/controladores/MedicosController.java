package med.voll.api.controladores;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.direccion.DatosDireccion;
import med.voll.api.dominio.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicosController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping()
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody
                                          @Valid
                                          DatosRegistroMedico datosRegistroMedico,
                                          UriComponentsBuilder uriComponentsBuilder) {

        Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getDocumento(),
                medico.getEspecialidad().toString(),
                new DatosDireccion(
                        medico.getDireccion().getCalle(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento(),
                        medico.getDireccion().getBarrio(),
                        medico.getDireccion().getCiudad()
                )
        );
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMedico);

    }

    @GetMapping()
    public ResponseEntity<Page<ListadoDeDatosMedicos>> obtenerTodosLosMedicos(@PageableDefault(size = 5) Pageable pagina){
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(pagina)
                .map(ListadoDeDatosMedicos::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> obtenerUnMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getDocumento(),
                medico.getEspecialidad().toString(),
                new DatosDireccion(
                        medico.getDireccion().getCalle(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento(),
                        medico.getDireccion().getBarrio(),
                        medico.getDireccion().getCiudad()));
        return ResponseEntity.ok(datosRespuestaMedico);
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<DatosRespuestaMedico> actualizarMedico(@RequestBody @Valid DatosActualizaciónMedico datosActualizacionMedico){
        Medico medico = medicoRepository.getReferenceById(datosActualizacionMedico.id());
        medico.actualizarDatos(datosActualizacionMedico);
        return ResponseEntity.ok(new DatosRespuestaMedico(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getDocumento(),
                medico.getEspecialidad().toString(),
                new DatosDireccion(
                        medico.getDireccion().getCalle(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento(),
                        medico.getDireccion().getBarrio(),
                        medico.getDireccion().getCiudad())));
    }

    //Logical Delete o Borrado Lógico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

    /*
    Literal Delete o Borrado Literal
    @DeleteMapping("/{id}")
    @Transactional
    public void borrarMedico(@PathVariable Long id)
        Medico medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
    */

}

