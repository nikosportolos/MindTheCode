package com.mindthecode.CompanyDirectory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompanyDirectoryApplication implements CommandLineRunner {

	@Autowired
	BusinessUnitRepository businessUnitRepository;

	public static void main(String[] args) {
		SpringApplication.run(CompanyDirectoryApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception{
		BusinessUnit bUnit1 = new BusinessUnit("Solutions");
		BusinessUnit bUnit2 = new BusinessUnit("Markets");
		BusinessUnit bUnit3 = new BusinessUnit("Services");

		businessUnitRepository.save(bUnit1);
		businessUnitRepository.save(bUnit2);
		businessUnitRepository.save(bUnit3);
	}
}
