package mybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.whirly.util.Constants;
import com.whirly.model.Student;
import com.whirly.model.User;
import com.whirly.service.StudentService;

@ContextConfiguration(locations = { "classpath:spring-*.xml", "classpath:mybatis-*.xml", })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestStudentService extends AbstractJUnit4SpringContextTests {

	@Autowired
	private StudentService studentService;

	@Test
	public void testInsert() {
		Student student = new Student();
		User user = User.createDefault();
		user.setAccount("2001");
		user.setRole(Constants.UserType.STUDENT);
		user.setUsername("whirly1");

		student.setUser(user);
		studentService.insert(student);
	}
}
