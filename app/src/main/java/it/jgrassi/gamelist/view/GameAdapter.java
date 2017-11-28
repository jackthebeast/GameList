package it.jgrassi.gamelist.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import it.jgrassi.gamelist.R;
import it.jgrassi.gamelist.databinding.ItemGameBinding;
import it.jgrassi.gamelist.model.Game;

/**
 * Created by j.grassi on 28/11/2017.
 */

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder>{
        private List<Game> list;

        public static class GameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            final ItemGameBinding binding;

            public GameViewHolder(ItemGameBinding binding){
                super(binding.getRoot());
                this.binding = binding;
            }

            public void bindData(Game game){
                binding.setClickListener(this);
                binding.setGame(game);
            }

            @Override
            public void onClick(View view) {
//                Pair<View, String> name = new Pair<>(view.findViewById(R.id.game_name),view.getContext().getResources().getString(R.string.transition_name));
//                DetailActivity.launchDetail(view.getContext(), binding.getName(), name);
            }

        }

        @Override
        public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ItemGameBinding itemGameBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_game, parent, false);
            return new GameViewHolder(itemGameBinding);
        }

        @Override
        public void onBindViewHolder(GameViewHolder holder, int position) {
            holder.bindData(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        public void setList(List<Game> list){
            this.list = list;
            notifyDataSetChanged();
        }
}
