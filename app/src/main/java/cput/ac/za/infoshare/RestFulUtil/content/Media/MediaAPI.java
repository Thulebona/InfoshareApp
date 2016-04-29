package cput.ac.za.infoshare.RestFulUtil.content.Media;


import java.util.Set;

import cput.ac.za.infoshare.AppConf.RestUtil;
import cput.ac.za.infoshare.domain.content.Media;

/**
 * Created by user9 on 2016/03/03.
 */
public class MediaAPI {
    public static Media save(Media media){
        return RestUtil.save(MediaBaseURL.Media.POST, media, Media.class);
    }
    public static Media findById(String org ,String id){
        return RestUtil.getById(MediaBaseURL.Media.GET_ID,org+"/"+id,Media.class);
    }
    public static Media update(Media media){
        return RestUtil.update(MediaBaseURL.Media.PUT,media);
    }
    public static Set<Media> findAll(String org){
        return RestUtil.getAll(MediaBaseURL.Media.GET_ALL+org,Media.class);
    }
}
