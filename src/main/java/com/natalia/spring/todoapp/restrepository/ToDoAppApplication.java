package com.natalia.spring.todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class ToDoAppApplication implements RepositoryRestConfigurer {
//Bez implementacji
	public static void main(String[] args) {
		SpringApplication.run(ToDoAppApplication.class, args);
	}

	@Override//jeżeli korzystamy z bilioteki zewnętrznej, która nie wpiera spring, musimy stworzyć obiek tej metody
	//w tym momencie jest to motego validator z anotacja Bean, obiekt utrzony z tej metody będzue przypisany do kontekstu springa
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
		validatingListener.addValidator("beforeCreate", validator());
		validatingListener.addValidator("beforeSave", validator());
	}
	//Przed tym ja nie było walidacji czyli metody: configureValidatingRepositoryEventListener, i wysłaliśmy np puste desc dostawaliśmy 500
	//dzięki validacji komunikat jest popprawny 400


	@Bean//annotacja bean oznacza że metoda jest widoczna przez springa
	Validator validator() {
		return new LocalValidatorFactoryBean();
	}
}
