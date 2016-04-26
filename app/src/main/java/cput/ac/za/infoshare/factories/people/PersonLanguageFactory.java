package cput.ac.za.infoshare.factories.people;


import java.util.Date;

import cput.ac.za.infoshare.AppConf.security.DomainState;
import cput.ac.za.infoshare.AppConf.security.KeyGenerator;
import cput.ac.za.infoshare.domain.person.PersonLanguage;


/**
 * Created by user9 on 2016/03/02.
 */
public class PersonLanguageFactory {
    public static PersonLanguage getPersonLanguage(
            String personId,
            String languageId, String reading, String writing, String speaking){
        return new  PersonLanguage.Builder()
                .id(KeyGenerator.getEntityId())
                .personId(personId)
                .languageId(languageId)
                .reading(reading)
                .writing(writing)
                .speaking(speaking)
                .date(new Date())
                .state(DomainState.ACTIVE.name())
                .build();
    }
}
