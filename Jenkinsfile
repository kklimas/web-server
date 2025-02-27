pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/kklimas/web-server' // Replace with your repo
            }
        }

        stage('Build') {
            steps {
                sh "./gradlew clean build"
            }
        }

        stage('Test') {
            steps {
                sh "./gradlew test"
            }
        }
    }

    post {
        success {
            echo 'Build and tests passed!'
        }
        failure {
            echo 'Build or tests failed!'
        }
    }
}
