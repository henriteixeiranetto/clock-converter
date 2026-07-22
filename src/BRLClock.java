public non-sealed class BRLClock extends Clock {

    @Override
    public Clock convert(Clock clock) {
        this.second = clock.getSecond();
        this.minute = clock.getMinute();
        switch (clock) {
            case USClock usClock -> this.hour = to24Hour(usClock);
            case BRLClock brlClock -> this.hour = brlClock.getHour();
        }
        return this;
    }

    private int to24Hour(USClock usClock) {
        int hour = usClock.getHour();
        boolean isPM = usClock.getPeriodIndicator().equals("PM");
        if (hour == 12) {
            return isPM ? 12 : 0;
        }
        return isPM ? hour + 12 : hour;
    }
}