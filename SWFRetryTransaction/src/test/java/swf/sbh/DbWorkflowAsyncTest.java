package swf.sbh;

import com.amazonaws.services.simpleworkflow.flow.core.TryCatchFinally;
import com.amazonaws.services.simpleworkflow.flow.junit.spring.FlowSpringJUnit4ClassRunner;
import com.amazonaws.services.simpleworkflow.flow.junit.spring.SpringWorkflowTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import swf.sbh.workflow.DbWorkflowClient;
import swf.sbh.workflow.DbWorkflowClientFactoryImpl;

/**
 *
 * @author Anghel Leonard
 */
@RunWith(FlowSpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DbWorkflowConfiguration.class)
public class DbWorkflowAsyncTest {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Rule
    @Autowired
    public SpringWorkflowTest springWorkflowTest;

    @Autowired
    public DbWorkflowClientFactoryImpl workflowClientFactory;   
    
    @Test
    public void testProcess() throws Exception {

        new TryCatchFinally() {
            @Override
            protected void doTry() throws Throwable {
                LOGGER.info("\n----------------------BEFORE TEST-------------------------");

                DbWorkflowClient workflowClient = workflowClientFactory.getClient();
                workflowClient.process();
            }

            @Override
            protected void doCatch(Throwable e) throws Throwable {
                LOGGER.error("\n----------------------ERROR-------------------------");
                LOGGER.error("\nError: " + e);
                LOGGER.error("\n----------------------ERROR-------------------------");
                throw e;
            }

            @Override
            protected void doFinally() throws Throwable {                
                LOGGER.info("\n----------------------AFTER TEST-------------------------");                
            }
        };
    }
}
