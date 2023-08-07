package com.example.restapidemo;

import com.example.restapidemo.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    PersonRepo repo;

    //SIMPLE SPRING MVC
    @RequestMapping("/")
    public String home(){
        return "home";
    }

    //SIMPLE Addition to db
    @RequestMapping("/addPerson")
    public String adPerson(Person person){
        repo.save(person);
        return "home";
    }

    @RequestMapping("/updatePerson")
    public String updatePerson(Person person){
        repo.delete(person);
        repo.save(person);
        return "home";
    }
    @RequestMapping("/deletePerson")
    public String delPerson(Person person){
        repo.delete(person);
        return "home";
    }
    @RequestMapping("/searchPerson")
    public ModelAndView searchPerson(@RequestParam int id){
        ModelAndView mv = new ModelAndView("display");

        Person person = repo.findById(id).orElse(new Person());
        System.out.println(repo.findByIdGreaterThan(2));// specific function
        System.out.println(repo.findByLocation("Isb"));// specific function
        System.out.println(repo.findByLocationInOrder("Isb"));// specific function

        mv.addObject(person);
        return mv;
    }
    /*    @RequestMapping("/Persons")
    @ResponseBody
    public String getPersons(){
        return repo.findAll().toString();
    }

    //find any id by using WILD CARD
    @RequestMapping("/Person/{id}")
    @ResponseBody
    public String getPerson(@PathVariable("id") int id){
        return repo.findById(id).toString();
    }*/


//(JPA is a repository i.e., crud etc)
//RestAPI   --> no ModelAndView just data. add @RespondBody because when we use String it automatically considers that we are
//returning view name but actually we are returning data so thats why we use @RespondBody, Also no parameters are required. also we don't need views e.g. jsp pages
//Remember: represent data in JSON or XML format, so other machines would understand
//Jackson converts list to JSON by default
//To fetch and send json also to delete or update a resource in this case we need a client for example POSTMAN (helps to build, test and modify APIs, enables you to create and send API requests)
//We do Content Negotiation if we want to use data in XML Format or if the customer want data in pdf format we can say we only support specific format
//first check if our app is sending XML format or not by sending GET request on POSTMAN and requesting XML  (i.e. Key = ACCEPT in Header , then Value = Application/XML)
//If Postman does not get XML Then add Jackson Dataformat XML dependency
//As we have to use @RespondBody every time for REST api, we can skip it by using @RestController rather than simple @Controller
//for creating an instance in db using REST we need to use annotation @PostMapping
//also during using Post request we need to remove function type String because string returns view. we just want to display data.
//We have done all the work so far in @Controller where we are just accepting requests and calling function, this is known as SPRING MVC REST,
//We can skip this controller part and just utilize JpaRepository interface, this is known as SPRING DATA REST, for that create Rest Repository project + h2 + jpa


    //SPRING MVC REST
    //Return JSON FORMAT
    @RequestMapping(path = "/Persons", produces = {"application/xml","application/json"}) //to restrict to specific format we use path and produces
//    @ResponseBody
    public List<Person> getPersons(){
        return repo.findAll();
    }

    //find any id by using WILD CARD i.e. {..}
    @RequestMapping("/Person/{id}")
//    @ResponseBody
    public Optional<Person> getPerson(@PathVariable("id") int id){
        return repo.findById(id);
    }

    //REST Addition to db   //consumes: means server will only accept specific format, so client cannot post other than this format
    @PostMapping(path = "/Person", consumes = {"application/json"}) //for REST post    //@RequestBody for RAW in postman
    public Person addPerson(@RequestBody Person person){ //remove String because we don't want to return view. instead return data/object
        repo.save(person);
        return person;
    }
    @PutMapping(path = "/Person", consumes = {"application/json"})
    public Person upPerson(@RequestBody Person person){
        repo.save(person);
        return person;
    }

    @DeleteMapping(path = "/Person/{id}")
    public String deletePerson(@PathVariable int id){
        Person p = repo.getOne(id);
        repo.delete(p);
        return "deleted";
    }



}
