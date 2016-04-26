package cput.ac.za.infoshare.factories.people;


import java.util.Date;
import java.util.Map;

import cput.ac.za.infoshare.AppConf.security.KeyGenerator;
import cput.ac.za.infoshare.domain.person.PersonImages;


/**
 * Created by user9 on 2016/04/25.
 */
public class PersonImagesFactory {

    public static PersonImages getPersonImages(Map<String,String> map){
        return  new PersonImages.Builder()
                .id(KeyGenerator.getEntityId())
                .personId(map.get("personId"))
                .org(map.get("org"))
                .url(map.get("url"))
                .description(map.get("description"))
                .mime(map.get("mime"))
                .size(map.get("size"))
                .date(new Date())
                .build();

    }

}
