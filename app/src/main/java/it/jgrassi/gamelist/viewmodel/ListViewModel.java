package it.jgrassi.gamelist.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import it.jgrassi.gamelist.data.GameService;
import it.jgrassi.gamelist.data.GamesAPI;
import it.jgrassi.gamelist.model.Game;
import it.jgrassi.gamelist.model.GamesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by j.grassi on 28/11/2017.
 */

public class ListViewModel extends ViewModel implements Callback<GamesResponse>{
    private MutableLiveData<List<Game>> list;

    public void loadGames(){

        Retrofit retrofit = GamesAPI.create();

        GameService service = retrofit.create(GameService.class);

        Call<GamesResponse> call = service.fetchGames();

        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<GamesResponse> call, Response<GamesResponse> response) {
        if(response.body().response.equals("success")){
            setList(response.body().data);
        }
    }

    @Override
    public void onFailure(Call<GamesResponse> call, Throwable t) {
        return;
    }

    public MutableLiveData<List<Game>> getList() {
        if(list == null)
            list = new MutableLiveData<>();
        if(list.getValue() == null)
            list.setValue(new ArrayList<Game>());
        return list;
    }

    public void setList(List<Game> list) {
        this.list.postValue(list);
    }
}
