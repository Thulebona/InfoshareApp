package cput.ac.za.infoshare.factories.people;


import java.util.Date;

import cput.ac.za.infoshare.AppConf.security.DomainState;
import cput.ac.za.infoshare.AppConf.security.KeyGenerator;
import cput.ac.za.infoshare.domain.person.PersonContact;

/**
 * Created by codet on 2016/02/23.
 */
public class PersonContactFactory {

    public static PersonContact getContact(String personId, String addressTypeId, String contactValue, String status)
    {
        PersonContact contact = new PersonContact.Builder()
                .id(KeyGenerator.getEntityId())
                .personId(personId)
                .addresstypeid(addressTypeId)
                .contactvalue(contactValue)
                .state(DomainState.ACTIVE.name())
                .date(new Date())
                .status(status)
                .build();

        return contact;
    }
}
