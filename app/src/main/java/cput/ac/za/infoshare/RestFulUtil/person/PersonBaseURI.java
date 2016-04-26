package cput.ac.za.infoshare.RestFulUtil.person;

import cput.ac.za.infoshare.AppConf.RestUtil;

/**
 * Created by user9 on 2016/04/21.
 */
public class PersonBaseURI {

    public static class Person {
        public static final String POST = RestUtil.URL + "/api/people/post";
        public static final String GET_ID = RestUtil.URL + "/api/people/get/";
        public static final String GET_Org = RestUtil.URL + "/api/peoples/";
        public static final String GET_BY_EMAIL = RestUtil.URL + "/api/people/email/";
    }

    public static class PersonRole {
        public static final String POST = RestUtil.URL + "/api/prole/post";
        public static final String GET_ID = RestUtil.URL + "/api/prole/get/";
        public static final String GETALL = RestUtil.URL + "/api/proles/";
    }
    public static class PersonAddress{
        public static final String POST = RestUtil.URL + "/api/paddr/post";
        public static final String GET_ID = RestUtil.URL + "/api/paddr/get/";
        public static final String GETALL = RestUtil.URL + "/api/paddrs/";
    }


    public static class PersonImages{
        public static final String POST = RestUtil.URL + "/api/pimage/post";
        public static final String GET_ID = RestUtil.URL + "/api/pimage/get/";
        public static final String GET_PID = RestUtil.URL + "/api/pimage/";
        public static final String GET_BY_ORG = RestUtil.URL + "/api/pimages/get/";
    }

    public static class PersonLanguage{
        public static final String POST = RestUtil.URL + "/api/plang/post";
        public static final String GET_ID = RestUtil.URL + "/api/plang/get/";
        public static final String GET_ALL = RestUtil.URL + "/api/plangs/";
    }
    public static class PersonContact{
        public static final String POST = RestUtil.URL + "/api/pcont/post";
        public static final String GET_ID = RestUtil.URL + "/api/pcont/get/";
        public static final String GET_ALL = RestUtil.URL + "/api/pconts/";
    }
    public static class PersonDemographics{
        public static final String POST = RestUtil.URL + "/api/pdemo/post";
        public static final String GET_ID = RestUtil.URL + "/api/pdemo/get/";
        public static final String GET_ALL = RestUtil.URL + "/api/pdemos/";
    }

}
