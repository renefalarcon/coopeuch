package cl.apiux.coopeuch;

import cl.apiux.coopeuch.controller.TareaDTOController;
import cl.apiux.coopeuch.dto.TareaDTO;
import cl.apiux.coopeuch.service.TareaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.Date;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CoopeuchApplicationTests {

	@Test
	void contextLoads() {
	}

	@InjectMocks
	TareaDTOController tareaDTOController;

	@Mock
	TareaService tareaService;

	@Test
	public void Agregar(){
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		TareaDTO employee = new TareaDTO();
		employee.setIdentificador(15);
		employee.setDescripcion("aaa");
		employee.setFechaCreacion(new Date());
		employee.setVigente(1);
		ResponseEntity<TareaDTO> responseEntity = tareaDTOController.guardarTareas(employee);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
	}

	@Test
	public void listardAll() {
		ResponseEntity<List<TareaDTO>> result = tareaDTOController.listarTareas();
		assertThat(result.getStatusCodeValue()).isEqualTo(204);
	}

	@Test
	public void eliminar() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		TareaDTO employee = new TareaDTO();
		Mockito.when(tareaService.buscarPorID(1)).thenReturn(employee);
		ResponseEntity<TareaDTO> responseEntity = tareaDTOController.eliminarTareas(1);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void modificar() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		TareaDTO employee = new TareaDTO();
		employee.setIdentificador(15);
		employee.setDescripcion("aaa");
		employee.setVigente(1);
		ResponseEntity<TareaDTO> responseEntity = tareaDTOController.modificarTareas(employee);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

}
