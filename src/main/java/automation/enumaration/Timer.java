package automation.enumaration;

/**
 * <b>Enumeration : Timer</b> Timer Names
 */

public enum Timer {

    SecondTimer(1),
    FiveSecondsTimer(5),
    TenSecondsTimer(10),
    HalfMinuteTimer(30),
    MinuteTimer(60),
    TwoMinutesTimer(120);

    public long option;

    Timer(long i) {
        this.option = i;
    }

}
