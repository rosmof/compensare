package ro.rosmof.model;

import org.apache.commons.lang.RandomStringUtils;

public class PerformanceTesting {
    public int testid;
    public String testName;

    public PerformanceTesting() {
        this.testid = 0;
        this.testName = "default";
    }

    public PerformanceTesting(String name) {
        this.testid = 0;
        this.testName = name;
    }

    public PerformanceTesting(int testid, String testName) {
        this.testid = testid;
        this.testName = testName;
    }

    public String getResult() {
        return RandomStringUtils.randomAlphabetic(64);
    }

    public int getCharForComparison() {
        return testName.toLowerCase().charAt(1);
    }
}
