package cput.ac.za.infoshare.domain.person;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by user9 on 2016/03/02.
 */
public class PersonContact implements Serializable, Comparable<PersonContact> {
    private String id;
    private String personId;
    private String addressTypeId;
    private String contactValue;
    private String status;
    private Date date;
    private String state;

    private PersonContact(){}

    public PersonContact(Builder builder) {
        this.id = builder.id;
        this.personId = builder.personId;
        this.addressTypeId = builder.addressTypeId;
        this.contactValue = builder.contactValue;
        this.state = builder.state;
        this.date = builder.date;
        this.status = builder.status;
    }

    public String getId() {
        return id;
    }

    public String getPersonId() {
        return personId;
    }

    public String getAddressTypeId() {
        return addressTypeId;
    }

    public String getContactValue() {
        return contactValue;
    }

    public String getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public String getState() {
        return state;
    }

    public static class Builder{
        private String id;
        private String personId;
        private String addressTypeId;
        private String contactValue;
        private String status;
        private Date date;
        private String state;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder personId(String personId) {
            this.personId = personId;
            return this;
        }

        public Builder addresstypeid(String addressTypeId) {
            this.addressTypeId = addressTypeId;
            return this;
        }

        public Builder contactvalue(String contactValue) {
            this.contactValue = contactValue;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
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

        public Builder copy(PersonContact personContact){
            this.id = personContact.id;
            this.personId = personContact.personId;
            this.addressTypeId = personContact.addressTypeId;
            this.contactValue = personContact.contactValue;
            this.state = personContact.state;
            this.date = personContact.date;
            this.status = personContact.status;

            return this;
        }

        public PersonContact build(){return new PersonContact(this);}
    }
    public  static Builder builder(){return new Builder();}
    @Override
    public int compareTo(PersonContact personContact) {
        return 0;
    }
}
