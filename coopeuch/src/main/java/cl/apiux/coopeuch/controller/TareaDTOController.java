package cl.apiux.coopeuch.controller;

import cl.apiux.coopeuch.dto.TareaDTO;
import cl.apiux.coopeuch.exception.TareasNotFoundException;
import cl.apiux.coopeuch.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/coopeuch")
public class TareaDTOController {

    @Autowired
    private TareaService tareaService;

    @GetMapping("/listarTareas")
    public ResponseEntity<List<TareaDTO>> listarTareas(){
        try {
            List<TareaDTO> tareaDTOS = tareaService.listarTodas();
            if(tareaDTOS.size() ==0 || tareaDTOS == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(tareaDTOS, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/agregarTareas")
    public ResponseEntity<TareaDTO> guardarTareas(@Valid @RequestBody TareaDTO tareaDTO) {
        try {
            tareaService.agregarTarea(tareaDTO);
            return new ResponseEntity<>(tareaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/modificarTareas")
    public ResponseEntity<TareaDTO> modificarTareas(@RequestBody TareaDTO tareaDTO) {
        try {
            tareaService.agregarTarea(tareaDTO);
            TareaDTO tareaDTO2 = tareaService.buscarPorID(tareaDTO.getIdentificador());
            return new ResponseEntity<>(tareaDTO2, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminarTareas/{identificador}")
    public ResponseEntity<TareaDTO> eliminarTareas(@PathVariable Integer identificador) throws Exception {

            TareaDTO tareaDTO = tareaService.buscarPorID(identificador);
            if(tareaDTO == null) {
                throw new TareasNotFoundException("CLIENTE NO ENCONTRADO");
            }
        tareaService.eliminarTarea(identificador);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
