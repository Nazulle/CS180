import org.junit.After;
import org.junit.Assert.*;
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

public class RunLocalTestTwo {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }
	
    public static class TestCase {
	@Test
    public void profileClassDeclarationTest() {
        Class<?> clazz;
        String className;
        //int modifiers;
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
	
	public void profileConstructorTest() {
        String className = "Profile";
        
        className = "Profile";
		try {
        Profile obj = new Profile("username", "password", "name", "age",
        		"phone", "email", "likes", "dislikes", "aboutMe");
        assertEquals("username", obj.getUsername());
        assertEquals("password", obj.getPassword());
        assertEquals("age", obj.getAge());
        assertEquals("phone", obj.getPhone());
        assertEquals("email", obj.getEmail());
        assertEquals("likes", obj.getLikes());
        assertEquals("dislikes", obj.getDislikes());
        assertEquals("aboutMe", obj.getAboutMe());
		} catch (Exception e) {
			Assert.fail(className + "'s big constructor doesn't work!");
		}
        
		try {
        Profile obj2 = new Profile("usernameOne", "passwordOne");
        assertEquals("usernameOne", obj2.getUsername());
        assertEquals("passwordOne", obj2.getPassword());
		} catch (Exception e) {
			Assert.fail(className + "'s small constructor doesn't work!");
		}
	}
	
	public void profileSetterTestCorrect() {
		try {
		Profile obj = new Profile(0, 0, 0, 0, 0, 0 ,0, 0, 0);
		
		obj.setUsername(0);
        assertEquals(0, obj.getUsername());
        
		obj.setPassword(0);
        assertEquals("one", obj.getPassword());
        
		obj.setName(0);
        assertEquals(0, obj.getName());
        
		obj.setAge(0);
        assertEquals(0, obj.getAge());
        
		obj.setPhone(0);
        assertEquals("one", obj.getPhone());
        
		obj.setEmail("one");
        assertEquals("one", obj.getEmail());
        
		obj.setLikes("one");
        assertEquals("one", obj.getLikes());
        
		obj.setDislikes("one");
        assertEquals("one", obj.getDislikes());
        
		obj.setAboutMe("one");
        assertEquals("one", obj.getAboutMe());
		} catch (Exception e) {
			Assert.fail("Profile's getter methods or contructor declaration doesn't work!");
		}
	}
	
	public void profileSetterTestIncorrect() {
		try {
		Profile obj = new Profile("phone", "email", "likes", "dislikes", "aboutMe");
		
		obj.setUsername("one");
        assertEquals("one", obj.getUsername());
        
		obj.setPassword("one");
        assertEquals("one", obj.getPassword());
        
		obj.setName("one");
        assertEquals("one", obj.getName());
        
		obj.setAge("one");
        assertEquals("one", obj.getAge());
        
		obj.setPhone("one");
        assertEquals("one", obj.getPhone());
        
		obj.setEmail("one");
        assertEquals("one", obj.getEmail());
        
		obj.setLikes("one");
        assertEquals("one", obj.getLikes());
        
		obj.setDislikes("one");
        assertEquals("one", obj.getDislikes());
        
		obj.setAboutMe("one");
        assertEquals("one", obj.getAboutMe());
		} catch (Exception e) {
			return;
		}
	}
	
	
	public void testToString() {
		try {
		 Profile obj = new Profile("username", "password", "name", "age",
	        		"phone", "email", "likes", "dislikes", "aboutMe");
		 assertEquals("username name email phone", obj.toString());
		} catch (Exception e) {
			Assert.fail("Profile's toString method or contructor declaration doesn't work!");
		}
	}
	}
}
