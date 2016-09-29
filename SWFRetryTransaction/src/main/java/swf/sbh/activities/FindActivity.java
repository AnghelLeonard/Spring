package swf.sbh.activities;

import com.amazonaws.services.simpleworkflow.flow.annotations.Activities;
import com.amazonaws.services.simpleworkflow.flow.annotations.ActivityRegistrationOptions;
import com.amazonaws.services.simpleworkflow.flow.annotations.ExponentialRetry;

/**
 *
 * @author Anghel Leonard
 */
@ActivityRegistrationOptions(
        defaultTaskScheduleToStartTimeoutSeconds = 24 * 60 * 60,
        defaultTaskStartToCloseTimeoutSeconds = 5 * 60)
@Activities(version = "1.7")
public interface FindActivity {

    @ExponentialRetry(initialRetryIntervalSeconds = 10, maximumAttempts = 5)
    void findRow();

}
