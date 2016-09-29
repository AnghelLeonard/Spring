package swf.sbh.workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import swf.sbh.activities.FindActivityClient;

/**
 *
 * @author Anghel Leonard
 */
@Component
@Scope("workflow")
public class DbWorkflowImpl implements DbWorkflow {
  
    @Autowired
    private FindActivityClient findActivity;

    @Override
    public void process() throws Throwable {
        findActivity.findRow();
    }
}
