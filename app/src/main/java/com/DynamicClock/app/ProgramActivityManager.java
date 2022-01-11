package com.DynamicClock.app;

import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.view.View;

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

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);

        RootView = findViewById(android.R.id.content).getRootView();
        RootView.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_200));
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
