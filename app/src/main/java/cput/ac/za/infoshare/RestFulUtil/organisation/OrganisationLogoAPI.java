package cput.ac.za.infoshare.RestFulUtil.organisation;


import java.util.Set;

import cput.ac.za.infoshare.AppConf.RestUtil;
import cput.ac.za.infoshare.domain.organisation.OrganisationLogo;

/**
 * Created by user9 on 2016/04/21.
 */
public class OrganisationLogoAPI {
    public static OrganisationLogo save(OrganisationLogo entity){
        return RestUtil.save(OrganisationBaseUrl.OrganisationLogo.POST, entity, OrganisationLogo.class);
    }
    public static OrganisationLogo findById(String org,String id){
        return RestUtil.getById(OrganisationBaseUrl.OrganisationLogo.GET_ID,org+"/"+id,OrganisationLogo.class);
    }
    public static Set<OrganisationLogo> findAll(String org){
        return RestUtil.getAll(OrganisationBaseUrl.OrganisationLogo.GET_ALL+org,OrganisationLogo.class);
    }
}