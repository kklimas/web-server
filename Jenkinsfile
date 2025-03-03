pipeline {
    agent any

    tools {
        dockerTools 'Default'
    }

    stages {
            stage('Checkout') {
                git branch: '*/${BRANCH}', url: 'https://github.com/kklimas/web-server.git'
            }

            stage('Build') {
                echo "Building from branch: ${branch}"
                sh "./gradlew build"
            }

            stage('Unit Tests') {
                echo "Running tests..."
                sh './gradlew test'
                echo "Archiving test results..."
                junit '**/build/test-results/test/*.xml'
            }

            stage('Docker build') {
                echo "Building docker image..."
                script {
                    docker.build("devops/web-server")
                }
            }
    }
}
