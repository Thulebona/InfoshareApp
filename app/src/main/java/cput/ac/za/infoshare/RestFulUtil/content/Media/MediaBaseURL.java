package cput.ac.za.infoshare.RestFulUtil.content.Media;

import cput.ac.za.infoshare.AppConf.RestUtil;

/**
 * Created by user9 on 2016/03/03.
 */
public class MediaBaseURL {

    public static class Media{
     public static final String POST     = RestUtil.URL+"/api/media/create"; // @controllers.content.MediaController.create
     public static final String GET_ID   =RestUtil.URL+"/api/media/"; // @controllers.content.MediaController.getContent(contentId,id)
     public static final String PUT      =RestUtil.URL+"/api/media/update"; // @controllers.content.MediaController.update
     public static final String GET_ALL  =RestUtil.URL+"/api/media/get/"; // @controllers.content.MediaController.getAll(contentId)

    }
}
