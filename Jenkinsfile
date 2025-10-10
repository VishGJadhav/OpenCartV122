pipeline {
    agent any

    environment {
        // Set browser for Selenium tests (optional)
        BROWSER = 'chrome'
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo "Checking out code from GitHub..."
                git branch: 'main',
                    url: 'https://github.com/VishGJadhav/OpenCartV122.git'
                    credentialsId: 'github-token'
            }
        }

        stage('Build Project') {
            steps {
                echo "Building project with Maven..."
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                echo "Running Selenium/Java tests..."
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            echo "Publishing test results..."
            // Publish JUnit XML reports
            junit '**/target/surefire-reports/*.xml'
            // Archive any build artifacts if needed
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
        }

        success {
            echo "All tests passed!"
        }

        failure {
            echo "Some tests failed!"
        }
    }
}
