package com.dxc.medxc.persistence.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.dxc.medxc.persistence.jpa.entities.JpaDoctor;
import com.dxc.medxc.persistence.model.Doctor;
import com.dxc.medxc.persistence.repository.AdminRepository;

/**
 * Implementation of AdminRepository interface.
 *
 * @author atsekov
 */
public final class JpaAdminRepository implements AdminRepository {

    @SuppressWarnings("nls")
    private static final String INSERT_CONSTANT = "INSERT INTO DOC_SPEC (DOC_NUM, SPEC_ID, ODD) "
            + "VALUES (:docNum,:specId,:odd)";

    private static final int INVALID_ODD_DAY_NUMBER = -1;

    private final EntityManager manager;

    /**
     * @param manager
     *            EntityManager
     */
    public JpaAdminRepository(final EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean createDoctor(final String docNum, final String name, final String email, final String phoneNumber,
            final List<String> specialtyNames) {

        final Doctor doc = new JpaDoctor(docNum, name, email, phoneNumber, null, null);
        manager.persist(doc);
        insertSpeciatiesForDoctor(docNum, specialtyNames);
        return true;
    }

    @SuppressWarnings("nls")
    @Override
    public List<String> getAllSpecialtyNames() {
        return manager.createNamedQuery("Specialty.getAllNames", String.class).getResultList();
    }

    @SuppressWarnings("nls")
    private void insertSpeciatiesForDoctor(final String docNum, final List<String> specialtyNames) {
        if (specialtyNames == null) {
            return;
        }

        for (final String spec : specialtyNames) {
            final List<Integer> specialtyNumber = manager.createNamedQuery("Specialty.getIdByName", Integer.class)
                    .setParameter("name", spec.trim()).getResultList();
            manager.createNativeQuery(INSERT_CONSTANT).setParameter("docNum", docNum)
                    .setParameter("specId", specialtyNumber.get(0))
                    .setParameter("odd", Integer.valueOf(INVALID_ODD_DAY_NUMBER)).executeUpdate();
        }
    }
}
