package com.luyucheng.fetch_audio;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.PluginRegistry.Registrar;


/**
 * FetchAudioPlugin
 */
public class FetchAudioPlugin implements MethodCallHandler {

    private static final String TAG = "FetchAudioPlugin";

    private Activity activity;

    private FetchAudioPlugin(Activity context) {
        this.activity = context;
    }

    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "fetch_audio");
        channel.setMethodCallHandler(new FetchAudioPlugin(registrar.activity()));
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onMethodCall(MethodCall call, Result result) {
        if ("getAllAudios".equals(call.method)) {
            //使用兼容库就无需判断系统版本
            int hasWriteStoragePermission = activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if (hasWriteStoragePermission == PackageManager.PERMISSION_GRANTED) {
                //拥有权限，执行操作
                ArrayList<HashMap> maps = dataToMap(getAllAudios());
                result.success(maps);
            } else {
                //没有权限，向用户请求权限
                result.error("permission", "No read storage permission!", null);
            }
        } else
            result.notImplemented();
    }

    private ArrayList<HashMap> dataToMap(List<Audio> audioList) {
        ArrayList<HashMap> maps = new ArrayList<>();
        for (Audio audio : audioList) {
            maps.add(audio.toMap());
        }
        return maps;
    }

    private List<Audio> getAllAudios() {
        List<Audio> audioList = new ArrayList<>();
        Cursor cursor = activity.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if (null != cursor)
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                    long album_id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
                    String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                    String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                    String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                    String display_name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                    String year = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.YEAR));
                    String url = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                    long duration = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                    long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));

                    Audio audio = new Audio();
                    audio.setId(id);
                    audio.setAlbumId(album_id);
                    audio.setTitle(title);
                    audio.setAlbum(album);
                    audio.setThumbUrl(getAlbumArt(activity, album_id));
                    audio.setArtist(artist);
                    audio.setDisplayName(display_name);
                    audio.setYear(year);
                    audio.setUrl(url);
                    audio.setDuration(duration);
                    audio.setSize(size);

                    audioList.add(audio);
                    cursor.moveToNext();
                }
                cursor.close();
            }
        return audioList;
    }


    private static String getAlbumArt(Context context, long album_id) {
        String mUriAlbums = "content://media/external/audio/albums";
        String[] projection = new String[]{"album_art"};
        Cursor cur = context.getContentResolver().query(Uri.parse(mUriAlbums + "/" + Long.toString(album_id)), projection, null, null, null);
        String album_art = null;
        if (cur.getCount() > 0 && cur.getColumnCount() > 0) {
            cur.moveToNext();
            album_art = cur.getString(0);
        }
        cur.close();
        String path = null;
        if (album_art != null) {
            path = album_art;
        } else {
            path = "";
        }
        return path;
    }

}
