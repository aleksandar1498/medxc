package com.dxc.medxc.dto;

/**
 * DTO class for spacialty entity
 *
 * @author atsekov
 */
public final class SpecialtyDTO {
    private final String name;

    private SpecialtyDTO() {
        this.name = ""; //$NON-NLS-1$
        // This is needed for deserialization.
    }

    /**
     * @param name
     *            The name of the specialty
     */
    public SpecialtyDTO(final String name) {
        this.name = name;
    }

    /**
     * @return The name of the specialty
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
