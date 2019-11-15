package com.mindthecode.CompanyDirectory;

import com.mindthecode.CompanyDirectory.models.entities.BusinessUnit;
import com.mindthecode.CompanyDirectory.models.entities.Position;
import com.mindthecode.CompanyDirectory.repositories.BusinessUnitRepository;
import com.mindthecode.CompanyDirectory.repositories.PositionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompanyDirectoryApplication implements CommandLineRunner {

    @Autowired
    private PositionRepo positionRepo;

    @Autowired
    private BusinessUnitRepository businessUnitRepository;

    public static void main(String[] args) {
        SpringApplication.run(CompanyDirectoryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Position pos1 = new Position(Position.AvailablePositions.JuniorAnalyst);
        Position pos2 = new Position(Position.AvailablePositions.JuniorDeveloper);
        Position pos3 = new Position(Position.AvailablePositions.JuniorAnalyst);
        Position pos4 = new Position(Position.AvailablePositions.SeniorAnalyst);
        Position pos5 = new Position(Position.AvailablePositions.SeniorDeveloper);
        Position pos6 = new Position(Position.AvailablePositions.JuniorAnalyst);
        Position pos7 = new Position(Position.AvailablePositions.JuniorDeveloper);
        Position pos8 = new Position(Position.AvailablePositions.JuniorAnalyst);
        Position pos9 = new Position(Position.AvailablePositions.SeniorAnalyst);
        Position pos10 = new Position(Position.AvailablePositions.SeniorDeveloper);

        positionRepo.save(pos1);
        positionRepo.save(pos2);
        positionRepo.save(pos3);
        positionRepo.save(pos4);
        positionRepo.save(pos5);
        positionRepo.save(pos6);
        positionRepo.save(pos7);
        positionRepo.save(pos8);
        positionRepo.save(pos9);
        positionRepo.save(pos10);

        BusinessUnit bUnit1 = new BusinessUnit("Solutions");
        BusinessUnit bUnit2 = new BusinessUnit("Markets");
        BusinessUnit bUnit3 = new BusinessUnit("Services");
        BusinessUnit bUnit4 = new BusinessUnit("Company");

        businessUnitRepository.save(bUnit1);
        businessUnitRepository.save(bUnit2);
        businessUnitRepository.save(bUnit3);
        businessUnitRepository.save(bUnit4);
    }
}
