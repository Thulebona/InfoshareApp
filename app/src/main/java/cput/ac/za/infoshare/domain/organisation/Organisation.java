package cput.ac.za.infoshare.domain.organisation;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by user9 on 2016/02/29.
 */
public class Organisation implements Serializable,Comparable<Organisation> {

    private String id;
    private String name;
    private Map<String,String> details;
    private String adminattached;
    private Date date;
    private String state;

    private Organisation(){}

    public Organisation(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.details = builder.details;
        this.adminattached = builder.adminattached;
        this.date = builder.date;
        this.state = builder.state;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public String getAdminattached() {
        return adminattached;
    }

    public Date getDate() {
        return date;
    }

    public String getState() {
        return state;
    }

    public static class Builder{
        private String id;
        private String name;
        private Map<String,String> details;
        private String adminattached;
        private Date date;
        private String state;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder details(Map<String, String> details) {
            this.details = details;
            return this;
        }

        public Builder adminattached(String adminattached) {
            this.adminattached = adminattached;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder copy(Organisation organisation){
            this.id = organisation.id;
            this.name = organisation.name;
            this.details = organisation.details;
            this.adminattached = organisation.adminattached;
            this.date = organisation.date;
            this.state = organisation.state;
            return this;
        }
        public Organisation build(){return new Organisation(this);}
    }
    public static Builder builder(){return new Builder();}
    @Override
    public int compareTo(Organisation organisation) {
        return name.compareTo(organisation.name);
    }
}
