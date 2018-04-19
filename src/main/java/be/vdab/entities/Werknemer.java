package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

@Entity
@Table(name="werknemers")
@NamedEntityGraph(name = "Werknemer.metFiliaal", attributeNodes = @NamedAttributeNode("filiaal"))
public class Werknemer implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String voornaam;
	private String familienaam;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "filiaalId")
	private Filiaal filiaal;
	private BigDecimal wedde;
	@Column(unique = true)
	private long rijksregisterNr;
	
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public String getFamilienaam() {
		return familienaam;
	}
	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}
	public BigDecimal getWedde() {
		return wedde;
	}
	public void setWedde(BigDecimal wedde) {
		this.wedde = wedde;
	}
	public long getRijksregisterNr() {
		return rijksregisterNr;
	}
	public void setRijksregisterNr(long rijksregisterNr) {
		this.rijksregisterNr = rijksregisterNr;
	}
	public Filiaal getFiliaal() {
		return filiaal;
	}
	
	@Override
	public int hashCode() {
		return Long.valueOf(rijksregisterNr).hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Werknemer)) {
			return false;
		}
		return ((Werknemer) obj).rijksregisterNr == rijksregisterNr;
	}
	
}
