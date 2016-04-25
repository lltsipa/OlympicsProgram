package domain.player;

import java.io.Serializable;

/**
 * Created by lodz on 2016/04/18.
 */
public class MedalRecieved implements Serializable {
    private int position;
    private Long id;

    private MedalRecieved()
    {

    }

    public MedalRecieved(Builder builder) {
        this.position = builder.position;
        this.id = builder.id;
    }

    public String getMedal() {
        if(position == 1)
        {
            return "Gold Medal";
        }
        else if(position == 2)
        {

            return "No Medal";
        }else if(position == 3)
        {
            return "Bronz Medal";
        }else
        {
            return "No Medal";
        }
    }
    public Long getId()
    {
        return id;
    }

    public static class Builder
    {
        private int position;
        private Long id;
        public Builder setNextPosition(int position) {
            this.position = position;
            return this;
        }
        public Builder setId(Long id)
        {
            this.id = id;
            return this;
        }
        public Builder copy(MedalRecieved medalRecieved)
        {
            this.position = medalRecieved.position;
            this.id = medalRecieved.id;
            return this;
        }

        public MedalRecieved build()
        {
            return new MedalRecieved(this);
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

        MedalRecieved medalRecieved = (MedalRecieved) obj;
        return id != null ? id.equals(this.id) : this.id == null;
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode():0;
    }
}
