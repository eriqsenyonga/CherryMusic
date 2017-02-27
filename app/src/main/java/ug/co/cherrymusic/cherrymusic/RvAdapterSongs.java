package ug.co.cherrymusic.cherrymusic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Eric on 2/27/2017.
 */

public class RvAdapterSongs extends RecyclerView.Adapter<RvAdapterSongs.ViewHolder>{


    Context context;
    ArrayList<Song> songs;

    public RvAdapterSongs(Context c, ArrayList<Song> songsList){

        context = c;

        songs = new ArrayList<Song>();

        songs = songsList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_song, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Song song = songs.get(position);

        holder.tvSongTitle.setText(song.getTitle());
        holder.tvSongArtist.setText(song.getArtist());



    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvSongTitle, tvSongArtist;


        public ViewHolder(View itemView) {
            super(itemView);

            tvSongArtist = (TextView) itemView.findViewById(R.id.tv_artist);
            tvSongTitle = (TextView) itemView.findViewById(R.id.tv_title);

        }


    }
}
