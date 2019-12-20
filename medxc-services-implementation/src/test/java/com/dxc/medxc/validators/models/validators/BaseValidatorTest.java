/**
 *
 */
package com.dxc.medxc.validators.models.validators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.dxc.medxc.validators.exceptions.ValidationException;
import com.dxc.medxc.validators.interfaces.ValidationRule;
import com.dxc.medxc.validators.models.rules.DateBeforeRule;
import com.dxc.medxc.validators.models.rules.StringConstraintRule;
import com.dxc.medxc.validators.models.rules.StringEmptinessRule;
import com.dxc.medxc.validators.models.rules.StringFormatRule;

/**
 * @author astefanov2
 */
class BaseValidatorTest {
    private BaseValidator validator;
    @SuppressWarnings("nls")
    private static final String PATIENT_ID_IDENTIFIER = "PAT_ID";
    @SuppressWarnings("nls")
    private static final String PATIENT_NAME_IDENTIFIER = "PAT_NAME";
    @SuppressWarnings("nls")
    private static final String PATIENT_VALIDATOR_PATTERN = "PAT[0-9]{1,10}";
    @SuppressWarnings("nls")
    private static final String DOCTOR_ID_IDENTIFIER = "DOC_ID";
    @SuppressWarnings("nls")
    private static final String DOCTOR_VALIDATOR_PATTERN = "DOC[0-9]{1,10}";
    @SuppressWarnings("nls")
    private static final String TEST_DATE_IDENTIFIER = "TEST_DATE";
    @SuppressWarnings("nls")
    private static final String EMPTY_VALIDATION_RESULT = "{}";
    @SuppressWarnings("nls")
    private static final String EMPTY_STRING = "";
    @SuppressWarnings("nls")
    private static final String VALID_NAME_BY_LENGTH = "Aleksandar";
    @SuppressWarnings("nls")
    private static final String INVALID_NAME_BY_LENGTH = "Aaaaaaaaaa Aaaaaaaaaa Aaaaaaaaaa Aaaaaaaaaa";
    @SuppressWarnings("nls")
    private static final String PATIENT_VALID_ID = "PAT111";
    @SuppressWarnings("nls")
    private static final String PATIENT_INVALID_ID = "PAT";
    @SuppressWarnings("nls")
    private static final String DOCTOR_VALID_ID = "DOC111";
    @SuppressWarnings("nls")
    private static final String DOCTOR_INVALID_ID = "DOC";
    @SuppressWarnings("nls")
    private static final ZonedDateTime DATE = ZonedDateTime.of(LocalDateTime.of(2019, 11, 14, 14, 20),
            ZoneId.of("UTC"));
    @SuppressWarnings("nls")
    private static final ZonedDateTime DATE_BEFORE = ZonedDateTime.of(LocalDateTime.of(1998, 11, 14, 14, 20),
            ZoneId.of("UTC"));
    @SuppressWarnings("nls")
    private static final ZonedDateTime DATE_AFTER = ZonedDateTime.of(LocalDateTime.of(2069, 11, 14, 14, 20),
            ZoneId.of("UTC"));

    @BeforeEach
    void init() {
        validator = new BaseValidator();
    }

    /**
     * Test method for
     * {@link com.dxc.medxc.validators.models.validators.BaseValidator#validate(java.lang.String, java.lang.Object, com.dxc.medxc.validators.interfaces.ValidationRule)}.
     */

    @Test
    @DisplayName("validate should return '{}' if all the checked fields are valid")
    void testValidateWithAllSuccessful() {
        validator.validate(PATIENT_ID_IDENTIFIER, PATIENT_VALID_ID, new StringFormatRule(PATIENT_VALIDATOR_PATTERN));
        validator.validate(PATIENT_NAME_IDENTIFIER, VALID_NAME_BY_LENGTH, new StringConstraintRule(0, 40));
        validator.validate(DOCTOR_ID_IDENTIFIER, DOCTOR_VALID_ID, new StringFormatRule(DOCTOR_VALIDATOR_PATTERN));
        assertEquals(EMPTY_VALIDATION_RESULT, validator.getErrors().toString());
    }

    /**
     * Test method for
     * {@link com.dxc.medxc.validators.models.validators.BaseValidator#validate(java.lang.String, java.lang.Object, com.dxc.medxc.validators.interfaces.ValidationRule)}.
     */
    @SuppressWarnings("nls")
    @Test
    @DisplayName("validate should add to errors all the invalid fields with the corresponding TypeError")
    void testValidateWithNotSuccessfullValidation() {
        validator.validate(PATIENT_ID_IDENTIFIER, PATIENT_INVALID_ID, new StringFormatRule(PATIENT_VALIDATOR_PATTERN));
        validator.validate(PATIENT_NAME_IDENTIFIER, INVALID_NAME_BY_LENGTH, new StringConstraintRule(1, 40));
        validator.validate(DOCTOR_ID_IDENTIFIER, DOCTOR_INVALID_ID, new StringFormatRule(DOCTOR_VALIDATOR_PATTERN));

        assertEquals(
                "{PAT_ID=[FORMAT [PAT[0-9]{1,10}]], PAT_NAME=[NOT_IN_RANGE [1, 40]], DOC_ID=[FORMAT [DOC[0-9]{1,10}]]}",
                validator.getErrors().toString());
    }

    /**
     * Test method for
     * {@link com.dxc.medxc.validators.models.validators.BaseValidator#validate(java.lang.String, java.lang.Object, com.dxc.medxc.validators.interfaces.ValidationRule)}.
     */
    @ParameterizedTest(name = "{1} should be valid with {2}.")
    @DisplayName("Parameterized validate with valid data")
    @MethodSource("validArguments")
    void testValidateSuccess(final String identifier, final Object obj, final ValidationRule rule) {
        validator.validate(identifier, obj, rule);
        assertTrue(validator.getErrors().isEmpty());
    }

    static Stream<Arguments> validArguments() {
        return Stream.of(
                Arguments.arguments(PATIENT_ID_IDENTIFIER, PATIENT_VALID_ID,
                        new StringFormatRule(PATIENT_VALIDATOR_PATTERN)),
                Arguments.arguments(PATIENT_NAME_IDENTIFIER, VALID_NAME_BY_LENGTH, new StringConstraintRule(0, 20)),
                Arguments.arguments(TEST_DATE_IDENTIFIER, DATE_BEFORE, new DateBeforeRule(DATE)),
                Arguments.arguments(DOCTOR_ID_IDENTIFIER, VALID_NAME_BY_LENGTH, new StringEmptinessRule()));
    }

    /**
     * Test method for
     * {@link com.dxc.medxc.validators.models.validators.BaseValidator#validate(java.lang.String, java.lang.Object, com.dxc.medxc.validators.interfaces.ValidationRule)}.
     */
    @ParameterizedTest(name = "{1} should be invalid with {2}.")
    @DisplayName("Parameterized validate with invalid data")
    @MethodSource("invalidArguments")
    void testValidateFail(final String identifier, final Object obj, final ValidationRule rule) {
        validator.validate(identifier, obj, rule);
        assertTrue(validator.getErrors().get(identifier).get(0).getErrorKey() == rule.getError());
    }

    static Stream<Arguments> invalidArguments() {
        return Stream.of(
                Arguments.arguments(PATIENT_ID_IDENTIFIER, PATIENT_INVALID_ID,
                        new StringFormatRule(PATIENT_VALIDATOR_PATTERN)),
                Arguments.arguments(PATIENT_NAME_IDENTIFIER, INVALID_NAME_BY_LENGTH, new StringConstraintRule(0, 20)),
                Arguments.arguments(TEST_DATE_IDENTIFIER, DATE_AFTER, new DateBeforeRule(DATE)),
                Arguments.arguments(DOCTOR_ID_IDENTIFIER, EMPTY_STRING, new StringEmptinessRule()));
    }

    /**
     * Test method for
     * {@link com.dxc.medxc.validators.models.validators.BaseValidator#checkValidated()}.
     */
    @SuppressWarnings("nls")
    @Test
    @DisplayName("checkValidate should throw ValidationException if there are invalid fields.")
    void testCheckValidatedShouldThrowValidationExceptionIfThereAreErrors() {
        validator.validate(PATIENT_ID_IDENTIFIER, "PAT", new StringFormatRule(PATIENT_VALIDATOR_PATTERN));
        assertThrows(ValidationException.class, () -> validator.checkValidated());
    }

    /**
     * Test method for
     * {@link com.dxc.medxc.validators.models.validators.BaseValidator#checkValidated()}.
     */
    @SuppressWarnings("nls")
    @Test
    @DisplayName("checkValidate should not throw ValidationException if there are not invalid fields.")
    void testCheckValidatedShouldNotThrowValidationExceptionIfThereAreNoErrors() {
        validator.validate(PATIENT_ID_IDENTIFIER, PATIENT_VALID_ID, new StringFormatRule(PATIENT_VALIDATOR_PATTERN));
        assertTrue(() -> {
            validator.checkValidated();
            return true;
        });
    }
}
