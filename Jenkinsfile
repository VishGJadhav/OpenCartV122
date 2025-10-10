pipeline {
    agent any

    environment {
        // Set browser for Selenium tests (optional)
        BROWSER = 'chrome'
    }

    stages {

        stage('Checkout Code') {
           steps {
                echo "Git checkout step must be inside 'steps'"
                git branch: 'main',
                    url: 'https://github.com/VishGJadhav/OpenCartV122.git',
                    credentialsId: 'github-token' // Must match your Jenkins credential ID
            }
        }

        stage('Build Project') {
            steps {
                echo "Building project with Maven..."
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                echo "Running Selenium/Java tests..."
                bat 'mvn test'
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
