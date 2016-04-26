package cput.ac.za.infoshare.domain.person;

import java.io.Serializable;

/**
 * Created by hashcode on 2016/02/21.
 */
public class PersonRole implements Serializable {

    private String personId;
    private String roleId;
    private String state;

    public String getState() {
        return state;
    }

    public String getPersonId() {
        return personId;
    }

    public String getRoleId() {
        return roleId;
    }

    private PersonRole() {
    }

    public PersonRole(Builder builder) {
        this.personId = builder.personId;
        this.roleId = builder.roleId;
        this.state = builder.state;
    }

    public static class Builder {
        private String personId;
        private String roleId;
        private String state;

        public Builder state(String value) {
            this.state = value;
            return this;
        }

        public Builder personId(String value) {
            this.personId = value;
            return this;
        }

        public Builder roleId(String value) {
            this.roleId = value;
            return this;
        }

        public Builder copy(PersonRole value) {
            this.state = value.state;
            this.personId = value.personId;
            this.roleId = value.roleId;
            return this;
        }

        public PersonRole build() {
            return new PersonRole(this);

        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
