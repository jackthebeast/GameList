package it.jgrassi.gamelist.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.opengl.Visibility;
import android.view.View;

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
    public MutableLiveData<Integer> progressVisibility = new MutableLiveData<>();
    public MutableLiveData<Integer> listVisibility = new MutableLiveData<>();
    public MutableLiveData<Integer> errorVisibility = new MutableLiveData<>();
    public MutableLiveData<String> error = new MutableLiveData<>();

    private MutableLiveData<List<Game>> list;

    public void loadGames(){
        setProgressVisibility(View.VISIBLE);
        setListVisibility(View.GONE);
        setErrorVisibility(View.GONE);

        Retrofit retrofit = GamesAPI.create();

        GameService service = retrofit.create(GameService.class);

        Call<GamesResponse> call = service.fetchGames();

        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<GamesResponse> call, Response<GamesResponse> response) {
        setProgressVisibility(View.GONE);
        if(response.body() == null || response.body().data.size() == 0){
            setListVisibility(View.GONE);
            setErrorVisibility(View.VISIBLE);
            setError("No games available");
        }else {
            setErrorVisibility(View.GONE);
            setListVisibility(View.VISIBLE);
            setList(response.body().data);
        }
    }

    @Override
    public void onFailure(Call<GamesResponse> call, Throwable t) {
        setProgressVisibility(View.GONE);
        setListVisibility(View.GONE);
        setErrorVisibility(View.VISIBLE);
        setError(t.getMessage());
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

    public MutableLiveData<Integer> getProgressVisibility() {
        return progressVisibility;
    }

    public void setProgressVisibility(Integer progressVisibility) {
        this.progressVisibility.postValue(progressVisibility);
    }

    public MutableLiveData<Integer> getListVisibility() {
        return listVisibility;
    }

    public void setListVisibility(Integer listVisibility) {
        this.listVisibility.postValue(listVisibility);
    }

    public MutableLiveData<Integer> getErrorVisibility() {
        return errorVisibility;
    }

    public void setErrorVisibility(Integer errorVisibility) {
        this.errorVisibility.postValue(errorVisibility);
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public void setError(String error) {
        this.error.postValue(error);
    }
}
