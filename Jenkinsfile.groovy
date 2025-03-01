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
        docker.build("devops/web-server:${commit}")
    }
}
