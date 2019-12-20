/**
 *
 */
package com.dxc.medxc.init;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.dxc.medxc.commons.store.implementation.Register;
import com.dxc.medxc.commons.store.implementation.StoreImpl;
import com.dxc.medxc.commons.store.model.Store;
import com.dxc.medxc.implementation.AdminServiceImpl;
import com.dxc.medxc.implementation.DoctorServiceImpl;
import com.dxc.medxc.implementation.PatientServiceImpl;
import com.dxc.medxc.persistence.jpa.transaction.TransactionManagerImpl;
import com.dxc.medxc.persistence.transaction.TransactionManager;
import com.dxc.medxc.services.AdminService;
import com.dxc.medxc.services.DoctorService;
import com.dxc.medxc.services.PatientService;

/**
 * Application initialization and destruction on start/shutdown.
 *
 * @author amirchev
 */
public final class AppContextListener implements ServletContextListener {

    private final EntityManagerFactory persistenceFactory = Persistence
            .createEntityManagerFactory("medxc-persistence-jpa"); //$NON-NLS-1$
    private final Logger logger = Logger.getLogger(AppContextListener.class.getName());

    /**
     * Context initializing and configuring on application start. Override from
     * {@link javax.servlet.ServletContextListener#contextInitialized(ServletContextEvent)}
     *
     * @param sce
     *            Servlet Context Event we get from server.
     */
    @SuppressWarnings("nls")
    @Override
    public void contextInitialized(final ServletContextEvent sce) {

        final TransactionManager trxManager = new TransactionManagerImpl(persistenceFactory);
        final Register<TransactionManager> serviceRegister = new Register<>();
        serviceRegister.register(DoctorService.class, DoctorServiceImpl::new);
        serviceRegister.register(PatientService.class, PatientServiceImpl::new);
        serviceRegister.register(AdminService.class, AdminServiceImpl::new);
        final StoreImpl<TransactionManager> serviceStore = new StoreImpl<>(trxManager, serviceRegister);

        logger.log(Level.INFO, "Starting up");

        final ServletContext context = sce.getServletContext();
        context.setAttribute(Store.class.getName(), serviceStore);
        logger.log(Level.INFO, "Service provided");
    }

    /**
     * Destroying context on application shutdown. Override from
     * {@link javax.servlet.ServletContextListener#contextDestroyed(ServletContextEvent)
     * contextDestroyed}
     *
     * @param sce
     *            Servlet Context Event we get from server.
     */
    @SuppressWarnings("nls")
    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
        persistenceFactory.close();
        logger.log(Level.INFO, "Shutting down");
    }

}
