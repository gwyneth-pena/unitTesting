package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfStudentExistsByEmail() {
        //given
        String email = "jamilla@gmail.com";
        Student student = new Student(
                "Jamilla",
                email,
                Gender.FEMALE
        );
        underTest.save(student);
        //when
        boolean expected=underTest.selectExistsEmail(email);
        //then
        assertThat(expected).isTrue();
    }
    @Test
    void itShouldCheckIfStudentEmailDoesNotExists() {
        //given
        String email = "jamilla@gmail.com";


        //when
        boolean expected=underTest.selectExistsEmail(email);
        //then
        assertThat(expected).isFalse();
    }

}