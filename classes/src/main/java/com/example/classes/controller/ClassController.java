package com.example.classes.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.classes.model.Class;
import com.example.classes.repository.ClassRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ClassController {
  @Autowired
  ClassRepository classRepository;

  @GetMapping("/classes")
  public ResponseEntity<List<Class>> getAllClasses(@RequestParam(required = false) String title){
    try{
      List<Class> classes = classRepository.findAll();
      if(title != null){
        classes = classRepository.findByTitleContaining(title);
      }

      if(classes.isEmpty()){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(classes, HttpStatus.OK);
    } catch (Exception e){
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/classes/{id}")
  public ResponseEntity<Class> getClassById(@PathVariable("id") long id){
    Optional<Class> classData = classRepository.findById(id);

    if(classData.isPresent()){
      return new ResponseEntity<>(classData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/classes")
  public ResponseEntity<Class> createClass(@RequestBody Class classEntity){
    try{
      Class newClass = classRepository.save(new Class(classEntity.getTitle(), classEntity.getDescription(), classEntity.isTaught()));
      return new ResponseEntity<>(newClass, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/classes/{id}")
  public ResponseEntity<Class> updateClass(@PathVariable("id") long id, @RequestBody Class classEntity){
    Optional<Class> classData = classRepository.findById(id);

    if (classData.isPresent()) {
      Class existingClass = classData.get();
      existingClass.setTitle(classEntity.getTitle());
      existingClass.setDescription(classEntity.getDescription());
      existingClass.setTaught(classEntity.getTaught());
      return new ResponseEntity<>(classRepository.save(existingClass), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }

  @DeleteMapping("/classes/{id}")
  public ResponseEntity<HttpStatus> deleteClass(@PathVariable("id") long id){
    try{
      classRepository.deleteById(id);
      return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/classes")
  public ResponseEntity<HttpStatus> deleteAllClasses() {
    try {
        classRepository.deleteAll(); 
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/classes/taught")
  public ResponseEntity<List<Class>> findByTaught() {
    try {
      List<Class> taughtClasses = classRepository.findByTaught(true);
      if (taughtClasses.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(taughtClasses, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
