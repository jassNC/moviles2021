package com.jasson.example.myapplication;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

public class SimplifyManager implements LifecycleObserver{
    
    public void setOwner(Lifecycle lc){
        lc.addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void resumeHappened(LifecycleOwner source){
        Log.d("Sample","Actividad reanudada-> ("+source+")");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void postHappened(LifecycleOwner source){
        Log.d("Sample","Actividad pausada-> ("+source+")");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    private void onEventHappened(LifecycleOwner source){
        Log.d("Sample","*cualquier evento* ("+source+")");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onEventDestroyed(LifecycleOwner source){
        Log.d("Sample","Actividad destruida ("+source+")");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onEventStarted(LifecycleOwner source){
        Log.d("Sample","Actividad iniciada ("+source+")");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onEventStopped(LifecycleOwner source){
        Log.d("Sample","Actividad detenida ("+source+")");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private void onEventCreated(LifecycleOwner source){
        Log.d("Sample","Actividad creada ("+source+")");
    }
}
