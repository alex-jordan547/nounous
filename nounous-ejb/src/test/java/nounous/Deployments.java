package nounous;

import java.io.File;

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;


@ArquillianSuiteDeployment
public class Deployments {

	
    @Deployment  
    public static Archive<?> getEarArchive() {
    	
    	System.setProperty( "org.apache.maven.user-settings", "C:\\dev22\\utils\\maven\\conf\\settings.xml" );

        var ear = ShrinkWrap.create(EnterpriseArchive.class, "nounous-ejb.ear");
    	
        var ejb = ShrinkWrap
				.create(JavaArchive.class, "nounous-ejb.jar")              
				.addPackage("nounous.ejb.dao")                
				.addPackage("nounous.ejb.dao.jpa")                
				.addPackage("nounous.ejb.data")                
				.addPackage("nounous.ejb.data.mapper")                
				.addPackage("nounous.ejb.service.standard")                
        		.addAsResource( "META-INF/persistence.xml" )
		        .addAsResource(  new File("../scripts-sql/3-data.sql") )
				;
        ear.addAsModule(ejb);
    	
        File[] ejbDependencies = Maven.resolver()
			.loadPomFromFile("../nounous-ejb/pom.xml")
			.importRuntimeDependencies()
			.resolve()
			.withTransitivity()
			.asFile();   
		for (File archive : ejbDependencies) {  
			ear.addAsLibrary(archive);  
		}   
	     
        // Export the EAR file to examine it in case of problems:
		ear.as(ZipExporter.class).exportTo(new File("C:\\TEMP\\nounous-ejb.ear"), true);

		return ear;
    }
}
