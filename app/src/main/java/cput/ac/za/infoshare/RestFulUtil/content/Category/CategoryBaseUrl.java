package cput.ac.za.infoshare.RestFulUtil.content.Category;


import cput.ac.za.infoshare.AppConf.RestUtil;

/**
 * Created by user9 on 2016/02/23.
 */
public class CategoryBaseUrl {

    public static class Category {
      public static final String POST    = RestUtil.URL+"/api/cat/create";   //@controllers.CategoryController.create
      public static final String GET     =RestUtil.URL+"/api/cat/";          //@controllers.CategoryController.getCategory(id)
      public static final String PUT     =RestUtil.URL+"/api/cat/update";    //@controllers.CategoryController.update
      public static final String GET_ALL = RestUtil.URL+"/api/cat/get/cats";  //@controllers.CategoryController.getAll
    }

}
