package net.silpp.user;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

public class UserValidatorTest { //��ȿ�� �׽�Ʈ
	private static Validator validator;

	@BeforeClass // Ŭ���� ��ü���� 1���� ����.
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test //userId ������
	public void userIdsNull() {
		User user = new User(null, "password", "name", "");

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

		assertEquals(1, constraintViolations.size());
		System.out.println(constraintViolations.iterator().next().getMessage());
	}
	@Test //userId ������ �ּ� �ִ� ����
	public void userIdLength() {
		User user = new User("us", "password", "name", "");

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

		assertEquals(1, constraintViolations.size());
		System.out.println(constraintViolations.iterator().next().getMessage());
	
		user = new User("userIduserIdusr", "password", "name", "");
		
		constraintViolations = validator.validate(user);
		
		assertEquals(1, constraintViolations.size());
		System.out.println(constraintViolations.iterator().next().getMessage());
	}
	@Test //email �ּҰ� ��ȿ���� ����
	public void email() throws Exception {
		User user = new User("user", "password", "name", "email");

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

		assertEquals(1, constraintViolations.size());
		System.out.println(constraintViolations.iterator().next().getMessage());
	}
	@Test //��� ���� ��ȿ���� �׽�Ʈ
	public void invalid() throws Exception {
	
		User user = new User("us", "pas", "name", "email");

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

		assertEquals(3, constraintViolations.size());
					
		Iterator<ConstraintViolation<User>> iterator = constraintViolations.iterator();
		while(iterator.hasNext()) {
		ConstraintViolation<User> each = iterator.next();
		System.out.println(each.getPropertyPath()+":"+ each.getMessage());
			
		}
	}
	
	

}
