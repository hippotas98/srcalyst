import java.util.Collections.*;
import java.util.*;
public class cau3
{	
int a = 0;
	//public void static abc ()
//{
//	a = 1;
//}
    public static void main (String [] args)
    {
        Teacher t1 = new Teacher();
        t1.setAge(10);
        t1.setGender("male");
        t1.setName("Hello");
        t1.setRank("Phd");
        t1.setSchool("ABC");
        System.out.println(t1.getName() + "\n" + t1.getGender()+
        "\n" + t1.getRank() + "\n" + t1.getSchool());
        
    }
}
interface hello
{
void draw();
}
interface h2
{
void print();
}
class Person 
{
    int age;
    String name;
    String gender;
    public int getAge() {
        return this.age;
    }
    public void setAge (int age) {
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender (String gender) {
        this.gender = gender;
    }
}
class Teacher extends Person implements hello
{
    String school;  
    String rank;
    public String getSchool() {
        return this.school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public String getRank() {
        return this.rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
	public void draw()
{
	school = "1";
}
}
//class President extends Person{
//    String country;
//    public String getCountry() {
//        return this.country;
//    }
//    public void setCountry(String country) {
//        this.country = country;
//    }
//}
class Developer extends Person{
    List<String> languages = new ArrayList<String>();
    public List<String> getLanguage() {
        return this.languages;
    }
    public void setLanguage(List<String> languages) {
        this.languages = languages;
    }
}
class Student extends Person{
    String school;
    String Class;
    public String getSchool() {
        return this.school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public String GetClass() {
        return this.Class;
    }
    public void setClass(String Class) {
        this.Class = Class;
    }
}
class Animal
{
    String gender;
    int leg;
    public int getLeg() {
        return this.leg;
    }
    public void setLeg(int leg) {
        this.leg = leg;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}
class Cat extends Animal{
    String species;
    public String getSpecies() {
        return this.species;
    }
    public void setSpecies(String species) {
        this.species = species;
        this.leg = 4;
    }
}
class Chicken extends Animal{
    String species;
    public String getSpecies() {
        return this.species;
    }
    public void setSpecies(String species) {
        this.species = species;
        this.leg = 2;
    }
}
class Fish extends Animal
{
    String habitat;
    String species;
    public String getSpecies() {
        return this.species;
    }
    public void setSpecies(String species) {
        this.species = species;
        this.leg = 0;
    }
    public String getHabitat() {
        return this.habitat;
    }
    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }
}
class Bird extends Animal{
    String habitat;
    boolean layeggs;
    public String getHabitat() {
        return this.habitat;
    }
    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }
    public boolean getLayeggs() {
        return this.layeggs;
    }
    public void setLayeggs(boolean layeggs) {
        this.layeggs = layeggs;
    }
}