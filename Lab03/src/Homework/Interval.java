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
    public String intervalToString(){
        return ("(" + startTime.toString() + " - " + endTime.toString() + ")");
    }

    public void reschedule(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        //We are very sorry for the inconvenience! lol
    }
    public boolean overlaps(Interval interval) {
        return ((this.startTime.isAfter(interval.startTime) || this.startTime.equals(interval.startTime))  && (this.startTime.isBefore(interval.endTime) || this.startTime.equals(interval.endTime))
                || (this.endTime.isAfter(interval.startTime) || this.endTime.equals(interval.startTime))  && (this.endTime.isBefore(interval.endTime) || this.endTime.equals(interval.endTime)));
    }

}
