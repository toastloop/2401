/**
 * It's a class that is used to time things
 */
public class Timer {

    // It's a variable that is used to store the start time of the timer.
    public long start = 0;
    // It's a variable that is used to store the end time of the timer.
    public long end = 0;
    // It's a variable that is used to determine if the timer is running.
    public boolean running = false;

    // It's a constructor.
    Timer(){}

    /**
     * This function starts the timer by setting the start time to the current time, and setting the
     * end time to 0
     */
    public void startTimer() {

        this.start = System.nanoTime();
        if(this.end != 0) this.end = 0;
        this.running = true;

    }
    /**
     * This function resets the timer
     */
    public void resetTimer() {

        this.start = 0;
        this.end = 0;
        this.running = false;

    }
    /**
     * If the timer is running, stop it and record the time
     */
    public void endTimer() {

        if(this.start == 0 || !this.running) this.resetTimer();
        this.end = System.nanoTime();
        this.running = false;

    }

    /**
     * If the start time is 0, or the end time is 0, or the timer is running, return -1. Otherwise, return
     * the difference between the end time and the start time
     * 
     * @return The time difference between the start and end time.
     */
    public long getTime() {

        return (this.start == 0 || this.end == 0 || this.running == true) ? -1 : this.end - this.start;

    }
}
