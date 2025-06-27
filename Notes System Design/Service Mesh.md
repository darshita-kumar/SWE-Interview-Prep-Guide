## Working of Service Mesh

https://www.youtube.com/watch?v=eIxdHepOeHw&ab_channel=Concept%26%26Coding-byShrayansh

### Problems solved by Service mesh:
Everything in yellow in below diagram:
<img width="1728" alt="Screenshot 2025-06-27 at 10 44 32 AM" src="https://github.com/user-attachments/assets/735bdd3c-b018-4ab5-ab7f-401a3e47c132" />

### Service mesh architecture:
<img width="1728" alt="Screenshot 2025-06-27 at 10 41 05 AM" src="https://github.com/user-attachments/assets/91c8c017-189d-424a-8726-4aef728569d8" />

Note: 
- There is no network call between microservices and their sidecar proxies, both are part of the same pod.
- A Pod in Kubernetes can run multiple containers that share the same network namespace, meaning they can communicate over localhost
- Since both containers share the same network namespace, the microservice talks to the sidecar proxy via localhost and a designated port (e.g., 127.0.0.1:15001).
- All inbound and outbound traffic for the microservice is routed through the sidecar proxy:
- Inbound: External requests → sidecar proxy → microservice.
- Outbound: Microservice → sidecar proxy → destination service’s sidecar proxy.

Popular Service Mesh: istio
Note: Istio has now combined all Control Plane elements into istiod (Need to confirm)

End-to-End Flow of Telemetry Metrics:
1. Request passes through the sidecar proxy:
   All inbound and outbound traffic is intercepted by the sidecar proxy (Envoy).
   It observes requests without changing the microservice code (no need to instrument the app).

2. Sidecar proxy generates telemetry data
Metrics like:
requests_total
request_duration_seconds
5xx_errors_total
These are exposed in Prometheus-compatible format at an HTTP endpoint (e.g., localhost:15090/stats/prometheus for Envoy).

3. Prometheus scrapes metrics from each sidecar
Prometheus is configured to:
Discover all pods.
Scrape the sidecar metrics endpoint.
Example scrape config in Prometheus:
```
scrape_configs:
  - job_name: 'envoy-sidecars'
    kubernetes_sd_configs:
      - role: pod
    relabel_configs:
      - source_labels: [__meta_kubernetes_pod_container_port_name]
        action: keep
        regex: 'envoy-prom'
```

4. Prometheus stores the metrics in time series database
   
5. Grafana queries Prometheus
Grafana is configured with Prometheus as a data source.
Prebuilt dashboards (e.g., Istio dashboards) or custom panels visualize the metrics.

