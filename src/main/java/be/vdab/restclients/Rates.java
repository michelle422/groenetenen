package be.vdab.restclients;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

class Rates {
	@JsonProperty("USD")
	private BigDecimal USD;

	public BigDecimal getUSD() {
		return USD;
	}

	public void setUSD(BigDecimal uSD) {
		USD = uSD;
	}
	
}
