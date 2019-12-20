/**
 *
 */
package com.dxc.medxc.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author amirchev
 */
public final class PatientHistoryDTO {
    private final List<RecordDTO> records;
    private final List<TestDTO> tests;

    /**
     * Empty constructor for PatientHistoryDTO
     */
    public PatientHistoryDTO() {
        records = null;
        tests = null;
    }

    /**
     * Constructor of PatientHistoryDTO
     *
     * @param records
     *            list of records
     * @param tests
     *            list of tests
     */
    public PatientHistoryDTO(final List<RecordDTO> records, final List<TestDTO> tests) {
        this.records = records == null ? new ArrayList<>() : Collections.unmodifiableList(records);
        this.tests = tests == null ? new ArrayList<>() : Collections.unmodifiableList(tests);
    }

    /**
     * @return the records
     */
    public List<RecordDTO> getRecords() {
        return records == null ? new ArrayList<>() : Collections.unmodifiableList(records);
    }

    /**
     * @return the tests
     */
    public List<TestDTO> getTests() {
        return tests == null ? new ArrayList<>() : Collections.unmodifiableList(tests);
    }

}
