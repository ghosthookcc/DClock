package com.DynamicClock.app;

import android.util.Log;

public class ProgramBluetoothActivity extends ProgramActivityManager
{
    private final ProgramActivityManager ActivityManager;

    public ProgramBluetoothActivity(ProgramActivityManager ActivityManager)
    {
        this.ActivityManager = ActivityManager;
        Log.println(Log.DEBUG, "debug", "ProgramBluetoothActivityClass ClassPrint " + this.ActivityManager.get_m_App().getClass().getName());
        Log.println(Log.DEBUG, "debug", "ProgramBluetoothActivityClass initialized successfully!");
    }
}
