package cput.ac.za.infoshare.RestFulUtil.content.content;

import java.util.Set;

import cput.ac.za.infoshare.AppConf.RestUtil;
import cput.ac.za.infoshare.domain.content.PublishedContent;

/**
 * Created by user9 on 2016/02/23.
 */
public class PublishedContentAPI {
    public static PublishedContent save(PublishedContent publishedContent){
        return RestUtil.save(ContentBaseUrl.Published.POST, publishedContent, PublishedContent.class);
    }
    public static PublishedContent findById(String org, String id){
        String org_id = org+"/"+id;
        return RestUtil.getById(ContentBaseUrl.Published.GET,org_id,PublishedContent.class);
    }
    public static PublishedContent update(PublishedContent publishedContent){
        return RestUtil.update(ContentBaseUrl.Published.PUT,publishedContent);
    }
    public static Set<PublishedContent> findAll(String org){
        return RestUtil.getAll(ContentBaseUrl.Published.GET_ALL+org,PublishedContent.class);
    }
}
