package cput.ac.za.infoshare.domain.person;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by user9 on 2015/07/24.
 */
public class PersonAddress implements Serializable,Comparable<PersonAddress> {

    private String id;
    private String personId;
    private String description;
    private Date date;
    private String state;
    private String postalCode;
    private String addressTypeId;

    private PersonAddress(){

    }

    public PersonAddress(Builder builder) {
        this.id = builder.id;
        this.personId = builder.personId;
        this.postalCode = builder.postalCode;
        this.addressTypeId = builder.addressTypeId;
        this.date = builder.date;
        this.state = builder.state;
        this.description = builder.description;
    }

    public String getId() {
        return id;
    }

    public String getPersonId() {
        return personId;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getAddressTypeId() {
        return addressTypeId;
    }

    public static class Builder{
        private String id;
        private String description;
        private Date date;
        private String state;
        private String postalCode;
        private String addressTypeId;
        public String personId;

        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder description(String description)
        {
            this.description = description;
            return this;
        }
        public Builder date(Date date){
            this.date = date;
            return this;
        }
        public Builder state(String state){
            this.state = state;
            return this;
        }

        public Builder postalCode(String postalCode){
            this.postalCode = postalCode;
            return this;
        }
        public Builder addressTypeId(String addressTypeId){
            this.addressTypeId = addressTypeId;
            return this;
        }

        public Builder personId(String personId) {
            this.personId = personId;
            return this;
        }

        public Builder copy(PersonAddress personAddress){
            this.id = personAddress.id;
            this.state = personAddress.state;
            this.date = personAddress.date;
            this.description = personAddress.description;
            this.addressTypeId = personAddress.addressTypeId;
            this.postalCode = personAddress.postalCode;
            this.addressTypeId = personAddress.addressTypeId;

            return this;
        }

        public PersonAddress build(){
            return new PersonAddress(this);
        }
    }
    public static Builder builder() {
        return new Builder();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonAddress personAddress = (PersonAddress) o;

        return !(id != null ? !id.equals(personAddress.id) : personAddress.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public int compareTo(PersonAddress personAddress) {
        return id.compareTo(personAddress.id);
    }
}
