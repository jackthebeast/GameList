package it.jgrassi.gamelist.data;

import it.jgrassi.gamelist.model.GamesResponse;
import it.jgrassi.gamelist.model.Player;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by j.grassi on 28/11/2017.
 */

public interface PlayerService {
    @GET("5zz3hibrxpspoe5/playerInfo.json")
    Call<Player> getPlayer();
}
