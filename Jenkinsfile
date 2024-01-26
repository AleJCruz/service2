pipeline {
    agent any
    environment {
        CF_API_ENDPOINT = 'https://api.run.pivotal.io'
        CF_USERNAME = 'mazinyer0717@gmail.com'
        CF_PASSWORD = 'Ricky39255+'
        AZURE_SUBSCRIPTION_ID = '625c5556-e32f-42ef-b464-e763dcf68f30'
        AZURE_RESOURCE_GROUP = 'service2v2_group'
        AZURE_APP_NAME = 'service2v2'
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
                    // Construir la aplicación (ajusta según tu proyecto)
                    sh './gradlew build'

                    // Desplegar a Azure App Service usando Azure CLI
                    sh "az webapp deploy --name $AZURE_APP_NAME --resource-group $AZURE_RESOURCE_GROUP --type jar --src-path ./build/libs"
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
