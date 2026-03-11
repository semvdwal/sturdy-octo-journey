package org.acme.reconciler;


import io.javaoperatorsdk.operator.api.reconciler.*;
import io.javaoperatorsdk.operator.api.reconciler.dependent.Dependent;
import org.acme.resource.Sample;

@Workflow(
        dependents = {
                @Dependent(type = KafkaTopicDependentResource.class)
        }
)
@ControllerConfiguration()
public class SampleReconciler implements Reconciler<Sample> {

    @Override
    public UpdateControl<Sample> reconcile(Sample resource, Context<Sample> context) {
        return UpdateControl.noUpdate();
    }
}
