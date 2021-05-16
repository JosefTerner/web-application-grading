package com.github.grading.utils;

import com.github.grading.dto.UserRegistrationDto;
import com.github.grading.entity.User;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TransferObjTest {

    UserRegistrationDto userRegistrationDto = new UserRegistrationDto("Tura","123456","123456");
    @Test
    public void toUser() {
        User exp = TransferObj.toUser(userRegistrationDto);
        User act = new User("Tura","123456");
        assertEquals(exp,act);
    }
}