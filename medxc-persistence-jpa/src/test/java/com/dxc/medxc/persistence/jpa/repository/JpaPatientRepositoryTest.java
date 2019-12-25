/**
 *
 */
package com.dxc.medxc.persistence.jpa.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.dxc.medxc.commons.store.model.Store;
import com.dxc.medxc.persistence.jpa.entities.JpaRecord;
import com.dxc.medxc.persistence.jpa.transaction.TransactionManagerImpl;
import com.dxc.medxc.persistence.transaction.TransactionManager;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import com.dxc.medxc.persistence.model.Record;
import com.dxc.medxc.persistence.repository.PatientRepository;

import static org.junit.Assert.assertEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertPropertyLenientEquals;

/**
 * @author astefanov2
 */
public class JpaPatientRepositoryTest extends UnitilsJUnit4 {
    private static final String PIN = "9812148221";
    private static final String INVALID_PIN = "0000000000";
    private static final EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("medxc-persistence-jpa");
    final TransactionManager trxManager = new TransactionManagerImpl(entityManagerFactory);


    /**
     *
     */
    @Test
    @DataSet({ "Clear.xml","RecordsDataInput.xml"})
    public void testGetRecordsByIdWithExistingPatient() {
        final List<JpaRecord> recs =  this.trxManager.execute((final Store store) ->{
            final PatientRepository rep = store.find(PatientRepository.class);
               return  rep.getAllRecordsById(PIN).stream().map(r -> new JpaRecord(r.getAppointment(),r.getAnamnesis(),r.getObservation(),r.getTherapy())).collect(Collectors.toList());
            });

        assertPropertyLenientEquals("id", Arrays.asList(1,2,3),recs);
    }
    /**
     *
     */
    @Test
    @DataSet({ "Clear.xml","RecordsDataInput.xml"})
    public void testGetRecordsByIdWithNotExistingPatient() {
        final List<JpaRecord> recs =  this.trxManager.execute((final Store store) ->{
            final PatientRepository rep = store.find(PatientRepository.class);
            return  rep.getAllRecordsById(INVALID_PIN).stream().map(r -> new JpaRecord(r.getAppointment(),r.getAnamnesis(),r.getObservation(),r.getTherapy())).collect(Collectors.toList());
        });

        assertEquals(0,recs.size());
    }


}
