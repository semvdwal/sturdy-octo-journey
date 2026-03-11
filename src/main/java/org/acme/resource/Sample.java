package org.acme.resource;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;

@Group("acme.org")
@Version("v1")
public class Sample extends CustomResource<SampleSpec, SampleStatus> implements Namespaced {
}
