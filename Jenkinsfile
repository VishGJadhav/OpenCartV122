pipeline {
    agent any

    environment {
        BROWSER = 'chrome'
    }

    triggers {
        // Trigger pipeline on GitHub push to main branch
        githubPush()
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo "Checking out code..."
                git branch: 'main',
                    url: 'https://github.com/VishGJadhav/OpenCartV122.git',
                    credentialsId: 'github-token'
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
            junit '**/target/surefire-reports/*.xml'
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
