package ro.rosmof.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.rosmof.model.ModelDatastore;
import ro.rosmof.model.Registration;

@Repository(value = "registrationRepository")
public class RegistrationRepository extends AbstractRepository<Registration> {
    @Autowired
    public RegistrationRepository(ModelDatastore<Registration> ds) {
        super(ds);
    }
}
