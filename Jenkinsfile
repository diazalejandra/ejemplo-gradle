pipeline {
    agent any
	
	tools {
		gradle 'gradle'
		maven 'maven'
	}
    stages {
        stage('build & test') {
            steps {
                echo 'Building...'
                sh "./gradlew build"          
            }
        }

        stage('sonar') {
           steps{
              withSonarQubeEnv(credentialsId:'newtoken',installationName:'SonarServer') { 
					script {
                        if(isUnix()) {
                            echo 'Unix OS'
                                sh './gradlew sonarqube \
                                     -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build'
                        } else {
                            echo 'Windows OS'
                                bat 'gradlew sonarqube \
                                    -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build'
                        }
					}
             }
          }
        }

        stage('run') {
            steps {
                echo 'Running...'
                sh "./gradlew bootrun &"          
            }
        }

		stage('test') {
            steps {
                echo 'Testing...'
                sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'"        
            }
        }
    }    
}