package domain.sport;

import java.io.Serializable;

/**
 * Created by lodz on 2016/04/18.
 */
public class Venue implements Serializable {
    private String venue;
    private Long id;

    private Venue()
    {

    }

    public Venue(Builder builder)
    {
        this.venue = builder.venue;
        this.id = builder.id;
    }

    public Long getId()
    {
        return id;
    }
    public String getVenue()
    {
        return venue;
    }

    public static class Builder{
        private String venue;
        private Long id;

        public Builder setVenue(String venue)
        {
            this.venue = venue;
            return this;
        }
        public Builder setId(Long id)
        {
            this.id = id;
            return this;
        }
        public Builder copy(Venue venue)
        {
            this.venue = venue.venue;
            return this;
        }

        public Venue build()
        {
            return new Venue(this);
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

        Venue venue = (Venue) obj;
        return id != null ? id.equals(this.id) : this.id == null;
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode():0;
    }
}

