package za.co.wonderbank.models;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Data
@ToString
@Getter
@Setter

@MappedSuperclass
public class Person {

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

}
