
/**
 * 24 HOUR INTERNAL BRANCH
 * 
 * The ClockDisplay class implements a digital clock display for a
 * US-style 12 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 12:00 am (midnight) to 11:59 pm (one minute before 
 * midnight).
 * 
 * The clock represents hours in the range from 0..23, and convert to US-style
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Michael Kölling and David J. Barnes
 * @author Alejandro Olea
 * @version 2016.02.29
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        setTime(hour, minute);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        int hourValue = hours.getValue();
        String meridian;
        
        //Determine if am or pm
        if (hourValue < 12)
        {
            meridian = "am";
        }
        else
        {
            meridian = "pm";
        }
        
        //Convert into 12 hour format
        int displayHour;
        if (hourValue == 0)
        {
            displayHour = 12;   //Change 0 to 12 to fit 12 hour format
        }
        else if (hourValue > 12)
        {
            displayHour = hourValue - 12;   //Converts values 13-23 to 1-11
        }
        else
        {
            displayHour = hourValue;
        }
        
        displayString = displayHour + ":" + minutes.getDisplayValue() + " " + meridian;
    }
}
