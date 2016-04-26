package cput.ac.za.infoshare.domain.person;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by user9 on 2016/02/29.
 */
public class PersonDemographics implements Serializable, Comparable<PersonDemographics> {
    private String id;
    private String personId;
    private String genderId;
    private Date dateOfBirth;
    private Date date;
    private String state;
    private String maritalStatusId;
    private int numberOfDependencies;
    private String personRaceId;

    private PersonDemographics(){
    }
    public PersonDemographics(Builder builder){
        this.id = builder.id;
        this.personId = builder.personId;
        this.genderId = builder.genderId;
        this.dateOfBirth = builder.dateOfBirth;
        this.date = builder.date;
        this.state = builder.state;
        this.maritalStatusId = builder.maritalStatusId;
        this.numberOfDependencies = builder.numberOfDependencies;
        this.personRaceId = builder.personRaceId;
    }

    public String getPersonRaceId() {
        return personRaceId;
    }

    public String getId() {
        return id;
    }

    public String getPersonId() {
        return personId;
    }

    public String getGenderId() {
        return genderId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }


    public Date getDate() {
        return date;
    }

    public String getState() {
        return state;
    }

    public String getMaritalStatusId() {
        return maritalStatusId;
    }

    public int getNumberOfDependencies() {
        return numberOfDependencies;
    }

    public static class Builder{
        private String id;
        private String personId;
        private String genderId;
        private Date dateOfBirth;
        private Date date;
        private String state;
        private String maritalStatusId;
        private int numberOfDependencies;
        private String personRaceId;

        public Builder id(String id) {
            this.id = id;
            return  this;
        }

        public Builder personraceid(String personRaceId) {
            this.personRaceId = personRaceId;
            return this;
        }

        public Builder personId(String personId) {
            this.personId = personId;
            return this;
        }

        public Builder genderId(String genderId) {
            this.genderId = genderId;
            return this;
        }

        public Builder dateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }


        public Builder numberofdependencies(int numberOfDependencies) {
            this.numberOfDependencies = numberOfDependencies;
            return this;
        }

        public Builder maritalstatusid(String maritalStatusId) {
            this.maritalStatusId = maritalStatusId;
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

        public Builder copy(PersonDemographics personDemographics){
            this.id = personDemographics.id;
            this.personId = personDemographics.personId;
            this.genderId = personDemographics.genderId;
            this.dateOfBirth = personDemographics.dateOfBirth;
            this.date = personDemographics.date;
            this.state = personDemographics.state;
            this.maritalStatusId = personDemographics.maritalStatusId;
            this.numberOfDependencies = personDemographics.numberOfDependencies;
            return this;
        }
        public PersonDemographics build(){return new PersonDemographics(this);}
    }

    public static Builder builder(){return new Builder();}
    @Override
    public int compareTo(PersonDemographics personDemographics) {
        return dateOfBirth.compareTo(personDemographics.dateOfBirth);
    }
}
