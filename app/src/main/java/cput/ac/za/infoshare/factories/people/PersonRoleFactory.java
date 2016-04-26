/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cput.ac.za.infoshare.factories.people;


import cput.ac.za.infoshare.AppConf.security.DomainState;
import cput.ac.za.infoshare.domain.person.PersonRole;

public class PersonRoleFactory {

    public static PersonRole getPersonRole(String personId, String roleId) {

        return  new PersonRole.Builder()
                .state(DomainState.ACTIVE.name())
                .personId(personId)
                .roleId(roleId)               
                .build();
    }
    
}
