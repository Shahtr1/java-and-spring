1.	Monolithic is 
	less productive, 
	slow development, 
	large & complex applications, we have to redeploy the entire application if one service is updated (block continuous development), unscalable(cpu usage), 
	unreliable(if one goes down everything goes down)
	inflexible(new framework)

2. It is a self contained process which can be broken down to small processes or services which together can access a larger system. Services are independent loosely coupled and are deployed independently

3.	Loose coupling in Java means that the classes are independent of each other. The only knowledge one class has about the other class is what the other class has exposed through its interfaces in loose coupling

4.	management: is responsilbe for placing services on nodes. identifying the failures, rebalancing services across nodes ...
5.	Service Discovery: maintain services. it maintains service lookup to find the end point of service
6.	Api gateway: Entry point for client. calls the appropriate service.

Features:
	Small Focused
	Loosely coupled
	Language neutral
	Bounded context

7.	Spring boot: 
provide great integration with Embedded servers which are easy to deploy with the containers
It helps in monitoring the multiple components
It helps in configuring the components externally
Quick Starter Projects with Auto Configuration
-	Web
-	JPA
Embedded Servers - Tomcat, Jetty or Undertow


Before Spring boot 
-	we needed to create default exception handing
-	a complete spring configuration file
-	a view resolver, to redirect the views to a jsp
-	we needed to configure web.xml as well, we need to configure DispatcherServlet there, also contextConfiguration, also Spring security

8. Richardson maturity model

-	Level 0:
	Expose SOAP web services in rest style

-	Level 1:
	Expose Your resources with a proper uri

-	Level 2:
	Level 1 + Proper use of HTTP methods

-	Level 3:
	Level 2 + HATEOS
	Data + Next possible actions

9.	What is Spring cloud?
-	You can store all the configuration of microservices in Spring cloud config server in git, so all config are in one place

10.	Dynamic Scale up and down (OLD)
	-	Naming Server (Eureka)
	-	Ribbon (Client Side Load Balancing)
	-	Feign (Easier REST Clients)

	The solutions of visibility and monitoring
	-	Zipkin Distributed Tracing
	-	Netflix API Gateway

	Fault Tolerance
	-	Histrix


	Dynamic Scale up and down (NEW)
	-	Naming Server (Eureka)
	-	Spring cloud (Client Side Load Balancing)
	-	Feign (Easier REST Clients)

	The solutions of visibility and monitoring
	-	Zipkin Distributed Tracing
	-	Spring cloud Gateway

	Fault Tolerance
	-	Resilience4j


11. Zipkin Distributed Tracing Server: 9411

12. Dockerising

	in run as maven build
	spring-boot:build-image

13. Kubernetes
	Docker container orchestration software

	we are using Google Kubernetes engine (GKB)


	Instructions:
	
-	Deploy to kubernetes cluster
	```bash
	kubectl create deployment hello-world-rest-api --image=in28min/hello-world-rest-api:0.0.1.RELEASE
	```

-	Expose to outside world
	```bash
	docker run -p 8080:8080 in28min/hello-world-rest-api:0.0.1.RELEASE
	 
	kubectl create deployment hello-world-rest-api --image=in28min/hello-world-rest-api:0.0.1.RELEASE
	kubectl expose deployment hello-world-rest-api --type=LoadBalancer --port=8080
	kubectl scale deployment hello-world-rest-api --replicas=3
	kubectl delete pod hello-world-rest-api-58ff5dd898-62l9d
	kubectl autoscale deployment hello-world-rest-api --max=10 --cpu-percent=70
	kubectl edit deployment hello-world-rest-api #minReadySeconds: 15
	kubectl set image deployment hello-world-rest-api hello-world-rest-api=in28min/hello-world-rest-api:0.0.2.RELEASE
	 
	gcloud container clusters get-credentials in28minutes-cluster --zone us-central1-a --project solid-course-258105
	kubectl create deployment hello-world-rest-api --image=in28min/hello-world-rest-api:0.0.1.RELEASE
	kubectl expose deployment hello-world-rest-api --type=LoadBalancer --port=8080
	kubectl set image deployment hello-world-rest-api hello-world-rest-api=DUMMY_IMAGE:TEST
	kubectl get events --sort-by=.metadata.creationTimestamp
	kubectl set image deployment hello-world-rest-api hello-world-rest-api=in28min/hello-world-rest-api:0.0.2.RELEASE
	kubectl get events --sort-by=.metadata.creationTimestamp
	kubectl get componentstatuses
	kubectl get pods --all-namespaces
	 
	kubectl get events
	kubectl get pods
	kubectl get replicaset
	kubectl get deployment
	kubectl get service
	 
	kubectl get pods -o wide
	 
	kubectl explain pods
	kubectl get pods -o wide
	 
	kubectl describe pod hello-world-rest-api-58ff5dd898-9trh2
	 
	kubectl get replicasets
	kubectl get replicaset
	 
	kubectl scale deployment hello-world-rest-api --replicas=3
	kubectl get pods
	kubectl get replicaset
	kubectl get events
	kubectl get events --sort.by=.metadata.creationTimestamp
	 
	kubectl get rs
	kubectl get rs -o wide
	kubectl set image deployment hello-world-rest-api hello-world-rest-api=DUMMY_IMAGE:TEST
	kubectl get rs -o wide
	kubectl get pods
	kubectl describe pod hello-world-rest-api-85995ddd5c-msjsm
	kubectl get events --sort-by=.metadata.creationTimestamp
	 
	kubectl set image deployment hello-world-rest-api hello-world-rest-api=in28min/hello-world-rest-api:0.0.2.RELEASE
	kubectl get events --sort-by=.metadata.creationTimestamp
	kubectl get pods -o wide
	kubectl delete pod hello-world-rest-api-67c79fd44f-n6c7l
	kubectl get pods -o wide
	kubectl delete pod hello-world-rest-api-67c79fd44f-8bhdt
	 
	gcloud container clusters get-credentials in28minutes-cluster --zone us-central1-c --project solid-course-258105
	docker login
	docker push in28min/mmv2-currency-exchange-service:0.0.11-SNAPSHOT
	docker push in28min/mmv2-currency-conversion-service:0.0.11-SNAPSHOT
	 
	kubectl create deployment currency-exchange --image=in28min/mmv2-currency-exchange-service:0.0.11-SNAPSHOT
	kubectl expose deployment currency-exchange --type=LoadBalancer --port=8000
	kubectl get svc
	kubectl get services
	kubectl get pods
	kubectl get po
	kubectl get replicaset
	kubectl get rs
	kubectl get all
	 
	kubectl create deployment currency-conversion --image=in28min/mmv2-currency-conversion-service:0.0.11-SNAPSHOT
	kubectl expose deployment currency-conversion --type=LoadBalancer --port=8100
	 
	kubectl get svc --watch
	 
	kubectl get deployments
	 
	kubectl get deployment currency-exchange -o yaml >> deployment.yaml 
	kubectl get service currency-exchange -o yaml >> service.yaml 
	 
	kubectl diff -f deployment.yaml
	kubectl apply -f deployment.yaml
	 
	kubectl delete all -l app=currency-exchange
	kubectl delete all -l app=currency-conversion
	 
	kubectl rollout history deployment currency-conversion
	kubectl rollout history deployment currency-exchange
	kubectl rollout undo deployment currency-exchange --to-revision=1
	 
	kubectl logs currency-exchange-9fc6f979b-2gmn8
	kubectl logs -f currency-exchange-9fc6f979b-2gmn8 
	 
	kubectl autoscale deployment currency-exchange --min=1 --max=3 --cpu-percent=5 
	kubectl get hpa
	 
	kubectl top pod
	kubectl top nodes
	kubectl get hpa
	kubectl delete hpa currency-exchange
	 
	kubectl create configmap currency-conversion --from-literal=CURRENCY_EXCHANGE_URI=http://currency-exchange
	kubectl get configmap
	 
	kubectl get configmap currency-conversion -o yaml >> configmap.yaml
	 
	watch -n 0.1 curl http://34.66.241.150:8100/currency-conversion-feign/from/USD/to/INR/quantity/10
	 
	docker push in28min/mmv2-currency-conversion-service:0.0.12-SNAPSHOT
	docker push in28min/mmv2-currency-exchange-service:0.0.12-SNAPSHOT

	```

create-deployment---------- creates pods, replica sets and deployments
expose--------------- creates services

-	Pods:
	a smallest deployment unit in K8S
	each pod has a unique ip address
	```bash
	kubectl get pods -o wide
	```

	to understand pods
	```bash
	kubectl explain pods
	```

	to see pods in depth
	```bash
	kubectl describe pod hello-world-rest-api-687d9c7bc7-9txng
	```

- Replicaset:
	ensures a specific number of pods are coming at all times

	scale deployment
	```bash
	kubectl scale deployment hello-world-rest-api --replicas=3
	```

- Deployment:
	If we want to add new version, we want zero down-time
	```bash
	kubectl set image deployment hello-world-rest-api hello-world-rest-api=DUMMY_IMAGE:TEST
	```
	hello-world-rest-api: deployment name
	hello-world-rest-api: container
	DUMMY_IMAGE:TEST: wrong image
