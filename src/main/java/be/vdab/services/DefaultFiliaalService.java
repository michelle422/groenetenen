package be.vdab.services;

import java.util.List;
import java.util.Optional;

import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.repositories.FiliaalRepository;
import be.vdab.valueobjects.PostcodeReeks;

@ReadOnlyTransactionalService
class DefaultFiliaalService implements FiliaalService {
	private final FiliaalRepository filiaalRepository;
	
	public DefaultFiliaalService(FiliaalRepository filiaalRepository) {
		this.filiaalRepository = filiaalRepository;
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void create(Filiaal filiaal) {
		filiaalRepository.create(filiaal);
	}

	@Override
	public Optional<Filiaal> read(long id) {
		return filiaalRepository.read(id);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void update(Filiaal filiaal) {
		filiaalRepository.update(filiaal);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void delete(long id) {
		Optional<Filiaal> optionalFiliaal = filiaalRepository.read(id);
		if (optionalFiliaal.isPresent()) {
			if (!optionalFiliaal.get().getWerknemers().isEmpty()) {
				throw new FiliaalHeeftNogWerknemersException();
			}
			filiaalRepository.delete(id);
		}
	}

	@Override
	public List<Filiaal> findAll() {
		return filiaalRepository.findAll();
	}

	@Override
	public long findAantalFilialen() {
		return filiaalRepository.findAantalFilialen();
	}

	@Override
	public List<Filiaal> findByPostcodeReeks(PostcodeReeks reeks) {
		return filiaalRepository.findByPostcodeReeks(reeks);
	}
	
}
