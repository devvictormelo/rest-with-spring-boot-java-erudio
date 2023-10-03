package br.com.erudio.services;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service //Instância o obejto = como se fosse private PersonServices service = new PersonServices();
public class PersonServices {

    private Logger logger = Logger.getLogger(Person.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll() {

        logger.info("Fiding all people!");

        return repository.findAll();
    }


    public Person findById(Long id) {

        logger.info("Fiding one person!");
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

    }

    public Person create(Person person) {

        logger.info("Creating one person!");

        return repository.save(person);
    }

    public Person update(Person person) {


        logger.info("Updating one person!");

        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));


        person.setFirstName(person.getFirstName());
        person.setLastName(person.getLastName());
        person.setAddress(person.getAddress());
        person.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

}
