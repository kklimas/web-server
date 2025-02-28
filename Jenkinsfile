pipeline {
    agent any

    parameters {
        gitParameter branchFilter: 'origin/(.*)', defaultValue: 'develop', name: 'BRANCH', type: 'PT_BRANCH'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: "${params.BRANCH}", url: 'https://github.com/kklimas/web-server.git'
                echo "Git commit: ${env.GIT_COMMIT}"
            }
        }

        stage('Build') {
            steps {
                sh "./gradlew clean build"
            }
        }

        stage('Unit Test') {
            steps {
                sh "./gradlew test"
            }
        }

        stage('Build Image') {
            steps {
                echo 'Building docker image...'
                sh "docker --version"
            }
        }
    }
}
