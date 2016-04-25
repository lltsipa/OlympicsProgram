package domain.player;

import java.io.Serializable;

/**
 * Created by lodz on 2016/04/18.
 */
public class Catagory implements Serializable {
    private String men;
    private String women;
    private Long id;

    private Catagory()
    {

    }

    public Catagory(Builder builder) {
        this.men = builder.men;
        this.women = builder.women;
        this.id = builder.id;
    }
    public Long getId()
    {
        return id;
    }

    public String getMen() {
        return men;
    }

    public String getWomen() {
        return women;
    }

    public static class Builder{
        private String men;
        private String women;
        private Long id;

        public Builder Men(String men) {
            this.men = men;
            return this;
        }

        public Builder setId(Long id)
        {
            this.id = id;
            return this;
        }
        public Builder Women(String women) {
            this.women = women;
            return this;
        }
        public Builder copy(Catagory catagory)
        {
            this.men = catagory.men;
            this.women = catagory.women;
            return this;
        }
        public Catagory build()
        {
            return new Catagory(this);
        }
        @Override
        public boolean equals(Object obj)
        {
            if(this == obj){return true;}
            if(obj == null || getClass() != obj.getClass()){return false;}

            Catagory catagory = (Catagory) obj;
            return id != null ? id.equals(this.id) : this.id == null;
        }

        @Override
        public int hashCode()
        {
            return id != null ? id.hashCode():0;
        }
    }
}
