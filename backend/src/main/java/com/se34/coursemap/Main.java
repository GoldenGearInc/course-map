package com.se34.coursemap;

import com.se34.coursemap.config.PersistenceJPAConfig;
import com.se34.coursemap.entity.Comment;
import com.se34.coursemap.entity.Institute;
import com.se34.coursemap.repository.CommentRepository;
import com.se34.coursemap.repository.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Configuration("mainBean")
@Import(PersistenceJPAConfig.class)
@Transactional
@EnableJpaRepositories(basePackages = "com.se34.coursemap.repository")
public class Main {

    @Autowired
    private InstituteRepository repository;

    @Autowired
    private DataSource dataSource;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(Main.class);
        ctx.refresh();
        System.out.println("Load context");
        Main s = (Main) ctx.getBean("mainBean");

        s.showInstitutes();
    }

    public void showInstitutes(){
        List<Institute> institutes = repository.findAll();
        for (Institute institute:institutes) {
            System.out.println(institute.getName());
        }
    }
}
