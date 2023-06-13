pipeline {
    agent any
      environment {
            SSH_KEY = credentials('ssh-key') // Jenkins credential ID for the SSH private key
            SSH_USER = 'ec2-user'
            EC2_HOST = 'ec2-44-212-156-197.compute-1.amazonaws.com'
            }
    tools{
        maven 'maven-3.9'
        }

    stages {
      stage('test') {
                steps {
                    script{
                        echo 'testing the application'
                        sh 'mvn test'
                    }
                }
            }
        stage('build') {
            steps {
                script{
                    echo 'bulding the application'
                    sh 'mvn clean package spring-boot:repackage'
                }
            }
        }

        stage('build image') {
            steps {
                script{
                    echo 'building images  the application'
                    withCredentials([usernamePassword(credentialsId:'docker-hub-repo', passwordVariable:'PASS',usernameVariable:'USER')]){
                        sh "echo $USER"
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh 'docker build -t  mariem2/apigw ./apigw/'
                        sh "echo $PASS | docker login -u $USER --password-stdin "
                        sh 'docker push mariem2/apigw'
                        sh 'docker build -t mariem2/eurekaserver ./eurekaserver/'
                        sh 'docker push mariem2/eurekaserver'
                        sh 'docker build -t mariem2/salaire ./users/'
                        sh 'docker push mariem2/users'
                        sh 'docker build -t mariem2/salaire ./salaires/'
                        sh 'docker push mariem2/salaire'
                        sh 'docker build -t mariem2/formation ./eurekaserver/'
                        sh 'docker push mariem2/formation'
                        sh 'docker build -t mariem2/conge ./eurekaserver/'
                        sh 'docker push mariem2/conge'
                    }
                }
            }
        }
        stage('Connect to EC2') {
            steps {
                sshagent(['ssh-key']) {
                sh "ssh -o StrictHostKeyChecking=no ${SSH_USER}@${EC2_HOST}"
                }
            }
        }

         stage('Pull Docker image') {
            steps {
                sshagent(['ssh-key']) {
                sh "ssh ${SSH_USER}@${EC2_HOST} docker container rm -f apigw"
                sh "ssh ${SSH_USER}@${EC2_HOST} docker container rm -f eurekaserver"
                sh "ssh ${SSH_USER}@${EC2_HOST} docker container rm -f users"
                sh "ssh ${SSH_USER}@${EC2_HOST} docker container rm -f salaire"
                sh "ssh ${SSH_USER}@${EC2_HOST} docker container rm -f conge"
                sh "ssh ${SSH_USER}@${EC2_HOST} docker container rm -f formation"
                sh "ssh ${SSH_USER}@${EC2_HOST} docker rmi \$(docker images -a -q) >/dev/null 2>&1 || true"
                sh "ssh ${SSH_USER}@${EC2_HOST} docker pull  mariem2/users:latest"
                sh "ssh ${SSH_USER}@${EC2_HOST} docker pull  mariem2/apigw:latest"
                sh "ssh ${SSH_USER}@${EC2_HOST} docker pull mariem2/eurekaserver:latest"
                sh "ssh ${SSH_USER}@${EC2_HOST} docker pull mariem2/salaire:latest"
                sh "ssh ${SSH_USER}@${EC2_HOST} docker pull mariem2/conge:latest"
                sh "ssh ${SSH_USER}@${EC2_HOST} docker pull mariem2/formation:latest"
                }
            }
         }

         stage('Run Docker container') {
            steps {
               sshagent(['ssh-key']) {
               sh "ssh ${SSH_USER}@${EC2_HOST} docker network create my-network >/dev/null 2>&1 || true"
               sh "ssh ${SSH_USER}@${EC2_HOST} docker run --rm --name apigw  --network my-network --hostname apigw -d  -p 9977:9977   mariem2/apigw:latest"
               sh "ssh ${SSH_USER}@${EC2_HOST} docker run --rm  --name users  --network my-network --hostname users  -d -p 8080:8080   mariem2/users:latest"
               sh "ssh ${SSH_USER}@${EC2_HOST} docker run --rm  --name salaire  --network my-network --hostname salaire  -d -p 8081:8081   mariem2/salaire:latest"
               sh "ssh ${SSH_USER}@${EC2_HOST} docker run  --rm --name  eurekaserver  --network my-network  --hostname eurekaserver  -d -p 7777:7777   mariem2/eurekaserver:latest"
               sh "ssh ${SSH_USER}@${EC2_HOST} docker run  --rm --name  formation  --network my-network  --hostname formation  -d -p 8077:8077   mariem2/formation:latest"
               sh "ssh ${SSH_USER}@${EC2_HOST} docker run  --rm --name  conge  --network my-network  --hostname conge  -d -p 8099:8099   mariem2/conge:latest"
               }
            }
         }
    }
         post {
            always {
                // Send an email to multiple recipients
                emailext subject: 'Build Notification',
                         body: 'The build has completed.',
                         to: 'mariemjaziri55@gmail.com'
            }
         }
}
