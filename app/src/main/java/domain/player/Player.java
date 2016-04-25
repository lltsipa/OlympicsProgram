package domain.player;

import java.io.Serializable;

/**
 * Created by lodz on 2016/04/18.
 */
public class Player implements Serializable {
    private String playerName;
    private String playerSurname;
    private String playerAge;
    private Long id;

    private Player()
    {

    }

    public Player(Builder builder)
    {
        this.playerName = builder.playerName;
        this.playerSurname = builder.playerSurname;
        this.playerAge = builder.playerAge;
        this.id = builder.id;
    }
    public Long getId()
    {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerAge() {
        return playerAge;
    }

    public String getPlayerSurname() {
        return playerSurname;
    }

    public static class Builder{
        private String playerName;
        private String playerSurname;
        private String playerAge;
        private Long id;

        public Builder PlayerName(String playerName) {
            this.playerName = playerName;
            return this;
        }

        public Builder setId(Long id)
        {
            this.id = id;
            return this;
        }
        public Builder PlayerSurname(String playerSurname) {
            this.playerSurname = playerSurname;
            return this;
        }

        public Builder PlayerAge(String playerAge) {
            this.playerAge = playerAge;
            return this;
        }

        public Builder copy(Player player){
            this.playerName = player.playerName;
            this.playerSurname = player.playerSurname;
            this.playerAge = player.playerAge;
            return this;
        }

        public Player build()
        {
            return  new Player(this);
        }
    }
    public static Builder builder(){
        return new Builder();
    }
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj){return true;}
        if(obj == null || getClass() != obj.getClass()){return false;}

        Player player = (Player) obj;
        return id != null ? id.equals(this.id) : this.id == null;
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode():0;
    }
}

