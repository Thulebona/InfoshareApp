package cput.ac.za.infoshare.factories.content;


import java.util.Date;
import java.util.Map;

import cput.ac.za.infoshare.AppConf.security.DomainState;
import cput.ac.za.infoshare.AppConf.security.KeyGenerator;
import cput.ac.za.infoshare.domain.content.PublishedContent;

/**
 * Created by user9 on 2016/03/01.
 */
public class PublishedContentFactory {

        public static PublishedContent getPublishedContent(Map<String,String> publishedContentVals, Date date) {
                PublishedContent publishedContent = new PublishedContent.Builder()
                        .id(KeyGenerator.getEntityId())
                        .dateCreated(date)
                        .creator(publishedContentVals.get("creator"))
                        .source(publishedContentVals.get("source"))
                        .category(publishedContentVals.get("category"))
                        .title(publishedContentVals.get("title"))
                        .content(publishedContentVals.get("content"))
                        .contentType(publishedContentVals.get("contentType"))
                        .status(publishedContentVals.get("status"))
                        .state(DomainState.ACTIVE.name())
                        .org(publishedContentVals.get("org"))
                        .build();
                return publishedContent;
        }
}
