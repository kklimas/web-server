pipeline {
    agent any

    stages {
            stage('Checkout') {
                def scmVariables = checkout([
                        $class: 'GitSCM',
                        branches: [[name: "*/${branch}"]],
                        userRemoteConfigs: [[url: 'https://github.com/kklimas/web-server.git']]
                ])
                commit = scmVariables.GIT_COMMIT
                echo "Triggered pipeline for branch ${branch} and commit ${commit}"
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
                    docker.build("devops/web-server:${commit}")
                }
            }
    }
}
