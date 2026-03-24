pipeline{
tools{
maven 'mymave'
}
agent any
stages{

stage('checkout'){

steps{

checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[https://github.com/ROGAN07sg/task6-7.git]])
}
post {
                success {
                    emailext(
                        subject: "checkout Success",
                        body: "checkout completed",
                        to: "shekhargupta349@gmail.com"
                    )
                }
            }

}

stage('clean_package'){

steps{
 sh 'mvn clean package'

}
post {
                success {
                    emailext(
                        subject: "cleanpackage Success",
                        body: "cleanpackage completed",
                        to: "shekhargupta349@gmail.com"
                    )
                }
            }

}

stage('build_img'){

steps{
 sh 'docker build -t sekharimg .'

}

}

stage('tag_img'){

steps{
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
stage('docker_push'){

steps{
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
}
