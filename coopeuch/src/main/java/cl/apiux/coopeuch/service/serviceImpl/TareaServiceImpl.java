package cl.apiux.coopeuch.service.serviceImpl;

import cl.apiux.coopeuch.dto.TareaDTO;
import cl.apiux.coopeuch.repository.TareaRepository;
import cl.apiux.coopeuch.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    TareaRepository tareaRepository;


    @Override
    public List<TareaDTO> listarTodas() {
        return tareaRepository.findAll();
    }

    @Override
    public void agregarTarea(TareaDTO tarea) {
        try {
            if (tarea.getIdentificador() == null){
                tarea.setVigente(1);
                tareaRepository.save(tarea);
            } else {
                Optional<TareaDTO> optional = tareaRepository.findById(tarea.getIdentificador());
                if(optional.isPresent()) {
                    TareaDTO tareaDTO = new TareaDTO();
                    tareaDTO.setIdentificador(optional.get().getIdentificador());
                    tareaDTO.setDescripcion(tarea.getDescripcion());
                    tareaDTO.setFechaCreacion(optional.get().getFechaCreacion());
                    tareaDTO.setVigente(tarea.getVigente());
                    tareaRepository.save(tareaDTO);
                }
            }

        } catch(Exception e) {
            System.out.println("Problemas al registrar");
        }
    }

    @Override
    public void eliminarTarea(Integer identificador) {
        try {
            if (identificador !=null) {
                Optional<TareaDTO> optional = tareaRepository.findById(identificador);
                if(optional.isPresent()) {
                    tareaRepository.deleteById(identificador);
                }
            } else {
                System.out.println("No existe el "+ identificador);
            }
        } catch (Exception e) {
            System.out.println("Problemas al eliminar");
        }
    }

    @Override
    public TareaDTO buscarPorID(Integer identificador) {
        Optional<TareaDTO> optional = tareaRepository.findById(identificador);
        if(optional.isPresent()) {
            TareaDTO tareaDTO = new TareaDTO();
            tareaDTO.setIdentificador(optional.get().getIdentificador());
            tareaDTO.setDescripcion(optional.get().getDescripcion());
            tareaDTO.setFechaCreacion(optional.get().getFechaCreacion());
            tareaDTO.setVigente(optional.get().getVigente());
            return tareaDTO;
        } else {
            return null;
        }
    }
}
