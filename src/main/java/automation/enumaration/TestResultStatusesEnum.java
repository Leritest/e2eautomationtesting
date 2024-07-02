package automation.enumaration;

public enum TestResultStatusesEnum {

    SKIP(3);
    private final int value;

    TestResultStatusesEnum(int value) {

        this.value = value;
    }

    public int getValue() {

        return this.value;
    }
}