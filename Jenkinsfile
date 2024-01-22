pipeline {
    agent any
    environment {
        CF_API_ENDPOINT = 'https://api.run.pivotal.io'
        CF_USERNAME = 'mazinyer0717@gmail.com'
        CF_PASSWORD = 'Ricky39255+'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    git url: 'https://github.com/AleJCruz/service1.git', branch: 'main', credentialsId: 'git'
                }
            }
        }

        stage('Build and Test') {
            steps {
                script {
                    sh 'chmod +x ./gradlew'
                    sh './gradlew build'
                }
            }
        }

        stage('Deploy to Cloud Foundry') {
            steps {
                script {
                    // Cloud Foundry login
                    sh "cf login -a ${CF_API_ENDPOINT} -u ${CF_USERNAME} -p ${CF_PASSWORD}"

                    // Use Cloud Foundry CLI to deploy
                    sh 'cf push -f manifest.yml'
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
