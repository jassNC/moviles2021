package com.jasson.example.myapplication;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class SimplifyManager implements LifecycleObserver{
    
    public void setOwner(Lifecycle lc){
        lc.addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void resumeHappened(){
        Log.d("Sample","Actividad reanudada");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void postHappened(){
        Log.d("Sample","Actividad pausada");

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    private void onEventHappened(){
        Log.d("Sample","*cualquier evento*");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onEventDestroyed(){
        Log.d("Sample","Actividad destruida");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onEventStarted(){
        Log.d("Sample","Actividad iniciada");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onEventStopped(){
        Log.d("Sample","Actividad detenida");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private void onEventCreated(){
        Log.d("Sample","Actividad creada");
    }
}
