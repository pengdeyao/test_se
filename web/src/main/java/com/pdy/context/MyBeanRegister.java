package com.pdy.context;

import com.pdy.model.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MyBeanRegister implements BeanFactoryAware {

    private BeanFactory beanFactory;


    @PostConstruct
    public void init(){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.setScope("singleton");

        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) this.beanFactory;
        registry.registerBeanDefinition("pdy",beanDefinitionBuilder.getBeanDefinition());


        registry.registerBeanDefinition("pdy2",beanDefinitionBuilder.getBeanDefinition());

    }



    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyBeanRegister.class);
        System.out.println(ctx.getBean("pdy"));
        System.out.println(ctx.getBean("pdy") == ctx.getBean("pdy"));
        System.out.println(ctx.getBean("pdy2") == ctx.getBean("pdy"));
        System.out.println(ctx.getBean("pdy2") == ctx.getBean("pdy2"));
    }
}
