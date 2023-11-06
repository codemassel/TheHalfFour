package ShopApp.Service;

import ShopApp.Model.Zipcode;
import ShopApp.Repository.ZipcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ZipcodeService {
    private final ZipcodeRepository zipRep;

    @Autowired
    public ZipcodeService(ZipcodeRepository zipRep) {
        this.zipRep = zipRep;
    }

    public Zipcode createZipcode(Zipcode zipcode) {
        // Überprüfen, ob die Zipcode bereits in der Datenbank vorhanden ist
        Zipcode existingZipcode = zipRep.findByZipcode(zipcode.getZipcode());

        if (existingZipcode == null) {
            // Wenn die Zipcode nicht in der Datenbank existiert, dann speichern
            return zipRep.save(zipcode);
        } else {
            // Wenn die Zipcode bereits existiert, gib einfach die existierende Zipcode zurück
            return existingZipcode;
        }
    }
}
