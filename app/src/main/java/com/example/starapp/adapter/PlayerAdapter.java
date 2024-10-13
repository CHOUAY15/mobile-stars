package com.example.starapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.starapp.R;
import com.example.starapp.beans.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> implements Filterable {
    private static final String TAG = "PlayerAdapter";
    private List<Player> players;
    private List<Player> playersFilter;
    private Context context;
    private NewFilter mfilter;
    private OnItemClickListener listener;  // Add this line

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(Player player);
    }

    // Adapter constructor with context and players
    public PlayerAdapter(Context context, List<Player> players) {
        this.players = players;
        this.context = context;
        this.playersFilter = new ArrayList<>(players);
        this.mfilter = new NewFilter(this);
    }

    // Method to set the item click listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(this.context).inflate(R.layout.players_item, parent, false);
        return new PlayerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Log.d(TAG, "onBindView call! " + position);
        Glide.with(context)
                .asBitmap()
                .load(playersFilter.get(position).getImg())
                .apply(new RequestOptions().override(100, 100))
                .into(holder.img);
        holder.name.setText(playersFilter.get(position).getNom().toUpperCase());
        holder.stars.setRating(playersFilter.get(position).getStar());
        holder.idss.setText(String.valueOf(playersFilter.get(position).getId()));

        // Set the click listener on the itemView
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(playersFilter.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return playersFilter.size();
    }

    @Override
    public Filter getFilter() {
        return mfilter;
    }

    public void refreshData(List<Player> newPlayers) {
        this.players = newPlayers;
        this.playersFilter = new ArrayList<>(newPlayers);
        notifyDataSetChanged();
    }




    // ViewHolder class
    public class PlayerViewHolder extends RecyclerView.ViewHolder {
        TextView idss;
        ImageView img;
        TextView name;
        RatingBar stars;
        RelativeLayout parent;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            idss = itemView.findViewById(R.id.ids);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            stars = itemView.findViewById(R.id.stars);
            parent = itemView.findViewById(R.id.parent);
        }
    }

    // Filter class
    public class NewFilter extends Filter {
        public RecyclerView.Adapter mAdapter;

        public NewFilter(RecyclerView.Adapter mAdapter) {
            super();
            this.mAdapter = mAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Player> filteredList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(players);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Player p : players) {
                    if (p.getNom().toLowerCase().contains(filterPattern)) {
                        filteredList.add(p);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            playersFilter.clear();
            playersFilter.addAll((List<Player>) filterResults.values);
            notifyDataSetChanged();
        }
    }
}
