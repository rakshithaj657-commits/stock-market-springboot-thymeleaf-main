pipeline {

    agent any

    stages {

        stage('Clone') {
            steps {
                git branch: 'main',
                url: 'https://github.com/rakshithaj657-commits/stock-market-springboot-thymeleaf-main.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t stock-test .'
            }
        }

        stage('Docker Run') {
            steps {
                sh '''
                docker stop stock-container || true
                docker rm stock-container || true
                docker run -d \
                --name stock-container \
                -p 8080:8080 \
                stock-test
                '''
            }
        }
    }
}