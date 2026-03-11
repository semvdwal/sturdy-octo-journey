package org.acme.reconciler;

import io.javaoperatorsdk.operator.api.reconciler.Context;
import io.javaoperatorsdk.operator.processing.dependent.kubernetes.CRUDKubernetesDependentResource;
import io.javaoperatorsdk.operator.processing.dependent.kubernetes.KubernetesDependent;
import io.strimzi.api.kafka.model.topic.KafkaTopic;
import io.strimzi.api.kafka.model.topic.KafkaTopicBuilder;
import org.acme.resource.Sample;

/**
 * A DependentResource that manages a KafkaTopic on behalf of a Sample resource.
 *
 * This class is the root cause of the build failure: KafkaTopic (from io.strimzi:api)
 * does not ship its own META-INF/jandex.idx, so QOSDK's buildReconcilerInfos step
 * cannot find it in the CombinedIndexBuildItem during augmentation.
 *
 * The fix (quarkus.index-dependency) works only when passed as a -D system property
 * or via the quarkus-maven-plugin <systemProperties> config — NOT from application.properties.
 */
@KubernetesDependent
public class SecondDependentResource
        extends CRUDKubernetesDependentResource<KafkaTopic, Sample> {

    public SecondDependentResource() {
        super(KafkaTopic.class);
    }

    @Override
    protected KafkaTopic desired(Sample primary, Context<Sample> context) {
        return new KafkaTopicBuilder()
                .withNewMetadata()
                    .withName(primary.getMetadata().getName())
                    .withNamespace(primary.getMetadata().getNamespace())
                .endMetadata()
                .withNewSpec()
                    .withPartitions(3)
                    .withReplicas(1)
                .endSpec()
                .build();
    }
}
