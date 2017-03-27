/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.management;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author kajornjit.songsaen
 */
@XmlRootElement(name="Persons")
@XmlAccessorType(XmlAccessType.FIELD)
public class Persons {

    @XmlElement(name="Person")
    private List<Person> people;

}