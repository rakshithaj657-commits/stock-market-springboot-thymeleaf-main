pipeline {
    agent any

    parameters {
        choice(
            name: 'ACTION',
            choices: ['DEPLOY', 'REMOVE'],
            description: 'Choose whether to deploy or remove containers'
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
                echo "Building Spring Boot JAR..."
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            when {
                expression { params.ACTION == 'DEPLOY' }
            }
            steps {
                echo "Building Docker Image..."
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Push Image to Docker Hub') {
            when {
                expression { params.ACTION == 'DEPLOY' }
            }
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub') {
                        sh 'docker push $IMAGE_NAME'
                    }
                }
            }
        }

        stage('Deploy Application') {
            when {
                expression { params.ACTION == 'DEPLOY' }
            }
            steps {
                echo "Deploying Application..."
                sh 'docker compose down || true'
                sh 'docker compose pull'
                sh 'docker compose up -d'
            }
        }

        stage('Remove Application') {
            when {
                expression { params.ACTION == 'REMOVE' }
            }
            steps {
                echo "Removing Application..."
                sh 'docker compose down'
                sh 'docker image prune -af'
            }
        }
    }

    post {
        success {
            echo "Pipeline executed successfully."
        }

        failure {
            echo "Pipeline execution failed."
        }

        always {
            echo "Pipeline completed."
        }
    }
}