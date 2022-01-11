package com.DynamicClock.app;

import android.util.Log;

public class Program extends android.app.Application
{
    private final ProgramActivityManager ActivityManager = new ProgramActivityManager();
    private final ProgramBluetoothActivity BluetoothActivity;

    public void onCreate()
    {
        super.onCreate();
    }

    public Program()
    {
        ActivityManager.set_m_App(this);

        BluetoothActivity = new ProgramBluetoothActivity(ActivityManager);

        Log.println(Log.DEBUG, "debug", "ProgramClass initialized successfully!");
    }
}
