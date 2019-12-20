/**
 *
 */
package com.dxc.medxc.dto;

/**
 * Data transfer object class.
 *
 * @author amirchev
 */
public final class RecordDTO {
    private String anamnesis;
    private String observation;
    private String therapy;

    /**
     * Constructor of recordDTO
     *
     * @param anamnesis
     *            anamnesis string to be used in constructor
     * @param observation
     *            observation string to be used in constructor
     * @param therapy
     *            therapy to be used in constructor
     */
    public RecordDTO(final String anamnesis, final String observation, final String therapy) {
        this.anamnesis = anamnesis;
        this.observation = observation;
        this.therapy = therapy;
    }

    /**
     *
     */
    public RecordDTO() {
    }

    /**
     * @return the anamnesis
     */
    public String getAnamnesis() {
        return this.anamnesis;
    }

    /**
     * @return the observation
     */
    public String getObservation() {
        return this.observation;
    }

    /**
     * @return the therapy
     */
    public String getTherapy() {
        return this.therapy;
    }

}
