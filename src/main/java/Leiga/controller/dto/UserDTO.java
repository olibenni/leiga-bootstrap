package Leiga.controller.dto;

public class UserDTO {
  private int age;
  private String name;
  private String id;

  public UserDTO(){};

  public UserDTO(int age, String name, String id)
  {
    this.age = age;
    this.name = name;
    this.id = id;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return this.id;
  }

  public void getId(String id) {
    this.id = id;
  }
}
