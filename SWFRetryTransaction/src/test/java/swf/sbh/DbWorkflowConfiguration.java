package swf.sbh;

import com.amazonaws.services.simpleworkflow.flow.junit.spring.SpringWorkflowTest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import swf.sbh.workflow.DbWorkflow;
import swf.sbh.workflow.DbWorkflowClientFactoryImpl;
import swf.sbh.activities.FindActivity;

/**
 *
 * @author Anghel Leonard
 */
@Configuration
@Import(SbhConfiguration.class)
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class DbWorkflowConfiguration {

    @Autowired
    private ApplicationContext context;

    @Bean
    protected SpringWorkflowTest getSpringWorkflowTest()
            throws InstantiationException, IllegalAccessException, NoSuchMethodException {
        SpringWorkflowTest springWorkflowTest = new SpringWorkflowTest();

        List activitiesImpls = new ArrayList<>();
        activitiesImpls.add(context.getBean(FindActivity.class));

        List workflowImpls = new ArrayList<>();
        workflowImpls.add(context.getBean(DbWorkflow.class));

        springWorkflowTest.setActivitiesImplementations(activitiesImpls);
        springWorkflowTest.setWorkflowImplementations(workflowImpls);

        return springWorkflowTest;
    }

    @Bean
    protected DbWorkflowClientFactoryImpl getWorkflowClientFactory() {
        return new DbWorkflowClientFactoryImpl();
    }   
}
