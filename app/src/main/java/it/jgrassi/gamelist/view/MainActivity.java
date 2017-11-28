package it.jgrassi.gamelist.view;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.reactivex.annotations.Nullable;
import it.jgrassi.gamelist.R;
import it.jgrassi.gamelist.databinding.ActivityMainBinding;
import it.jgrassi.gamelist.model.Game;
import it.jgrassi.gamelist.viewmodel.ListViewModel;

public class MainActivity extends AppCompatActivity {

    private ListViewModel listViewModel;
    private RecyclerView list;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        listViewModel = ViewModelProviders.of(this).get(ListViewModel.class);

        binding.setViewModel(listViewModel);

        list = findViewById(R.id.games_list);

        listViewModel.getList().observeForever(new Observer<List<Game>>() {
            @Override
            public void onChanged(@Nullable List<Game> items) {
                setUpList(items);
            }
        });

        listViewModel.loadGames();
    }

    private void setUpList(List<Game> items){
        list.setLayoutManager(new LinearLayoutManager(this));
        GameAdapter adapter = new GameAdapter();
        adapter.setList(items);
        list.setAdapter(adapter);
    }
}
