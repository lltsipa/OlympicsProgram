package domain.player;

import java.io.Serializable;

/**
 * Created by lodz on 2016/04/18.
 */
public class Nationality implements Serializable {

    private String continent;
    private String country;
    private Long id;

    private Nationality()
    {

    }

    public Nationality(Builder builder) {
        this.continent = builder.continent;
        this.country = builder.country;
        this.id = builder.id;
    }

    public Long getId()
    {
        return id;
    }
    public String getContinent() {
        return continent;
    }

    public String getCountry() {
        return country;
    }

    public static class Builder{
        private String continent;
        private String country;
        private Long id;

        public Builder setId(Long id)
        {
            this.id = id;
            return this;
        }
        public Builder Continent(String continent) {
            this.continent = continent;
            return this;
        }

        public Builder Country(String country) {
            this.country = country;
            return this;
        }

        public Builder copy(Nationality nationality)
        {
            this.continent = nationality.continent;
            this.country = nationality.country;
            return this;
        }

        public Nationality build()
        {
            return new Nationality(this);
        }
    }
    public static Builder builder()
    {
        return new Builder();
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj){return true;}
        if(obj == null || getClass() != obj.getClass()){return false;}

        Nationality nationality = (Nationality) obj;
        return id != null ? id.equals(this.id) : this.id == null;
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode():0;
    }
}
