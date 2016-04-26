package cput.ac.za.infoshare.factories.content;


import java.util.Date;
import java.util.Map;

import cput.ac.za.infoshare.AppConf.security.DomainState;
import cput.ac.za.infoshare.AppConf.security.KeyGenerator;
import cput.ac.za.infoshare.domain.content.RawContent;


/**
 * Created by user9 on 2016/03/01.
 */
public class RawContentFactory {

    public static RawContent getRawContent(Map<String,String> rawContentVals){
        RawContent rawContent = new RawContent.Builder()
                .id(KeyGenerator.getEntityId())
                .dateCreated(new Date())
                .creator(rawContentVals.get("creator"))
                .source(rawContentVals.get("source"))
                .category(rawContentVals.get("category"))
                .title(rawContentVals.get("title"))
                .content(rawContentVals.get("content"))
                .contentType(rawContentVals.get("contentType"))
                .status(rawContentVals.get("status"))
                .state(DomainState.ACTIVE.name())
                .org(rawContentVals.get("org"))
                .build();
        return rawContent;
    }
}
