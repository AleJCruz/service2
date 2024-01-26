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

        stage('Debugging - List Files') {
            steps {
                script {
                    // Debugging: List files in the build/libs directory
                    sh "ls -la $WORKSPACE/build/libs"
                }
            }
        }

        stage('Build and Deploy to Azure App Service') {
            steps {
                script {
                    // Configurar las credenciales de Azure
                    sh "az login --service-principal --username 625c5556-e32f-42ef-b464-e763dcf68f30 --password go_8Q~d1ttDvlcj4fcU7Tn~mNvDxeWigrnkEEdaa --tenant e3de6039-942b-4f3e-b8fa-8d8008f8fd74"

                    // Navegar al directorio build/libs
                    dir("$WORKSPACE/build/libs") {
                        // Desplegar en Azure App Service usando Azure CLI
                        sh "az webapp deploy --name $AZURE_APP_NAME --resource-group $AZURE_RESOURCE_GROUP --type jar --src-path ./service1-0.0.1-SNAPSHOT.jar"
                    }
                }
            }
        }


        stage('Debugging - Publish to Artifactory') {
            steps {
                script {
                    // Debugging: Publish to Artifactory
                    sh 'jfrog rt u $WORKSPACE/build/libs/your-app.jar gr-gradle-dev/'
                }
            }
        }

        stage('Publish to Artifactory') {
            steps {
                script {
                    // Actual Publish to Artifactory without debugging
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
