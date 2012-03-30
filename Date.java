import java.util.Timer;

/**
 * Date
 * CitySim
 * v0.1
 * 
 * Created by Felix Mo on 02-17-2012
 * 
 * Data structure for dates
 * 
 */

public class Date  
{

    private City city;
    private Timer timer;

    private int days;
    private int months;
    private int years;

    public Date(City city, int days, int months, int years) {
        this.city = city;

        this.days = days;
        this.months = months;
        this.years = years;
    }

    public void increment() {
        days++;

        if (days == 31) {
            months++;
            days = 0;
        }

        if (months == 12) {
            years++;
            months = 0;
        }

        city.didIncrementDate();
    }

    public void start() {
        System.out.println("Timer has begun...");

        timer = new Timer();
        timer.schedule(new DateIncrementor(this), 0, 1000);
    }

    public void stop() {
        System.out.println("Timer has stopped.");

        timer.cancel();
        timer = null;
    }

    public String toString() {

        String daysString;
        String monthsString;
        String yearsString;

        if (days < 10) {
            daysString = "0" + days;
        }
        else {
            daysString = Integer.toString(days);
        }

        if (months < 10) {
            monthsString = "0" + months;
        }
        else {
            monthsString = Integer.toString(months);
        }

        if (years < 10) {
            yearsString = "0" + years;
        }
        else {
            yearsString = Integer.toString(years);
        }

        return monthsString + "/" + daysString + "/" + yearsString;
    }

    public int days() {
        return days;
    }

    public int months() {
        return months;
    }

    public int years() {
        return years;
    }

}