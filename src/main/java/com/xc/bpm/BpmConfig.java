package com.xc.bpm;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lifeifan on 2018/2/6.
 */
@Configuration
public class BpmConfig {
//    @Bean
//    CommandLineRunner init(final RepositoryService repositoryService, final RuntimeService runtimeService, final TaskService taskService){
//        System.out.println(repositoryService.createDeploymentQuery().count());
//        System.out.println(taskService.createTaskQuery().count());
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... strings) throws Exception {
//                runtimeService.startProcessInstanceByKey("testProcess");
//                System.out.println(taskService.createTaskQuery().count());
//            }
//        };
//    }

    @Bean
    InitializingBean userAndGroupsInitializer(final IdentityService identityService){
        return new InitializingBean() {
            @Override
            public void afterPropertiesSet() throws Exception {
                Group group = identityService.newGroup("user");
                group.setName("users");
                group.setType("security-role");
                identityService.saveGroup(group);

                User apply = identityService.newUser("apply");
                apply.setPassword("apply");
                User submit = identityService.newUser("submit");
                submit.setPassword("submit");
//                User admin = identityService. newUser("admin");
//                apply.setPassword("admin");
                identityService.saveUser(apply);
                identityService.saveUser(submit);
//                identityService.saveUser(admin);
            }
        };
    }
}
