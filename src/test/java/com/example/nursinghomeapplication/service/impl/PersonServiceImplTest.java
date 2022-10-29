package com.example.nursinghomeapplication.service.impl;

import com.example.nursinghomeapplication.entity.drug.DrugList;
import com.example.nursinghomeapplication.entity.personnel.PersonUser;
import com.example.nursinghomeapplication.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceImplTest {
    @Resource
    PersonService personService;
    @Test
    void getPersonDrug(){
        List<Map<String, Object>> personDrug = personService.getPersonDrug(1);
        System.out.println(personDrug);
    }
    @Test
    void getPerFoodsById(){
        List<Map<String, Object>> perFoodsById = personService.getPerFoodsById(1);
        System.out.println(perFoodsById);
    }

    @Test
    void getPersonnelUser() {
        List<PersonUser> personUserList = personService.getPersonnelUser(1);
        System.out.println(personUserList);
    }
}
