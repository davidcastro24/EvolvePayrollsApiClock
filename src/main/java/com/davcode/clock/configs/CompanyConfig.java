package com.davcode.clock.configs;

import com.davcode.clock.models.Company;
import com.davcode.clock.repositories.CompanyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

@Configuration
public class CompanyConfig {

    @Bean
    CommandLineRunner commandLineRunner(CompanyRepository companyRepository){
        return args -> {
            Company com1 = new Company(
                    1L,
                    "Company Name",
                    TimeZone.getDefault(),
                    true,
                    true,
                    false
            );
            Company com2 = new Company(
                    2L,
                    "Company Name2",
                    TimeZone.getDefault(),
                    true,
                    true,
                    false
            );
            Company com3 = new Company(
                    3L,
                    "Company Name",
                    TimeZone.getDefault(),
                    true,
                    true,
                    false
            );
            companyRepository.saveAll(Arrays.asList(com1, com2, com3));
        };
    }

}
