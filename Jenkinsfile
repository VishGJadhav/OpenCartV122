pipeline {
    agent any

    triggers {
        // This enables automatic builds when GitHub webhook triggers Jenkins
        githubPush()
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Checking out code from main branch..."
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "Running Maven build..."
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo "Running Tests..."
                bat 'mvn test'
            }
        }

        stage('Post-Build') {
            steps {
                echo "Build completed successfully!"
            }
        }
    }

    post {
        failure {
            echo "Build failed! Check console output for details."
        }
    }
}
