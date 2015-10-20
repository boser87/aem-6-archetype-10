package com.mycompany.myproject.servlets;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import com.stefano.acme.acme.core.configurations.ConfigurationService;

import javax.servlet.ServletException;

import java.io.IOException;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */
@SuppressWarnings("serial")
@SlingServlet(paths = "/bin/simpleservletagency")
public class SimpleServlet extends SlingSafeMethodsServlet {
	
	@Reference(target="(configurationservice.label=AGENCY1)")
	ConfigurationService configurationService;

    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
        final Resource resource = req.getResource();
        resp.getOutputStream().println(resource.toString());
        resp.getOutputStream().println(
                "This content is generated by the SimpleServlet");
        resp.getOutputStream().print(configurationService.getConfig());
    }
}