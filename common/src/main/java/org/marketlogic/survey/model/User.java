package org.marketlogic.survey.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;
    @Column
    private String userName;

    public User(UserDto userDto) {
        this.userName = userDto.getUserName();
    }
}
