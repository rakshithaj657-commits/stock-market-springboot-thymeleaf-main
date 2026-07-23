pipeline {
    agent any

    parameters {
        choice(
            name: 'ACTION',
            choices: ['DEPLOY', 'REMOVE'],
            description: 'Choose Action'
        )
    }

    tools {
        maven 'maven'
    }

    environment {
        IMAGE_NAME = "rakshithaj657/stock-market-app:latest"
    }

    stages {

        stage('Build JAR') {
            when {
                expression { params.ACTION == 'DEPLOY' }
            }
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            when {
                expression { params.ACTION == 'DEPLOY' }
            }
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Push Docker Image') {
            when {
                expression { params.ACTION == 'DEPLOY' }
            }
            steps {
                script {
                    docker.withRegistry('', 'dockerhub') {
                        sh 'docker push $IMAGE_NAME'
                    }
                }
            }
        }

        stage('Deploy') {
            when {
                expression { params.ACTION == 'DEPLOY' }
            }
            steps {
                sh 'docker compose down || true'
                sh 'docker compose pull'
                sh 'docker compose up -d'
            }
        }

        stage('Remove') {
            when {
                expression { params.ACTION == 'REMOVE' }
            }
            steps {
                sh 'docker compose down'
            }
        }
    }
}