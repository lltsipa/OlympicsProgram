package domain.player;

import java.io.Serializable;

/**
 * Created by lodz on 2016/04/18.
 */
public class PlayerStats implements Serializable {
    private String caps;
    private String gamesStarted;
    private String gamesWon;
    private String gamesLost;
    private Long id;

    private PlayerStats()
    {

    }

    public PlayerStats(Builder builder) {
        this.caps = builder.caps;
        this.gamesLost = builder.gamesLost;
        this.gamesStarted = builder.gamesStarted;
        this.gamesWon = builder.gamesWon;
        this.id = builder.id;
    }

    public Long getId()
    {
        return id;
    }
    public String getCaps() {
        return caps;
    }

    public String getGamesStarted() {
        return gamesStarted;
    }

    public String getGamesWon() {
        return gamesWon;
    }

    public String getGamesLost() {
        return gamesLost;
    }


    public static class Builder{
        private String caps;
        private String gamesStarted;
        private String gamesWon;
        private String gamesLost;
        private Long id;

        public Builder setId(Long id)
        {
            this.id = id;
            return this;
        }
        public Builder Caps(String caps) {
            this.caps = caps;
            return this;
        }

        public Builder GamesStarted(String gamesStarted) {
            this.gamesStarted = gamesStarted;
            return this;
        }

        public Builder GamesWon(String gamesWon) {
            this.gamesWon = gamesWon;
            return this;
        }

        public Builder GamesLost(String gamesLost) {
            this.gamesLost = gamesLost;
            return this;
        }
        public Builder copy(PlayerStats playerStats)
        {
            this.caps = playerStats.caps;
            this.gamesLost = playerStats.gamesLost;
            this.gamesStarted = playerStats.gamesStarted;
            this.gamesWon = playerStats.gamesWon;
            return this;
        }

        public PlayerStats build()
        {
            return new PlayerStats(this);
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

        PlayerStats playerStats = (PlayerStats) obj;
        return id != null ? id.equals(this.id) : this.id == null;
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode():0;
    }
}
