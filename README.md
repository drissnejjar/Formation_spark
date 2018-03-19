#Formation_spark

The aim of this project is to share the material to start using Spark 1.6 and 2 in scala

###local[*]

    new SparkConf().setMaster("local[2]")
* This is specific to run the job in local mode

* This is specifically used to test the code in small amount of data in local environment

* It Does not provide the advantages of distributed environment
is the number of cpu cores to be allocated to perform the local operation
It helps in debugging the code by applying breakpoints while running from Eclipse or IntelliJ

###yarn-client

    --master yarn --deploy-mode client
Yarn client mode: your driver program is running on the yarn client where you type the command to submit the spark application (may not be a machine in the yarn cluster). 

In this mode, although the driver program is running on the client machine, the tasks are executed on the executors in the node managers of the YARN cluster


###yarn-cluster

    --master yarn --deploy-mode cluster
This is the most advisable pattern for executing/submitting your spark jobs in production
Yarn cluster mode: Your driver program is running on the cluster master machine where you type the command to submit the spark application

#HDP pre-installation configuration

The aime of this part is to prepare a centOs cluster for HDP installation. This is based on the configuration document given by HortonWorks

##Enable HTTPD

These are the commands to enable httpd

    sudo yum install httpd –y
    firewall-cmd --permanent --add-port=443/tcp
    firewall-cmd --permanent --add-port=80/tcp
    firewall-cmd --reload
    systemctl start httpd
    systemctl enable httpd
    reboot
    

##Password-less ssh

