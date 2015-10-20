package com.mycompany.myproject.impl;

import javax.jcr.Repository;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.jcr.api.SlingRepository;

import com.mycompany.myproject.HelloService;
import com.stefano.acme.acme.core.configurations.ConfigurationService;

/**
 * One implementation of the {@link HelloService}. Note that
 * the repository is injected, not retrieved.
 */
@Service
@Component(metatype = false)
public class HelloServiceImpl implements HelloService {
    
    @Reference
    private SlingRepository repository;
    
	@Reference(target="(configurationservice.label=AGENCY1)")
	ConfigurationService configurationService;

    public String getRepositoryName() {
        return repository.getDescriptor(Repository.REP_NAME_DESC);
    }
    
    public String getConfiguration() {
    	return configurationService.getConfig();
    }

}
