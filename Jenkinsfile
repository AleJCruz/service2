pipeline {
    agent any

    environment {
        GITHUB_TOKEN = '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'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    git url: 'https://github.com/AleJCruz/service2.git', credentials: [
                        usernamePassword(credentialsId: '', username: 'token', password: env.GITHUB_TOKEN)
                    ]
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
                    sh 'curl -H "Authorization: Bearer $GITHUB_TOKEN" http://localhost:8080/actuator/health'
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
