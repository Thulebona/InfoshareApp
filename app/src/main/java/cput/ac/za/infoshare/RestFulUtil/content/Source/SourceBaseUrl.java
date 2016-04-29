package infoshare.restapi.ContentFiles.Source;

import infoshare.app.conf.RestUtil;

/**
 * Created by user9 on 2016/02/23.
 */
public class SourceBaseUrl {
    public static class Source{
      public static final String  POST    =RestUtil.URL+"/api/src/create";    //@controllers.SourceController.create
      public static final String  GET_ID  =RestUtil.URL+"/api/src/";    //@controllers.SourceController.getSource(id)
      public static final String  PUT     =RestUtil.URL+"/api/src/update";    //@controllers.SourceController.update
      public static final String  GET_ALL =RestUtil.URL+"/api/src/get/srcs/";    //@controllers.SourceController.getAll
    }


}
