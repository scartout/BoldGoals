package pl.scartout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import pl.scartout.model.Role;
import pl.scartout.repo.RoleRepo;

@SpringBootApplication
public class SpringJpaBootApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringJpaBootApplication.class, args);

        RoleRepo roleRepo = ctx.getBean(RoleRepo.class);
        if (roleRepo.findByRole("admin") == null) {
        	Role role1 = new Role(1L, "admin");
        	roleRepo.save(role1);}
        if (roleRepo.findByRole("user") == null) {
        	Role role2 = new Role(2L, "user");
        	roleRepo.save(role2);}
        
    }
}