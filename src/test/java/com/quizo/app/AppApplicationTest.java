package com.quizo.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
		"spring.datasource.url=jdbc:h2:mem:testdb",
		"spring.datasource.driverClassName=org.h2.Driver",
		"spring.datasource.username=sa",
		"spring.datasource.password=",
		"spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
		"spring.h2.console.enabled=true"
})
@DisplayName("AppApplication Tests")
class AppApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	@DisplayName("Context should load successfully")
	void contextLoads() {
		assertNotNull(applicationContext, "Application context should not be null");
	}

	@Test
	@DisplayName("Application context should not be null")
	void applicationContextIsNotNull() {
		assertNotNull(applicationContext);
	}

	@Test
	@DisplayName("AppApplication bean should be present in context")
	void appApplicationBeanExists() {
		assertTrue(applicationContext.containsBean("appApplication"),
				"AppApplication bean should be registered in the context");
	}

	@Test
	@DisplayName("AppApplication class should be instantiated")
	void appApplicationCanBeInstantiated() {
		AppApplication app = applicationContext.getBean(AppApplication.class);
		assertNotNull(app, "AppApplication bean should be retrievable from context");
	}

	@Test
	@DisplayName("Main method should run without exceptions")
	void mainMethodRuns() {
		assertDoesNotThrow(() -> AppApplication.main(new String[] {}),
				"Application main method should start without throwing exceptions");
	}

	@Test
	@DisplayName("Application should handle empty arguments")
	void applicationShouldHandleEmptyArguments() {
		assertDoesNotThrow(() -> AppApplication.main(new String[0]),
				"Application should handle empty arguments array");
	}
}
