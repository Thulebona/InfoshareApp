package cput.ac.za.infoshare.domain.person;

import java.io.Serializable;

/**
 * Created by hashcode on 2016/02/21.
 */
public class Person implements Serializable, Comparable<Person> {
    private static final long serialVersionUID = 1L;
    private String id;
    private String org;
    private String firstName;
    private String middleName;
    private String emailAddress;
    private String lastName;
    private String authvalue;
    private Boolean enabled;
    private Boolean accountNonExpired;
    private Boolean credentialsNonExpired;
    private Boolean accountNonLocked;
    private String state;


    public String getState() {
        return state;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getOrg() {
        return org;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAuthvalue() {
        return authvalue;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    private Person() {

    }

    public Person(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.org = builder.org;
        this.middleName = builder.middleName;
        this.lastName = builder.lastName;
        this.emailAddress = builder.emailAddress;
        this.authvalue = builder.authvalue;
        this.enabled = builder.enabled;
        this.accountNonExpired = builder.accountNonExpired;
        this.accountNonLocked = builder.accountNonLocked;
        this.credentialsNonExpired = builder.credentialsNonExpired;
        this.state = builder.state;
    }

    public static class Builder {
        private String id;
        private String org;
        private String firstName;
        private String middleName;
        private String lastName;
        private String emailAddress;
        private String authvalue;
        private Boolean enabled;
        private Boolean accountNonExpired;
        private Boolean credentialsNonExpired;
        private Boolean accountNonLocked;
        private String state;

        public Builder enabled(Boolean value) {
            this.enabled = value;
            return this;
        }

        public Builder accountNonExpired(Boolean value) {
            this.accountNonExpired = value;
            return this;
        }

        public Builder org(String org){
            this.org =org;
            return this;
        }
        public Builder state(String value) {
            this.state = value;
            return this;
        }

        public Builder credentialsNonExpired(Boolean value) {
            this.credentialsNonExpired = value;
            return this;
        }

        public Builder accountNonLocked(Boolean value) {
            this.accountNonLocked = value;
            return this;
        }

        public Builder id(String value) {
            this.id = value;
            return this;
        }

        public Builder firstName(String value) {
            this.firstName = value;
            return this;
        }

        public Builder middleName(String value) {
            this.middleName = value;
            return this;
        }


        public Builder lastName(String value) {
            this.lastName = value;
            return this;
        }

        public Builder emailAddress(String value) {
            this.emailAddress = value;
            return this;
        }

        public Builder authvalue(String value) {
            this.authvalue = value;
            return this;

        }

        public Builder copy(Person value) {
            this.enabled = value.enabled;
            this.accountNonExpired = value.accountNonExpired;
            this.accountNonLocked = value.accountNonLocked;
            this.credentialsNonExpired = value.credentialsNonExpired;
            this.id = value.id;
            this.org =value.org;
            this.firstName = value.firstName;
            this.middleName = value.middleName;
            this.lastName = value.lastName;
            this.emailAddress = value.emailAddress;
            this.authvalue = value.authvalue;
            this.state = value.state;
            return this;
        }

        public Person build() {
            return new Person(this);

        }
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int compareTo(Person o) {
        return lastName.compareTo(o.lastName);
    }
}
