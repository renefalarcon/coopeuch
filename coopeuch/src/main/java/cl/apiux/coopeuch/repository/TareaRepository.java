package cl.apiux.coopeuch.repository;

import cl.apiux.coopeuch.dto.TareaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<TareaDTO,Integer> {
}
