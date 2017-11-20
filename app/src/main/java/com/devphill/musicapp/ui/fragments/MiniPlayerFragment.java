package com.devphill.musicapp.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.devphill.musicapp.R;
import com.devphill.musicapp.model.Song;
import com.devphill.musicapp.ui.view.PlayerViewAdapter;
import com.ohoussein.playpause.PlayPauseView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MiniPlayerFragment extends Fragment {

    private static final String TAG = "MiniPlayerFragment";

    View rootView;

    @BindView(R.id.mini_play)
    PlayPauseView playPauseView;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    @BindView(R.id.track_name)
    TextView trackName;

    @BindView(R.id.artist_name)
    TextView artistName;

    @BindView(R.id.mini_album_artwork)
    ImageView miniArtwork;


    private Unbinder unbinder;

    public MiniPlayerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_mini_player, container, false);

        unbinder = ButterKnife.bind(this, rootView);

        artistName.setText("50 cent");
        trackName.setText("Candy Shop");
        miniArtwork.setImageResource(R.drawable.candy_shop);


        rootView.setOnTouchListener(new OnSwipeTouchListener(getActivity()));

      /*  rootView.setOnClickListener(v -> {
            MultiSheetView multiSheetView = MultiSheetView.getParentMultiSheetView(rootView);
            if (multiSheetView != null) {
                multiSheetView.expandSheet(MultiSheetView.Sheet.FIRST);
            }
        });
*/

        playPauseView.setOnClickListener(v -> {
            playPauseView.toggle();
          //  playPauseView.postDelayed(() -> presenter.togglePlayback(), 200);
        });

        progressBar.setMax(100);
        progressBar.setProgress(85);
       /* disposable.add(Aesthetic.get(getContext())
                .colorPrimary()
                .compose(distinctToMainThread())
                .subscribe(color -> {
                    boolean isDark = !Util.isColorLight(color);
                    trackName.setTextColor(isDark ? Color.WHITE : Color.BLACK);
                    artistName.setTextColor(isDark ? Color.WHITE : Color.BLACK);
                    ViewBackgroundAction.create(rootView).accept(color);
                }, onErrorLogAndRethrow()));
*/
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      //  presenter.bindView(playerViewAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

      /*  if (presenter != null) {
            presenter.updateTrackInfo();
        }*/
    }

    @Override
    public void onDestroyView() {
       /* presenter.unbindView(playerViewAdapter);
        disposable.clear();
        unbinder.unbind();*/
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        rootView.setOnTouchListener(null);

        super.onDestroy();
    }

    private class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        OnSwipeTouchListener(Context context) {
            gestureDetector = new GestureDetector(context, new GestureListener());
        }

        void onSwipeLeft() {
            Toast.makeText(getContext(), "onSwipeLeft", Toast.LENGTH_SHORT).show();
        }

        void onSwipeRight() {
            Toast.makeText(getContext(), "onSwipeRight", Toast.LENGTH_SHORT).show();
        }

        public boolean onTouch(View v, MotionEvent event) {

            boolean consumed = gestureDetector.onTouchEvent(event);

            if (!consumed) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.performClick();
                    Toast.makeText(getContext(), "performClick", Toast.LENGTH_SHORT).show();
                }
            }

            return consumed;
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_DISTANCE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            GestureListener() {
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float distanceX = e2.getX() - e1.getX();
                float distanceY = e2.getY() - e1.getY();
                if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (distanceX > 0)
                        onSwipeRight();
                    else
                        onSwipeLeft();
                    return true;
                }
                return false;
            }
        }
    }



    PlayerViewAdapter playerViewAdapter = new PlayerViewAdapter() {

        @Override
        public void setSeekProgress(int progress) {
            progressBar.setProgress(progress);
        }


        @Override
        public void playbackChanged(boolean isPlaying) {
    /*        if (isPlaying) {
                if (playPauseView.isPlay()) {
                    playPauseView.toggle();
                }
            } else {
                if (!playPauseView.isPlay()) {
                    playPauseView.toggle();
                }
            }*/
        }

        @Override
        public void trackInfoChanged(@Nullable Song song) {

            if (song == null) return;

            trackName.setText(song.name);
            artistName.setText(String.format("%s | %s", song.artistName, song.albumName));

           /* Glide.with(getContext())
                    .load(song)
                    .priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(PlaceholderProvider.getInstance().getPlaceHolderDrawable(song.name, false))
                    .into(miniArtwork);

            rootView.setContentDescription(getString(R.string.btn_now_playing, song.name, song.artistName));*/



        }
    };
}
