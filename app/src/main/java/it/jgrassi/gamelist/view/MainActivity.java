package it.jgrassi.gamelist.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.reactivex.annotations.Nullable;
import it.jgrassi.gamelist.R;
import it.jgrassi.gamelist.databinding.ActivityMainBinding;
import it.jgrassi.gamelist.model.Game;
import it.jgrassi.gamelist.model.Player;
import it.jgrassi.gamelist.viewmodel.ListViewModel;
import it.jgrassi.gamelist.viewmodel.PlayerViewModel;

public class MainActivity extends AppCompatActivity {

    private ListViewModel listViewModel;
    private PlayerViewModel playerViewModel;

    private ActivityMainBinding binding;

    private RecyclerView list;
    private TextView error;
    private ProgressBar progress;
    private TextView playerName;
    private TextView playerBalance;
    private TextView playerLogin;
    private ImageView playerImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        listViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);


        list = findViewById(R.id.games_list);

        progress = findViewById(R.id.main_progress);
        error = findViewById(R.id.main_list_error);
        playerName = findViewById(R.id.player_name);
        playerBalance = findViewById(R.id.player_balance);
        playerLogin = findViewById(R.id.player_login);
        playerImage = findViewById(R.id.player_image);

        listViewModel.getProgressVisibility().observeForever(new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer visibility) {
                progress.setVisibility(visibility);
            }
        });

        listViewModel.getListVisibility().observeForever(new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer visibility) {
                list.setVisibility(visibility);
            }
        });

        listViewModel.getErrorVisibility().observeForever(new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer visibility) {
                error.setVisibility(visibility);
            }
        });

        listViewModel.getError().observeForever(new Observer<String>() {
            @Override
            public void onChanged(@Nullable String text) {
                error.setText(text);
            }
        });

        playerViewModel.getPlayer().observeForever(new Observer<Player>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable Player player) {
                if(player != null){
                    playerName.setText(player.name);
                    if(playerBalance != null){
                        playerBalance.setText(Integer.toString(player.balance));
                        //playerLogin.setText();
                        Glide.with(getApplicationContext()).load(player.avatarLink).into(playerImage);
                    }
                }
            }
        });

        listViewModel.getList().observeForever(new Observer<List<Game>>() {
            @Override
            public void onChanged(@Nullable List<Game> items) {
                setUpList(items);
            }
        });

        listViewModel.loadGames();

        playerViewModel.loadPlayer();
    }

    private void setUpList(List<Game> items){
        list.setLayoutManager(new LinearLayoutManager(this));
        GameAdapter adapter = new GameAdapter();
        adapter.setList(items);
        list.setAdapter(adapter);
    }
}
