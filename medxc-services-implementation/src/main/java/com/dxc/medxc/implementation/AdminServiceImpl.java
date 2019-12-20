package com.dxc.medxc.implementation;

import java.util.List;

import com.dxc.medxc.commons.store.model.Store;
import com.dxc.medxc.converters.ObjectConverter;
import com.dxc.medxc.dto.DoctorDTO;
import com.dxc.medxc.dto.SpecialtyDTO;
import com.dxc.medxc.persistence.repository.AdminRepository;
import com.dxc.medxc.persistence.transaction.TransactionManager;
import com.dxc.medxc.services.AdminService;
import com.dxc.medxc.validators.models.rules.StringConstraintTopLimitRule;
import com.dxc.medxc.validators.models.rules.StringEmptinessRule;
import com.dxc.medxc.validators.models.validators.BaseValidator;

/**
 * Implementation of AdminService interface.
 *
 * @author atsekov
 */
public final class AdminServiceImpl implements AdminService {
    private final static int ID_LENGTH = 10;
    private final static int NAME_LENGTH = 70;
    private final static int EMAIL_LENGTH = 50;
    private final static int PHONE_NUMBER_LENGTH = 20;

    private final TransactionManager manager;

    /**
     * Constructor of the class.
     *
     * @param manager
     *            TransactionManager used in the service.
     */
    public AdminServiceImpl(final TransactionManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean createDoctor(final DoctorDTO docDto) {
        validateDoctor(docDto);
        return this.manager.execute((final Store s) -> {
            final AdminRepository rep = s.find(AdminRepository.class);
            return Boolean.valueOf(rep.createDoctor(docDto.getId(), docDto.getName(), docDto.getEmail(),
                    docDto.getPhoneNumber(), getSpecialtyNames(docDto.getSpecialties())));
        }).booleanValue();

    }

    @SuppressWarnings("static-method")
    private List<String> getSpecialtyNames(final List<SpecialtyDTO> specialties) {
        return ObjectConverter.convertList(SpecialtyDTO::getName, specialties);
    }

    @Override
    public List<String> getAllSpecialtyNames() {
        return this.manager.execute((final Store s) -> {
            final AdminRepository rep = s.find(AdminRepository.class);
            return rep.getAllSpecialtyNames();
        });
    }

    @SuppressWarnings({ "nls", "static-method" })
    private void validateDoctor(final DoctorDTO docDto) {
        final BaseValidator validator = new BaseValidator();
        validator.validate("DOC_ID", docDto.getId(), new StringEmptinessRule());
        validator.validate("DOC_ID", docDto.getId(), new StringConstraintTopLimitRule(ID_LENGTH));

        validator.validate("DOC_NAME", docDto.getName(), new StringEmptinessRule());
        validator.validate("DOC_NAME", docDto.getName(), new StringConstraintTopLimitRule(NAME_LENGTH));

        validator.validate("DOC_EMAIL", docDto.getEmail(), new StringEmptinessRule());
        validator.validate("DOC_EMAIL", docDto.getEmail(), new StringConstraintTopLimitRule(EMAIL_LENGTH));

        validator.validate("DOC_PHONE", docDto.getPhoneNumber(), new StringEmptinessRule());
        validator.validate("DOC_PHONE", docDto.getPhoneNumber(), new StringConstraintTopLimitRule(PHONE_NUMBER_LENGTH));

        validator.checkValidated();
    }
}
