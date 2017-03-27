/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.management;

/**
 *
 * @author kajornjit.songsaen
 */
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

    @XmlElement(name="Name")
    private String name;

    @XmlElement(name="Age")
    private int age;

    public Person() {
    }
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }    
}