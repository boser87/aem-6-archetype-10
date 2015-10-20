package com.stefano.acme.acme.core.configurations.impl;

import java.util.Dictionary;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stefano.acme.acme.core.configurations.ConfigurationService;

@Component(configurationFactory = true, policy = ConfigurationPolicy.REQUIRE, metatype = true, immediate = true)
@Service()
public class ConfigurationServiceImpl implements ConfigurationService {

	private static final Logger LOG = LoggerFactory.getLogger(ConfigurationServiceImpl.class);
	
	@Property(description="Label for this configuration service")
	private static final String NAME = "configurationservice.label";
	
	// define config properties here as usual
	@Property(name = "some.prop.name", label = "My Property", value = "default value")
	private String myProperty;

	/**
	 * Activate the configuration.
	 * 
	 * @param ctx
	 *            the component context object
	 */
	@Activate
	protected void activate(final ComponentContext ctx) {
		LOG.info("activating instance of {}", this.getClass().getName());
		Dictionary<?, ?> props = ctx.getProperties();
		// init property values using PropertiesUtil
		myProperty = PropertiesUtil.toString(props.get("some.prop.name"), "");
	}

	@Override
	public String getConfig() {
		return myProperty;
	}
}