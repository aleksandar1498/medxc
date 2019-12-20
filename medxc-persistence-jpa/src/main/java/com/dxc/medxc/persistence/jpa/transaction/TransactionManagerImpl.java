package com.dxc.medxc.persistence.jpa.transaction;

import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.dxc.medxc.commons.store.implementation.Register;
import com.dxc.medxc.commons.store.implementation.StoreImpl;
import com.dxc.medxc.commons.store.model.Store;
import com.dxc.medxc.persistence.jpa.repository.JpaAdminRepository;
import com.dxc.medxc.persistence.jpa.repository.JpaDoctorRepository;
import com.dxc.medxc.persistence.jpa.repository.JpaPatientRepository;
import com.dxc.medxc.persistence.repository.AdminRepository;
import com.dxc.medxc.persistence.repository.DoctorRepository;
import com.dxc.medxc.persistence.repository.PatientRepository;
import com.dxc.medxc.persistence.transaction.TransactionManager;

/**
 * Implementation of TransactionManager interface
 *
 * @author atsekov
 */
public class TransactionManagerImpl implements TransactionManager {
    private final EntityManagerFactory persistenceFactory;
    private static final Register<EntityManager> repositoryRegister;

    static {
        repositoryRegister = new Register<>();
        repositoryRegister.register(DoctorRepository.class, JpaDoctorRepository::new);
        repositoryRegister.register(PatientRepository.class, JpaPatientRepository::new);
        repositoryRegister.register(AdminRepository.class, JpaAdminRepository::new);
    }

    /**
     * @param factoryParam
     *            EntityManagerFactory passed from init module
     */
    public TransactionManagerImpl(final EntityManagerFactory factoryParam) {
        persistenceFactory = factoryParam;
    }

    @Override
    public <R> R execute(final Function<Store, R> function) {
        R result;
        final EntityManager entityManager = persistenceFactory.createEntityManager();
        try {
            final EntityTransaction entityTrx = entityManager.getTransaction();
            entityTrx.begin();
            try {
                final StoreImpl<EntityManager> repositoryStore = new StoreImpl<>(entityManager, repositoryRegister);

                result = function.apply(repositoryStore);
                entityTrx.commit();
            } finally {
                if (entityTrx.isActive()) {
                    entityTrx.rollback();
                }
            }
        } finally {
            entityManager.close();
        }
        return result;
    }
}
