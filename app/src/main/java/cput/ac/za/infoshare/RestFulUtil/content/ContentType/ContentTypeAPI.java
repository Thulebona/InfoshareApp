package cput.ac.za.infoshare.RestFulUtil.content.ContentType;


import java.util.Set;

import cput.ac.za.infoshare.AppConf.RestUtil;
import cput.ac.za.infoshare.domain.content.ContentType;

/**
 * Created by user9 on 2016/02/23.
 */
public class ContentTypeAPI {

    public static ContentType save(ContentType contentType){
        return RestUtil.save(ContentTypeBaseUrl.ContentType.POST, contentType, ContentType.class);
    }
    public static ContentType findById(String id){
        return RestUtil.getById(ContentTypeBaseUrl.ContentType.GET,id,ContentType.class);
    }
    public static ContentType update(ContentType contentType){
        return RestUtil.update(ContentTypeBaseUrl.ContentType.PUT,contentType);
    }
    public static Set<ContentType> findAll(){
        return RestUtil.getAll(ContentTypeBaseUrl.ContentType.GET_ALL,ContentType.class);
    }
}
