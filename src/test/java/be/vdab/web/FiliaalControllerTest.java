package be.vdab.web;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import be.vdab.entities.Filiaal;
import be.vdab.services.FiliaalService;
import be.vdab.valueobjects.Adres;

public class FiliaalControllerTest {
	private FiliaalController filiaalController;
	private FiliaalService filiaalService;
	private List<Filiaal> filialen;
	private Filiaal filiaal;
	private Locale locale = new Locale("nl", "BE");
	
	@Before
	public void before() {
		filialen = Collections.emptyList();
		filiaalService = mock(FiliaalService.class);
		when(filiaalService.findAll()).thenReturn(filialen);
		filiaalController = new FiliaalController(filiaalService);
		filiaal = new Filiaal("naam1", true, BigDecimal.ONE, LocalDate.now(), 
				new Adres("straat1", "huisnr1", 1, "gemeente1"));
		when(filiaalService.read(1)).thenReturn(Optional.of(filiaal));
		when(filiaalService.read(666)).thenReturn(Optional.empty());
	}
	
	@Test
	public void findAllActiveertJuisteView() {
		assertEquals("filialen/filialen", filiaalController.findAll(locale).getViewName());
	}
	
	@Test
	public void findAllMaaktRequestAttribuutFilialen() {
		assertSame(filialen, filiaalController.findAll(locale).getModelMap().get("filialen"));
	}
	
	@Test 
	public void readActiveertJuisteView() {   
		assertEquals("filialen/filiaal", filiaalController.read(1L).getViewName()); 
	}

	@Test
	public void readMetBestaandeIDGeeftFiliaalTerug() {
		assertSame(filiaal, filiaalController.read(1L).getModelMap().get("filiaal"));
	}
	
	@Test
	public void readMetOnbestaandeIDGeeftNullTerug() {
		assertNull(filiaalController.read(666L).getModelMap().get("filiaal"));
	}
}
