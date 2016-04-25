package domain.sport;

import java.io.Serializable;

/**
 * Created by lodz on 2016/04/18.
 */
public class Sport implements Serializable {
    private String sport;
    private Long id;

    private Sport()
    {

    }

    public Sport(Builder builder) {

        this.sport = builder.sport;
        this.id = builder.id;
    }

    public String getSport() {
        return sport;
    }

    public Long getId()
    {
        return id;
    }
    public static class Builder{
        private String sport;
        private Long id;

        public Builder Sport(String sport) {
            this.sport = sport;
            return this;
        }

        public Builder setId(Long id)
        {
            this.id = id;
            return this;
        }
        public Builder copy(Sport sport){
            this.sport = sport.sport;
            return this;
        }

        public Sport build()
        {
            return new Sport(this);
        }
    }
    public static Builder builder(){
        return  new Builder();
    }
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj){return true;}
        if(obj == null || getClass() != obj.getClass()){return false;}

        Sport sport = (Sport) obj;
        return id != null ? id.equals(this.id) : this.id == null;
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode():0;
    }
}

