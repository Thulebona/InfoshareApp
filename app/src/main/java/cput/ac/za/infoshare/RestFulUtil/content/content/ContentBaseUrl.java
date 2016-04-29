package cput.ac.za.infoshare.RestFulUtil.content.content;


import cput.ac.za.infoshare.AppConf.RestUtil;

/**
 * Created by user9 on 2016/02/23.
 */
public class ContentBaseUrl {

    public static class Raw {

        public static final String POST    = RestUtil.URL+"/api/cont/raw/create";  //@controllers.RawContentController.create
        public static final String GET     =RestUtil.URL+"/api/cont/raw/";  //@controllers.RawContentController.getContent(id)
        public static final String PUT     =RestUtil.URL+"/api/cont/raw/update";  //@controllers.RawContentController.update
        public static final String GET_ALL = RestUtil.URL+"/api/cont/raw/get/conts/";  //@controllers.RawContentController.getAll
    }

    public static class Published {

        public static final String POST    =RestUtil.URL+"/api/cont/pub/create";      //@controllers.PublishedContentController.create
        public static final String GET     =RestUtil.URL+"/api/cont/pub/";      //@controllers.PublishedContentController.getContent(id)
        public static final String PUT     =RestUtil.URL+"/api/cont/pub/update";      //@controllers.PublishedContentController.update
        public static final String GET_ALL =RestUtil.URL+"/api/cont/pub/get/conts/";      //@controllers.PublishedContentController.getAll
    }
       /*

POST        /api/cont/ed/create                         @controllers.content.EditedContentController.create
GET         /api/cont/ed/:orgId/:id                     @controllers.content.EditedContentController.getContentById(orgId,id)
PUT         /api/cont/ed/update                         @controllers.content.EditedContentController.update
GET         /api/cont/ed/get/conts/:orgId               @controllers.content.EditedContentController.getAll(orgId)  */
}
