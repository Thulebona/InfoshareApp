package cput.ac.za.infoshare.factories.content;


import java.util.Map;

import cput.ac.za.infoshare.AppConf.security.KeyGenerator;
import cput.ac.za.infoshare.domain.content.Source;


/**
 * Created by user9 on 2016/03/01.
 */
public class SourceFactory {

    public static Source getSource(Map<String,String> sourceVals){
        Source source = new Source.Builder()
                .id(KeyGenerator.getEntityId())
                .org(sourceVals.get("org"))
                .name(sourceVals.get("name"))
                .description(sourceVals.get("description"))
                .build();
        return source;
    }
}
