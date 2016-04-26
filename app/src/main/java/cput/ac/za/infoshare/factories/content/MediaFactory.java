package cput.ac.za.infoshare.factories.content;


import java.util.Date;
import java.util.Map;

import cput.ac.za.infoshare.AppConf.security.DomainState;
import cput.ac.za.infoshare.AppConf.security.KeyGenerator;
import cput.ac.za.infoshare.domain.content.Media;


/**
 * Created by user9 on 2016/03/01.
 */
public class MediaFactory {

    public static Media getMedia(Map<String, String> mediaVals){
        Media media = new Media.Builder()
                .id(KeyGenerator.getEntityId())
                .contentid(mediaVals.get("contentId"))
                .description(mediaVals.get("description"))
                .url(mediaVals.get("url"))
                .mime(mediaVals.get("mime"))
                .state(DomainState.ACTIVE.name())
                .date(new Date())
                .build();
        return media;
    }
}
