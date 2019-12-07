pipeline {
    agent any

    options {
        disableConcurrentBuilds()
    }

    triggers {
        pollSCM('* * * * *')
    }

    stages {
        stage("Allow gradlew access") {
            steps {
                sh "chmod +x gradlew"
            }
        }

        stage("Compile") {
            steps {
                sh "./gradlew compileJava"
            }
        }

        stage("Unit Tests") {
            steps {
                sh "./gradlew test"
            }
        }

        stage("Package") {
            steps {
                sh "./gradlew clean build"
            }
        }
        stage("Docker build") {
            steps {
                sh "docker build -t ozihler/sandbox:1 ."
            }
        }

        stage("Docker push") {
            environment {
                DOCKER_HUB_CREDENTIALS = credentials('Dockerhub')
            }
            steps {
                sh "docker login --username " + DOCKER_HUB_CREDENTIALS_USR + " --password " + DOCKER_HUB_CREDENTIALS_PSW
                sh "docker push ozihler/sandbox:1"
            }
        }

        stage("Deploy to staging") {
            steps {
                sh "docker stop /sandbox || true && sleep 30 && docker run -d --rm -p 8092:8092 --name sandbox ozihler/sandbox:1"
            }
        }
    }
}
