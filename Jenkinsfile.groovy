node {
    def commit = env.GIT_COMMIT
    def branch = params.BRANCH ?: 'develop'

    stage('Checkout') {
        echo "Triggered pipeline for branch ${branch} and commit ${commit}"
        checkout([
                $class: 'GitSCM',
                branches: [[name: "*/${branch}"]],
                userRemoteConfigs: [[url: 'https://github.com/kklimas/web-server.git']]
        ])
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
    }
}
//
//pipeline {
//    agent any
//
//    parameters {
//        gitParameter branchFilter: 'origin/(.*)', defaultValue: 'develop', name: 'BRANCH', type: 'PT_BRANCH'
//    }
//
//    stages {
//        stage('Checkout') {
//            steps {
//                git branch: "${params.BRANCH}", url: 'https://github.com/kklimas/web-server.git'
//                echo "Git commit: ${env.GIT_COMMIT}"
//            }
//        }
//
//        stage('Build') {
//            steps {
//                sh "./gradlew clean build"
//            }
//        }
//
//        stage('Unit Test') {
//            steps {
//                sh "./gradlew test"
//            }
//        }
//
//        stage('Build Image') {
//            steps {
//                echo 'Building docker image...'
//                script {
//                    sh "docker build -t devops/web-server:${env.GIT_COMMIT} ."
//
//                }
//            }
//        }
//    }
//}
