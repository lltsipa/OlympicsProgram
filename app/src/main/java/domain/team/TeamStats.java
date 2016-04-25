package domain.team;

import java.io.Serializable;

/**
 * Created by lodz on 2016/04/18.
 */
public class TeamStats implements Rankings,Serializable {

    private int wins;
    private int loses;
    private Long id;

    public int getPlayed() {
        return played;
    }

    private int played;

    private TeamStats()
    {

    }

    public TeamStats(Builder builder) {
        this.wins = builder.wins;
        this.loses = builder.loses;
        this.played = builder.played;
        this.id = builder.id;
    }

    public Long getId()
    {
        return id;
    }
    public int getLoses() {
        return loses;
    }

    public int getWins() {
        return wins;
    }

    public int countryRankingPoints() {
        int total = (wins * 4)-(loses*2);
        return total;
    }

    public static class Builder{
        private int wins;
        private int loses;
        private int played;
        private Long id;

        public Builder Played(int played) {
            this.played = played;
            return this;
        }
        public Builder id(Long id)
        {
            this.id = id;
            return this;
        }

        public Builder Wins(int wins) {
            this.wins = wins;
            return this;
        }

        public Builder Loses(int loses) {
            this.loses = loses;
            return this;
        }

        public Builder copy(TeamStats teamStats)
        {
            this.played = teamStats.played;
            this.wins = teamStats.wins;
            this.loses = teamStats.loses;
            return this;
        }

        public TeamStats build()
        {
            return new TeamStats(this);
        }
    }
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj){return true;}
        if(obj == null || getClass() != obj.getClass()){return false;}

        TeamStats teamStats = (TeamStats) obj;

        return id != null ? id.equals(this.id) : this.id == null;
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode():0;
    }

    public Builder builder()
    {
        return new Builder();
    }
}
