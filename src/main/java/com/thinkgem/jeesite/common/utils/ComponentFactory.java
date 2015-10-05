package com.thinkgem.jeesite.common.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ContextLoader;

public class ComponentFactory implements ApplicationContextAware {

	private static Logger log = LoggerFactory.getLogger(ComponentFactory.class);
	private static boolean initialized = false;
	private static ApplicationContext applicationContext = null;
	public static BeanFactory factory = null;

	public static Object getBean(String id) {
		if (!initialized) {
			log.info("ComponentFactory没有初始化,尝试初始化");
			applicationContext = ContextLoader.getCurrentWebApplicationContext();
			if (applicationContext != null)
				initialized = true;
			else
				throw new RuntimeException("ComponentFactory没有初始化,请先初始化");
		}
		try {
			if (applicationContext != null) {
				return applicationContext.getBean(id);
			}
			return factory.getBean(id);
		} catch (BeansException e) {
			log.error("", e);
			throw new RuntimeException(e);
		}
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static boolean isInitialized() {
		return initialized;
	}

	public static synchronized void destroy() {
		factory = null;
		applicationContext = null;
		initialized = false;
	}

	public void setApplicationContext(ApplicationContext context) {
		applicationContext = context;
		initialized = true;
	}

	public static void lookBeans(ConfigurableListableBeanFactory x) {
		log.info(" bean 的数量：" + x.getBeanDefinitionCount());
	}
}

