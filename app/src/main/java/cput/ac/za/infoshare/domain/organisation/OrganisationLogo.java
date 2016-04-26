package cput.ac.za.infoshare.domain.organisation;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by user9 on 2016/02/29.
 */
public class OrganisationLogo implements Serializable, Comparable<OrganisationLogo> {

    private String org;
    private String id;
    private String url;
    private String size;
    private String description;
    private String mime;
    private Date date;

    private OrganisationLogo() {
    }

    public OrganisationLogo(Builder builder) {
        this.org = builder.org;
        this.id = builder.id;
        this.description = builder.description;
        this.url = builder.url;
        this.size = builder.size;
        this.mime = builder.mime;
        this.date = builder.date;
    }

    public String getOrg() {
        return org;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getSize() {
        return size;
    }

    public String getDescription() {
        return description;
    }

    public String getMime() {
        return mime;
    }

    public Date getDate() {
        return date;
    }

    public static class Builder{
        private String org;
        private String id;
        private String url;
        private String size;
        private String description;
        private String mime;
        private Date date;

        public Builder org(String org) {
            this.org = org;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder size(String size) {
            this.size = size;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder mime(String mime) {
            this.mime = mime;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder copy(OrganisationLogo organisationLogo){
            this.org = organisationLogo.org;
            this.id = organisationLogo.id;
            this.description = organisationLogo.description;
            this.url = organisationLogo.url;
            this.size = organisationLogo.size;
            this.mime = organisationLogo.mime;
            this.date = organisationLogo.date;

            return this;

        }

        public OrganisationLogo build(){return new OrganisationLogo(this);}
    }

    public static Builder builder(){return new Builder();}
    @Override
    public int compareTo(OrganisationLogo organisationLogo) {
        return org.compareTo(organisationLogo.org);
    }
}
