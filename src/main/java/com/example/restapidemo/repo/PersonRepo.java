package com.example.restapidemo.repo;

import com.example.restapidemo.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepo extends JpaRepository<Person, Integer> { //We are using JpaRepository because it has extra features including CrudRepository ones. Extra feature which we need is List one which allows return type of List and Jackson automatically converts List to JSON Format ,, We dont need to create any other function
    List<Person> findByLocation(String location); //after findBy, name has to be exact as the variable in model class
    List<Person> findByIdGreaterThan(int id); //GreaterThan has functionality
    @Query("from Person where location=?1 order by name")
    List<Person> findByLocationInOrder(String location); //If we don't want to use property of model class so we can create our own query.

}
