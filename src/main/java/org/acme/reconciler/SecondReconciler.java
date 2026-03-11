package org.acme.reconciler;


import io.javaoperatorsdk.operator.api.reconciler.*;
import io.javaoperatorsdk.operator.api.reconciler.dependent.Dependent;
import io.quarkiverse.operatorsdk.annotations.CSVMetadata;
import io.strimzi.api.kafka.model.topic.KafkaTopic;
import org.acme.resource.Sample;

@Workflow(
    dependents = {
        @Dependent(type = KafkaTopicDependentResource.class)
    }
)
@ControllerConfiguration()
public class SecondReconciler implements Reconciler<Sample> {

    @Override
    public UpdateControl<Sample> reconcile(Sample resource, Context<Sample> context) {
        return UpdateControl.noUpdate();
    }
}
