
/**
 * 12 HOUR INTERNAL
 * 
 * The ClockDisplay class implements a digital clock display for a
 * US-style 12 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 12:00 am (midnight) to 11:59 pm (one minute before 
 * midnight).
 * 
 * This version requires the programmer to maintain an internal variable to
 * indicate if it is ante-meridian or post-meridian (AM or PM).
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
    private String meridian;    //am/pm indicator
    private String displayString;    // simulates the actual display
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 12:00 am.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(13);
        minutes = new NumberDisplay(60);
        
        //set default hour to midnight
        hours.setValue(12);
        meridian = "am";
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute, String meridian)
    {
        hours = new NumberDisplay(13);
        minutes = new NumberDisplay(60);
        
        //Validate hour
        if (hour < 1)
        {
            hours.setValue(12);
        }
        else
        {
            if (hour > 12)
            {
                hours.setValue(12);
            }
            else
            {
                hours.setValue(hour);
            }
        }
        
        //Validate meridian
        if (meridian.equals("am"))
        {
            this.meridian = "am";
        }
        else if (meridian.equals("pm"))
        {
            this.meridian = "pm";
        }
        else
        {
            this.meridian = "am";   //Unrecognized input defaults to am
        }
        
        minutes.setValue(minute);
        updateDisplay();
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
        {
            int oldHour = hours.getValue();
            if (oldHour == 12)
            {
                hours.setValue(1);
            }
            else
            {
                hours.setValue(oldHour + 1);
            }
            
            if (oldHour == 11)
            {
                if (meridian.equals("am"))
                {
                    meridian = "pm";
                }
                else
                {
                    meridian = "am";
                }
            }
        }
        }
        updateDisplay();
    }


    /**
     * Set the time of the display to the specified hour,
     * minute, and meridian.
     */
    public void setTime(int hour, int minute, String meridian)
    {
        if (hour < 1)
        {
            hours.setValue(12);
        }
        else
        {
            if (hour > 12)
            {
                hours.setValue(12);
            }
            else
            {
                hours.setValue(hour);
            }
        }
        
        if (meridian.equals("am"))
        {
            this.meridian = "am";
        }
        else if (meridian.equals("pm"))
        {
            this.meridian = "pm";
        }
        else
        {
            this.meridian = "am";
        }
        
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
        displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue()+ " " + meridian;
    }
    
    //Toggle for am/pm
    private void toggleMeridian()
    {
        if (meridian.equals("am"))
        {
            meridian = "pm";
        }
        else
        {
            meridian = "am";
        }
    }
}
