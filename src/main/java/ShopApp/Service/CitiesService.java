package ShopApp.Service;

import ShopApp.Repository.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CitiesService {
    private final CitiesRepository zipRep;

    @Autowired
    public CitiesService(CitiesRepository zipRep) {
        this.zipRep = zipRep;
    }

    /*
    public Cities createZipcode(Cities cities) {
        // Überprüfen, ob die Cities bereits in der Datenbank vorhanden ist
        Cities existingCities = zipRep.findByZipcode(cities.getZipcode());

        if (existingCities == null) {
            // Wenn die Cities nicht in der Datenbank existiert, dann speichern
            return zipRep.save(cities);
        } else {
            // Wenn die Cities bereits existiert, gib einfach die existierende Cities zurück
            return existingCities;
        }
    }
    */
}
