# Kubernetes Notes

---

## Kubernetes Concepts

- Nodes: The physical servers or virtual machines that create a kubernetes cluster.
- Pods: The smallest unit of execution in kubernetes. They are deployable units and consist of one or more containers, which internally consist of on or more application.
- Controllers: Used to deploy, manage and scale the pods.
- Services: Used to expose deployments through NodePort or LoadBalancer.
- NodePort: Exposes the services on a static port on the node IP address. NodePorts are in the 30000-32767 range by default.
- LoadBalancer: Exposes a single external ip and internally holds multiple ports to distribute the load.

*Exposing a kubernetes service* - Point the external IP to nodes and set firewall access to the node.

