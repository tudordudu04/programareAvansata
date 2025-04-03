package Homework;

import java.time.LocalTime;

public class Interval {
    LocalTime startTime;
    LocalTime endTime;

    public Interval(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public Interval getInterval() {
        return this;
    }

    //Mai bine dau override aici
    public String intervalToString(){
        return ("(" + startTime.toString() + " - " + endTime.toString() + ")");
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void reschedule(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        //We are very sorry for the inconvenience! lol
    }
    public boolean overlaps(Interval interval) {
        return ((this.endTime.isAfter(interval.startTime) || this.endTime.equals(interval.startTime))  && (this.endTime.isBefore(interval.endTime) || this.endTime.equals(interval.endTime)));
    }

}
