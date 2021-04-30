import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import junit.framework.TestCase;

import org.junit.runner.JUnitCore;
import org.junit.*;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.*;

class RunLocalTestTwo {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
	
    class TestCase {
    	//Supposed to be public static class TestCase

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
    public void profileClassDeclarationTest() {
        Class<?> clazz;
        String className;
        int modifiers;
        Field testField;
        
        clazz = Profile.class;
        className = "Profile";
        
        String username = "username";
        String password = "password";
        String name = "name";
        String age = "age";
        String email = "email";
        String phone = "phone";
        String aboutMe = "aboutMe";
        String likes = "likes";
        String dislikes = "dislikes";
        
        try {
            testField = clazz.getDeclaredField(username);
        } catch (NoSuchFieldException e) {
            Assert.fail("Ensure that `" + className + "` declares a field named `" + username + "`!");

            return;
        }
        
        
        try {
            testField = clazz.getDeclaredField(password);
        } catch (NoSuchFieldException e) {
            Assert.fail("Ensure that `" + className + "` declares a field named `" + password + "`!");

            return;
        }
        
        
        try {
            testField = clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            Assert.fail("Ensure that `" + className + "` declares a field named `" + name + "`!");

            return;
        }
        
        try {
            testField = clazz.getDeclaredField(age);
        } catch (NoSuchFieldException e) {
            Assert.fail("Ensure that `" + className + "` declares a field named `" + age + "`!");

            return;
        }
        
        try {
            testField = clazz.getDeclaredField(email);
        } catch (NoSuchFieldException e) {
            Assert.fail("Ensure that `" + className + "` declares a field named `" + email + "`!");

            return;
        }
        
        try {
            testField = clazz.getDeclaredField(phone);
        } catch (NoSuchFieldException e) {
            Assert.fail("Ensure that `" + className + "` declares a field named `" + phone + "`!");

            return;
        }
        
        try {
            testField = clazz.getDeclaredField(aboutMe);
        } catch (NoSuchFieldException e) {
            Assert.fail("Ensure that `" + className + "` declares a field named `" + aboutMe + "`!");

            return;
        }
        
        try {
            testField = clazz.getDeclaredField(likes);
        } catch (NoSuchFieldException e) {
            Assert.fail("Ensure that `" + className + "` declares a field named `" + likes + "`!");

            return;
        }
        
        try {
            testField = clazz.getDeclaredField(dislikes);
        } catch (NoSuchFieldException e) {
            Assert.fail("Ensure that `" + className + "` declares a field named `" + dislikes + "`!");

            return;
        }
        
        }
	}
    }
}
