package cput.ac.za.infoshare.RestFulUtil.content.Source;


import java.util.Set;

import cput.ac.za.infoshare.AppConf.RestUtil;
import cput.ac.za.infoshare.domain.content.Source;

/**
 * Created by user9 on 2016/02/23.
 */
public class SourceAPI {
    public static Source save(Source source){
        return RestUtil.save(SourceBaseUrl.Source.POST, source, Source.class);
    }
    public static Source findById(String org ,String id){
        return RestUtil.getById(SourceBaseUrl.Source.GET_ID,org+"/"+id,Source.class);
    }
    public static Source update(Source source){
        return RestUtil.update(SourceBaseUrl.Source.PUT,source);
    }
    public static Set<Source> findAll(String org){
        return RestUtil.getAll(SourceBaseUrl.Source.GET_ALL+org,Source.class);
    }
}
