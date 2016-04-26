package cput.ac.za.infoshare.domain.content;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by user9 on 2016/02/29.
 */
public class Media implements Serializable,Comparable<Media>{

    private String contentId;
    private String id;
    private String description;
    private String url;
    private String mime;
    private String state;
    private Date date;

    private Media(){}
    private Media(Builder builder){
        this.contentId = builder.contentId;
        this.id = builder.id;
        this.description = builder.description;
        this.url = builder.url;
        this.mime = builder.mime;
        this.date = builder.date;
        this.state = builder.state;
    }

    public String getContentId() {
        return contentId;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getMime() {
        return mime;
    }

    public String getState() {
        return state;
    }

    public Date getDate() {
        return date;
    }
    public  static class Builder{
        private String contentId;
        private String id;
        private String description;
        private String url;
        private String mime;
        private String state;
        private Date date;

        public Builder contentid(String contentId) {
            this.contentId = contentId;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder mime(String mime) {
            this.mime = mime;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder copy(Media media){

            this.contentId = media.contentId;
            this.id = media.id;
            this.description = media.description;
            this.url = media.url;
            this.mime = media.mime;
            this.date = media.date;
            this.state = media.state;
            return this;
        }
        public Media build(){return new Media(this);}
    }
    public static Builder builder(){return new Builder();}
    @Override
    public int compareTo(Media media) {
        return url.compareTo(media.url);
    }
}
