package me.wcy.music.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import me.wcy.music.service.PlayService;
import me.wcy.music.storage.db.DBManager;

/**
 * 自定义Application
 * Created by wcy on 2015/11/27.
 */
public class MusicApplication extends Application {

    public static Context getContext() {
        return context;
    }

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        AppCache.get().init(this);
        ForegroundObserver.init(this);
        DBManager.get().init(this);

        Intent intent = new Intent(this, PlayService.class);
        startService(intent);
    }
}
