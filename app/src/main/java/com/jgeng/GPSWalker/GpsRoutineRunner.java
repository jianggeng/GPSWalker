package com.jgeng.GPSWalker;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;

class GpsRoutineRunner {
  public static class Step {
    public Step(double latitude, double longitdue, int interval) {
      super();
      mLatitude = latitude;
      mLongitude = longitdue;
      mInterval = interval;
    }

    public int mInterval;
    public double mLatitude;
    public double mLongitude;
  };

  interface GpsRoutine {
    Step getNextStep();
  }

  private Context mContext;

  Handler mHandler;
  public String mMockProviderName = "mock";

  GpsRoutine mGpsRoutine;

  public GpsRoutineRunner(Context context,
                          Handler handler, String mockProviderName, GpsRoutine routine) {
    super();
    mMockProviderName = mockProviderName;
    mGpsRoutine = routine;
    mContext = context;
    mHandler = handler;
  }

  public void start() {
    nextStep();
  }

  Runnable runnable = new Runnable() {
    @Override
    public void run() {
      nextStep();
    }
  };

  private void nextStep() {
    Step step = mGpsRoutine.getNextStep();
    if (step == null) return;

    LocationManager locationManager = (LocationManager) mContext
        .getSystemService(Context.LOCATION_SERVICE);

    Location loc = new Location(mMockProviderName);
    loc.setLatitude(step.mLatitude);
    loc.setLongitude(step.mLongitude);
    loc.setTime(System.currentTimeMillis());
    loc.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
    loc.setAccuracy(0.01f);
    locationManager.setTestProviderLocation(mMockProviderName, loc);
    Log.i("walk to:", mMockProviderName + String.format(": latitude =%s longitude=%s", step.mLatitude, step.mLongitude));
    mHandler.postDelayed(runnable, step.mInterval);
  }
}// end class GpsRoutineRunner