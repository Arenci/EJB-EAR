package com.test.hello;


import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Date;


import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.ws.api.annotation.WebContext;


/**
 * Session Bean implementation class HelloWebService
 */
@SecurityDomain("HelloWS")
@Stateless
@WebService(serviceName = "HelloService")
@WebContext(authMethod = "BASIC", urlPattern = "/*", secureWSDLAccess = false)
@DeclareRoles({"CLIENT", "FROM_USD_TO_GBP", "FROM_GBP_TO_USD", "FROM_USD_TO_EURO", "FROM_EURO_TO_USD"})
@RolesAllowed("CLIENT")

public class HelloWebService {


    @Resource
    private SessionContext ctx;
    @PersistenceContext
    private EntityManager entityManager;
	
	
	@RolesAllowed("CLIENT")
	public void ping() {
		
	}
    
	
	private void doLogAction(String description) {
	    ActionLog log = new ActionLog();
	    log.setActionTime(new Date());
	    log.setUsername(ctx.getCallerPrincipal().getName());
	    entityManager.persist(log);
	    ActionLogDescripton desc = new ActionLogDescripton();
	    desc.setActionLogId(log.getId());
	    desc.setDescription(description);
	    entityManager.persist(desc);
	}
	
    public HelloWebService() {
        	
    }
    
    @WebMethod
    @RolesAllowed("FROM_USD_TO_GBP")
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 
	public double fromUSDtoGBP(@WebParam(name = "amount")double amount) {
    	 doLogAction("fromUSDtoGBP(amount=" + amount + ")");         
    	 return amount * 0.60;
	}
    
    @WebMethod
    @RolesAllowed("FROM_GBP_TO_USD")
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 
	public double fromGBPtoUSD(@WebParam(name = "amount")double amount) {
    	doLogAction("fromGBPtoUSD(amount=" + amount + ")");
    	return amount / 0.60;
	}
    
    @WebMethod
    @RolesAllowed("FROM_USD_TO_EURO")
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 
	public double fromUSDtoEURO(@WebParam(name = "amount")double amount) {
    	doLogAction("fromUSDtoEURO(amount=" + amount + ")");
    	return amount * 0.72;
	}
    
    @WebMethod
    @RolesAllowed("FROM_EURO_TO_USD")
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) 
	public double fromEUROtoUSD(@WebParam(name = "amount")double amount) {
    	doLogAction("fromEUROtoUSD(amount=" + amount + ")");
    	return amount / 0.72;
	}

}
