package cput.ac.za.infoshare.RestFulUtil.organisation;


import java.util.Set;

import cput.ac.za.infoshare.AppConf.RestUtil;
import cput.ac.za.infoshare.domain.organisation.Organisation;

/**
 * Created by user9 on 2016/04/21.
 */
public class OrganisationAPI {
    public static Organisation save(Organisation entity){
        return RestUtil.save(OrganisationBaseUrl.Organisation.POST,entity,Organisation.class);
    }
    public static Organisation update(Organisation entity){
        return RestUtil.save(OrganisationBaseUrl.Organisation.POST,entity,Organisation.class);
    }
    public static Organisation findById(String org){
        return RestUtil.getById(OrganisationBaseUrl.Organisation.GET_ID,org,Organisation.class);
    }
    public static Set<Organisation> findAll(){
        return RestUtil.getAll(OrganisationBaseUrl.Organisation.GET_ALL,Organisation.class);
    }
}