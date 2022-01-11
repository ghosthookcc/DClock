package com.DynamicClock.app;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class ProgramActivityManager extends Activity
{
    protected View RootView;

    private Program m_App;

    public final void set_m_App(Program m_App)
    {
        if(this.m_App == null && m_App != null)
            this.m_App = m_App;
        else
            Log.println(Log.DEBUG, "debug", "m_App already set!");
    }

    public final Program get_m_App()
    {
        return(m_App);
    }

    private Activity p_CurrentActivity = null;

    public Activity getCurrentActivity()
    {
        return(p_CurrentActivity);
    }

    protected void setCurrentActivity(Activity p_CurrentActivity)
    {
        this.p_CurrentActivity = p_CurrentActivity;
    }

    protected BluetoothAdapter m_BTAdapter;
    protected BluetoothA2dp m_A2DPService;

    AudioManager m_AudioManager;

    protected BroadcastReceiver m_BTReceiver = new BroadcastReceiver()
    {
        public void onReceive(Context ctx, Intent intent)
        {
            String action = intent.getAction();
            Log.println(Log.DEBUG,"debug", "Intent received : " + action);
        }
    };

    protected BluetoothProfile.ServiceListener m_BTListener = new BluetoothProfile.ServiceListener()
    {
        public void onServiceConnected(int profile, BluetoothProfile A2DP)
        {
            Log.println(Log.DEBUG, "debug", "A2DP service connected... on profile[" + profile + "]");
            if(profile == BluetoothProfile.A2DP)
            {
                m_A2DPService = (BluetoothA2dp)A2DP;
                if(m_AudioManager.isBluetoothA2dpOn())
                {
                    setIsA2dpReady(true);
                }
                else
                {
                    Log.println(Log.DEBUG, "debug","Bluetooth A2DP was not on while service connected!");
                }
            }
        }

        public void onServiceDisconnected(int profile)
        {
            setIsA2dpReady(false);
        }
    };

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);

        RootView = findViewById(android.R.id.content).getRootView();
        RootView.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_200));

        m_AudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        registerReceiver(m_BTReceiver, new IntentFilter(BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED));
        registerReceiver(m_BTReceiver, new IntentFilter(BluetoothA2dp.ACTION_PLAYING_STATE_CHANGED));

        m_BTAdapter = BluetoothAdapter.getDefaultAdapter();
        m_BTAdapter.getProfileProxy(this, m_BTListener, BluetoothProfile.A2DP);
    }

    boolean mIsA2dpReady = false;
    void setIsA2dpReady(boolean ready)
    {
        mIsA2dpReady = ready;
        Toast.makeText(this, "A2DP ready ? " + (ready ? "true" : "false"), Toast.LENGTH_SHORT).show();
    }

    protected void onResume()
    {
        super.onResume();
        m_App = (Program)this.getApplicationContext();
    }

    protected void onPause()
    {
        clearReferences();
        super.onPause();
    }

    protected void onDestroy()
    {
        m_BTAdapter.closeProfileProxy(BluetoothProfile.A2DP, m_A2DPService);
        unregisterReceiver(m_BTReceiver);

        clearReferences();
        super.onDestroy();
    }

    protected void clearReferences()
    {
        Activity CurrentActivity = getCurrentActivity();
        if(this.equals(CurrentActivity))
            setCurrentActivity(null);
    }

    public ProgramActivityManager()
    {
        Log.println(Log.DEBUG, "debug", "ProgramActivityManagerClass initialized successfully!");
    }
}
