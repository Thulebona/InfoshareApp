package cput.ac.za.infoshare.domain.person;



import java.io.Serializable;
import java.util.Date;

/**
 * Created by user9 on 2016/02/29.
 */
public class PersonImages implements Serializable,Comparable<PersonImages> {

    private String org;
    private String personId;
    private String id;
    private String url;
    private String description;
    private String mime;
    private String size;
    private Date date;

    private PersonImages(){}

    public PersonImages(Builder builder){
        this.org = builder.org;
        this.personId = builder.personId;
        this.id = builder.id;
        this.size = builder.size;
        this.url = builder.url;
        this.description = builder.description;
        this.mime = builder.mime;
        this.date = builder.date;
    }

    public String getSize() {
        return size;
    }
    public String getOrg() {
        return org;
    }

    public String getPersonId() {
        return personId;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
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
        private String personId;
        private String id;
        private String url;
        private String description;
        private String mime;
        private String size;
        private Date date;

        public Builder org(String org) {
            this.org = org;
            return this;
        }

        public Builder personId(String personId) {
            this.personId = personId;
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

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder mime(String mime) {
            this.mime = mime;
            return this;
        }

        public Builder size(String size) {
            this.size = size;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder copy(PersonImages personImages){
            this.org = personImages.org;
            this.personId = personImages.personId;
            this.id = personImages.id;
            this.url = personImages.url;
            this.size = personImages.size;
            this.description = personImages.description;
            this.mime = personImages.mime;
            this.date = personImages.date;

            return this;
        }

        public PersonImages build(){return new PersonImages(this);}
    }
    public static Builder builder(){return new Builder();}
    @Override
    public int compareTo(PersonImages personImages) {
        return url.compareTo(personImages.url);
    }
}
