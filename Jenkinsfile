pipeline {
    agent any

    parameters {
        gitParameter branchFilter: 'origin/(.*)', defaultValue: 'develop', name: 'BRANCH', type: 'PT_BRANCH'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: '${params.BRANCH}', url: 'https://github.com/kklimas/web-server.git'
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
