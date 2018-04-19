package be.vdab.restclients;

import java.math.BigDecimal;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import be.vdab.exceptions.KanKoersNietLezenException;

@Component
class FixerKoersenClient implements KoersenClient {
	private static final Logger LOGGER = Logger.getLogger(FixerKoersenClient.class.getName());
	private final URI fixerURL;
	private final RestTemplate restTemplate;
	
	public FixerKoersenClient(@Value("${fixerKoersenURL}") URI fixerURL, RestTemplate restTemplate) {
		this.fixerURL = fixerURL;
		this.restTemplate = restTemplate;
	}

	@Override
	public BigDecimal getDollarKoers() {
		try {
			USDRate rate = restTemplate.getForObject(fixerURL, USDRate.class);
			return rate.getRates().getUSD();
		} catch (RestClientException ex) {
			LOGGER.log(Level.SEVERE, "kan koers niet lezen", ex);
			throw new KanKoersNietLezenException();
		}
	}
	
}
