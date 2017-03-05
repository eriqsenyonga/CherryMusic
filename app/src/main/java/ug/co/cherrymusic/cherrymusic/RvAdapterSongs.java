package ug.co.cherrymusic.cherrymusic;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Eric on 2/27/2017.
 */

public class RvAdapterSongs extends RecyclerView.Adapter<RvAdapterSongs.ViewHolder> {


    Context context;
    ArrayList<Song> songs;
    MediaPlayer player;
    MediaController controller;

    int songPos;

    public RvAdapterSongs(Context c, ArrayList<Song> songsList, MediaPlayer mPlayer, MediaController mController) {

        context = c;

        songs = new ArrayList<Song>();

        songs = songsList;

        player = mPlayer;

        controller = mController;

      /*  player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

*/



    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_song, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Song song = songs.get(position);

        holder.tvSongTitle.setText(song.getTitle());
        holder.tvSongArtist.setText(song.getArtist());

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                songPos = position;

                Toast.makeText(context, "" + position, Toast.LENGTH_LONG).show();

                //  Toast.makeText(context, "Song " + song.getTitle() + " clicked", Toast.LENGTH_LONG).show();

                //       context.startService(new Intent(context, MusicService.class));


                playSong();

                controller.setPrevNextListeners(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playNext();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playPrev();
                    }
                });


            }
        });

    }

    private void playPrev() {

        songPos--;
        if (songPos < 0) songPos = songs.size() - 1;
        playSong();
    }

    private void playNext() {

        songPos++;
        if (songPos == songs.size()) songPos = 0;
        playSong();
    }

    private void playSong() {


        player.reset();

        Song currentSong = songs.get(songPos);

        //get id
        long currSong = currentSong.getID();

        //set uri
        Uri trackUri = ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                currSong);

        try {
            player.setDataSource(context, trackUri);
        } catch (Exception e) {
            Log.e("MUSIC SERVICE", "Error setting data source", e);
        }

        player.prepareAsync();
        player.start();




    }

    @Override
    public int getItemCount() {
        return songs.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvSongTitle, tvSongArtist;
        ItemClickListener itemClickListener;


        public ViewHolder(View itemView) {
            super(itemView);

            tvSongArtist = (TextView) itemView.findViewById(R.id.tv_artist);
            tvSongTitle = (TextView) itemView.findViewById(R.id.tv_title);

            itemView.setOnClickListener(this);

        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }


        @Override
        public void onClick(View v) {
            this.itemClickListener.onClick(v, getAdapterPosition(), false);
        }
    }
}
