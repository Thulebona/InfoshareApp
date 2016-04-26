package cput.ac.za.infoshare.factories.people;

import java.util.Date;
import java.util.Map;

import cput.ac.za.infoshare.AppConf.security.DomainState;
import cput.ac.za.infoshare.AppConf.security.KeyGenerator;
import cput.ac.za.infoshare.domain.person.PersonAddress;

/**
 * Created by user9 on 2016/04/25.
 */
public class PersonAddressFactory {

    public static PersonAddress personAddress(Map<String,String>map){
        return new PersonAddress.Builder()
                .id(KeyGenerator.getEntityId())
                .personId(map.get("personId"))
                .description(map.get("description"))
                .date(new Date())
                .state(DomainState.ACTIVE.name())
                .postalCode(map.get("postalCode"))
                .addressTypeId(map.get("addressTypeId"))
                .build();
    }
}
