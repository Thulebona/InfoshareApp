package cput.ac.za.infoshare.domain.content;

import java.io.Serializable;

/**
 * Created by codex on 2015/06/25.
 */
public class ContentType implements Serializable, Comparable<ContentType> {

    private String id;
    private String name;
    private String description;

    private ContentType(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    @Override
    public int compareTo(ContentType contentType) {
        return name.compareTo(contentType.name);
    }

    public static class Builder{
        private String id;
        private String name;
        private String description;

        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder description(String description){
            this.description = description;
            return this;
        }

        public Builder copy(ContentType contentType){
            this.id = contentType.id;
            this.name = contentType.name;
            this.description = contentType.description;

            return this;
        }
        public ContentType build(){
            return  new ContentType(this);
        }
    }

    public static Builder builder(){return new Builder();}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContentType that = (ContentType) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
