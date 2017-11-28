package it.jgrassi.gamelist.data;

import it.jgrassi.gamelist.model.GamesResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by j.grassi on 28/11/2017.
 */

public interface GameService {
    @GET("2ewt6r22zo4qwgx/gameData.json")
    Call<GamesResponse> fetchGames();
}
