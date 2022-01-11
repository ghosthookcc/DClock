package com.DynamicClock.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ProgramBluetoothActivity extends ProgramActivityManager
{
    private final ProgramActivityManager ActivityManager;

    public ProgramBluetoothActivity(ProgramActivityManager ActivityManager)
    {
        this.ActivityManager = ActivityManager;
        Log.println(Log.DEBUG, "debug", "ProgramBluetoothActivityClass initialized successfully!");
    }
}
