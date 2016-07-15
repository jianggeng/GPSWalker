package com.jgeng.GPSWalker;

/**
 * Created by jgeng on 7/15/16.
 */

public class GPSLinearRoutine implements GpsRoutineRunner.GpsRoutine {
  private double mLatStart;
  private double mLongStart;
  private double mLatEnd;
  private double mLongEnd;
  private int mDuration;
  private int mInterval;

  private double mTotalSteps;
  private double mCurStep;

  public GPSLinearRoutine(double latStart, double longStart, double latEnd, double longEnd, int duration, int interval)
  {
    init(latStart,longStart, latEnd, longEnd, duration, interval);
  }

  public GPSLinearRoutine(double latStart, double longStart, double latEnd, double longEnd, int duration)
  {
    init(latStart,longStart, latEnd, longEnd, duration, 1000);
  }

  private void init(double latStart, double longStart, double latEnd, double longEnd, int duration, int interval) {
    mLatStart = latStart;
    mLongStart = longStart;
    mLatEnd = latEnd;
    mLongEnd = longEnd;
    mDuration = duration;
    mInterval = interval;

    mTotalSteps = mDuration/mInterval;
    mCurStep = 0;
  }
  @Override
  public GpsRoutineRunner.Step getNextStep() {
    if (mCurStep > mTotalSteps) return null;
    double factor = mCurStep / mTotalSteps;
    double latitude = mLatStart + (mLatEnd - mLatStart)*factor;
    double longitude = mLongStart + (mLongEnd - mLongStart)*factor;
    mCurStep++;
    return new GpsRoutineRunner.Step(latitude, longitude, mInterval);
  }
}
