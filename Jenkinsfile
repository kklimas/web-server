pipeline {
    agent any

    environment {
        GRADLE_HOME = tool 'Gradle' // Ensure Gradle is configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/kklimas/web-server' // Replace with your repo
            }
        }

        stage('Build') {
            steps {
                sh "${GRADLE_HOME}/bin/gradle clean build"
            }
        }

        stage('Test') {
            steps {
                sh "${GRADLE_HOME}/bin/gradle test"
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
