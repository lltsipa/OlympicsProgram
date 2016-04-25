package domain.team;

import java.io.Serializable;

/**
 * Created by lodz on 2016/04/18.
 */
public class TeamDetails implements Serializable {

    private String established;
    private String appearances;
    private Long id;

    private TeamDetails()
    {

    }

    public TeamDetails(Builder builder) {
        this.appearances = builder.appearances;
        this.established = builder.established;
        this.id = builder.id;
    }

    public Long getId()
    {
        return id;
    }
    public String getEstablished() {
        return established;
    }

    public String getAppearances() {
        return appearances;
    }

    public static class Builder{
        public String established;
        public String appearances;
        private Long id;

        public Builder established(String established) {
            this.established = established;
            return this;
        }

        public Builder setId(Long id)
        {
            this.id = id;
            return this;
        }
        public Builder appearances(String appearances) {
            this.appearances = appearances;
            return this;
        }

        public Builder copy(TeamDetails teamDetails){
            this.established = teamDetails.established;
            this.appearances = teamDetails.appearances;
            return this;
        }

        public TeamDetails build()
        {
            return new TeamDetails(this);
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

        TeamDetails teamDetails = (TeamDetails) obj;
        return id != null ? id.equals(this.id) : this.id == null;
    }

    public int hashCode()
    {
        return id != null ? id.hashCode():0;
    }
}
