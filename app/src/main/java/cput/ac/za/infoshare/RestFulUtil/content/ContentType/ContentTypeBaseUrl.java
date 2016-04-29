package cput.ac.za.infoshare.RestFulUtil.content.ContentType;

import cput.ac.za.infoshare.AppConf.RestUtil;

/**
 * Created by user9 on 2016/02/23.
 */
public class ContentTypeBaseUrl {

    public static class ContentType{
      public static final String POST    = RestUtil.URL+"/api/ctype/create";  //@controllers.ContntTypeController.create
      public static final String GET     = RestUtil.URL+"/api/ctype/";  //@controllers.ContntTypeController.getContentType(id)
      public static final String PUT     =RestUtil.URL+"/api/ctype/update";  //@controllers.ContntTypeController.update
      public static final String GET_ALL =RestUtil.URL+"/api/ctype/get/ctypes";  //@controllers.ContntTypeController.getAll
    }

}
