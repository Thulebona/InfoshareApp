package cput.ac.za.infoshare.factories.people;


import java.util.Date;

import cput.ac.za.infoshare.AppConf.security.DomainState;
import cput.ac.za.infoshare.AppConf.security.KeyGenerator;
import cput.ac.za.infoshare.domain.person.PersonDemographics;


/**
 * Created by user9 on 2016/03/02.
 */
public class PersonDemographicsFactory {
    public static PersonDemographics getPersonDemographics(
            String personId, String genderId, Date dateOfBirth, String raceId){
        PersonDemographics personDemographics = new PersonDemographics.Builder()
                .id(KeyGenerator.getEntityId())
                .genderId(genderId)
                .personId(personId)
                .personraceid(raceId)
                .dateOfBirth(dateOfBirth)
                .date(new Date())
                .state(DomainState.ACTIVE.name())
                .build();

        return personDemographics;

    }
}
