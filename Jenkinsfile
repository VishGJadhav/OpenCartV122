pipeline {
    agent any

    tools {
        jdk 'Myajava'          // Must match the JDK name in Jenkins configuration
        maven 'mvn installer'       // Must match the Maven name in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/username/repo.git'
            }
        }

        stage('Build') {
            steps {
                echo "Building project..."
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                echo "Running automation tests..."
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            echo "Publishing test results..."
            junit '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
        }
        success {
            echo "Tests passed!"
        }
        failure {
            echo "Some tests failed."
        }
    }
}
