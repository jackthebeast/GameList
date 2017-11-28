package it.jgrassi.gamelist.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import it.jgrassi.gamelist.data.GamesAPI;
import it.jgrassi.gamelist.data.PlayerService;
import it.jgrassi.gamelist.model.Game;
import it.jgrassi.gamelist.model.Player;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by jacop on 28/11/2017.
 */

public class PlayerViewModel extends ViewModel implements Callback<Player> {
    private MutableLiveData<Player> player;

    public void loadPlayer(){
        Retrofit retrofit = GamesAPI.create();

        PlayerService service = retrofit.create(PlayerService.class);

        Call<Player> call = service.getPlayer();

        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<Player> call, Response<Player> response) {
            setPlayer(response.body());
    }

    @Override
    public void onFailure(Call<Player> call, Throwable t) {
        //setPlayer(new Player(t.getMessage()));
        return;
    }

    public MutableLiveData<Player> getPlayer() {
        if(player == null)
            player = new MutableLiveData<>();
        return player;
    }

    public void setPlayer(Player player) {
        this.player.postValue(player);
    }

}
