package com.pdy.context;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**  工厂bean  ，编码方式创建javaBean ，适合将第三方的bean引入到spring容器中 <BR>
 *   通过getBean("xxx")获取的是 xxx.getObject() 返回的对象.
 * Created by pengdeyao on 2019/2/15
 */
@Component
public class MyFactoryBean implements FactoryBean<String> {
    @Override
    public String getObject() throws Exception {
        return new String("pdy");
    }

    @Override
    public Class<?> getObjectType() {
        return String.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
