package edu.westga.cs6242.remotebound;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;

public class RemoteBoundActivity extends AppCompatActivity {
    Messenger myService = null;
    boolean isBound;
    private ServiceConnection myConnection =
            new ServiceConnection() {
                public void onServiceConnected(ComponentName className,
                                               IBinder service) {
                    myService = new Messenger(service);
                    isBound = true;
                }

                public void onServiceDisconnected(ComponentName className) {
                    myService = null;
                    isBound = false;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_bound);

        Intent intent = new Intent(getApplicationContext(),
                RemoteService.class);

        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
    }
}
