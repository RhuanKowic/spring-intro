package main.java.com.example.classes.model;
import javax.annotation.processing.Generated;

import jakarta.persistence;

@Entity
@Table(name = "classes")
public class Class {
  @Id
  @GeneratedVale(strategy = GeneratedType.AUTO)
  private long id;
  
  @Column(name = "title")
  private String title;

  @Column(name = "taught")
  private boolean taught;

  public Class(){

  }

  public Class (String title, String description, boolean taught){
    this.title = title;
    this.description = description;
    this.taught = taught;
  }
  public long getId(){
    return id;
  }
  public String getTitle(){
    return title;
  }
  public void setTitle(String title){
    this.title = title;
  }
  public String getDescription(){
    return description;
  }
  public void setDescription(String description){
    this.description = description;
  }
  public boolean isTaught(){
    return taught;
  }
  public void setTaught(boolean wasTaught){
    this.taught = wasTaught;
  }

  @Override
  public String toString(){
    return "Class [id=" + id + ", title=" + title + ", description=" + description + ", taught=" + taught + "]";
  }
}
