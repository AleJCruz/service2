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
        stage('Build and Deploy to Azure App Service') {
    steps {
        script {
            withCredentials([azureServicePrincipal('azure')]) {
                sh './gradlew build'
                azureWebAppPublish appName: 'YourAzureAppName', resourceGroup: 'YourResourceGroup', filePath: '**/build/libs/*.jar'
            }
        }
    }
}



       stage('Publish to Artifactory') {
    steps {
        script {
            // Use JFrog CLI to publish artifacts
            sh 'jfrog rt u build/libs/*.jar gr-gradle-dev/'
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
