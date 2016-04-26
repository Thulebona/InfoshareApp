package cput.ac.za.infoshare.RestFulUtil.organisation;

import cput.ac.za.infoshare.AppConf.RestUtil;

/**
 * Created by user9 on 2016/04/21.
 */
public class OrganisationBaseUrl {
    public static class Location{
        public static final String  POST  = RestUtil.URL+"/api/location/post"; // @controllers.organisation.LocationController.createOrUpdate
        public static final String  GET_ID =RestUtil.URL+"/api/location/get/"; // @controllers.organisation.LocationController.getById(orgId,id)
        public static final String  GET_ALL =RestUtil.URL+"/api/locations/"; // @controllers.organisation.LocationController.getAllLocations(orgId)
    }
    public static class Organisation{
        public static final String  POST=RestUtil.URL+"/api/organisation/post"; // @controllers.organisation.LocationController.createOrUpdate
        public static final String  GET_ID =RestUtil.URL+"/api/organisation/get/"; // @controllers.organisation.LocationController.getById(orgId,id)
        public static final String  GET_ALL =RestUtil.URL+"/api/organisation/get"; // @controllers.organisation.LocationController.getAllLocations(orgId)
    }
    public static class OrganisationLogo{
        public static final String  POST    =RestUtil.URL+"/api/organisationlogo/post"; // @controllers.organisation.LocationController.createOrUpdate
        public static final String  GET_ID  =RestUtil.URL+"/api/organisationlogo/get/"; // @controllers.organisation.LocationController.getById(orgId,id)
        public static final String  GET_ALL =RestUtil.URL+"/api/organisationlogo/get/"; // @controllers.organisation.LocationController.getAllLocations(orgId)
    }
}