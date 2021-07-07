package cl.apiux.coopeuch.service;

import cl.apiux.coopeuch.dto.TareaDTO;

import java.util.List;

public interface TareaService {

    public List<TareaDTO> listarTodas();

    public void agregarTarea(TareaDTO tarea);

    public void eliminarTarea(Integer identificador);

    public TareaDTO buscarPorID(Integer identificador);
}
