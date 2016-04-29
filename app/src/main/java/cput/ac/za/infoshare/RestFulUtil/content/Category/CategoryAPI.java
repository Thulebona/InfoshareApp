package cput.ac.za.infoshare.RestFulUtil.content.Category;


import java.util.Set;

import cput.ac.za.infoshare.AppConf.RestUtil;
import cput.ac.za.infoshare.domain.content.Category;

/**
 * Created by user9 on 2016/02/23.
 */
public class CategoryAPI {
    public static Category save(Category category){
        return RestUtil.save(CategoryBaseUrl.Category.POST,category,Category.class);
    }
    public static Category findById(String id){
        return RestUtil.getById(CategoryBaseUrl.Category.GET,id,Category.class);
    }
    public static Category update(Category category){
        return RestUtil.update(CategoryBaseUrl.Category.PUT,category);
    }
    public static Set<Category> findAll(){
        return RestUtil.getAll(CategoryBaseUrl.Category.GET_ALL,Category.class);
    }
}
