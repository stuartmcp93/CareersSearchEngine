package com.stu.careers_search_engine;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class tests the creation of a new User.
 *
 * @Result a new User is created
 * @Author Stuart McPherson
 */
public class UserTest {

    User user = new User();

    @Test
    public void getUsername() {
        user.setUsername("JoeB");
        assertEquals(user.getUsername(), "JoeB");
    }

    @Test
    public void getId(){
        user.setId(12);
        assertEquals(user.getId(), 12);
    }
}

