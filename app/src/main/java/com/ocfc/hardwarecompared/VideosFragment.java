package com.ocfc.hardwarecompared;


import android.media.MediaPlayer;
import android.os.Handler;
import android.widget.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideosFragment extends Fragment {


    public VideosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_videos, container, false);
        VideoView videoView;
        videoView = (VideoView)view.findViewById(R.id.videoView);
        MediaController mediaController;
        mediaController = new MediaController(view.getContext());
        mediaController.setAnchorView(videoView);
        Uri uri= Uri.parse("android.resource://com.ocfc.hardwarecompared/res/hardwarecompared");
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();




        return view;
    }


}
