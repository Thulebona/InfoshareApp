package cput.ac.za.infoshare.domain.person;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by user9 on 2016/02/29.
 */
public class PersonLanguage implements Serializable , Comparable<PersonLanguage> {
    private String id;
    private String personId;
    private String languageId;
    private String reading;
    private String writing;
    private String speaking;
    private String state;
    private Date date;

    private PersonLanguage(){}
    public PersonLanguage(Builder builder){
        this.id = builder.id;
        this.personId = builder.personId;
        this.languageId = builder.languageId;
        this.reading = builder.reading;
        this.writing = builder.writing;
        this.speaking = builder.speaking;
        this.date = builder.date;
        this.state = builder.state;

    }

    public String getId() {
        return id;
    }

    public String getPersonId() {
        return personId;
    }

    public String getLanguageId() {
        return languageId;
    }

    public String getReading() {
        return reading;
    }

    public String getWriting() {
        return writing;
    }

    public String getSpeaking() {
        return speaking;
    }

    public String getState() {
        return state;
    }

    public Date getDate() {
        return date;
    }


    public static class Builder{
        private String id;
        private String personId;
        private String languageId;
        private String reading;
        private String writing;
        private String speaking;
        private String state;
        private Date date;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder personId(String personId) {
            this.personId = personId;
            return this;
        }

        public Builder languageId(String languageId) {
            this.languageId = languageId;
            return this;
        }

        public Builder reading(String reading) {
            this.reading = reading;
            return this;
        }

        public Builder writing(String writing) {
            this.writing = writing;
            return this;
        }

        public Builder speaking(String speaking) {
            this.speaking = speaking;
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
        public Builder copy(PersonLanguage personLanguage){
            this.id = personLanguage.id;
            this.personId = personLanguage.personId;
            this.languageId = personLanguage.languageId;
            this.reading = personLanguage.reading;
            this.writing = personLanguage.writing;
            this.speaking = personLanguage.speaking;
            this.date = personLanguage.date;
            this.state = personLanguage.state;

            return this;

        }

        public PersonLanguage build(){return new PersonLanguage(this);}
    }
    public static Builder builder(){return new Builder();}
    @Override
    public int compareTo(PersonLanguage personLanguage) {
        return personId.compareTo(personLanguage.personId);
    }
}
