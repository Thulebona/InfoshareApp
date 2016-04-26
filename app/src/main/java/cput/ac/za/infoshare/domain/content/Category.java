package cput.ac.za.infoshare.domain.content;

import java.io.Serializable;
import java.lang.Comparable;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;

/**
 * Created by user13 on 2015/06/25.
 */
public class Category implements Serializable, Comparable<Category> {

    private String id;
    private String name;
    private String description;

    private Category() {
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
    private Category(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
    }

    public static class Builder {

        private String id;
        private String name;
        private String description;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder description(String value) {
            this.description = value;
            return this;
        }

        public Builder copy(Category category){
            this.id = category.id;
            this.name = category.name;
            this.description = category.description;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }

    public static Builder builder(){return new Builder();}
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public int compareTo(Category category) {
        return name.compareToIgnoreCase(category.name);
    }

}
