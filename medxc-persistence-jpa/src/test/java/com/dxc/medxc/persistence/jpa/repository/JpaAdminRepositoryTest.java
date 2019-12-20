package com.dxc.medxc.persistence.jpa.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;

import com.dxc.medxc.persistence.repository.AdminRepository;

/**
 * Test class for JpaAdminRepository
 * 
 * @author atsekov
 */
public class JpaAdminRepositoryTest extends UnitilsJUnit4 {
    private static final EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("medxc-persistence-jpa"); //$NON-NLS-1$

    /**
     * Test for createDoctor without specialties
     */
    @SuppressWarnings({ "nls", "static-method" })
    @Test
    @DataSet("Clear.xml")
    @ExpectedDataSet("CreateDoctorExpected.xml")
    public void TestCreateDoctor() {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final AdminRepository adminRepo = new JpaAdminRepository(entityManager);
        List<String> specialties = new ArrayList<>();
        try {
            final EntityTransaction entityTrx = entityManager.getTransaction();
            entityTrx.begin();
            adminRepo.createDoctor("0000000001", "Atanas", "ABC@abv.bg", "0899999999", specialties);
            entityTrx.commit();
        } finally {
            entityManager.close();
        }
    }

    /**
     * Test for createDoctor with specialties
     */
    @SuppressWarnings({ "nls", "static-method" })
    @Test
    @DataSet({ "Clear.xml", "SpecialtiesDataInput.xml" })
    @ExpectedDataSet("CreateDoctorWithSpecialtiesExpected.xml")
    public void TestCreateDoctorWithSpecialties() {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final AdminRepository adminRepo = new JpaAdminRepository(entityManager);

        final List<String> specialties = new ArrayList<>();
        specialties.add("TEST SPECIALTY");
        specialties.add("SPECIALTY");
        try {
            final EntityTransaction entityTrx = entityManager.getTransaction();
            entityTrx.begin();
            adminRepo.createDoctor("0000000002", "Pavel", "testEmail@abv.bg", "0899999999", specialties);
            entityTrx.commit();
        } finally {
            entityManager.close();
        }
    }

    /**
     * Test for getAllSpecialtyNames.
     */
    @SuppressWarnings("nls")
    @Test
    @DataSet({ "Clear.xml", "SpecialtiesDataInput.xml" })
    public void TestGetSpecialties() {
        final List<String> expected = new ArrayList<>();
        List<String> specialties = new ArrayList<>();
        expected.add("TEST SPECIALTY");
        expected.add("SPECIALTY");

        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final AdminRepository adminRepo = new JpaAdminRepository(entityManager);
        try {
            specialties = adminRepo.getAllSpecialtyNames();
            specialties = trimSpecialties(specialties);
        } finally {
            entityManager.close();
        }
        assertEquals(specialties, expected);
    }

    private List<String> trimSpecialties(final List<String> specialties) {
        List<String> newSpec = new ArrayList<>();
        for (String spec : specialties) {
            newSpec.add(spec.trim());
        }
        return newSpec;
    }
}
