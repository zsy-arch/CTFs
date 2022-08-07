package com.parse;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import bolts.Capture;
import bolts.Task;
import bolts.TaskCompletionSource;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class LocationNotifier {
    private static Location fakeLocation = null;

    LocationNotifier() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Task<Location> getCurrentLocationAsync(Context context, long timeout, Criteria criteria) {
        final TaskCompletionSource<Location> tcs = new TaskCompletionSource<>();
        final Capture<ScheduledFuture<?>> timeoutFuture = new Capture<>();
        final LocationManager manager = (LocationManager) context.getSystemService("location");
        final LocationListener listener = new LocationListener() { // from class: com.parse.LocationNotifier.1
            @Override // android.location.LocationListener
            public void onLocationChanged(Location location) {
                if (location != null) {
                    ((ScheduledFuture) Capture.this.get()).cancel(true);
                    tcs.trySetResult(location);
                    manager.removeUpdates(this);
                }
            }

            @Override // android.location.LocationListener
            public void onProviderDisabled(String provider) {
            }

            @Override // android.location.LocationListener
            public void onProviderEnabled(String provider) {
            }

            @Override // android.location.LocationListener
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
        };
        timeoutFuture.set(ParseExecutors.scheduled().schedule(new Runnable() { // from class: com.parse.LocationNotifier.2
            @Override // java.lang.Runnable
            public void run() {
                TaskCompletionSource.this.trySetError(new ParseException(124, "Location fetch timed out."));
                manager.removeUpdates(listener);
            }
        }, timeout, TimeUnit.MILLISECONDS));
        String provider = manager.getBestProvider(criteria, true);
        if (provider != null) {
            manager.requestLocationUpdates(provider, 0L, 0.0f, listener);
        }
        if (fakeLocation != null) {
            listener.onLocationChanged(fakeLocation);
        }
        return tcs.getTask();
    }

    static void setFakeLocation(Location location) {
        fakeLocation = location;
    }
}
