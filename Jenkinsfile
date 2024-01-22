pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                script {
                    git url: 'https://github.com/AleJCruz/service2.git', branch: 'main', credentialsId: 'git'
                }
            }
        }

        stage('Build and Test') {
            steps {
                script {
                    sh './gradlew build'
                }
            }
        }

        stage('Endpoint Health Check') {
            steps {
                script {
                    sh './gradlew bootRun &'
                    sleep 30
                    sh 'curl http://localhost:8080/actuator/health'
                }
            }
        }
    }

    post {
        success {
            echo 'Build successful! Deploy or do further actions here.'
        }

        failure {
            echo 'Build failed. Take necessary actions.'
        }
    }
}
