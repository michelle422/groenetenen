package be.vdab.restservices;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

import be.vdab.entities.Filiaal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
class FilialenResource extends ResourceSupport {
	@XmlElement(name = "filiaal")
	@JsonProperty("filialen")
	private final List<FiliaalIdNaam> filialenIdNaam = new ArrayList<>();
	
	FilialenResource() {}
	
	FilialenResource(Iterable<Filiaal> filialen, EntityLinks entityLinks) {
		for (Filiaal filiaal : filialen) {
			this.filialenIdNaam.add(new FiliaalIdNaam(filiaal));
			this.add(entityLinks.linkToSingleResource(Filiaal.class, filiaal.getId())
					.withRel("detail"));
		}
		this.add(entityLinks.linkToCollectionResource(Filiaal.class));
	}
}
