package com.dxc.medxc.dto;

import java.time.ZonedDateTime;

/**
 * Data transfer object class.
 *
 * @author amirchev
 */
public final class TestDTO {
    private final TestTypeDTO testType;
    private final ZonedDateTime dueDate;
    private String testDescription;
    private Double value;
    private ZonedDateTime dateTaken;

    /**
     * Constructor of TestDTO
     *
     * @param testType Type of the test
     * @param dueDate Due date of the test
     * @param testDescription Description of the test
     * @param value Value of the test
     * @param dateTaken Date the test was taken
     */
    public TestDTO(final TestTypeDTO testType, final ZonedDateTime dueDate, final String testDescription,
            final Double value, final ZonedDateTime dateTaken) {
        this.testType = testType;
        this.dueDate = dueDate;
        this.testDescription = testDescription;
        this.value =value;
        this.dateTaken = dateTaken;
    }

    /**
     * Constructor of TestDTO
     *
     * @param testType
     *            test type
     * @param dueDate
     *            due date
     */
    public TestDTO(final TestTypeDTO testType, final ZonedDateTime dueDate) {
        super();
        this.testType = testType;
        this.dueDate = dueDate;
    }

    /**
     * @return the testType
     */
    public TestTypeDTO getTestType() {
        return testType;
    }

    /**
     * @return the dueDate
     */
    public ZonedDateTime getDueDate() {
        return dueDate;
    }

    /**
     * @return the testDescription
     */
    public String getTestDescription() {
        return testDescription;
    }

    /**
     * @return the value
     */
    public Double getValue() {
        return this.value;
    }


    /**
     * @return the dateTaken
     */
    public ZonedDateTime getDateTaken() {
        return this.dateTaken;
    }

}
