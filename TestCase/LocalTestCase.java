import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

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
    	private final PrintStream originalOutput = System.out;
        private final InputStream originalSysin = System.in;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayInputStream testIn;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayOutputStream testOut;

        @Before
        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @After
        public void restoreInputAndOutput() {
            System.setIn(originalSysin);
            System.setOut(originalOutput);
        }

        private String getOutput() {
            return testOut.toString();
        }

        @SuppressWarnings("SameParameterValue")
        private void receiveInput(String str) {
            testIn = new ByteArrayInputStream(str.getBytes());
            System.setIn(testIn);
        }


        @Test(timeout = 1_000)
        public void ProfileClassDeclarationTest() {
            Class<?> clazz = Profile.class;
            Constructor<?> constructor;
            String className = "Profile";
            Class<?>[] exceptions;   
            int expectedLength = 0;
            int modifiers;
            
            try {
                constructor = clazz.getDeclaredConstructor(String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a constructor that is `public` and has 9 parameters with type String!");

                return;
            }

            modifiers = constructor.getModifiers();

            exceptions = constructor.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s parameterized constructor is `public`!", Modifier.isPublic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s parameterized constructor has no throws clauses!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1_000)
        public void IncorrectProfileClassDeclarationTest() {
            Class<?> clazz = Profile.class;
            Constructor<?> constructor;
            String className = "Profile";
            Class<?>[] exceptions;   
            int expectedLength = 0;
            int numParameters = 9;
            int modifiers;
            
            try {//Incorrect: declared 8 parameters instead of 9
                constructor = clazz.getDeclaredConstructor(String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class);
                Assert.fail("Ensure that `" + className + "` declares a constructor that is `public` and has 9 parameters with type String!");
            } catch (NoSuchMethodException e) {
                return;
            }


        }
        
        @Test(timeout = 1_000)
        public void IncorrectProfileClassDeclarationTestTwo() {
            Class<?> clazz = Profile.class;
            Constructor<?> constructor;
            String className = "Profile";
            Class<?>[] exceptions;   
            int expectedLength = 0;
            int modifiers;

            try {
                constructor = clazz.getDeclaredConstructor(String.class);
                Assert.fail("Ensure that `" + className + "` declares a constructor that is `public` and has 2 parameters with type String!");
            } catch (NoSuchMethodException e) {
                return;
            }
            
        }
        
        @Test(timeout = 1_000)
        public void ProfileClassDeclarationTestTwo() {
            Class<?> clazz = Profile.class;
            Constructor<?> constructor;
            String className = "Profile";
            Class<?>[] exceptions;   
            int expectedLength = 0;
            int modifiers;
            
            try {//Incorrect: declared 1 parameters instead of 2
                constructor = clazz.getDeclaredConstructor(String.class, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a constructor that is `public` and has three parameters with types String and String!");

                return;
            }

            modifiers = constructor.getModifiers();

            exceptions = constructor.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s parameterized constructor is `public`!", Modifier.isPublic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s parameterized constructor has an no throws clauses!", expectedLength, exceptions.length);
        }
        
        
        @Test(timeout = 1000)
        public void profileGetUsernameMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getUsername";
            Class<?> expectedReturnType = String.class;

            clazz = Profile.class;

            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            } 

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileGetUsernameMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getUsername";
            Class<?> expectedReturnType = int.class;
            Class<? extends String> actualReturnType = methodName.getClass();

            clazz = Profile.class;

            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);


        }
        
        @Test(timeout = 1000)
        public void profileGetPasswordMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getPassword";
            Class<?> expectedReturnType = String.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        
        
        @Test(timeout = 1000)
        public void IncorrectProfileGetPasswordMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getPassword";
            Class<?> expectedReturnType = int.class;
            Class<? extends String> actualReturnType = methodName.getClass();

            clazz = Profile.class;

            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);


        }
        
        @Test(timeout = 1000)
        public void profileGetPhoneMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getPhone";
            Class<?> expectedReturnType = String.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileGetPhoneMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getPhone";
            Class<?> expectedReturnType = int.class;
            Class<? extends String> actualReturnType = methodName.getClass();

            clazz = Profile.class;

            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);


        }
        
        @Test(timeout = 1000)
        public void profileGetEmailMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getEmail";
            Class<?> expectedReturnType = String.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileGetEmailMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getPassword";
            Class<?> expectedReturnType = int.class;
            Class<? extends String> actualReturnType = methodName.getClass();

            clazz = Profile.class;

            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
        }
        
        @Test(timeout = 1000)
        public void profileGetNameMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getName";
            Class<?> expectedReturnType = String.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileGetNameMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getName";
            Class<?> expectedReturnType = int.class;
            Class<? extends String> actualReturnType = methodName.getClass();

            clazz = Profile.class;

            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);


        }
        
        @Test(timeout = 1000)
        public void profileGetAgeMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getAge";
            Class<?> expectedReturnType = String.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileGetAgeMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getAge";
            Class<?> expectedReturnType = int.class;
            Class<? extends String> actualReturnType = methodName.getClass();

            clazz = Profile.class;

            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);


        }
        
        @Test(timeout = 1000)
        public void profileGetAboutMeMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getAboutMe";
            Class<?> expectedReturnType = String.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileGetAboutMeMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getAboutMe";
            Class<?> expectedReturnType = int.class;
            Class<? extends String> actualReturnType = methodName.getClass();

            clazz = Profile.class;

            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);


        }
        
        @Test(timeout = 1000)
        public void profileGetLikesMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getLikes";
            Class<?> expectedReturnType = String.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileGetLikesMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getLikes";
            Class<?> expectedReturnType = int.class;
            Class<? extends String> actualReturnType = methodName.getClass();

            clazz = Profile.class;

            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);


        }
        
        @Test(timeout = 1000)
        public void profileGetDislikesMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getDislikes";
            Class<?> expectedReturnType = String.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileGetDislikesMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getDislikes";
            Class<?> expectedReturnType = int.class;
            Class<? extends String> actualReturnType = methodName.getClass();

            clazz = Profile.class;

            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);


        }
        
        @Test(timeout = 1000)
        public void profileSetUsernameMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0; //throws clause
            Class<?>[] exceptions;

            String methodName = "setUsername";
            Class<?> expectedReturnType = void.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileSetUsernameMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "setUsername";
            Class<?> actualReturnType = methodName.getClass();
            Class<?> expectedReturnType = int.class;
            clazz = Profile.class;
            
            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            try {
                method = clazz.getDeclaredMethod(methodName, int.class);
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");
            } catch (NoSuchMethodException e) {
                return;
            }
        }
        
        @Test(timeout = 1000)
        public void profileSetPasswordMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "setPassword";
            Class<?> expectedReturnType = void.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileSetPasswordMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "setPassword";
            Class<?> actualReturnType = methodName.getClass();
            Class<?> expectedReturnType = int.class;
            clazz = Profile.class;
            
            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            try {
                method = clazz.getDeclaredMethod(methodName, int.class);
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");
            } catch (NoSuchMethodException e) {
                return;
            }
        }
        
        @Test(timeout = 1000)
        public void profileSetPhoneMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "setPhone";
            Class<?> expectedReturnType = void.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileSetPhoneMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "setPhone";
            Class<?> actualReturnType = methodName.getClass();
            Class<?> expectedReturnType = int.class;
            clazz = Profile.class;
            
            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            try {
                method = clazz.getDeclaredMethod(methodName, int.class);
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");
            } catch (NoSuchMethodException e) {
                return;
            }
        }
        
        @Test(timeout = 1000)
        public void profileSetEmailMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "setEmail";
            Class<?> expectedReturnType = void.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileSetEmailMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "setEmail";
            Class<?> actualReturnType = methodName.getClass();
            Class<?> expectedReturnType = int.class;
            clazz = Profile.class;
            
            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            try {
                method = clazz.getDeclaredMethod(methodName, int.class);
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");
            } catch (NoSuchMethodException e) {
                return;
            }
        }
        
        @Test(timeout = 1000)
        public void profileSetNameMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "setName";
            Class<?> expectedReturnType = void.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileSetNameMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "setName";
            Class<?> actualReturnType = methodName.getClass();
            Class<?> expectedReturnType = int.class;
            clazz = Profile.class;
            
            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            try {
                method = clazz.getDeclaredMethod(methodName, int.class);
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");
            } catch (NoSuchMethodException e) {
                return;
            }
        }
        
        @Test(timeout = 1000)
        public void profileSetAgeMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "setAge";
            Class<?> expectedReturnType = void.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileSetAgeMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "setAge";
            Class<?> actualReturnType = methodName.getClass();
            Class<?> expectedReturnType = int.class;
            clazz = Profile.class;
            
            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            try {
                method = clazz.getDeclaredMethod(methodName, int.class);
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");
            } catch (NoSuchMethodException e) {
                return;
            }
        }
        
        @Test(timeout = 1000)
        public void profileSetAboutMeMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "setAboutMe";
            Class<?> expectedReturnType = void.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileSetAboutMeMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "setAboutMe";
            Class<?> actualReturnType = methodName.getClass();
            Class<?> expectedReturnType = int.class;
            clazz = Profile.class;
            
            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            try {
                method = clazz.getDeclaredMethod(methodName, int.class);
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");
            } catch (NoSuchMethodException e) {
                return;
            }
        }
        
        @Test(timeout = 1000)
        public void profileSetLikesMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "setLikes";
            Class<?> expectedReturnType = void.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileSetLikesMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "setLikes";
            Class<?> actualReturnType = methodName.getClass();
            Class<?> expectedReturnType = int.class;
            clazz = Profile.class;
            
            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            try {
                method = clazz.getDeclaredMethod(methodName, int.class);
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");
            } catch (NoSuchMethodException e) {
                return;
            }
        }
        
        @Test(timeout = 1000)
        public void profileSetDislikesMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "setDislikes";
            Class<?> expectedReturnType = void.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileSetDislikesMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "setDislikes";
            Class<?> actualReturnType = methodName.getClass();
            Class<?> expectedReturnType = int.class;
            clazz = Profile.class;
            
            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);
            
            try {
                method = clazz.getDeclaredMethod(methodName, int.class);
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");
            } catch (NoSuchMethodException e) {
                return;
            }
        }
        
        @Test(timeout = 1000)
        public void profileGetFriendsMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getFriends";
            Class<?> expectedReturnType = ArrayList.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileGetFriendsMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getFriends";
            Class<?> expectedReturnType = int.class;
            Class<? extends String> actualReturnType = methodName.getClass();

            clazz = Profile.class;

            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);


        }
        
        @Test(timeout = 1000)
        public void profileGetReceivedFriendRequestMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getReceivedFriendRequest";
            Class<?> expectedReturnType = ArrayList.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileGetReceivedFriendRequestMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getReceivedFriendRequest";
            Class<?> expectedReturnType = int.class;
            Class<? extends String> actualReturnType = methodName.getClass();

            clazz = Profile.class;

            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);


        }
        
        @Test(timeout = 1000)
        public void profileGetSentFriendRequestMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getSentFriendRequest";
            Class<?> expectedReturnType = ArrayList.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileGetSentFriendRequestMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getSentFriendRequest";
            Class<?> expectedReturnType = int.class;
            Class<? extends String> actualReturnType = methodName.getClass();

            clazz = Profile.class;

            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);


        }
        
        @Test(timeout = 1000)
        public void profileToStringMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;


            String methodName = "toString";
            Class<?> expectedReturnType = String.class;
            clazz = Profile.class;

            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            }


            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileToStringMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getPhone";
            Class<?> expectedReturnType = int.class;
            Class<? extends String> actualReturnType = methodName.getClass();

            clazz = Profile.class;

            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);


        }
        
        @Test(timeout = 1000)
        public void profileSendFriendRequestTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 1;
            Class<?>[] exceptions;

            String methodName = "sendFriendRequest";
            Class<?> expectedReturnType = void.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName, Profile.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has a `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileSendFriendRequestMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getPhone";
            Class<?> expectedReturnType = int.class;
            Class<? extends String> actualReturnType = methodName.getClass();

            clazz = Profile.class;

            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            try {
                method = clazz.getDeclaredMethod(methodName, int.class);
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");
            } catch (NoSuchMethodException e) {

                return;
            }

        }
        
        @Test(timeout = 1000)
        public void profileAcceptFriendRequestTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 1;
            Class<?>[] exceptions;

            String methodName = "acceptFriendRequest";
            Class<?> expectedReturnType = void.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName, Profile.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has a `throws` clause!", expectedLength, exceptions.length);
        }
        
        
        @Test(timeout = 1000)
        public void IncorrectProfileAcceptFriendRequestMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getPhone";
            Class<?> expectedReturnType = int.class;
            Class<? extends String> actualReturnType = methodName.getClass();

            clazz = Profile.class;

            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            try {
                method = clazz.getDeclaredMethod(methodName, int.class);
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");
            } catch (NoSuchMethodException e) {

                return;
            }

        }
        @Test(timeout = 1000)
        public void profileRemoveFriendRequestTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 1;
            Class<?>[] exceptions;

            String methodName = "removeFriendRequest";
            Class<?> expectedReturnType = void.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName, Profile.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has a `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileRemoveFriendRequestMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getPhone";
            Class<?> expectedReturnType = int.class;
            Class<? extends String> actualReturnType = methodName.getClass();

            clazz = Profile.class;

            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            try {
                method = clazz.getDeclaredMethod(methodName, int.class);
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");
            } catch (NoSuchMethodException e) {

                return;
            }

        }
        
        @Test(timeout = 1000)
        public void profileUnFriendTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 1;
            Class<?>[] exceptions;

            String methodName = "unFriend";
            Class<?> expectedReturnType = void.class;
            clazz = Profile.class;
            
            try {
                method = clazz.getDeclaredMethod(methodName, Profile.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has a `throws` clause!", expectedLength, exceptions.length);
        }
        
        @Test(timeout = 1000)
        public void IncorrectProfileUnFriendMethodTest() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getPhone";
            Class<?> expectedReturnType = int.class;
            Class<? extends String> actualReturnType = methodName.getClass();

            clazz = Profile.class;

            Assert.assertNotEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            try {
                method = clazz.getDeclaredMethod(methodName, int.class);
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");
            } catch (NoSuchMethodException e) {

                return;
            }

        }
	    
        @Test(timeout = 1_000)
        public void authenticationClassDeclarationTest() {
            Class<?> clazz = Authentication.class;
            Constructor<?> constructor; //used to test constructors
            String className = "Authentication";
            Class<?>[] exceptions; 
            int expectedLength = 0; //Number of throws
            int modifiers;
            
            try { //Constructor has parameter of arraylist 
                constructor = clazz.getDeclaredConstructor(ArrayList.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a constructor that is `public` and has 1 parameter with type Arraylist<Profile>!");

                return;
            }

            modifiers = constructor.getModifiers();

            exceptions = constructor.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s parameterized constructor is `public`!", Modifier.isPublic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s parameterized constructor has no throws clauses!", expectedLength, exceptions.length);
        }
        @Test(timeout = 1000)
        public void authenticationCreateAccountTest() {
            Class<?> clazz;
            String className = "Authentication";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "createAccount";
            Class<?> expectedReturnType = Profile.class; 
            clazz = Authentication.class; 
            
            try {
                method = clazz.getDeclaredMethod(methodName, String.class, String.class); 
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 2 parameters!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        @Test(timeout = 1000)
        public void authenticationLoginTest() {
            Class<?> clazz;
            String className = "Authentication";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "login";
            Class<?> expectedReturnType = Profile.class; 
            clazz = Authentication.class; 
            
            try {
                method = clazz.getDeclaredMethod(methodName, String.class, String.class); 
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 2 parameters!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        @Test(timeout = 1000)
        public void authenticationGetProfileTest() {
            Class<?> clazz;
            String className = "Authentication";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getProfile";
            Class<?> expectedReturnType = Profile.class; 
            clazz = Authentication.class; 
            
            try {
                method = clazz.getDeclaredMethod(methodName, String.class); 
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameter!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        @Test(timeout = 1000)
        public void authenticationRemoveProfileTest() {
            Class<?> clazz;
            String className = "Authentication";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "removeProfile";
            Class<?> expectedReturnType = Profile.class; 
            clazz = Authentication.class; 
            
            try {
                method = clazz.getDeclaredMethod(methodName, String.class); 
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has 1 parameters!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        @Test(timeout = 1000)
        public void authenticationGetProfilesTest() {
            Class<?> clazz;
            String className = "Authentication";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "getProfiles";
            Class<?> expectedReturnType = ArrayList.class; 
            clazz = Authentication.class; 
            
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        @Test(timeout = 1000)
        public void authenticationToStringMethodTest() { 
            Class<?> clazz;
            String className = "Authentication";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            String methodName = "toString";
            Class<?> expectedReturnType = String.class;
            clazz = Authentication.class;

            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            }

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an empty `throws` clause!", expectedLength, exceptions.length);
        }
	}
}
