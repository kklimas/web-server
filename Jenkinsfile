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
                    sh "./gradlew build"
                }
            }

            stage('Unit Tests') {
                steps {
                    sh './gradlew test'
                    junit '**/build/test-results/test/*.xml'
                }
            }

            stage('Docker build and push') {
                steps {
                    script {
                        docker.withRegistry('https://registry.hub.docker.com', 'docker.io') {
                            def image = docker.build("devops/web-server")
                            image.push()
                        }
                    }
                }
            }
    }
}
