package net.silpp.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class JavaBeanUtilTest {
	private static final Logger logger = LoggerFactory.getLogger(JavaBeanUtilTest.class);
	@Test
	void test() throws Exception {
		final Map<String, String[]> params = new HashMap<>();
	    params.put("userName", new String[]{"userA"});
	    params.put("password", new String[]{"secrect"});
	    params.put("id", new String[]{"10"});
	    final JavaBean javaBean = new JavaBean();
	    BeanUtilsBean.getInstance().populate(javaBean, params);
	   logger.info(javaBean.getUserName());
	   logger.debug(javaBean.getPassword());
	   logger.debug(javaBean.getId()+"");
	}
	    
}
