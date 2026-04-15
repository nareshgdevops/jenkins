def call() {
    pipeline {
        agent any

        stages {
            stage('Fetch Secrets') {
                steps {
                    script {
                        fetchSecrets()
                    }
                }
            }

            stage('Build App Dependencies') {
                steps {
                    script {
                        buildAppDependencies(env.APP_TYPE)
                    }
                }
            }
            stage('Sonar Qube Scan') {
                when {
                    expression { env.APP_TYPE != 'python' }
                }
                steps {
                    sh 'echo /home/github/sonar-scanner-7.3.0.5189-linux-x64/bin/sonar-scanner -Dsonar.host.url=http://sonarqube.nareshdevops1218.online:9000 -Dsonar.token=$SONAR_TOKEN -Dsonar.projectKey=roboshop-$APP_NAME'
                }
            }

            stage('CheckMarx SAST Scan') {
                steps {
                    sh 'echo cx scan create -s . --project-name roboshop-$APP_NAME --scan-types sast'
                }
            }

            stage('Docker Build') {
                steps {
                    script {
                        dockerBuild()
                    }
                }
            }

            stage('Docker Push') {
                steps {
                    script {
                        dockerPush()
                    }
                }
            }
        }
    }
}