pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                script {
                    git 'https://github.com/AleJCruz/service2.git'
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
                    sleep 30 // Espera a que la aplicación arranque
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
