package entity;

public class Cat {
  
  private int catId;
  private String name;
  private String attitude;
  private String gender;
  
  public Cat(int catId, String name, String attitude, String gender) {
      this.catId = catId;
      this.name = name;
      this.attitude = attitude;
      this.gender = gender;
  }

  public int getCatId() {
    return catId;
  }
  public void setCatId(int catId) {
    this.catId = catId;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getAttitude() {
    return attitude;
  }
  public void setAttitude(String attitude) {
    this.attitude = attitude;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  
}



