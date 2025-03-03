pipeline {
    agent any

    tools {
        dockerTool 'Default'
    }

    stages {
            stage('Checkout') {
                steps {
                    git branch: '${BRANCH}', url: 'https://github.com/kklimas/web-server.git'
                }
            }

            stage('Build') {
                steps {
                    echo "Building from branch: ${BRANCH}"
                    sh "./gradlew build"
                }
            }

            stage('Unit Tests') {
                steps {
                    echo "Running tests..."
                    sh './gradlew test'
                    echo "Archiving test results..."
                    junit '**/build/test-results/test/*.xml'
                }
            }

            stage('Docker build') {
                steps {
                    echo "Building docker image..."
                    script {
                        docker.build("devops/web-server")
                    }
                }
            }
    }
}
