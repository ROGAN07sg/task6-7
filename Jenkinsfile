pipeline {
    agent any
    
    tools {
        maven 'mymave'
    }
    
    stages {
        stage('checkout') {
            steps {
                checkout scmGit(
                    branches: [[name: '*/main']],
                    extensions: [],
                    userRemoteConfigs: [[url: 'https://github.com/ROGAN07sg/task6-7.git']]
                )
            }
        }

        stage('clean_package') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('build_img') {
            steps {
                sh 'docker build -t sekharimg .'
            }
        }

        stage('tag_img') {
            steps {
                sh 'docker tag sekharimg rogan07sg/sekharimg:v1'
            }
        }

        stage('Login to Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'docker-cred',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                }
            }
        }

        stage('docker_push') {
            steps {
                sh 'docker push rogan07sg/sekharimg:v1'
            }
        }

        stage('K8s Deployment') {
            steps {
                withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]) {
                    sh '''
                    export KUBECONFIG=$KUBECONFIG
                    kubectl get nodes
                    kubectl apply -f task67.yml
                    '''
                }
            }
        }
    }

    post {
        success {
            emailext(
                subject: "Pipeline Success",
                body: "All stages completed successfully",
                to: "shekhargupta349@gmail.com"
            )
        }
        failure {
            emailext(
                subject: "Pipeline Failed",
                body: "Pipeline failed — check Jenkins logs",
                to: "shekhargupta349@gmail.com"
            )
        }
    }
}
