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
            mail to: 'recipient@example.com',
                 subject: "Jenkins Pipeline Success: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: """Hello,

The Jenkins pipeline job '${env.JOB_NAME}' (build #${env.BUILD_NUMBER}) completed successfully.

You can check the details at: ${env.BUILD_URL}

Regards,
Jenkins"""
        }

        failure {
            echo "Some tests failed!"
        }
    }
}
