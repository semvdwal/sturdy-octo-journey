package org.acme.reconciler;


import io.javaoperatorsdk.operator.api.reconciler.*;
import io.javaoperatorsdk.operator.api.reconciler.dependent.Dependent;
import io.quarkiverse.operatorsdk.annotations.CSVMetadata;
import io.strimzi.api.kafka.model.topic.KafkaTopic;
import org.acme.resource.Sample;

@CSVMetadata(
        bundleName = "test-operator",
        version = "0.1.1",
        provider = @CSVMetadata.Provider(
            name = "Acme"
        ),
        displayName = "Test bundle",
        description = "Show existence of a bug encoutered in the build process",
        minKubeVersion = "1.31.0",
        requiredCRDs = {
                @CSVMetadata.RequiredCRD(
                        kind = KafkaTopic.RESOURCE_KIND,
                        name = KafkaTopic.CRD_NAME,
                        version = KafkaTopic.CONSUMED_VERSION
                )
        },
        annotations = @CSVMetadata.Annotations(
                containerImage = "acme/test-operator:0.1.1"
        )
)
@Workflow(
        dependents = {
                @Dependent(type = SecondDependentResource.class)
        }
)
@ControllerConfiguration()
public class FirstReconciler implements Reconciler<Sample> {

    @Override
    public UpdateControl<Sample> reconcile(Sample resource, Context<Sample> context) {
        return UpdateControl.noUpdate();
    }
}
